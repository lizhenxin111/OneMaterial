package com.lzx.onematerial.entity.search;

import com.lzx.onematerial.entity.day.Author;

import java.util.List;

/**
 * Created by lizhenxin on 17-12-1.
 */

public class SearchOtherItem {
    private String audio;
    private String anchor;
    private String category;
    private String id;
    private String title;
    private String web_url;
    private String platform;
    private String music_id;
    private String praisenum;
    private String commentnum;
    private List<String> tag_list;
    private List<Author> author_list;

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getPraisenum() {
        return praisenum;
    }

    public void setPraisenum(String praisenum) {
        this.praisenum = praisenum;
    }

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
    }

    public List<String> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<String> tag_list) {
        this.tag_list = tag_list;
    }

    public List<Author> getAuthor_list() {
        return author_list;
    }

    public void setAuthor_list(List<Author> author_list) {
        this.author_list = author_list;
    }
}
