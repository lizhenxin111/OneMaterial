package com.lzx.onematerial.MVP.dayMVP;

import com.lzx.onematerial.listener.OnGetDayDataListener;


/**
 * Created by lizhe on 2017/6/4.
 */

public interface IDayModel {
    void getData(int day, OnGetDayDataListener onGetDayDataListener);
}
