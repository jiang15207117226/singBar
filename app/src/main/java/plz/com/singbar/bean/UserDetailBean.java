package plz.com.singbar.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/5.
 */
public class UserDetailBean extends DataSupport implements Serializable{
    private int id;
    private int age;                //年龄
    private String constellation;   //星座
    private String birthday;        //生日
    private String stadius;         //情感状态
    private int height;             //身高
    private String profression;     //职业
    private String degree;          //学历
    private String homeCountry;     //家乡
    private String nowLiving;       //现居地


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStadius() {
        return stadius;
    }

    public void setStadius(String stadius) {
        this.stadius = stadius;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getProfression() {
        return profression;
    }

    public void setProfression(String profression) {
        this.profression = profression;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }

    public String getNowLiving() {
        return nowLiving;
    }

    public void setNowLiving(String nowLiving) {
        this.nowLiving = nowLiving;
    }
}
