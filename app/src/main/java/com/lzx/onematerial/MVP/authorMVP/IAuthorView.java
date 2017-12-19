package com.lzx.onematerial.MVP.authorMVP;

import com.lzx.onematerial.entity.category.author.AuthorProfile;
import com.lzx.onematerial.entity.day.ContentItem;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-25.
 */

public interface IAuthorView {
    void setAuthors(List<AuthorProfile> list);
    void setAuthorDetails(AuthorProfile profile);
    void setAuthorWorks(List<ContentItem> list);
}
