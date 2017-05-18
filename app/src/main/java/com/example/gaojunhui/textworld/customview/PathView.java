package com.example.gaojunhui.textworld.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gaojunhui on 2017/4/26.
 */

public class PathView extends View {
    private Path mPath;
    private Paint mPaint;
    private float vWidth,vHeight;//控件宽高
    private float ctrX,ctrY;//控制点坐标
    private float waveY;//整个wave顶部两端点的Y坐标，该坐标与控制点Y坐标增减幅一致
    private boolean isInc;//判断是向左还是向右移动

    public PathView(Context context) {
        super(context);
        init(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    private void init(Context context){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(0xFFA2D6AE);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(5);

        mPath=new Path();
//        mPath.moveTo(100,100);
//        mPath.quadTo(200,200,300,100);一阶贝塞尔曲线
//        mPath.cubicTo(200,200,300,0,500,100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        vWidth=w;
        vHeight=h;
        //计算控制点Y坐标
        waveY=1/8F*vHeight;
        //计算端点Y坐标
        ctrY=-1/16f*vHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPath(mPath,mPaint);
        /**
         * 设置path起点，设置在控件外部的区域
         */
        mPath.moveTo(-1/4F*vWidth,waveY);
         /**
         * 以二阶曲线的方式通过控制点连接位于控件右边的终点
         * 终点的位置也是在控件外部
         * 我们只需不断让ctrX的大小变化即可实现“浪”的效果
         */
         mPath.quadTo(ctrX,ctrY,vWidth+1/4F,waveY);
        //围绕控件闭合曲线
        mPath.lineTo(vWidth+1/4F*vWidth,vHeight);
        mPath.lineTo(-1/4*vWidth,vHeight);
        mPath.close();

        canvas.drawPath(mPath,mPaint);
          /*
         * 当控制点的x坐标大于或等于终点x坐标时更改标识值
         */
        if (ctrX >= vWidth + 1 / 4F * vWidth) {
            isInc = false;
        }
        /*
         * 当控制点的x坐标小于或等于起点x坐标时更改标识值
         */
        else if (ctrX <= -1 / 4F * vWidth) {
            isInc = true;
        }

        // 根据标识值判断当前的控制点x坐标是该加还是减
        ctrX = isInc ? ctrX + 20 : ctrX - 20;

        /*
         * 让“水”不断减少
         */
        if (ctrY <= vHeight) {
            ctrY += 2;
            waveY += 2;
        }

        mPath.reset();

        // 重绘
        invalidate();

    }
}
