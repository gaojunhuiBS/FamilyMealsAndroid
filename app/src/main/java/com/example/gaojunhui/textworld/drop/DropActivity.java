package com.example.gaojunhui.textworld.drop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.gaojunhui.textworld.R;

/**
 * Created by gaojunhui on 2017/5/3.
 */

public class DropActivity extends AppCompatActivity {
    WaterDrop waterDrop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop);
        CoverManager.getInstance().init(this);
       waterDrop= (WaterDrop) findViewById(R.id.water_drop);
        CoverManager.getInstance().setEffectDuration(150);
        CoverManager.getInstance().setMaxDragDistance(1000);
        waterDrop.setOnDragCompeteListener(() -> Toast.makeText(DropActivity.this, "drop", Toast.LENGTH_SHORT).show());
    }
}
