package plz.com.singbar.bean;

import org.litepal.crud.DataSupport;

/**plz.com.singbar.bean.UserBean
 * Created by Administrator on 2016/8/29.
 */
public class UserBean extends DataSupport {
    private int id;
    private String account;
    private String pw;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
