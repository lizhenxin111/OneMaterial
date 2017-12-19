package com.lzx.onematerial.entity.day;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lizhenxin on 17-11-14.
 */

public class Weather implements Parcelable {

    private String city_name;
    private String date;
    private String temperature;
    private String humidity;
    private String climate;
    private String wind_direction;
    private String hurricane;
    private Icons icons;

    public Icons getIcons() {
        return icons;
    }

    public void setIcons(Icons icons) {
        this.icons = icons;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getHurricane() {
        return hurricane;
    }

    public void setHurricane(String hurricane) {
        this.hurricane = hurricane;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city_name);
        dest.writeString(this.date);
        dest.writeString(this.temperature);
        dest.writeString(this.humidity);
        dest.writeString(this.climate);
        dest.writeString(this.wind_direction);
        dest.writeString(this.hurricane);
        dest.writeParcelable(this.icons, flags);
    }

    public Weather() {
    }

    protected Weather(Parcel in) {
        this.city_name = in.readString();
        this.date = in.readString();
        this.temperature = in.readString();
        this.humidity = in.readString();
        this.climate = in.readString();
        this.wind_direction = in.readString();
        this.hurricane = in.readString();
        this.icons = in.readParcelable(Icons.class.getClassLoader());
    }

    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}
