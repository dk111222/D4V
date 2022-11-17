package com.tvc.network.response;

import com.tvc.network.bean.AdBean;
import com.tvc.network.bean.LatestAppBean;

public class StartResponse extends BaseResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public AdBean getAd() {
        return data == null ? null : data.ad;
    }

    public String[] getLines() {
        return data == null ? null : data.lines;
    }

    public LatestAppBean getAppInfo() {
        return data == null ? null : data.appInfo;
    }

    public static class Data {
        private AdBean ad;
        private String[] lines;
        private LatestAppBean appInfo;

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public String[] getLines() {
            return lines;
        }

        public void setLines(String[] lines) {
            this.lines = lines;
        }

        public LatestAppBean getAppInfo() {
            return appInfo;
        }

        public void setAppInfo(LatestAppBean appInfo) {
            this.appInfo = appInfo;
        }
    }
}
