package com.example.gaojunhui.textworld.headerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by gaojunhui on 2017/5/31.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> listTitles;
    public FragmentAdapter(FragmentManager fm,List<Fragment> list,List<String> listTitles) {
        super(fm);
        this.list=list;
        this.listTitles=listTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitles.get(position  );
    }
}
