package com.tvc.network.bean;

import java.util.List;

/**
 * 一个演员 + 最多三个视频 Bean
 */
public class ArtistWithVideosBean {
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
