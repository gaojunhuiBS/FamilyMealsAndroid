package com.example.gaojunhui.textworld.toasty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gaojunhui.textworld.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gaojunhui on 2017/4/21.
 */

public class CustomToastActivity extends AppCompatActivity {
    @Bind(R.id.toast_success)
    Button toastSuccess;
    @Bind(R.id.toast_error)
    Button toastError;
    @Bind(R.id.toast_info)
    Button toastInfo;
    @Bind(R.id.toast_warning)
    Button toastWarning;
    @Bind(R.id.toast_normal)
    Button toastNormal;
    @Bind(R.id.toast_normal_icon)
    Button toastNormalIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toast_success, R.id.toast_error, R.id.toast_info, R.id.toast_warning, R.id.toast_normal, R.id.toast_normal_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toast_success:
                Toasty.success(this, "success", Toast.LENGTH_SHORT, true).show();
                break;
            case R.id.toast_error:
                Toasty.error(this, "error", Toast.LENGTH_SHORT, true).show();
                break;
            case R.id.toast_info:
                Toasty.info(this, "info", Toast.LENGTH_SHORT, true).show();
                break;
            case R.id.toast_warning:
                Toasty.warning(this, "warning", Toast.LENGTH_SHORT, true).show();
                break;
            case R.id.toast_normal:
                Toasty.normal(this, "normal").show();
                break;
            case R.id.toast_normal_icon:
//                Toasty.normal(this, "normal_icon", getResources().getDrawable(R.drawable.ic_check_white_48dp)).show();
                Toasty.custom(this,
                        "cusotm",
                        getResources().getDrawable(R.drawable.ic_check_white_48dp),
                        Color.parseColor("#aa00aa"),
                        Color.parseColor("#00aa00"),
                        Toast.LENGTH_SHORT,
                        true,
                        true).show();
                break;
        }
    }
}
