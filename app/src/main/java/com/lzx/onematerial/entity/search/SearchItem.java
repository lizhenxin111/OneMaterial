package com.lzx.onematerial.entity.search;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-28.
 */

public class SearchItem {

    private String title;
    private String subtitle;
    private String cover;
    private String category;
    private String content_id;
    private String date;
    private List<String> serial_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getSerial_list() {
        return serial_list;
    }

    public void setSerial_list(List<String> serial_list) {
        this.serial_list = serial_list;
    }
}
