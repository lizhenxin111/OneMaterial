package com.lzx.onematerial.MVP.topicMVP;

import com.lzx.onematerial.listener.OnGetTopicItemListener;

/**
 * Created by lizhenxin on 17-11-21.
 */

public interface ITopicModel {
    void getBanner(OnGetTopicItemListener onGetTopicItemListener);
    void getNormalTopics(String lastContentId, OnGetTopicItemListener onGetTopicItemListener);
    void getQATopics(OnGetTopicItemListener onGetTopicItemListener);
}
