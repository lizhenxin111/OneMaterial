package com.lzx.onematerial.entity.day;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-14.
 *
 *
 */

public class DayData {
    private String id;
    private Weather Weather;
    private String date;
    private List<ContentItem> content_list;

    public void setId(String id) {
        this.id = id;
    }

    public void setWeather(com.lzx.onematerial.entity.day.Weather weather) {
        Weather = weather;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent_list(List<ContentItem> content_list) {
        this.content_list = content_list;
    }

    public String getId() {
        return id;
    }

    public com.lzx.onematerial.entity.day.Weather getWeather() {
        return Weather;
    }

    public String getDate() {
        return date;
    }

    public List<ContentItem> getContent_list() {
        return content_list;
    }
}
