package com.tvc.network.api;

import com.tvc.network.UrlConstants;
import com.tvc.network.response.PlayUrlResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Video3rdService {


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_VIDEO_SOURCE)
    Observable<PlayUrlResponse> getVideoUrls(@Body RequestBody requestBody, @Path("ID") String id);
}
