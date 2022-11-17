package com.tvc.network.response;

import com.tvc.network.bean.AlertMsgBean;

import java.util.List;

public class TResponse extends BaseResponse{

    private DataInner data;

    public DataInner getData() {
        return data;
    }

    public void setData(DataInner data) {
        this.data = data;
    }

    public static class DataInner {
        private String link;    // 分享时候的地址
        private String apkUrl;  // 升级时候的apk地址
        private int update;
        private int version;
        private String desc;
        private String en_desc;
        private String token;

        private List<AlertMsgBean> alerts;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getUpdate() {
            return update;
        }

        public void setUpdate(int update) {
            this.update = update;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEn_desc() {
            return en_desc;
        }

        public void setEn_desc(String en_desc) {
            this.en_desc = en_desc;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<AlertMsgBean> getAlerts() {
            return alerts;
        }

        public void setAlerts(List<AlertMsgBean> alerts) {
            this.alerts = alerts;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }
    }
}
