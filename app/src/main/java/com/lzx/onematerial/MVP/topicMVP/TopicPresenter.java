package com.lzx.onematerial.MVP.topicMVP;

import com.lzx.onematerial.entity.topic.banner.TopicItem;
import com.lzx.onematerial.listener.OnGetTopicItemListener;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-21.
 */

public class TopicPresenter {
    private ITopicModel mModel;
    private ITopicView mView;

    private static String LAST_ITEM_ID = "0";       //已经加载的最后一项的content_id

    public TopicPresenter(ITopicView mView) {
        this.mView = mView;
        mModel = new TopicModel();
    }

    public static String getLastItemId() {
        return LAST_ITEM_ID;
    }

    public void setBanner() {
        mModel.getBanner(new OnGetTopicItemListener() {
            @Override
            public void getTopics(List<TopicItem> linkList) {
                mView.setBanner(linkList);
            }
        });
    }

    public void setQATopics() {
        mModel.getQATopics(new OnGetTopicItemListener() {
            @Override
            public void getTopics(List<TopicItem> linkList) {
                mView.setQATopics(linkList);
            }
        });
    }

    public void setNormalTpoic(String id) {
        mModel.getNormalTopics(id, new OnGetTopicItemListener() {
            @Override
            public void getTopics(List<TopicItem> itemList) {
                mView.setNormalTipics(itemList);
                LAST_ITEM_ID = itemList.get(itemList.size() - 1).getContent_id();
            }
        });
    }
}
