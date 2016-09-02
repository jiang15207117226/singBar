package plz.com.singbar.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/9/1.
 */
public class MyAttenBean extends DataSupport {
    private int id;
    private String myAccount;
    private String accountBy;
    private boolean isAttenMe;
    private UserBean userBean;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMyAccount() {
        return myAccount;
    }

    public void setMyAccount(String myAccount) {
        this.myAccount = myAccount;
    }

    public String getAccountBy() {
        return accountBy;
    }

    public void setAccountBy(String accountBy) {
        this.accountBy = accountBy;
    }

    public boolean isAttenMe() {
        return isAttenMe;
    }

    public void setAttenMe(boolean attenMe) {
        isAttenMe = attenMe;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
