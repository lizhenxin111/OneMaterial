package com.lzx.onematerial.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.mob.MobSDK;
import com.xsj.crasheye.Crasheye;

import androidlib.image.cache.DoubleCache;
import androidlib.net.HttpGet.HttpRequest;

/**
 * Created by lizhe on 2017/7/1.
 */

public class MyApp extends Application{
    private static Context context;

    public static HttpRequest mainRequest;

    private static DoubleCache mDoubleCacne;


    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate() {
        context = getApplicationContext();
        mainRequest = HttpRequest.getInstance();
        mDoubleCacne = new DoubleCache(context ,"image", 1024*1024*20);
        MobSDK.init(this, "22c248427c9e3", "f9f2661d7f69c6fe91b69163561cb7f8");
        Crasheye.init(this, "4b60c0f0");
    }

    public static Context getContext(){
        return context;
    }

    public static HttpRequest getMainRequest() {
        return mainRequest;
    }

    public static boolean needCheckPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public static DoubleCache getCache() {
        return mDoubleCacne;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mDoubleCacne.clearMemoryCache();
    }
}
