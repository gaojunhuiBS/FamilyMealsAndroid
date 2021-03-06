package com.example.gaojunhui.textworld.drop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class WaterDrop extends RelativeLayout {
    private Paint mPaint = new Paint();
    private TextView mTextView;
    private CoverManager.OnDragCompeteListener mOnDragCompeteListener;
    private boolean mHolderEventFlag;

    public WaterDrop(Context context) {
        super(context);
        init();
    }

    public WaterDrop(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setText(String str) {
        mTextView.setText(str);
    }

    public void setTextSize(int size) {
        mTextView.setTextSize(size);
    }

    /**
     * -1 for origin
     *
     * @param resourceId
     */
    public void setEffectResource(int resourceId){
        CoverManager.getInstance().setEffectResource(resourceId);
    }

    private void init() {
        mPaint.setAntiAlias(true);
        if (VERSION.SDK_INT > 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        mTextView = new TextView(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTextView.setTextSize(8);
        mTextView.setTextColor(Color.parseColor("#ffffff"));
        mTextView.setLayoutParams(params);
        addView(mTextView);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#fcae04"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ViewGroup parent = getScrollableParent();
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            mHolderEventFlag = !CoverManager.getInstance().isRunning();
            if (mHolderEventFlag) {
                if (parent != null)
                    parent.requestDisallowInterceptTouchEvent(true);
                CoverManager.getInstance().start(this, event.getRawX(), event.getRawY(), mOnDragCompeteListener);
            }
            break;
        case MotionEvent.ACTION_MOVE:
            if (mHolderEventFlag) {
                CoverManager.getInstance().update(event.getRawX(), event.getRawY());
            }
            break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
            if (mHolderEventFlag) {
                if (parent != null)
                    parent.requestDisallowInterceptTouchEvent(false);
                CoverManager.getInstance().finishDrag(this, event.getRawX(), event.getRawY());
            }
            break;
        }

        return true;
    }

    private ViewGroup getScrollableParent() {
        View target = this;
        while (true) {
            View parent;
            try {
                /**
                 * ViewRootImpl cannot be cast to android.view.View
                 */
                parent = (View) target.getParent();
            } catch (Exception e) {
                return null;
            }
            if (parent == null)
                return null;
            if (parent instanceof ListView || parent instanceof ScrollView) {
                return (ViewGroup) parent;
            }
            target = parent;
        }

    }

    public void setOnDragCompeteListener(CoverManager.OnDragCompeteListener onDragCompeteListener) {
        mOnDragCompeteListener = onDragCompeteListener;
    }
}
