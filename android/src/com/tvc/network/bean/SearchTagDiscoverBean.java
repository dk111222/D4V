package com.tvc.network.bean;

/**
 * 搜索-发现： 广告信息
 */
public class SearchTagDiscoverBean {

    private String name;
    private String en_name;
    private String value;
    private String en_value;
    private String sn;
    private int sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEn_value() {
        return en_value;
    }

    public void setEn_value(String en_value) {
        this.en_value = en_value;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
