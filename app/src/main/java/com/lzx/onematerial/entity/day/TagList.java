package com.lzx.onematerial.entity.day;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lizhe on 2017/4/23.
 */

public class TagList implements Parcelable {
    private String id;
    private String title;

    public String getId(){return id;}
    public String getTitle(){return title;}

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
    }

    public TagList() {
    }

    protected TagList(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<TagList> CREATOR = new Parcelable.Creator<TagList>() {
        @Override
        public TagList createFromParcel(Parcel source) {
            return new TagList(source);
        }

        @Override
        public TagList[] newArray(int size) {
            return new TagList[size];
        }
    };
}
