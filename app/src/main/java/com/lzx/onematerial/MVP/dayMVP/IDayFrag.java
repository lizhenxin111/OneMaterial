package com.lzx.onematerial.MVP.dayMVP;

/**
 * Created by lizhe on 2017/6/3.
 */

public interface IDayFrag {
    //参数：根view， 标签， 图片链接， 标题， 作者， 摘要， 文章链接，
    void setContentItem(String category, String picUrl, String title, String author, String abstracts,
                        String url, String audio, String contentTag, int id);

    void setPic(String picUrl, String picAuthor, String words, String wordsAuthor, String url);

    void setRadio(String title, String author, String imageUrl, String audioUrl, String articleUrl);

    void setDate(String date);

    String getDate();

    void showLoading();

    void removeLoading();
}
