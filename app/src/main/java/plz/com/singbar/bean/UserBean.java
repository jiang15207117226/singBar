package plz.com.singbar.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * plz.com.singbar.bean.UserBean
 * Created by Administrator on 2016/8/29.
 */
public class UserBean extends DataSupport implements Serializable {
    private int id;             //id
    private String head;        //头像
    private String sign;        //签名
    private String account;     //麦吧账号
    private String petName;     //昵称
    private String pw;          //密码
    private long phone;         //手机号
    private int flowers;        //收到的总花数
    private int listenCount;    //听众
    private String time;        //时间
    private String butility;    //称号
    private int attentionCount; //关注数
    private int fansCount;      //粉丝数
    private List<UserOwnSongsBean> userOwnSongsBeen = new ArrayList<>();
    private List<AttenBean> attenBeen = new ArrayList<>();
    private int userOwnSongsCount;
    private UserDetailBean userDetailBean;

    public UserDetailBean getUserDetailBean() {
        return userDetailBean;
    }

    public void setUserDetailBean(UserDetailBean userDetailBean) {
        this.userDetailBean = userDetailBean;
    }

    public List<AttenBean> getAttenBeen() {
        return attenBeen;
    }

    public void setAttenBeen(List<AttenBean> attenBeen) {
        this.attenBeen = attenBeen;
    }

    public int getUserOwnSongsCount() {
        return userOwnSongsCount;
    }

    public void setUserOwnSongsCount(int userOwnSongsCount) {
        this.userOwnSongsCount = userOwnSongsCount;
    }

    public List<UserOwnSongsBean> getUserOwnSongsBeen() {
        return userOwnSongsBeen;
    }

    public void setUserOwnSongsBeen(List<UserOwnSongsBean> userOwnSongsBeen) {
        this.userOwnSongsBeen = userOwnSongsBeen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
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

    public int getFlowers() {
        return flowers;
    }

    public void setFlowers(int flowers) {
        this.flowers = flowers;
    }

    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
    }

    public String getButility() {
        return butility;
    }

    public void setButility(String butility) {
        this.butility = butility;
    }

    public int getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(int attentionCount) {
        this.attentionCount = attentionCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }


}
