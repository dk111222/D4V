package com.tvc.network.interactor;


import com.tvc.network.Service3rdGenerator;
import com.tvc.network.api.Video3rdService;
import com.tvc.network.response.PlayUrlResponse;

import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 用于观看页面，获取三方平台的视频播放地址信息
 */
public class Video3rdInteractor {

    private static Video3rdInteractor sInstance;

    public static Video3rdInteractor getInstance() {
        if (sInstance == null) {
            sInstance = new Video3rdInteractor();
        }
        return sInstance;
    }

    private Video3rdInteractor() {
    }

    public Observable<PlayUrlResponse> getVideoUrls(String watchUrl) {
        String host = Service3rdGenerator.getHost(watchUrl);
        String id = Service3rdGenerator.getId(watchUrl);
        JSONObject requestData = new JSONObject();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return Service3rdGenerator.createVideo3rService(Video3rdService.class, host).getVideoUrls(requestBody, id);
    }
}
