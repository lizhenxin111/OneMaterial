package com.lzx.onematerial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-15.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<?> list;
    public MainViewPagerAdapter(FragmentManager fm, List<?> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
