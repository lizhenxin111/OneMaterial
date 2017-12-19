package com.lzx.onematerial.listener;

import com.lzx.onematerial.entity.day.ContentItem;
import com.lzx.onematerial.entity.day.Weather;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-16.
 */

public interface OnGetDayDataListener {
    void getDate(String date);
    void getContentItems(List<ContentItem> items);
    void getWeather(Weather weather);
    void getId(String id);
}
