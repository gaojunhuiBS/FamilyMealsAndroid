package com.example.gaojunhui.textworld.mvp.presenter;

import com.example.gaojunhui.textworld.mvp.model.MvpModel;
import com.example.gaojunhui.textworld.mvp.model.OnLoadListener;
import com.example.gaojunhui.textworld.mvp.view.MvpViewImp;
import com.example.gaojunhui.textworld.rest.data.Data;
import java.util.List;

/**
 * Created by gaojunhui on 2017/5/5.
 */

public class MvpPresenter implements MvpPresenterImpl, OnLoadListener {
    private static final String TAG = "MvpPresenter";
    private MvpViewImp mvpViewImp;
    private MvpModel mvpModel;

    public MvpPresenter(MvpViewImp mvpViewImp) {
        this.mvpViewImp = mvpViewImp;
        this.mvpModel = new MvpModel();
    }

    @Override
    public void loadData(String method, int pageNum, int page_row, long timeStamp, boolean isRefresh) {
        if (pageNum == 0) {
            mvpViewImp.showProgress();
        }
        mvpModel.loadData(method, pageNum, page_row, timeStamp, this, isRefresh);
    }

    @Override
    public void onSuccess(List<Data> datas, boolean isRefresh) {
        mvpViewImp.hideProgress();
        mvpViewImp.addData(datas, isRefresh);
    }

    @Override
    public void onFailure(String message, String e) {
        mvpViewImp.hideProgress();
        mvpViewImp.showLoadFiled();
    }
}
