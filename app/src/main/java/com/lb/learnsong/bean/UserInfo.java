package com.lb.learnsong.bean;

public class UserInfo {
    //"headimg": "",头像地址 <string>
//"phone": "",手机号 <string>
//"nickName": "",昵称 <string>
//"userDesc": "",简介 <string>
//"userName": "",用户名 <string>
//"email": "xufangsheng10835@163.com"邮箱地址 <string>


    private String phone;
    private String headimg;
    private String userName;
    private String userDesc;
    private String nickName;
    private String emailc;

    public String getPhone() {
        return phone;
    }

    public String getHeadimg() {
        return headimg;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public String getEmailc() {
        return emailc;
    }

    public String getNickname() {
        return nickName;
    }
}
