package com.tvc.network.response;

import com.tvc.network.bean.VideoBean;

import java.util.List;

/**
 * 视频列表
 */
public class VideoResponse extends BaseResponse {

    private List<VideoBean> data;

    public List<VideoBean> getData() {
        return data;
    }

    public void setData(List<VideoBean> data) {
        this.data = data;
    }
}
