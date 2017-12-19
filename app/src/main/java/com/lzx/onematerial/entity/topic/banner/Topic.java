package com.lzx.onematerial.entity.topic.banner;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-21.
 */

public class Topic {
    private String res;
    private List<TopicItem> data;


    public Topic() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public List<TopicItem> getData() {
        return data;
    }

    public void setData(List<TopicItem> data) {
        this.data = data;
    }
}
