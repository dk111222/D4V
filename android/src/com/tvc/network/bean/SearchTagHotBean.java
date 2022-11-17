package com.tvc.network.bean;

/**
 * 广告信息
 */
public class SearchTagHotBean {

    private String tab;
    private String values;
    private String en_values;
    private String sn;
    private int sort;

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
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

    public String getEn_values() {
        return en_values;
    }

    public void setEn_values(String en_values) {
        this.en_values = en_values;
    }
}
