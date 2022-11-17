package com.tvc.network.response;

import com.tvc.network.bean.PlayUrlBean;

import java.util.List;

/**
 * 登陆
 */
public class PlayUrlResponse extends BaseResponse {

    private List<PlayUrlBean> data;

    public List<PlayUrlBean> getData() {
        return data;
    }

    public void setData(List<PlayUrlBean> data) {
        this.data = data;
    }
}
