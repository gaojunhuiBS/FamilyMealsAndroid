package com.example.gaojunhui.textworld.crlayout.app;


import com.example.gaojunhui.textworld.crlayout.SwipeRefreshAdapterView;

/**
 * 如果开启{@linkplain SwipeRefreshAdapterFragment#setEnabledLoad(boolean) 上拉加载}功能
 * 必须重写 {@linkplain SwipeRefreshAdapterFragment#onListLoad() onListLoad()}方法<br>
 */
abstract class SwipeRefreshAdapterFragment<T extends SwipeRefreshAdapterView> extends BaseSwipeRefreshFragment<T>
        implements SwipeRefreshAdapterView.OnListLoadListener {


    /**
     * 设置允许上拉加载，重写{@link #onListLoad() onListLoad 方法实现上拉加载数据}
     *
     * @param enabled
     */
    public final void setEnabledLoad(boolean enabled) {
        if (enabled)
            mSwipeRefresh.setOnListLoadListener(this);
        mSwipeRefresh.setEnabledLoad(enabled);
    }


    /**
     * {@link SwipeRefreshAdapterView#setLoading(boolean)}
     */
    public final void setLoading(boolean loading) {
        getSwipeRefreshLayout().setLoading(loading);
    }

    @Override
    public void onListLoad() {

    }
}
