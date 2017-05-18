package com.example.gaojunhui.textworld.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.util.UIUtil;

/**
 * Created by gaojunhui on 2017/4/26.
 */

public class CustomView extends View {
    private Paint mPaint;//画笔
    private Canvas mCanvas;//画布
    private Context mContext;
    private int radiu;//半径
    private Bitmap mBitmap;
    private int x, y;//位图的坐标
    private boolean isClick;

    public CustomView(Context context) {
        super(context);
        init(context);
        initResouce(context);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initResouce(context);
        setOnClickListener(view -> {
            if (isClick) {
                // 如果已经被点击了则点击时设置颜色过滤为空还原本色
                mPaint.setColorFilter(null);
                isClick = false;
            } else {
                // 如果未被点击则点击时设置颜色过滤后为黄色
                mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0X00FFFF00));
                isClick=true;
            }
            invalidate();
        });
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initResouce(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initResouce(context);
    }

    /**
     * 初始化画笔
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        //初始化画笔，并设置抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * STROKE:描边
         * FILL_AND_STROKE:描边并填充
         * FILL:填充
         */
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.parseColor("#aa00aa"));
//        mPaint.setStrokeWidth(10);//px,当为0是，代表一个像素
//        // 生成色彩矩阵
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                -1, 0, 0, 1, 1,
//                0, -1, 0, 1, 1,
//                0, 0, -1, 1, 1,
//                0, 0, 0, 1, 0,
//        });
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//        //去掉绿色
//        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));
        // 设置颜色过滤  (变暗)
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
    }

    /**
     * 获取位图资源
     *
     * @param context
     */
    private void initResouce(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img1);
        x = UIUtil.getScreenWidth(mContext) / 2 - mBitmap.getWidth() / 2;
        y = UIUtil.getScreenHeight(mContext) / 2 - mBitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(UIUtil.getScreenWidth(mContext) / 2, UIUtil.getScreenHeight(mContext) / 2, radiu, mPaint);
        canvas.drawBitmap(mBitmap, x, y, mPaint);
    }

//    @Override
//    public void run() {
//        while (true) {
//            try {
//                if (radiu <= 200) {
//                    radiu += 10;
//                    postInvalidate();//刷新view
//                } else {
//                    radiu = 0;
//                }
//                Thread.sleep(400);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
