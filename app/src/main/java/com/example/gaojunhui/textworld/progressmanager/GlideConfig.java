package com.example.gaojunhui.textworld.progressmanager;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.example.gaojunhui.textworld.myapplication.MyApplication;
import com.example.gaojunhui.textworld.rest.RestClient;
import java.io.InputStream;

/**
 * Created by gaojunhui on 2017/6/12.
 * 配置Glide访问网络的方式
 * Glide底层默认使用 HttpConnection 进行网络请求,
 * 这里替换为 Okhttp 后才能使用本框架,进行 Glide 的加载进度监听
 */

public class GlideConfig implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        MyApplication myApplication = (MyApplication) context.getApplicationContext();
        //Glide底层默认使用 HttpConnection 进行网络请求,这里替换为 Okhttp 后才能使用本框架,进行 Glide 的加载进度监听
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(RestClient.instance().getOkHttpClient()));
    }
}
