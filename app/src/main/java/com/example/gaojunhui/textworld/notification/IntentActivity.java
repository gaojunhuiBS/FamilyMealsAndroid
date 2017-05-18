package com.example.gaojunhui.textworld.notification;

import android.widget.TextView;
import com.example.gaojunhui.textworld.BaseActivity;
import com.example.gaojunhui.textworld.R;

/**
 * Created by gaojunhui on 2017/5/12.
 */

public class IntentActivity extends BaseActivity {
    private TextView textView;
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_intent;
    }

    @Override
    protected void initView() {
        textView= (TextView) findViewById(R.id.tv_intent);
    }
}
