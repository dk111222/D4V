package com.tvc.network.response;

import com.tvc.network.bean.ArtistBean;

import java.util.List;

/**
 * 演员列表
 */
public class ArtistsResponse extends BaseResponse {

    private List<ArtistBean> data;

    public List<ArtistBean> getData() {
        return data;
    }

    public void setData(List<ArtistBean> data) {
        this.data = data;
    }
}