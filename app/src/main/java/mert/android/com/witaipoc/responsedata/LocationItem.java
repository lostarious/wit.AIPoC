package mert.android.com.witaipoc.responsedata;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LocationItem{

	@SerializedName("suggested")
	private boolean suggested;

	@SerializedName("confidence")
	private double confidence;

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private String value;

	public void setSuggested(boolean suggested){
		this.suggested = suggested;
	}

	public boolean isSuggested(){
		return suggested;
	}

	public void setConfidence(double confidence){
		this.confidence = confidence;
	}

	public double getConfidence(){
		return confidence;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}
}