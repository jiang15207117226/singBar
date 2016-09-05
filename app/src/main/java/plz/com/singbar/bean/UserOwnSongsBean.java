package plz.com.singbar.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/31.
 */
public class UserOwnSongsBean implements Serializable{
    private int id;             //id
    private String songName;    //歌曲名
    private int flowers;        //收花数
    private int comments;       //评论数
    private int trys;           //试听数
    private String voiceUrl;    //歌曲地址
    private String time;        //时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getFlowers() {
        return flowers;
    }

    public void setFlowers(int flowers) {
        this.flowers = flowers;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getTrys() {
        return trys;
    }

    public void setTrys(int trys) {
        this.trys = trys;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
