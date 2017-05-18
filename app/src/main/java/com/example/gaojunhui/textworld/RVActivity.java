package com.example.gaojunhui.textworld;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.ButterKnife;
import com.example.gaojunhui.textworld.adapter.RVAdapter;
import com.example.gaojunhui.textworld.loadinglayout.XRefreshLayout;
import com.example.gaojunhui.textworld.rest.RestClient;
import com.example.gaojunhui.textworld.rest.data.Data;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gaojunhui on 2017/4/10.
 */

public class RVActivity extends AppCompatActivity implements XRefreshLayout.OnRefreshListener {
    RecyclerView rv;
    XRefreshLayout refresh;
    private Subscriber mSubscriber;
    private LinearLayoutManager mLManager;
    private RVAdapter mAdapter;
    private List<Data> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);
        rv= (RecyclerView) findViewById(R.id.rv);
        refresh= (XRefreshLayout) findViewById(R.id.refresh);
        mLManager = new LinearLayoutManager(RVActivity.this);
        if (refresh != null) {
            refresh.setOnRefreshListener(this);
        }
        onRefresh();
    }

    /**
     * 获取数据
     * @param method
     * @param page_num
     * @param page_row
     */
    protected void getData(String method, int page_num, int page_row) {
        mSubscriber = (Subscriber) RestClient.instance().netService().getCircleHome(method, page_num, page_row, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(circleHomeEntity -> {
                    mList = circleHomeEntity.data.data;
                    if (mAdapter == null) {
                        mAdapter = new RVAdapter(RVActivity.this, mList);
                        if (rv != null) {
                            rv.setLayoutManager(mLManager);
                            rv.setAdapter(mAdapter);
                        }
                    } else {
                        mAdapter.notifyDataSetChanged();
                    }
                    new Handler().postDelayed(() -> {
                        if (refresh != null) {
                            refresh.completeRefresh();
                        }
                    }, 2000);
                }, throwable -> new Handler().postDelayed(() -> {
                    if (refresh != null) {
                        refresh.completeRefresh();
                    }
                }, 2000)
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mSubscriber.isUnsubscribed()) {
            mSubscriber.unsubscribe();
        }
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        getData("init", 1, 5);
    }

    @Override
    public void onLoadMore() {
        getData("init", 1, 5);
    }
}
