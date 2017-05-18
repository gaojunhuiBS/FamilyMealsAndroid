package com.example.gaojunhui.textworld.mvp.presenter;

/**
 * Created by gaojunhui on 2017/5/5.
 */

public interface MvpPresenterImpl {
    void loadData(String method,int page_num,int page_row,long timeStamp,boolean isRefresh);
}
