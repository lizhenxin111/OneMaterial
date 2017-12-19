package com.lzx.onematerial.MVP.topicMVP;


import com.lzx.onematerial.entity.topic.banner.TopicItem;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-21.
 */

public interface ITopicView {
    void setBanner(List<TopicItem> itemList);
    void setNormalTipics(List<TopicItem> itemList);
    void setQATopics(List<TopicItem> itemList);
}
