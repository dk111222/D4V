package com.tvc.network.response;

import com.tvc.network.bean.TagBean;

import java.util.List;

/**
 * 演员列表
 */
public class TagsResponse extends BaseResponse {

    private List<TagBean> data;

    public List<TagBean> getData() {
        return data;
    }

    public void setData(List<TagBean> data) {
        this.data = data;
    }
}
