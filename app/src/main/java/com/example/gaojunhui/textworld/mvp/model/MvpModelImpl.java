package com.example.gaojunhui.textworld.mvp.model;

/**
 * Created by gaojunhui on 2017/5/5.
 */

public interface MvpModelImpl {
    void loadData(String method,int page_num,int page_row,long timestamp,OnLoadListener loadListener,boolean isRefresh);
}
