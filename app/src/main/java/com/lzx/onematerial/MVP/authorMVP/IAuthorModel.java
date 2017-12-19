package com.lzx.onematerial.MVP.authorMVP;

import com.lzx.onematerial.entity.category.author.AuthorProfile;
import com.lzx.onematerial.entity.day.ContentItem;
import com.lzx.onematerial.listener.OnGetListDataListener;
import com.lzx.onematerial.listener.OnGetStringListener;

/**
 * Created by lizhenxin on 17-11-25.
 */

public interface IAuthorModel {
    void getAuthors(OnGetListDataListener<AuthorProfile> onGetListDataListener);

    void getAuthorDetail(String userId, OnGetStringListener onGetStringListener);

    void getAuthorWorks(String userId, String pageNum, OnGetListDataListener<ContentItem> list);
}
