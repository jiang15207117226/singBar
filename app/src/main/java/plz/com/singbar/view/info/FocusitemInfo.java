package plz.com.singbar.view.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/29.
 */
public class FocusitemInfo implements Serializable{
    private int head;
    private String name;
    private String singname;
    private String time;
    private int singnum;
    private int comment;
    private int flower;
    private String voiceUrl;

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSingname() {
        return singname;
    }

    public void setSingname(String singname) {
        this.singname = singname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSingnum() {
        return singnum;
    }

    public void setSingnum(int singnum) {
        this.singnum = singnum;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getFlower() {
        return flower;
    }

    public void setFlower(int flower) {
        this.flower = flower;
    }
}
