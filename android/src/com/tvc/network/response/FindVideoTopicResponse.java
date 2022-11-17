package com.tvc.network.response;


import com.tvc.network.bean.VideoBean;

import java.util.List;

/**
 * 找片-演员信息
 */
public class FindVideoTopicResponse extends BaseResponse {

    private List<TopicData> data;

    public List<TopicData> getData() {
        return data;
    }

    public void setData(List<TopicData> data) {
        this.data = data;
    }

    public static class TopicData {
        private List<VideoBean> streams;
        private Topic topic;

        public List<VideoBean> getStreams() {
            return streams;
        }

        public void setStreams(List<VideoBean> streams) {
            this.streams = streams;
        }

        public Topic getTopic() {
            return topic;
        }

        public void setTopic(Topic topic) {
            this.topic = topic;
        }
    }

    /**
     * 找片-演员信息
     */
    public static class Topic {
        private String name;
        private String en_name;
        private String sn;
        private String sort;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEn_name() {
            return en_name;
        }

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
