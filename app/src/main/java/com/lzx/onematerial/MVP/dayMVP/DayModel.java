package com.lzx.onematerial.MVP.dayMVP;


import com.google.gson.Gson;
import com.lzx.onematerial.entity.day.DayContentId;
import com.lzx.onematerial.entity.day.DayData;
import com.lzx.onematerial.entity.day.DayResource;
import com.lzx.onematerial.listener.OnGetDayDataListener;
import com.lzx.onematerial.utils.ApiUtil;
import com.lzx.onematerial.utils.MyApp;

import java.io.IOException;

import android.os.Handler;

import androidlib.net.HttpGet.HttpRunnable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lizhe on 2017/6/4.
 */

public class DayModel implements IDayModel {

    private String TAG = getClass().getSimpleName();

    public DayModel(){
    }

    private Handler handler = new android.os.Handler();

    public void getData(final int day, final OnGetDayDataListener onGetDayDataListener) {
        MyApp.getMainRequest().execute(new HttpRunnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Gson gson = new Gson();

                    //获取日期对应的id
                    Request idRequest = new Request.Builder()
                            .url(ApiUtil.getDayIdListUrl())
                            .build();
                    Response idResponse = client.newCall(idRequest).execute();
                    String idJson = idResponse.body().string();
                    DayContentId contentId = gson.fromJson(idJson, DayContentId.class);
                    String aimId = contentId.getData().get(day);

                    //根据id下载相应的json并解析
                    Request contentRequest = new Request.Builder()
                            .url(ApiUtil.getDayContentUrl(aimId))
                            .build();
                    Response contentResponse = client.newCall(contentRequest).execute();
                    String mContentJson = contentResponse.body().string();
                    Gson contentJson = new Gson();
                    DayResource dayResource = contentJson.fromJson(mContentJson, DayResource.class);
                    final DayData dayData = dayResource.getData();

                    //回调
                    if (dayData != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                onGetDayDataListener.getDate(dayData.getDate());
                                onGetDayDataListener.getContentItems(dayData.getContent_list());
                                onGetDayDataListener.getWeather(dayData.getWeather());
                                onGetDayDataListener.getId(dayData.getId());
                            }
                        });
                    } else {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
