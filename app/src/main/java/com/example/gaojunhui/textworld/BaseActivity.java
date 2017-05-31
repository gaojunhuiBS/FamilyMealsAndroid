package com.example.gaojunhui.textworld;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;
import butterknife.ButterKnife;

/**
 * Created by gaojunhui on 2017/5/10.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutResID();

    protected abstract void initView();

    protected void initData() {}

    private Toast mToast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        init();
        statusBar();
    }
    protected final void init() {
        initView();
        initData();
    }
    private void statusBar() {
        if (Build.VERSION.SDK_INT >= 19) {//4.4
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void showToast(String toast) {
        if (TextUtils.isEmpty(toast)) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(this, toast, Toast.LENGTH_SHORT);
        }
        mToast.setText(toast);
        mToast.show();
    }
}
