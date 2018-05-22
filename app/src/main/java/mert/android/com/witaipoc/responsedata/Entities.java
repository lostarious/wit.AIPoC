package mert.android.com.witaipoc.responsedata;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Entities{

	@SerializedName("location")
	private List<LocationItem> location;

	@SerializedName("time")
	private List<TimeItem> time;

	@SerializedName("intent")
	private List<IntentItem> intent;

	public void setLocation(List<LocationItem> location){
		this.location = location;
	}

	public List<LocationItem> getLocation(){
		return location;
	}

	public void setTime(List<TimeItem> time){
		this.time = time;
	}

	public List<TimeItem> getTime(){
		return time;
	}

	public void setIntent(List<IntentItem> intent){
		this.intent = intent;
	}

	public List<IntentItem> getIntent(){
		return intent;
	}
}