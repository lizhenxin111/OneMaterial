package com.lzx.onematerial.entity.topic.banner;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-21.
 */

public class TopicItem {
    private String id;
    private String cover;
    private String title;
    private String category;
    private String content_id;
    private boolean is_stick;
    private String link_url;
    private List<String> serial_list;

    public TopicItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isIs_stick() {
        return is_stick;
    }

    public void setIs_stick(boolean is_stick) {
        this.is_stick = is_stick;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public List<?> getSerial_list() {
        return serial_list;
    }

    public void setSerial_list(List<String> serial_list) {
        this.serial_list = serial_list;
    }
}
