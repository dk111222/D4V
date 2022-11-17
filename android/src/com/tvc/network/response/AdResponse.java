package com.tvc.network.response;

import com.tvc.network.bean.AdBean;

import java.util.List;

/**
 * 广告列表
 */
public class AdResponse extends BaseResponse {

    private List<AdBean> data;

    public List<AdBean> getData() {
        return data;
    }

    public void setData(List<AdBean> data) {
        this.data = data;
    }
}