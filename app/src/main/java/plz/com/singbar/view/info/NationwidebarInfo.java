package plz.com.singbar.view.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NationwidebarInfo implements Serializable {

    private String goldMedal;//金牌
    private String silverMedal;//银牌
    private String coppermedal;//铜牌
    private String headPortraits;//头像
    private String userName;//名字
    private String singname;//歌曲名
    private int audition;//试听量



    public String getHeadPortraits() {
        return headPortraits;
    }

    public void setHeadPortraits(String headPortraits) {
        this.headPortraits = headPortraits;
    }


    public String getGoldMedal() {
        return goldMedal;
    }

    public void setGoldMedal(String goldMedal) {
        this.goldMedal = goldMedal;
    }

    public String getSilverMedal() {
        return silverMedal;
    }

    public void setSilverMedal(String silverMedal) {
        this.silverMedal = silverMedal;
    }

    public String getCoppermedal() {
        return coppermedal;
    }

    public void setCoppermedal(String coppermedal) {
        this.coppermedal = coppermedal;
    }

    public String getSingname() {
        return singname;
    }

    public void setSingname(String singname) {
        this.singname = singname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAudition() {
        return audition;
    }

    public void setAudition(int audition) {
        this.audition = audition;
    }
}
