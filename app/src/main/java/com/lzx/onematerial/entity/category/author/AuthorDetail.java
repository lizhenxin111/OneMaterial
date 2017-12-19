package com.lzx.onematerial.entity.category.author;

/**
 * Created by lizhenxin on 17-11-25.
 */

public class AuthorDetail {
    private String res;
    private AuthorProfile data;

    public AuthorDetail() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public AuthorProfile getData() {
        return data;
    }

    public void setData(AuthorProfile data) {
        this.data = data;
    }
}
