package com.tvc.network.response;

import com.tvc.network.bean.SearchTagDiscoverBean;

import java.util.List;

/**
 * 搜索-discover
 */
public class SearchTagDiscoverResponse extends BaseResponse {
    private List<SearchTagDiscoverBean> data;

    public List<SearchTagDiscoverBean> getData() {
        return data;
    }

    public void setData(List<SearchTagDiscoverBean> data) {
        this.data = data;
    }
}
