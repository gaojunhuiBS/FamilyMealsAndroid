package com.example.gaojunhui.textworld.mvp.model;

import com.example.gaojunhui.textworld.rest.RestClient;
import com.example.gaojunhui.textworld.rest.data.CircleHomeEntity;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by gaojunhui on 2017/5/5.
 */

public class MvpModel implements MvpModelImpl {

    @Override
    public void loadData(String method, int page_num, int page_row, long timestamp,OnLoadListener loadListener,boolean isRefresh) {
        RestClient.instance().netService().getCircleHome(method,page_num,page_row,timestamp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CircleHomeEntity>() {
                    @Override
                    public void call(CircleHomeEntity circleHomeEntity) {
                        loadListener.onSuccess(circleHomeEntity.data.data,isRefresh);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        loadListener.onFailure("load failure",throwable.getMessage());
                    }
                });

    }
}
