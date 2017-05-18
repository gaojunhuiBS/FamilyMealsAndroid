package com.example.gaojunhui.textworld;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.gaojunhui.textworld.rest.RestClient;
import com.example.gaojunhui.textworld.util.LogUtils;
import com.jakewharton.rxbinding.view.RxView;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by gaojunhui on 2017/5/4.
 */

public class PostFileActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    private File file;
    private String path;
    private RequestBody requestBody;
    private Map<String, RequestBody> map = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_file);
        button = (Button) findViewById(R.id.btn_post_file);
        imageView = (ImageView) findViewById(R.id.iv_post_file);
        path = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/pic.jpg";
        LogUtils.logD("-----", path);
        file = new File(path);
        RxView.clicks(button).subscribe(aVoid -> {
            postFile();
        });
    }

    /*上传文件*/
    private void postFile() {
        if (file != null) {
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        }
        //map.clear();
        //map.put(file.getName()+"",requestBody);
        LogUtils.logD("----", file.getName());
        RestClient.instance().fileService().postFile(file.getName(), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(avatarEntity -> Glide.with(PostFileActivity.this).load(avatarEntity.key).into(imageView),
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Toast.makeText(PostFileActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                                LogUtils.logD("-----", throwable.getMessage());
                            }
                        });
    }
}
