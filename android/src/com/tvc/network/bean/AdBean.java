package com.tvc.network.bean;

/**
 * 广告信息
 */
public class AdBean {
    //    @Id
    //    @API(value = "sn", readonly = true)
    private String sn;

    //    @API(value = "广告位")
    private String adpid;

    //    @API(value = "url")
    private String url;

    //    @API(value = "imageUrl")
    private String imageUrl;

    //    @API(value = "action")
    private String action;

    //    @API(value = "title", visible = true, search = true)
    private String title;

    //    @API(value = "type", search = true)
    private String type;

    //    @API(value = "desc", search = true)
    private String desc;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getAdpid() {
        return adpid;
    }

    public void setAdpid(String adpid) {
        this.adpid = adpid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
