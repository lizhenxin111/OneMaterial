package com.lzx.onematerial.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseLazyLoadFragment extends Fragment {


    private boolean isVisible;
    private boolean isViewCreated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        isViewCreated = true;
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            loadForData();
        } else {
            isVisible = false;
            onInvisible();
        }
        if (isViewCreated && isVisible)
            loadForUI();
    }

    protected abstract void onInvisible();

    protected abstract void loadForData();

    protected abstract void loadForUI();

    // provide layout id
    public abstract int getLayoutID();


}
