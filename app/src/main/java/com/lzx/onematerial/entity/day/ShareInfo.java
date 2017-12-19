package com.lzx.onematerial.entity.day;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lizhenxin on 17-11-14.
 */

public class ShareInfo implements Parcelable {

    private String url;
    private String image;
    private String title;
    private String content;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.image);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public ShareInfo() {
    }

    protected ShareInfo(Parcel in) {
        this.url = in.readString();
        this.image = in.readString();
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<ShareInfo> CREATOR = new Parcelable.Creator<ShareInfo>() {
        @Override
        public ShareInfo createFromParcel(Parcel source) {
            return new ShareInfo(source);
        }

        @Override
        public ShareInfo[] newArray(int size) {
            return new ShareInfo[size];
        }
    };
}
