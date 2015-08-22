package com.springapp.mvc.entity;

/**
 * Created by think on 2015/8/9.
 */
public class UserInfo {
    private String  userNum;
    private String  password;
    private String  nickName;
    private String  headImg;
    private String  sex;
    private String  birthday;
    private String  city;
    private String  realName;
    private String  email;
    private String  registerTime;

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setHeadimg(String headimg) {
        this.headImg = headimg;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRealname(String realname) {
        this.realName = realname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUserNum() {

        return userNum;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public String getHeadimg() {
        return headImg;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCity() {
        return city;
    }

    public String getRealname() {
        return realName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public UserInfo(String userNum, String password, String nickName, String headimg, String sex, String birthday, String city, String realname, String email, String registerTime) {

        this.userNum = userNum;
        this.password = password;
        this.nickName = nickName;
        this.headImg = headimg;
        this.sex = sex;
        this.birthday = birthday;
        this.city = city;
        this.realName = realname;
        this.email = email;
        this.registerTime = registerTime;
    }




}
