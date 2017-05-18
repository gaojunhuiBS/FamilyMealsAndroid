package com.example.gaojunhui.textworld.rest;



import com.example.gaojunhui.textworld.BuildConfig;
import com.example.gaojunhui.textworld.rest.service.FileService;
import com.example.gaojunhui.textworld.rest.service.NetService;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaojunhui on 2017/4/10.
 */

public class RestClient {

    private static RestClient mInstance;
    private Retrofit mRetrofit;
    private NetService mNetService;
    public static final String BASE_URL = "http://snow-t.9h-sports.com/";
    private static final String UPLOAD_SERVER = "http://common.9h-sports.com:31860/";
    private static final int TIMEOUT_IN_SECOND = 15;
    private static final OkHttpClient.Builder mHttpClientBuilder = new OkHttpClient.Builder();
    private OkHttpClient mOkHttpClient;

    private RestClient() {
        //DEBUG模式下 添加日志拦截器
        if (BuildConfig.DEBUG) {
            Interceptor interceptor = chain -> {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("X-User-Token", "")
                        .addHeader("systemtypeid", "1")
                        .addHeader("equipmentnum", "device_token")
                        .build();
                return chain.proceed(request);
            };
            mHttpClientBuilder.connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .interceptors()
                    .add(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY));
            mOkHttpClient = mHttpClientBuilder.build();

        }
        mRetrofit = getRetrofit(BASE_URL);
    }

    //对外提供获取实例的方法
    public static RestClient instance() {
        if (mInstance == null) {
            synchronized (RestClient.class){
                if(mInstance == null){
                    mInstance = new RestClient();
                }
            }
        }
        return mInstance;
    }

    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    //提供获取其成员变量的方法
    public NetService netService() {
        return getRetrofit(BASE_URL).create(NetService.class);
    }
    //上传文件
    public FileService fileService(){
        return getRetrofit(UPLOAD_SERVER).create(FileService.class);
    }

}
