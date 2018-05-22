package mert.android.com.witaipoc.responsedata.wit;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class WitResponse{

	@SerializedName("entities")
	private Entities entities;

	@SerializedName("_text")
	private String text;

	@SerializedName("msg_id")
	private String msgId;

	public void setEntities(Entities entities){
		this.entities = entities;
	}

	public Entities getEntities(){
		return entities;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setMsgId(String msgId){
		this.msgId = msgId;
	}

	public String getMsgId(){
		return msgId;
	}
}