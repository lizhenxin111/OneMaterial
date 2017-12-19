package com.lzx.onematerial.MVP.authorMVP;

import com.google.gson.Gson;
import com.lzx.onematerial.entity.category.author.AuthorProfile;
import com.lzx.onematerial.entity.category.author.AuthorWorks;
import com.lzx.onematerial.entity.category.author.HotAuthor;
import com.lzx.onematerial.entity.day.ContentItem;
import com.lzx.onematerial.listener.OnGetListDataListener;
import com.lzx.onematerial.listener.OnGetStringListener;
import com.lzx.onematerial.utils.ApiUtil;
import com.lzx.onematerial.utils.MyApp;

import androidlib.net.HttpGet.HttpGetListener;

/**
 * Created by lizhenxin on 17-11-25.
 */

public class AuthorModel implements IAuthorModel {

    @Override
    public void getAuthors(final OnGetListDataListener<AuthorProfile> onGetListDataListener) {
        MyApp.getMainRequest().getString(ApiUtil.getAuthorsUrl(), new HttpGetListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HotAuthor hotAuthor = gson.fromJson(result, HotAuthor.class);
                onGetListDataListener.getData(hotAuthor.getData());
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void getAuthorDetail(String userId, final OnGetStringListener onGetStringListener) {
        MyApp.getMainRequest().getString(ApiUtil.getAuthorDetailUrl(userId), new HttpGetListener() {
            @Override
            public void onSuccess(String result) {
                onGetStringListener.getString(result);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void getAuthorWorks(String userId, String pageNum, final OnGetListDataListener<ContentItem> onGetListDataListener) {
        MyApp.getMainRequest().getString(ApiUtil.getAuthorWorkUrl(pageNum, userId), new HttpGetListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                AuthorWorks authorWorks = gson.fromJson(result, AuthorWorks.class);
                onGetListDataListener.getData(authorWorks.getData());
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
