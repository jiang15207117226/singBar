package plz.com.singbar.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/9/1.
 */
public class AttenBean extends DataSupport {
    private int id;
    private String head;
    private int attenUserId;
    private int userId;
    private boolean stadus;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttenUserId() {
        return attenUserId;
    }

    public void setAttenUserId(int attenUserId) {
        this.attenUserId = attenUserId;
    }

    public boolean isStadus() {
        return stadus;
    }

    public void setStadus(boolean stadus) {
        this.stadus = stadus;
    }
}
