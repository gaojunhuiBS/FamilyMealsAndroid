package com.example.gaojunhui.textworld.h5fullscreen;

import android.Manifest;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import com.example.gaojunhui.textworld.BaseActivity;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.h5fullscreen.X5Utils.X5WebView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import java.util.List;

/**
 * Created by gaojunhui on 2017/5/22.
 */

public class X5TextActivity extends BaseActivity {
    private X5WebView webView;
    private ProgressBar progressBar;
    //private String url="http://v.pptv.com/show/hly7OaEHd7WiaFX7kVA.html?rcc_id=wap_144";
    private String url = "http://www.baidu.com";

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_x5;
    }

    @Override
    protected void initView() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//避免闪烁
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        initWebView();
        postPermision();
    }

    /**
     * 6.0系统，x5内核需要：READ_PHONE_STATE权限
     * 手动添加权限
     */
    private void postPermision() {
        if (AndPermission.hasPermission(this, android.Manifest.permission.READ_PHONE_STATE
        )) {
            webView.loadUrl(url);
        } else {
            AndPermission.with(this).requestCode(103).permission(
                    Manifest.permission.READ_PHONE_STATE
            ).send();
        }
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            if (requestCode == 103) {

                webView.loadUrl(url);
                
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            if (requestCode == 103) {
                showToast("获取手机状态权限失败，请手动添加。");
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    private void initWebView() {
        webView = (X5WebView) findViewById(R.id.x5WebView);
        webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        webView.setWebViewClient(mClient);
        webView.setWebChromeClient(new WebViewCromeClient());
    }

    //确保在应用内打开，不跳转到外部浏览器
    private WebViewClient mClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {
            webView.loadUrl(s);
            return true;
        }

        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
            if (progressBar != null && progressBar.isShown()) {
                progressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    private class WebViewCromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(i);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
