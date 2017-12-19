package com.lzx.onematerial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-14.
 * 一定是继承FragmentStatePagerAdapter！！！
 * FragmentStatePagerAdapter持有的内容，被切换掉就会从内存中删除
 * 而FragmentPagerAdapter会将所有内容保存在内存中，无法动态修改内容
 */

public class DayViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public DayViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mFragmentList = list;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
