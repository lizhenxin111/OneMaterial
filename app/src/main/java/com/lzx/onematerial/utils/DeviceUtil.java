package com.lzx.onematerial.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 在MainActivity中初始化，其他地方都可以使用
 * Created by lizhenxin on 17-12-2.
 */

public class DeviceUtil {
    private static int WIDTH_PX;
    private static int HEIGHT_PX;

    private static int WIDTH_DP;
    private static int HEIGHT_DP;

    public DeviceUtil() {
        
    }

    public DeviceUtil(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        HEIGHT_PX = displayMetrics.heightPixels;
        WIDTH_PX = displayMetrics.widthPixels;
        WIDTH_DP = px2dip(activity, WIDTH_PX);
        HEIGHT_DP = px2dip(activity, HEIGHT_PX);
    }

    private static class LAZY_LOADER{
        private static DeviceUtil getIntance(Activity activity) {
            return new DeviceUtil(activity);
        }
    }

    public static DeviceUtil init(Activity activity) {
        return LAZY_LOADER.getIntance(activity);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getWidthPx() {
        return WIDTH_PX;
    }

    public static int getHeightPx() {
        return HEIGHT_PX;
    }

    public static int getWidthDp() {
        return WIDTH_DP;
    }

    public static int getHeightDp() {
        return HEIGHT_DP;
    }
}
