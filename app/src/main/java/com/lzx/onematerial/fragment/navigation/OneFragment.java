package com.lzx.onematerial.fragment.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzx.onematerial.R;
import com.lzx.onematerial.activity.MainActivity;
import com.lzx.onematerial.adapter.DayViewPagerAdapter;
import com.lzx.onematerial.MVP.dayMVP.DayFrag;
import com.lzx.onematerial.fragment.DayPlaceHolderFragment;

import java.util.ArrayList;
import java.util.List;

import androidlib.activity.BaseFragment;

/**
 * 每日内容的Fragment。左右滑动可以切换不同日期的内容。
 *
 * 无限滚动原理：Fragment持有一个ViewPager，ViewPager持有3个Fragment：第0、2个为占位的fragment，第1个为显示内容的Fragment。
 *              当切监测到切换到第0、2个fragment时，返回到第1个fragment并加载前一天或者后一天的数据。
 *
 * 解决滑动卡顿的思想：等ViewPager稳定（不再滑动时）再从网络上加载数据。
 *              方法：1.设置标志位isPageChanged标识fragment是否切换过（onPageSelected()中翻页后isPageChanged=true；onPageScrollStateChanged()中加载数据后isPageChanged=false）
 *                    2.onPageScrollStateChanged中，当state==0时ViewPager停止滚动。
 *                      所以当达成(state == 0 && mViewPager.getCurrentItem() == 2 && isPageChanged)条件时加载数据
 */
public class OneFragment extends BaseFragment {

    private ViewPager mViewPager;
    private List<Fragment> mList = new ArrayList<>();
    private DayViewPagerAdapter mAdapter;

    private int mDay = 0;

    //页面是否变化
    private boolean isPageChanged = false;

    private DayFrag mDayFragment;

    public OneFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initFragmentList();
        initViewPager(view);
        return view;
    }


    private void initFragmentList() {
        mDayFragment = new DayFrag();
        mList.add(new DayPlaceHolderFragment());
        mDayFragment.updateData(0);
        mList.add(mDayFragment);
        mList.add(new DayPlaceHolderFragment());
    }

    private void initViewPager(View view) {
        mViewPager = view.findViewById(R.id.one_viewpager);
        //mViewPager.setCancelScroll(false);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    if (mDay == 0) {
                        toastLong(getString(R.string.toast_first_day));
                    } else {
                        mDay -= 1;
                        isPageChanged = true;
                    }
                    mViewPager.setCurrentItem(1);
                } else if (position == 2) {
                    if (mDay == 9) {
                        toastLong(getString(R.string.toast_last_day));
                    } else {
                        mDay += 1;
                        isPageChanged = true;
                    }
                    mViewPager.setCurrentItem(1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //0（END）,1(PRESS) , 2(UP)
                if (state == 0 && mViewPager.getCurrentItem() == 1 && isPageChanged) {
                    stableToLoadFragment();
                    isPageChanged = false;
                }
            }
        });
        mAdapter = new DayViewPagerAdapter(getActivity().getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(1);
    }

    /**
     * 当ViewPager稳定（不再滑动）时加载Fragment的内容，防止滑动时加载数据造成卡顿。
     * 设置标志位isPageChanged监测页面是否发生改变。此标志位在页面发生变化时（即onPageSelected()中回弹到第1页）设为true（）
     */
    private void stableToLoadFragment() {
        changeFragment();
    }



    private void changeFragment() {
        /*DayFrag dayFrag = new DayFrag();
        Bundle bundle = new Bundle();
        bundle.putInt("day", mDay);
        dayFrag.setArguments(bundle);
        mList.set(1, dayFrag);*/
        mDayFragment.removeViews();
        mDayFragment.updateData(mDay);
        //mAdapter.notifyDataSetChanged();
    }
}
