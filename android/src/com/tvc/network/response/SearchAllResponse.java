package com.tvc.network.response;

import com.tvc.network.bean.ArtistWithVideosBean;
import com.tvc.network.bean.VideoBean;

import java.util.List;

/**
 * 搜索全部
 */
public class SearchAllResponse extends BaseResponse {
    private SearchAllData data;

    public SearchAllData getData() {
        return data;
    }

    public void setData(SearchAllData data) {
        this.data = data;
    }

    public static class SearchAllData {
        private List<ArtistWithVideosBean> actress;

        private List<VideoBean> stream;

        public List<ArtistWithVideosBean> getActress() {
            return actress;
        }

        public void setActress(List<ArtistWithVideosBean> actress) {
            this.actress = actress;
        }

        public List<VideoBean> getStream() {
            return stream;
        }

        public void setStream(List<VideoBean> stream) {
            this.stream = stream;
        }
    }

}