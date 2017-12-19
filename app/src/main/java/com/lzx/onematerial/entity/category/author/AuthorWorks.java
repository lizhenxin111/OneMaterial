package com.lzx.onematerial.entity.category.author;

import com.lzx.onematerial.entity.day.ContentItem;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-26.
 */

public class AuthorWorks {
    private String res;
    private List<ContentItem> data;

    public AuthorWorks() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public List<ContentItem> getData() {
        return data;
    }

    public void setData(List<ContentItem> data) {
        this.data = data;
    }
}

