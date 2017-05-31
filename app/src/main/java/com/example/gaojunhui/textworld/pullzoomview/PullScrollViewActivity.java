package com.example.gaojunhui.textworld.pullzoomview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.pullzoomview.weight.PullZoomView;
import com.example.gaojunhui.textworld.pullzoomview.weight.PullZoomView.OnScrollListener;

import static com.example.gaojunhui.textworld.R.id.toolBar;

public class PullScrollViewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imageView;
    private int toTop;//MyScrollView距离屏幕顶部的距离
    private int hasScroll;//MyScrollView已滑动的距离
    private int toolBarHeight;//toolBar的高度
    private MyScrollView myScrollView;
    private int[] screenLocation = new int[2];
    private PullZoomView pzv;
    private ViewGroup.LayoutParams layoutParams;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_scrollview);
        pzv = (PullZoomView) findViewById(R.id.pzv);
        toolbar = (Toolbar) findViewById(toolBar);
        imageView = (ImageView) findViewById(R.id.iv_back);
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        layoutParams=  myScrollView.getLayoutParams();
        getToScreenHeight();
        setSupportActionBar(toolbar);
        statusBar();
        toolBarHeight = toolbar.getHeight();
        Intent intent = getIntent();
        float sensitive = intent.getFloatExtra("sensitive", 1.5f);//放大的敏感系数
        int zoomTime = intent.getIntExtra("zoomTime", 500);//头部的缩放时间
        boolean isParallax = intent.getBooleanExtra("isParallax", true);//是否有视差视图
        boolean isZoomEnable = intent.getBooleanExtra("isZoomEnable", true);//是否允许头部放大
        PullZoomView pzv = (PullZoomView) findViewById(R.id.pzv);
        pzv.setIsParallax(isParallax);
        pzv.setIsZoomEnable(isZoomEnable);
        pzv.setSensitive(sensitive);
        pzv.setZoomTime(zoomTime);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PullScrollViewActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        pzv.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                Log.i("-----", "onContentScroll: " + t);
                if (toTop-toolBarHeight<t) {
                    Log.i("----", "onScroll: "+(toTop-toolBarHeight));
                    //layoutParams.topMargin = DensityUtil.dip2px(PullScrollViewActivity.this,60);

                }else{
                    //layoutParams.topMargin = 0;
                }
                //myScrollView.setLayoutParams(layoutParams);
            }

            @Override
            public void onHeaderScroll(int currentY, int maxY) {
                System.out.println("onHeaderScroll   currentY:" + currentY + "  maxY:" + maxY);
            }

            @Override
            public void onContentScroll(int l, int t, int oldl, int oldt) {

            }
        });
        pzv.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
            @Override
            public void onPullZoom(int originHeight, int currentHeight) {
                System.out.println("onPullZoom  originHeight:" + originHeight + "  currentHeight:" + currentHeight);
            }

            @Override
            public void onZoomFinish() {
                System.out.println("onZoomFinish");
            }
        });
    }

    private void statusBar() {
        if (Build.VERSION.SDK_INT >= 19) {//4.4
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void getToScreenHeight() {
        myScrollView.post(new Runnable() {
            @Override
            public void run() {
                myScrollView.getLocationOnScreen(screenLocation);
                toTop = screenLocation[1];
                Log.e("-----", "toTop: " + toTop);
            }
        });
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                toolBarHeight = toolbar.getMeasuredHeight();
                Log.e("----", "toolBarHeight: " + toolBarHeight);
            }
        });
    }
}
