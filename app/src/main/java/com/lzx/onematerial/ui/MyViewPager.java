package com.lzx.onematerial.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by lizhenxin on 17-11-15.
 * 可选择禁止左右滑动 ： setCanScroll()
 */

public class MyViewPager extends ViewPager {
    private boolean isScrolled = false;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }




    /**
     * @param cancleScroll
     * 是否取消滑动
     */
    public void setCancelScroll(boolean cancleScroll) {
        isScrolled = cancleScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !isScrolled && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !isScrolled && super.onTouchEvent(ev);
    }
}
