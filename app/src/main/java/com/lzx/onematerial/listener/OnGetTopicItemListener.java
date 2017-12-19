package com.lzx.onematerial.listener;

import com.lzx.onematerial.entity.topic.banner.TopicItem;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-21.
 */

public interface OnGetTopicItemListener {
    void getTopics(List<TopicItem> itemList);
}
