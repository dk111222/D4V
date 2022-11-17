package com.tvc.network.api;

import com.tvc.network.response.DhtListResponse;
import com.tvc.network.response.DhtDetailResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DhtService {

//    @GET("News")
//    Call<NewsBean> getItem(@Query("newsId") String newsId);
    @Headers({"Content-Type: application/json;charset=UTF-8", "whitelist:spider_man"})
    @GET("/api/dht/btHashList")
    Observable<DhtListResponse> btHashList(@Query("pageNo")int pageNo, @Query("pageSize")int pageSize);


    @Headers({"Content-Type: application/json;charset=UTF-8", "whitelist:spider_man"})
    @GET("/api/dht/hashDetail")
    Observable<DhtDetailResponse> hashDetail(@Query("id")  String id);

    @Headers({"Content-Type: application/json;charset=UTF-8", "whitelist:spider_man"})
    @GET("/api/dht/search")
    Observable<DhtListResponse> search(@Query("keyword")String keyword, @Query("pageNo")int pageNo, @Query("pageSize")int pageSize);

}
