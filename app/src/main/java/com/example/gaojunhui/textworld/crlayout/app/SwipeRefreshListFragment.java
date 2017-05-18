package com.example.gaojunhui.textworld.crlayout.app;


import com.example.gaojunhui.textworld.crlayout.SwipeRefreshListView;

public abstract class SwipeRefreshListFragment extends SwipeRefreshAbsListFragment<SwipeRefreshListView> {

    @Override
    public SwipeRefreshListView createSwipeRefreshLayout() {
        return new SwipeRefreshListView(getActivity());
    }
}
