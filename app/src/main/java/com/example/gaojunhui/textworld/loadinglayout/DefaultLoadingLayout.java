package com.example.gaojunhui.textworld.loadinglayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.util.L;


/**
 * Created by dance on 2017/4/2.
 */

public class DefaultLoadingLayout implements ILoadingLayout {

    private View headerView;
    private View footerView;
    private ImageView ivHeaderProgress,ivHeaderArrow;
    private ImageView ivFooterProgress,ivFooterArrow;
    private TextView tvHeaderState,tvFooterState;

    @Override
    public View createLoadingHeader(Context context,ViewGroup parent) {
        headerView = LayoutInflater.from(context).inflate(R.layout.xrl_default_header, parent, false);
        tvHeaderState = (TextView) headerView.findViewById(R.id.tv_header_state);
        ivHeaderProgress = (ImageView) headerView.findViewById(R.id.iv_header_progress);
        ivHeaderArrow = (ImageView) headerView.findViewById(R.id.iv_header_arrow);
        return headerView;
    }

    @Override
    public View createLoadingFooter(Context context,ViewGroup parent) {
        footerView = LayoutInflater.from(context).inflate(R.layout.xrl_default_footer, parent, false);
        tvFooterState = (TextView) footerView.findViewById(R.id.tv_footer_state);
        ivFooterProgress = (ImageView) footerView.findViewById(R.id.iv_footer_progress);
        ivFooterArrow = (ImageView) footerView.findViewById(R.id.iv_footer_arrow);
        return footerView;
    }

    @Override
    public void initAndResetHeader() {
        tvHeaderState.setText("下拉刷新");
        ivHeaderArrow.setVisibility(View.VISIBLE);
        ivHeaderArrow.setRotation(0);
        ivHeaderProgress.setVisibility(View.INVISIBLE);
        AnimationDrawable drawable = (AnimationDrawable) ivHeaderProgress.getBackground();
        drawable.stop();
    }

    @Override
    public void initAndResetFooter() {
        tvFooterState.setText("上拉加载");
        ivFooterArrow.setVisibility(View.VISIBLE);
        ivFooterArrow.setRotation(0);
        ivFooterProgress.setVisibility(View.INVISIBLE);
        AnimationDrawable drawable = (AnimationDrawable) ivFooterProgress.getBackground();
        drawable.stop();
    }

    @Override
    public void onPullHeader(float percent) {
        tvHeaderState.setText("释放立即刷新");
        ivHeaderArrow.setRotation(360*percent);
    }

    @Override
    public void onPullFooter(float percent) {
        L.d("footer percent ： "+percent);
        tvFooterState.setText("释放立即加载");
        ivFooterArrow.setRotation(360*percent);
    }

    @Override
    public void onHeaderRefreshing() {
        tvHeaderState.setText("正在刷新...");
        ivHeaderArrow.setVisibility(View.INVISIBLE);
        ivHeaderProgress.setVisibility(View.VISIBLE);
        AnimationDrawable drawable = (AnimationDrawable) ivHeaderProgress.getBackground();
        drawable.start();
    }

    @Override
    public void onFooterRefreshing() {
        tvFooterState.setText("正在加载...");
        ivFooterArrow.setVisibility(View.INVISIBLE);
        ivFooterProgress.setVisibility(View.VISIBLE);
        AnimationDrawable drawable = (AnimationDrawable) ivFooterProgress.getBackground();
        drawable.start();
    }

}
