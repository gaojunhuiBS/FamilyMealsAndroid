package com.example.gaojunhui.textworld.rest.service;


import com.example.gaojunhui.textworld.rest.data.CircleHomeEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gaojunhui on 2017/4/10.
 */

public interface NetService {
    /**
     * 获取圈子的总数据
     *
     * @param method
     * @param page_num
     * @param page_row
     * @return
     */
    @GET("/group/v1/gateway/actives")
    Observable<CircleHomeEntity> getCircleHome(@Query("method") String method,
                                               @Query("page-num") int page_num,
                                               @Query("page-row") int page_row,
                                               @Query("timestamp") long timestamp);
}
