package com.tvc.network.bean;

public class AlertMsgBean {
    private String sn;
    private String message;
    private String en_message;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMessage() {
        return message;
    }

    public String getEn_message() {
        return en_message;
    }

    public void setEn_message(String en_message) {
        this.en_message = en_message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
