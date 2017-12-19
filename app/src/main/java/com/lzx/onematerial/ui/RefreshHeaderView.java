package com.lzx.onematerial.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.lzx.onematerial.R;

/**
 * Created by lizhenxin on 17-12-17.
 */

public class RefreshHeaderView extends View implements IHeaderView{
    private View mView;
    public RefreshHeaderView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public RefreshHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context){
        mView = LayoutInflater.from(context).inflate(R.layout.refresh_header_view, null, false);
    }

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {

    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {

    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {

    }

    @Override
    public void reset() {

    }
}
