package com.tvc.network.response;

import com.tvc.network.bean.FindVideoCategoryBean;

import java.util.List;

/**
 * 找片：大分类
 */
public class FindVideoCategoriesResponse extends BaseResponse {

    private List<FindVideoCategoryBean> data;

    public List<FindVideoCategoryBean> getData() {
        return data;
    }

    public void setData(List<FindVideoCategoryBean> data) {
        this.data = data;
    }

}
