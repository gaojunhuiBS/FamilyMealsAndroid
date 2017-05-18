package com.example.gaojunhui.textworld.mvp.model;

import com.example.gaojunhui.textworld.rest.data.Data;
import java.util.List;

/**
 * Created by gaojunhui on 2017/5/5.
 */

public interface OnLoadListener {
    void onSuccess(List<Data> datas,boolean isRefresh);
    void onFailure(String message,String e);
}
