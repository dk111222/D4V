package com.tvc.network.bean;

/**
 * 视频信息
 */
public class VideoBean {

    //    @API(value = "sn", readonly = true)
    private String sn;

    //    @API(value = "排序", visible = true)
    private int sort;

    //    @API(value = "女优", visible = true, search = true)
    private String actress;

    private String en_actress;

    //    @API(value = "标签", visible = true, search = true)
    private String tags;

    //    @API(value = "系列", visible = true, search = true)
    private String series;

    //    @API(value = "番号", visible = true, search = true)
    private String slogan;

    //    @API(value = "标题", visible = true, search = true)
    private String title;
    private String en_title;

    //    @API(value = "播放量", visible = true)
    private String count;

    //    @API(value = "封面")
    private String imageUrl;

    //    @API(value = "视频地址")
    private String url;

    private int favor;

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

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }

    public String getEn_actress() {
        return en_actress;
    }

    public void setEn_actress(String en_actress) {
        this.en_actress = en_actress;
    }

    public String getEn_title() {
        return en_title;
    }

    public void setEn_title(String en_title) {
        this.en_title = en_title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }
}
