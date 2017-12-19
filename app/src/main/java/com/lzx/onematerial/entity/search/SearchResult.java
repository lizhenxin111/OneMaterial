package com.lzx.onematerial.entity.search;

/**
 * Created by lizhenxin on 17-11-30.
 */

public class SearchResult {
    private String res;
    private SearchImageItem data;

    public SearchResult() {
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public SearchImageItem getData() {
        return data;
    }

    public void setData(SearchImageItem data) {
        this.data = data;
    }
}
