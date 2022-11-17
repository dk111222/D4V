package com.tvc.network.response;

import com.tvc.network.bean.ArtistBean;
import com.tvc.network.bean.VideoBean;

import java.util.List;

/**
 * 演员详情
 */
public class ArtistDetailResponse extends BaseResponse {

    private ArtistDetailInner data;

    public ArtistDetailInner getData() {
        return data;
    }

    public void setData(ArtistDetailInner data) {
        this.data = data;
    }

    public static class  ArtistDetailInner {

        private ArtistBean actress;
        private List<VideoBean> streams;

        public ArtistBean getActress() {
            return actress;
        }

        public void setActress(ArtistBean actress) {
            this.actress = actress;
        }

        public List<VideoBean> getStreams() {
            return streams;
        }

        public void setStreams(List<VideoBean> streams) {
            this.streams = streams;
        }
    }
}
