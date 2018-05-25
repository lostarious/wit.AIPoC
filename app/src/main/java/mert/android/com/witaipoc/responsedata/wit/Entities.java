package mert.android.com.witaipoc.responsedata.wit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Entities {

    @SerializedName("hava_durumu")
    private List<HavaDurumuItem> havaDurumu;

    @SerializedName("location")
    private List<LocationItem> location;

    @SerializedName("time")
    private List<TimeItem> time;

    public List<HavaDurumuItem> getHavaDurumu() {
        return havaDurumu;
    }

    public void setHavaDurumu(List<HavaDurumuItem> havaDurumu) {
        this.havaDurumu = havaDurumu;
    }

    public List<LocationItem> getLocation() {
        return location;
    }

    public void setLocation(List<LocationItem> location) {
        this.location = location;
    }

    public List<TimeItem> getTime() {
        return time;
    }

    public void setTime(List<TimeItem> time) {
        this.time = time;
    }
}