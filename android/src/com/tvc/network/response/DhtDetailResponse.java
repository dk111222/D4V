package com.tvc.network.response;

public class DhtDetailResponse {
    private int code;
    private DhtData data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DhtData getData() {
        return data;
    }

    public void setData(DhtData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
