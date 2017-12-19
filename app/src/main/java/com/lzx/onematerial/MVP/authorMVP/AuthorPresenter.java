package com.lzx.onematerial.MVP.authorMVP;

import com.google.gson.Gson;
import com.lzx.onematerial.entity.category.author.AuthorDetail;
import com.lzx.onematerial.entity.category.author.AuthorProfile;
import com.lzx.onematerial.entity.day.ContentItem;
import com.lzx.onematerial.listener.OnGetListDataListener;
import com.lzx.onematerial.listener.OnGetStringListener;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-25.
 */

public class AuthorPresenter {

    private IAuthorModel mModel;
    private IAuthorView mView;

    private int mCurrentPage = 0;

    public String getPageNum() {
        return Integer.valueOf(mCurrentPage++).toString();
    }


    public AuthorPresenter(IAuthorView mView) {
        this.mView = mView;
        mModel = new AuthorModel();
    }

    public void setHotAuthors() {
        mModel.getAuthors(new OnGetListDataListener<AuthorProfile>() {
            @Override
            public void getData(List<AuthorProfile> list) {
                mView.setAuthors(list);
            }
        });
    }

    public void setAuthorDetail(String userId) {
        mModel.getAuthorDetail(userId, new OnGetStringListener() {
            @Override
            public void getString(String result) {
                Gson gson = new Gson();
                AuthorDetail profile = gson.fromJson(result, AuthorDetail.class);
                mView.setAuthorDetails(profile.getData());
            }
        });
    }

    public void setAuthorWorks(String userId, String pageNum) {
        mModel.getAuthorWorks(userId, pageNum, new OnGetListDataListener<ContentItem>() {
            @Override
            public void getData(List<ContentItem> list) {
                mView.setAuthorWorks(list);
            }
        });
    }
}
