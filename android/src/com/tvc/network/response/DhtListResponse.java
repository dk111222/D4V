package com.tvc.network.response;

import com.tvc.network.bean.ArtistBean;

import java.util.List;

public class DhtListResponse {
    private int code;
    private List<DhtData> data;
    private int total;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DhtData> getData() {
        return data;
    }

    public void setData(List<DhtData> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
