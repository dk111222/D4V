package com.tvc.network.response;

import com.tvc.network.bean.ArtistWithVideosBean;
import com.tvc.network.bean.VideoActionBean;
import com.tvc.network.bean.VideoBean;

import java.util.List;

/**
 * 视频观看页面所有详情信息
 */
public class VideoDetailsResponse extends BaseResponse {

    private VideoDetailsData data;

    public VideoDetailsData getData() {
        return data;
    }

    public void setData(VideoDetailsData data) {
        this.data = data;
    }

    public static class VideoDetailsData{
        private List<VideoBean> recommendStreams;
        private List<ArtistWithVideosBean> actressStreams;
        private List<VideoActionBean> actions;

        public List<VideoBean> getRecommendStreams() {
            return recommendStreams;
        }

        public void setRecommendStreams(List<VideoBean> recommendStreams) {
            this.recommendStreams = recommendStreams;
        }

        public List<ArtistWithVideosBean> getActressStreams() {
            return actressStreams;
        }

        public void setActressStreams(List<ArtistWithVideosBean> actressStreams) {
            this.actressStreams = actressStreams;
        }

        public List<VideoActionBean> getActions() {
            return actions;
        }

        public void setActions(List<VideoActionBean> actions) {
            this.actions = actions;
        }
    }

}
