package com.example.gaojunhui.textworld.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gaojunhui.textworld.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gaojunhui on 2017/4/26.
 */

public class CustomViewActivity extends AppCompatActivity {
    private PagerTurnView mPathView;
    private List<Bitmap> mBitmaps=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
//        mPathView= (PagerTurnView) findViewById(R.id.pathView);
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.drawable.img1));
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.drawable.img2));
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.drawable.img3));
        mPathView.setBitmaps(mBitmaps);
//        new Thread(customView).start();
    }
}
