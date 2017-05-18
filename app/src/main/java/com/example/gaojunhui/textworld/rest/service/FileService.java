package com.example.gaojunhui.textworld.rest.service;

import com.example.gaojunhui.textworld.rest.data.AvatarEntity;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by gaojunhui on 2017/5/4.
 * Retrofit2.0文件上传
 */

public interface FileService {
    @Multipart
    @POST("/filebase/upload")
    //Observable<AvatarEntity> postFile(@PartMap Map<String, RequestBody> params);
    Observable<AvatarEntity> postFile(
            @Part("fileName") String fileName,
            @Part("file\"; filename=\"image.png\"") RequestBody requestBody);

}
