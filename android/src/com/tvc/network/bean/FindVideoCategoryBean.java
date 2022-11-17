package com.tvc.network.bean;

import java.io.Serializable;

/**
 * 找片，5个分类信息
 */
public class FindVideoCategoryBean implements Serializable {
    private static final long serialVersionUID = 5461344781588144485L;

    private String sn;
    private String imageUrl;
    private String values;
    private String en_values;
    private String name;
    private String en_name;
    private int sort;

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

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getEn_values() {
        return en_values;
    }

    public void setEn_values(String en_values) {
        this.en_values = en_values;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

}
