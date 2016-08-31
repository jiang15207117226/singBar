package plz.com.singbar.bean;

import org.litepal.crud.DataSupport;

/**
 * plz.com.singbar.bean.UserBean
 * Created by Administrator on 2016/8/29.
 */
public class UserBean extends DataSupport {
    private int id;
    private String account;
    private String pw;
    private long phone;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
