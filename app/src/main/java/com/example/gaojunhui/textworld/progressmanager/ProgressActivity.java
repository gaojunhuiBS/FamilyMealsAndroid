package com.example.gaojunhui.textworld.progressmanager;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.gaojunhui.textworld.BaseActivity;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.progressmanager.weight.CBProgressBar;
import com.example.gaojunhui.textworld.progressmanager.weight.ImageLoadingView;
import com.example.gaojunhui.textworld.rest.RestClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import me.jessyan.progressmanager.ProgressInfo;
import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gaojunhui on 2017/6/12.
 */

public class ProgressActivity extends BaseActivity implements View.OnClickListener {

    public static final String IMAGE_URL =
            "https://raw.githubusercontent.com/JessYanCoding/MVPArmsTemplate/master/art/step.png";
    public static final String DOWNLOAD_URL =
            "https://raw.githubusercontent.com/JessYanCoding/MVPArmsTemplate/master/art/MVPArms.gif";
    public static final String UPLOAD_URL = "http://upload.qiniu.com/";
    private Button startGlide;
    private Button startUpData;
    private ImageView imageView;
    private TextView progressTv;
    private ProgressBar progressBar;
    private Button start;
    private ProgressInfo mLastDownloadingInfo;
    private ProgressInfo mLastUploadingingInfo;
    private OkHttpClient mOkHttpClient;
    private ImageLoadingView loadView;
    private int lastProgress;
    private CBProgressBar cbProgressBar;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_progress;
    }

    @Override
    protected void initView() {
        //mOkHttpClient = ((MyApplication) getApplicationContext()).getOkHttpClient();
        mOkHttpClient = RestClient.instance().getOkHttpClient();
        imageView = (ImageView) findViewById(R.id.progress_iv);
        progressTv = (TextView) findViewById(R.id.progress_tv);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        start = (Button) findViewById(R.id.start);
        startGlide = (Button) findViewById(R.id.start_glide);
        startUpData= (Button) findViewById(R.id.start_up);
        loadView = (ImageLoadingView) findViewById(R.id.progress_load_view);
        cbProgressBar = (CBProgressBar) findViewById(R.id.my_progress);
        cbProgressBar.setMax(100);
        //cbProgressBar.setStrokeWidth(UIUtil.dip2px(this,40));
        cbProgressBar.setProgressBarBgColor(Color.parseColor("#aa00aa"));
        cbProgressBar.setProgressColor(Color.parseColor("#000000"));
        //cbProgressBar.setRectRound(UIUtil.dip2px(this,200));
        start.setOnClickListener(this);
        startGlide.setOnClickListener(this);
        startUpData.setOnClickListener(this);
        initListener();
    }

    private void initListener() {
        //Glide加载监听
        ProgressManager.getInstance().addResponseListener(IMAGE_URL, getGlideListener());
        //Okhttp/Retofit 下载监听
        ProgressManager.getInstance().addResponseListener(DOWNLOAD_URL, getDownloadListener());
        //Okhttp/Retofit 上传监听
        ProgressManager.getInstance().addRequestLisenter(UPLOAD_URL, getUploadListener());
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                downloadStart();
                break;
            case R.id.start_glide:
                loadGlide();
                break;
            case R.id.start_up:
                uploadStart();
                break;
        }
    }

    private ProgressListener getGlideListener() {
        return new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                Log.d("----", progressInfo.getId() + "glide");
                int progress = (int) ((100 * (progressInfo.getCurrentbytes())) / progressInfo.getContentLength());
                //progressBar.setProgress(progress);
                //loadView.setProgress(progress);
                cbProgressBar.setProgress(progress);
                progressTv.setText(progress + "%");
                Log.d("----", progressInfo.getId() + "--glide--" + progress + " %");
                if (progress == 100) {
                    cbProgressBar.setVisibility(View.GONE);
                    Log.e("----", progressInfo.getId() + "finish");
                } else {
                    cbProgressBar.setVisibility(View.VISIBLE);
                }
            }
        };
    }

    @NonNull
    private ProgressListener getDownloadListener() {
        return new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                // 如果你不屏蔽用户重复点击上传或下载按钮,就可能存在同一个 Url 地址,上一次的上传或下载操作都还没结束,
                // 又开始了新的上传或下载操作,那现在就需要用到 id(请求开始时的时间) 来区分正在执行的进度信息
                // 这里我就取最新的下载操作用来展示,顺便展示下 id 的用法
                Log.d("----", progressInfo.getId() + "--download--");
                if (mLastDownloadingInfo == null) {
                    mLastDownloadingInfo = progressInfo;
                }

                //因为是以请求开始时的时间作为 Id ,所以值越大,说明该请求越新
                if (progressInfo.getId() < mLastDownloadingInfo.getId()) {
                    return;
                } else if (progressInfo.getId() > mLastDownloadingInfo.getId()) {
                    mLastDownloadingInfo = progressInfo;
                }

                int progress = (int) ((100 * mLastDownloadingInfo.getCurrentbytes())
                        / mLastDownloadingInfo.getContentLength());

                //progressBar.setProgress(progress);
                //loadView.setProgress(lastProgress);
                cbProgressBar.setProgress(progress);
                progressTv.setText(progress + "%");
                Log.d("----", mLastDownloadingInfo.getId() + "--download--" + progress + " %");
                if (progress == 100) {
                    cbProgressBar.setVisibility(View.GONE);
                    //loadView.loadCompleted();
                } else {
                    cbProgressBar.setVisibility(View.VISIBLE);
                }
            }
        };
    }
    @NonNull
    private ProgressListener getUploadListener() {
        return new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                // 如果你不屏蔽用户重复点击上传或下载按钮,就可能存在同一个 Url 地址,上一次的上传或下载操作都还没结束,
                // 又开始了新的上传或下载操作,那现在就需要用到 id(请求开始时的时间) 来区分正在执行的进度信息
                // 这里我就取最新的上传操作用来展示,顺便展示下 id 的用法

                if (mLastUploadingingInfo == null) {
                    mLastUploadingingInfo = progressInfo;
                }

                //因为是以请求开始时的时间作为 Id ,所以值越大,说明该请求越新
                if (progressInfo.getId() < mLastUploadingingInfo.getId()) {
                    return;
                } else if (progressInfo.getId() > mLastUploadingingInfo.getId()) {
                    mLastUploadingingInfo = progressInfo;
                }
                int progress = (int) ((100 * mLastUploadingingInfo.getCurrentbytes()) / mLastUploadingingInfo.getContentLength());
                cbProgressBar.setProgress(progress);
                progressTv.setText(progress + "%");
                if (progress == 100) {
                    cbProgressBar.setVisibility(View.GONE);
                    //loadView.loadCompleted();
                } else {
                    cbProgressBar.setVisibility(View.VISIBLE);
                }
                Log.d("----", mLastUploadingingInfo.getId() + "--upload--" + progress + " %  " +mLastUploadingingInfo.getCurrentbytes() +"  "+mLastUploadingingInfo.getContentLength());
            }
        };
    }

    /**
     * 点击开始上传资源,为了演示,就不做重复点击的处理,即允许用户在还有进度没完成的情况下,使用同一个 url 开始新的上传
     */
    private void uploadStart() {
        File file = new File(getCacheDir(), "a.java");
        try {
            //读取Assets里面的数据,作为上传源数据
            writeToFile(getAssets().open("a.java"), file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        final Request request = new Request.Builder()
                .url(UPLOAD_URL)
                .post(RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * 点击开始下载资源,为了演示,就不做重复点击的处理,即允许用户在还有进度没完成的情况下,使用同一个 url 开始新的下载
     */
    private void downloadStart() {
        final Request request = new Request.Builder()
                .url(DOWNLOAD_URL)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 点击开始 Glide 加载图片,为了演示,就不做重复点击的处理,
     * 但是 Glide 自己对重复加载做了处理
     * 即重复加载同一个 Url 时,停止还在请求当中的进度,再开启新的加载
     */
    private void loadGlide() {
        Glide.with(this).
                load(IMAGE_URL).
                listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                            boolean isFirstResource) {
                        showToast("加载图片失败");
                        if (cbProgressBar != null && cbProgressBar.isShown()) {
                            cbProgressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                            boolean isFromMemoryCache, boolean isFirstResource) {
                        showToast("加载图片成功");
                        if (cbProgressBar != null && cbProgressBar.isShown()) {
                            cbProgressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }
                }).
                centerCrop().
                placeholder(R.color.colorAccent).
                diskCacheStrategy(DiskCacheStrategy.NONE).
                centerCrop().
                into(imageView);
    }
    public static File writeToFile(InputStream in, File file) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        byte[] buf = new byte[1024];
        int num = 0;
        while ((num = in.read(buf)) != -1) {
            out.write(buf, 0, buf.length);
        }
        out.close();
        return file;
    }
}
