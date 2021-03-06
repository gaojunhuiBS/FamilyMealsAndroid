package com.example.gaojunhui.textworld.headerview.weight;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.example.gaojunhui.textworld.R;

/**
 * Created by xiaolf1 on 2015/1/20 0020.
 */
public class TouchPanelLayout extends RelativeLayout {

    private View mHeaderView;
    private View mContentView;
    private float mMaxTransY = 0;
    private float mMinTransY = 0;
    private float mPreDistanceY = 0;
    private float mSpringbackDistance = 0;

    private GestureDetector mGestureDetector = null;

    private IConfigCurrentPagerScroll configCurrentPagerScroll;
    private OnViewUpdateListener onViewUpdateListener;

    public TouchPanelLayout(Context context) {
        super(context);
        init();
    }

    public TouchPanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchPanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TouchPanelLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * get min transY when finish layout
     */
    private void init() {
        mGestureDetector = new GestureDetector(getContext(), mGestureListener);
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                TouchPanelLayout.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mMinTransY = configCurrentPagerScroll == null ? 0 : configCurrentPagerScroll.getActionBarHeight();
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mHeaderView == null || mContentView == null) {
            mHeaderView = findViewById(R.id.fhvp_header);

            mContentView = findViewById(R.id.fhvp_content);
            mMaxTransY = mContentView.getTranslationY();

            //if springback distance is not be set, give half of max transY
            if (mSpringbackDistance == 0) {
                mSpringbackDistance = mMaxTransY / 2;
            }
        }
    }

    public void setSpringbackDistance(float springbackDistance) {
        this.mSpringbackDistance = springbackDistance;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        jumpBack(ev);
        if (mGestureDetector.onTouchEvent(ev)) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jumpBack(event);

        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * jump back to the max transY when up
     */
    private void jumpBack(MotionEvent event) {

        if ((event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL) && mContentView.getTranslationY() > mMaxTransY) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mContentView, View.TRANSLATION_Y, mContentView.getTranslationY(), mMaxTransY);
            ValueAnimator animator1 = ValueAnimator.ofInt(mHeaderView.getMeasuredHeight(), (int) mMaxTransY);
            animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    LayoutParams lp = (LayoutParams) mHeaderView.getLayoutParams();
                    lp.height = (int) animation.getAnimatedValue();
                    mHeaderView.setLayoutParams(lp);
                }
            });

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.playTogether(animator, animator1);
            animatorSet.start();
        }
    }

    private boolean onScrollChange(float distance) {
        float currentTransY = mContentView.getTranslationY();
        if (currentTransY == mMinTransY && distance < 0) {
            return false;
        }

        if (distance > 0) {
            if (currentTransY == (mMaxTransY + mSpringbackDistance)) {
                return false;
            }

            if (configCurrentPagerScroll != null) {
                IPagerScroll pagerScroll = configCurrentPagerScroll.getCurrentPagerScroll();
                if (pagerScroll != null && !pagerScroll.isFirstChildOnTop()) {
                    return false;
                }
            }

        }

        currentTransY += distance;
        if (currentTransY < mMinTransY) {
            currentTransY = mMinTransY;
        } else if (currentTransY > (mMaxTransY + mSpringbackDistance)) {
            currentTransY = mMaxTransY + mSpringbackDistance;
        }
        updateHeaderAndContentState(currentTransY);
        updateAlpha(currentTransY);
        return true;
    }

    private void updateHeaderAndContentState(float contentViewTransY) {
        mContentView.setTranslationY(contentViewTransY);
        LayoutParams lp = (LayoutParams) mHeaderView.getLayoutParams();
        lp.height = (int) contentViewTransY;
        mHeaderView.setLayoutParams(lp);
    }

    private void updateAlpha(float contentViewTransY) {
        float ratio = (mMaxTransY - contentViewTransY) / (mMaxTransY - mMinTransY);
        if (ratio > 0.95) {
            ratio = 1.0f;
        } else if (ratio < 0.05) {
            ratio = 0;
        }
        if (onViewUpdateListener != null) {
            onViewUpdateListener.onAlphaChanged((int) (ratio * 255));
        }
    }

    public interface IConfigCurrentPagerScroll {

        public IPagerScroll getCurrentPagerScroll();

        public float getActionBarHeight();
    }

    public void setOnViewUpdateListener(OnViewUpdateListener onViewUpdateListener) {
        this.onViewUpdateListener = onViewUpdateListener;
    }

    public interface OnViewUpdateListener {

        public void onAlphaChanged(int alpha);
    }

    public void setConfigCurrentPagerScroll(IConfigCurrentPagerScroll configCurrentPagerScroll) {
        this.configCurrentPagerScroll = configCurrentPagerScroll;
    }

    private GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return onScrollChange(-distanceY);
            }
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return handleViewFling(e2, velocityX, velocityY);
        }

    };

    private boolean handleViewFling(MotionEvent e2, float velocityX, float velocityY) {
        long takeTime = SystemClock.uptimeMillis() - e2.getEventTime();
        float seconds = takeTime / 1000.0f;
        float distanceX = seconds * velocityX;
        float distanceY = seconds * velocityY;
        if (Math.abs(distanceY) > Math.abs(distanceX)) {
            float offset = distanceY - mPreDistanceY;
            mPreDistanceY = distanceY;
            return onScrollChange(offset);
        } else {
            mPreDistanceY = distanceY;
            return false;
        }
    }

}
