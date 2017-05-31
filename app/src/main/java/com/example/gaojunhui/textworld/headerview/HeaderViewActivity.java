package com.example.gaojunhui.textworld.headerview;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.headerview.weight.IPagerScroll;
import com.example.gaojunhui.textworld.headerview.weight.TouchPanelLayout;
import com.example.gaojunhui.textworld.util.UIUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojunhui on 2017/5/31.
 */

public class HeaderViewActivity extends AppCompatActivity implements TouchPanelLayout.IConfigCurrentPagerScroll,
                                                                     TouchPanelLayout.OnViewUpdateListener {
    private Toolbar toolbar;
    private int toolBarHeight;
    private TouchPanelLayout touchPanelLayout;
    private ViewPager viewPager;
    private List<Fragment> listFragments = new ArrayList<>();
    private List<String> listTitls = new ArrayList<>();
    private TabLayout tab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        initView();
        statusBar();
    }

    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.head_toolbar);
        toolbar.post(() -> {
            toolBarHeight = toolbar.getMeasuredHeight();
            Log.i("----", "getActionBarHeight1: " + toolBarHeight);
        });
        setSupportActionBar(toolbar);
        touchPanelLayout = (TouchPanelLayout) findViewById(R.id.touchPaneLayout);
        touchPanelLayout.setConfigCurrentPagerScroll(this);
        touchPanelLayout.setOnViewUpdateListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tab = (TabLayout) findViewById(R.id.tab);
        listTitls.add("风");
        listTitls.add("雨");
        listTitls.add("雷");
        listFragments.add(new TextFragment());
        listFragments.add(new TextFragment());
        listFragments.add(new TextFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), listFragments, listTitls);
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }

    private void statusBar() {
        if (Build.VERSION.SDK_INT >= 19) {//4.4
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public IPagerScroll getCurrentPagerScroll() {
        return null;
    }

    @Override
    public float getActionBarHeight() {
        return UIUtil.dip2px(this,60);
    }

    @Override
    public void onAlphaChanged(int alpha) {

    }
}
