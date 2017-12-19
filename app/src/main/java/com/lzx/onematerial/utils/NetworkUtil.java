package com.lzx.onematerial.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络管理类， 用于获取网络状态
 */

public class NetworkUtil {
    public static final int NONE = -1;
    public static final int TYPE_WIFI = -2;
    public static final int TYPE_MOBILE = -3;

    public NetworkUtil(){
    }

    public static int getNetworkState(){
        ConnectivityManager manager = (ConnectivityManager)MyApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null){
            if (info.getType() == ConnectivityManager.TYPE_WIFI){
                return TYPE_WIFI;
            }else if (info.getType() == ConnectivityManager.TYPE_MOBILE){
                return TYPE_MOBILE;
            }
        }
        return NONE;
    }
}
