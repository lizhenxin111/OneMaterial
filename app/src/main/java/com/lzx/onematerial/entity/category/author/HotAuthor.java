package com.lzx.onematerial.entity.category.author;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-25.
 */

public class HotAuthor {
    private String res;
    private List<AuthorProfile> data;

    public HotAuthor() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public List<AuthorProfile> getData() {
        return data;
    }

    public void setData(List<AuthorProfile> data) {
        this.data = data;
    }
}
