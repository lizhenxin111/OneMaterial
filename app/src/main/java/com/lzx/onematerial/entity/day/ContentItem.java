package com.lzx.onematerial.entity.day;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lizhenxin on 17-11-14.
 */

public class ContentItem implements Parcelable {

    public static final int CONTENT_TEXT = 1;
    public static final int CONTENT_MUSIC = 2;
    public static final int CONTENT_FM = 3;

    private int contentTag;

    public int getContentTag() {
        if (category.equals("4")){
            if (tag_list == null){
                return CONTENT_MUSIC;
            }else {
                return CONTENT_FM;
            }
        }else {
            return CONTENT_TEXT;
        }
    }

    public void setContentTag(int contentTag) {
        this.contentTag = contentTag;
    }

    private String id;
    private String category;
    private String display_category;
    private String item_id;
    private String title;
    private String forward;
    private String img_url;
    private String like_count;
    private String post_date;
    private String last_update_date;
    private Author author;
    private String video_url;
    private String audio_url;
    private String audio_platform;
    private String start_video;
    private String has_reading;
    private String volume;
    private String pic_info;
    private String words_info;
    private String subtitle;
    private String number;
    private String serial_id;
    private String movie_story_id;
    private String ad_id;
    private String ad_type;
    private String ad_pvurl;
    private String ad_linkurl;
    private String ad_makettime;
    private String ad_closetime;
    private String ad_share_cnt;
    private String ad_pvurl_vendor;
    private String content_id;
    private String content_type;
    private String content_bgcolor;
    private String share_url;
    private ShareInfo share_info;
    private List<String> serial_list;
    private List<TagList> tag_list;
    private String is_regular;

    public String getCategory() {
        if (tag_list.size() != 0){
            return tag_list.get(0).getTitle();
        }
        else {
            switch (category){
                case "0":
                    return "每日一图";
                case "2":
                    return "连载";
                case "3":
                    return "问答";
                case "4":
                    return "音乐";
                case "5":
                    return "电影";
                default:
                    return null;
            }
        }
    }


    public static int getContentText() {
        return CONTENT_TEXT;
    }

    public static int getContentMusic() {
        return CONTENT_MUSIC;
    }

    public static int getContentFm() {
        return CONTENT_FM;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDisplay_category() {
        return display_category;
    }

    public void setDisplay_category(String display_category) {
        this.display_category = display_category;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getAudio_platform() {
        return audio_platform;
    }

    public void setAudio_platform(String audio_platform) {
        this.audio_platform = audio_platform;
    }

    public String getStart_video() {
        return start_video;
    }

    public void setStart_video(String start_video) {
        this.start_video = start_video;
    }

    public String getHas_reading() {
        return has_reading;
    }

    public void setHas_reading(String has_reading) {
        this.has_reading = has_reading;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPic_info() {
        return pic_info;
    }

    public void setPic_info(String pic_info) {
        this.pic_info = pic_info;
    }

    public String getWords_info() {
        return words_info;
    }

    public void setWords_info(String words_info) {
        this.words_info = words_info;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
    }

    public String getMovie_story_id() {
        return movie_story_id;
    }

    public void setMovie_story_id(String movie_story_id) {
        this.movie_story_id = movie_story_id;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_pvurl() {
        return ad_pvurl;
    }

    public void setAd_pvurl(String ad_pvurl) {
        this.ad_pvurl = ad_pvurl;
    }

    public String getAd_linkurl() {
        return ad_linkurl;
    }

    public void setAd_linkurl(String ad_linkurl) {
        this.ad_linkurl = ad_linkurl;
    }

    public String getAd_makettime() {
        return ad_makettime;
    }

    public void setAd_makettime(String ad_makettime) {
        this.ad_makettime = ad_makettime;
    }

    public String getAd_closetime() {
        return ad_closetime;
    }

    public void setAd_closetime(String ad_closetime) {
        this.ad_closetime = ad_closetime;
    }

    public String getAd_share_cnt() {
        return ad_share_cnt;
    }

    public void setAd_share_cnt(String ad_share_cnt) {
        this.ad_share_cnt = ad_share_cnt;
    }

    public String getAd_pvurl_vendor() {
        return ad_pvurl_vendor;
    }

    public void setAd_pvurl_vendor(String ad_pvurl_vendor) {
        this.ad_pvurl_vendor = ad_pvurl_vendor;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getContent_bgcolor() {
        return content_bgcolor;
    }

    public void setContent_bgcolor(String content_bgcolor) {
        this.content_bgcolor = content_bgcolor;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public ShareInfo getShare_info() {
        return share_info;
    }

    public void setShare_info(ShareInfo share_info) {
        this.share_info = share_info;
    }

    public List<String> getSerial_list() {
        return serial_list;
    }

    public void setSerial_list(List<String> serial_list) {
        this.serial_list = serial_list;
    }

    public List<TagList> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<TagList> tag_list) {
        this.tag_list = tag_list;
    }

    public String getIs_regular() {
        return is_regular;
    }

    public void setIs_regular(String is_regular) {
        this.is_regular = is_regular;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.contentTag);
        dest.writeString(this.id);
        dest.writeString(this.category);
        dest.writeString(this.display_category);
        dest.writeString(this.item_id);
        dest.writeString(this.title);
        dest.writeString(this.forward);
        dest.writeString(this.img_url);
        dest.writeString(this.like_count);
        dest.writeString(this.post_date);
        dest.writeString(this.last_update_date);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.video_url);
        dest.writeString(this.audio_url);
        dest.writeString(this.audio_platform);
        dest.writeString(this.start_video);
        dest.writeString(this.has_reading);
        dest.writeString(this.volume);
        dest.writeString(this.pic_info);
        dest.writeString(this.words_info);
        dest.writeString(this.subtitle);
        dest.writeString(this.number);
        dest.writeString(this.serial_id);
        dest.writeString(this.movie_story_id);
        dest.writeString(this.ad_id);
        dest.writeString(this.ad_type);
        dest.writeString(this.ad_pvurl);
        dest.writeString(this.ad_linkurl);
        dest.writeString(this.ad_makettime);
        dest.writeString(this.ad_closetime);
        dest.writeString(this.ad_share_cnt);
        dest.writeString(this.ad_pvurl_vendor);
        dest.writeString(this.content_id);
        dest.writeString(this.content_type);
        dest.writeString(this.content_bgcolor);
        dest.writeString(this.share_url);
        dest.writeParcelable(this.share_info, flags);
        dest.writeStringList(this.serial_list);
        dest.writeTypedList(this.tag_list);
        dest.writeString(this.is_regular);
    }

    public ContentItem() {
    }

    protected ContentItem(Parcel in) {
        this.contentTag = in.readInt();
        this.id = in.readString();
        this.category = in.readString();
        this.display_category = in.readString();
        this.item_id = in.readString();
        this.title = in.readString();
        this.forward = in.readString();
        this.img_url = in.readString();
        this.like_count = in.readString();
        this.post_date = in.readString();
        this.last_update_date = in.readString();
        this.author = in.readParcelable(Author.class.getClassLoader());
        this.video_url = in.readString();
        this.audio_url = in.readString();
        this.audio_platform = in.readString();
        this.start_video = in.readString();
        this.has_reading = in.readString();
        this.volume = in.readString();
        this.pic_info = in.readString();
        this.words_info = in.readString();
        this.subtitle = in.readString();
        this.number = in.readString();
        this.serial_id = in.readString();
        this.movie_story_id = in.readString();
        this.ad_id = in.readString();
        this.ad_type = in.readString();
        this.ad_pvurl = in.readString();
        this.ad_linkurl = in.readString();
        this.ad_makettime = in.readString();
        this.ad_closetime = in.readString();
        this.ad_share_cnt = in.readString();
        this.ad_pvurl_vendor = in.readString();
        this.content_id = in.readString();
        this.content_type = in.readString();
        this.content_bgcolor = in.readString();
        this.share_url = in.readString();
        this.share_info = in.readParcelable(ShareInfo.class.getClassLoader());
        this.serial_list = in.createStringArrayList();
        this.tag_list = in.createTypedArrayList(TagList.CREATOR);
        this.is_regular = in.readString();
    }

    public static final Parcelable.Creator<ContentItem> CREATOR = new Parcelable.Creator<ContentItem>() {
        @Override
        public ContentItem createFromParcel(Parcel source) {
            return new ContentItem(source);
        }

        @Override
        public ContentItem[] newArray(int size) {
            return new ContentItem[size];
        }
    };
}
