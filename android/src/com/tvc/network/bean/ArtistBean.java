package com.tvc.network.bean;

import java.io.Serializable;

/**
 * 演员信息
 */
public class ArtistBean implements Serializable {

    private String sn;
    //    @API(value = "图片")
    private String imageUrl;

    //    @API(value = "头像")
    private String avatar;

    //    @API(value = "排序", visible = true)
    private int sort;

    //    @API(value = "名称", visible = true, search = true)
    private String name;

    //    @API(value = "数量", visible = true)
    private int count = -1;

    //    @API(value = "中文名", visible = true, search = true)
    private String cn_name;

    //    @API(value = "英文名", visible = true, search = true)
    private String en_name;

    //    @API(value = "身高")
    private String height;
    //    @API(value = "三维")
    private String bwh;
    //    @API(value = "罩杯")
    private String bra;
    //    @API(value = "生日")
    private String birthday;
    //    @API(value = "年龄")
    private String age;
    //    @API(value = "出生地")
    private String city;
    //    @API(value = "血型")
    private String blood;
    // 代替 height,bwh,bra... 服务器直接返回详情信息
    private String info;

    private int heart = -1; // 收藏的个数

    private int favor = 1;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCn_name() {
        return cn_name;
    }

    public void setCn_name(String cn_name) {
        this.cn_name = cn_name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBwh() {
        return bwh;
    }

    public void setBwh(String bwh) {
        this.bwh = bwh;
    }

    public String getBra() {
        return bra;
    }

    public void setBra(String bra) {
        this.bra = bra;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }
}
