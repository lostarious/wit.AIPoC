package mert.android.com.witaipoc.responsedata.wit;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class HavaDurumuItem {

    @SerializedName("confidence")
    private double confidence;

    @SerializedName("value")
    private String value;

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}