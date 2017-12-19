package com.lzx.onematerial.entity.day;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lizhenxin on 17-11-14.
 */

public class Icons implements Parcelable {
    private String day;
    private String night;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.day);
        dest.writeString(this.night);
    }

    public Icons() {
    }

    protected Icons(Parcel in) {
        this.day = in.readString();
        this.night = in.readString();
    }

    public static final Parcelable.Creator<Icons> CREATOR = new Parcelable.Creator<Icons>() {
        @Override
        public Icons createFromParcel(Parcel source) {
            return new Icons(source);
        }

        @Override
        public Icons[] newArray(int size) {
            return new Icons[size];
        }
    };
}
