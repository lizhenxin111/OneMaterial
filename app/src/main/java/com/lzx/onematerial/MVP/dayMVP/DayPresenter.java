package com.lzx.onematerial.MVP.dayMVP;


import android.util.Log;

import com.lzx.onematerial.entity.day.ContentItem;
import com.lzx.onematerial.entity.day.Weather;
import com.lzx.onematerial.listener.OnGetDayDataListener;

import java.util.List;

/**
 * Created by lizhe on 2017/6/4.
 */

public class DayPresenter {

    private DayFrag dayFrag;
    private DayModel dayModel;

    public DayPresenter(DayFrag dayFrag){
        this.dayFrag = dayFrag;
        this.dayModel = new DayModel();
    }

    public void getAllItem(int day) {
        dayModel.getData(day, mOnGetDayDataListener);
    }

    private OnGetDayDataListener mOnGetDayDataListener = new OnGetDayDataListener() {
        @Override
        public void getDate(String date) {
            dayFrag.setDate(date);
        }

        @Override
        public void getContentItems(List<ContentItem> items) {
            dayFrag.showLoading();
            ContentItem contentItem = items.get(0);
            dayFrag.setPic(contentItem.getImg_url(), "插画 | " + contentItem.getPic_info(), contentItem.getForward(),
                    "by : " + contentItem.getWords_info(), contentItem.getShare_url());
            for (int i = 1; i < items.size(); i++){
                contentItem = items.get(i);
                if ("1".equals(contentItem.getIs_regular())){
                    dayFrag.setRadio(contentItem.getTitle(), contentItem.getAuthor().getUser_name(), contentItem.getImg_url(),
                            contentItem.getAudio_url(), contentItem.getShare_url());
                } else if ("2".equals(contentItem.getIs_regular())) {

                } else {
                    Log.d("CATEGORY", "category: " + contentItem.getCategory());
                    dayFrag.setContentItem("- " + contentItem.getCategory() + " -", contentItem.getImg_url(),
                            contentItem.getTitle(), contentItem.getAuthor().getUser_name(), contentItem.getForward(),
                            contentItem.getShare_url(), contentItem.getAudio_url(),
                            contentItem.getContent_type(), i);
                }
            }
            dayFrag.removeLoading();
        }

        @Override
        public void getWeather(Weather weather) {

        }

        @Override
        public void getId(String id) {

        }
    };
}
