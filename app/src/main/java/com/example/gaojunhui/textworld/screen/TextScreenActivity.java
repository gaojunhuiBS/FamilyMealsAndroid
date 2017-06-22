package com.example.gaojunhui.textworld.screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.example.gaojunhui.textworld.R;
import com.jakewharton.rxbinding.view.RxView;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaojunhui on 2017/6/20.
 */

public class TextScreenActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    private TextView textView;
    private String text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        ButterKnife.bind(this);
        button = (Button) findViewById(R.id.screen_click);
        imageView = (ImageView) findViewById(R.id.screen_iv);
        textView= (TextView) findViewById(R.id.screen_tv);
        text = "xuanzhuan";
        RxView.clicks(button)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    textView.setText(text);
                    imageView.setImageResource(R.drawable.img1);
                    for (int i = 0; i <10 ; i++) {
                        Log.i("----", "onCreate: "+i);
                    }
                });

    }
}
