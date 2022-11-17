package com.tvc.network.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 视频信息
 */
public class TestVideoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String origin_title;
    private String src_url;
    private String thumbnail;
    private String sn;
    private List<String> dmm;
    private String slogan;

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public List<String> getDmm() {
        return dmm;
    }

    public void setDmm(List<String> dmm) {
        this.dmm = dmm;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("origin_title:").append(origin_title).append(" thumb:").append(thumbnail).append(" video:").append(src_url);
        return sb.toString();
    }
}
