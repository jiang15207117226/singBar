package plz.com.singbar.view.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class DgGxInfo implements Serializable{
    private String ima;
    private String singname;
    private String singername;
    private String playurl;

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }

    public String getSingname() {
        return singname;
    }

    public void setSingname(String singname) {
        this.singname = singname;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public String getSingdx() {
        return singdx;
    }

    public void setSingdx(String singdx) {
        this.singdx = singdx;
    }

    private String singdx;
}
