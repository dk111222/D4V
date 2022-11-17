package com.tvc.network.response;

import com.tvc.network.bean.SearchTagHotBean;

import java.util.List;

/**
 * 热搜
 */
public class SearchTagHotResponse extends BaseResponse {
    private List<SearchTagHotBean> data;

    public List<SearchTagHotBean> getData() {
        return data;
    }

    public void setData(List<SearchTagHotBean> data) {
        this.data = data;
    }
}
