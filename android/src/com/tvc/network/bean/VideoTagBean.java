package com.tvc.network.bean;

import java.io.Serializable;

public class VideoTagBean implements Serializable {
    private int sId;       // 服务端id
    private String tag;      // 类型
    private String desc;          // 描述
    private String iconUrl;       // 图标
    private int favor;            // 标记为喜欢
    private int videoCount;       // 视频总数

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }


}
