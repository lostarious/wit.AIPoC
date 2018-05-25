package mert.android.com.witaipoc.responsedata.wit;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class WitResponse {

    @SerializedName("entities")
    private Entities entities;

    @SerializedName("_text")
    private String text;

    @SerializedName("msg_id")
    private String msgId;

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}