package com.tvc.network.bean;

import java.io.Serializable;

/**
 * 用户信息
 */
public class UserInfoBean implements Serializable {

    private String country;
    private String name;
    private String phone;
    private String sex;
    private String age;

    private int vip = 0;  // 会员等级 普通，月，季度，年，永久

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoneWithCountry() {
        return country + phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }
}
