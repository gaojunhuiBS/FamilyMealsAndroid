package com.example.gaojunhui.textworld.mvp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.adapter.RVAdapter;
import com.example.gaojunhui.textworld.loadinglayout.XRefreshLayout;
import com.example.gaojunhui.textworld.mvp.presenter.MvpPresenter;
import com.example.gaojunhui.textworld.mvp.view.MvpViewImp;
import com.example.gaojunhui.textworld.rest.data.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojunhui on 2017/5/5.
 */

public class MvpActivity extends AppCompatActivity implements MvpViewImp, XRefreshLayout.OnRefreshListener {

    RecyclerView rv;
    XRefreshLayout refresh;
    ProgressBar mvpProgress;
    private RVAdapter rvAdapter;
    private LinearLayoutManager manager;
    private List<Data> list = new ArrayList<>();
    private MvpPresenter presenter;
    private boolean isInit=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        refresh = (XRefreshLayout) findViewById(R.id.refresh);
        mvpProgress = (ProgressBar) findViewById(R.id.mvp_progress);
        presenter = new MvpPresenter(this);
        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        refresh.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    public void showProgress() {
        if (isInit) {
            mvpProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addData(List<Data> data, boolean isRefresh) {
        if (isInit) {
            isInit=false;
            list=data;
            rvAdapter = new RVAdapter(this, list);
            rv.setAdapter(rvAdapter);
            //rvAdapter.initData(data);
        } else {
            if (isRefresh) {
                rvAdapter.notifyData(data);
                //list.clear();
                //list.addAll(data);
                //rvAdapter.notifyDataSetChanged();
            } else {
                //list.addAll(data);
                rvAdapter.loadMore(data);
                //rvAdapter.notifyDataSetChanged();
            }
        }
        new Handler().postDelayed(() -> {
            if (refresh != null) {
                refresh.completeRefresh();
            }
        }, 2000);
    }

    @Override
    public void hideProgress() {
        mvpProgress.setVisibility(View.GONE);
    }

    @Override
    public void showLoadFiled() {
        new Handler().postDelayed(() -> {
            if (refresh != null) {
                refresh.completeRefresh();
            }
        }, 2000);
        mvpProgress.setVisibility(View.GONE);
        Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        presenter.loadData("init", 0, 5, 0, true);
    }

    @Override
    public void onLoadMore() {
        presenter.loadData("init", 0, 5, 0, false);
    }
}
