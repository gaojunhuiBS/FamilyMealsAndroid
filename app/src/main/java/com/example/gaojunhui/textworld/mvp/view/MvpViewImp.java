package com.example.gaojunhui.textworld.mvp.view;

import com.example.gaojunhui.textworld.rest.data.Data;
import java.util.List;

/**
 * Created by gaojunhui on 2017/5/5.
 */

public interface MvpViewImp {
    void showProgress();
    void addData(List<Data> data,boolean isRefresh);
    void hideProgress();
    void showLoadFiled();

}
