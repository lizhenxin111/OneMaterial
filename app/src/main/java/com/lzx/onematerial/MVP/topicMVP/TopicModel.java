package com.lzx.onematerial.MVP.topicMVP;

import com.google.gson.Gson;
import com.lzx.onematerial.entity.topic.banner.Topic;
import com.lzx.onematerial.listener.OnGetTopicItemListener;
import com.lzx.onematerial.utils.ApiUtil;
import com.lzx.onematerial.utils.MyApp;

import androidlib.net.HttpGet.HttpGetListener;

/**
 * Created by lizhenxin on 17-11-21.
 * banner+专题
 */

public class TopicModel implements ITopicModel{

    public TopicModel() {
    }

    @Override
    public void getBanner(final OnGetTopicItemListener onGetTopicItemListener) {
        MyApp.getMainRequest().getString(ApiUtil.getBannerListUrl(ApiUtil.BANNER), new HttpGetListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Topic topic = gson.fromJson(result, Topic.class);
                onGetTopicItemListener.getTopics(topic.getData());
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void getNormalTopics(final String lastContentId, final OnGetTopicItemListener onGetTopicItemListener) {
        MyApp.getMainRequest().getString(ApiUtil.getBannerListUrl(ApiUtil.BANNER_NORMAL), new HttpGetListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Topic topic = gson.fromJson(result, Topic.class);
                onGetTopicItemListener.getTopics(topic.getData());
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void getQATopics(final OnGetTopicItemListener onGetTopicItemListener) {
        //问答
        MyApp.getMainRequest().getString(ApiUtil.getBannerListUrl(ApiUtil.BANNER_QA), new HttpGetListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Topic topic = gson.fromJson(result, Topic.class);
                onGetTopicItemListener.getTopics(topic.getData());
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
