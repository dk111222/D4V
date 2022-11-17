package com.tvc.network.api;

import com.tvc.network.UrlConstants;
import com.tvc.network.response.AdResponse;
import com.tvc.network.response.ArtistDetailResponse;
import com.tvc.network.response.ArtistsResponse;
import com.tvc.network.response.BaseResponse;
import com.tvc.network.response.FindVideoCategoriesResponse;
import com.tvc.network.response.FindVideoTopicResponse;
import com.tvc.network.response.LoginResponse;
import com.tvc.network.response.PaymentResponse;
import com.tvc.network.response.SearchAllResponse;
import com.tvc.network.response.SearchTagDiscoverResponse;
import com.tvc.network.response.SearchTagHotResponse;
import com.tvc.network.response.TagsResponse;
import com.tvc.network.response.TResponse;
import com.tvc.network.response.VideoDetailsResponse;
import com.tvc.network.response.VideoResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AppService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.API_TOKEN)
    Observable<TResponse> getToken(@Body RequestBody requestBody);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.API_APP_UPGRADE)
    Observable<BaseResponse> getAppVersion(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.API_AD_LIST)
    Observable<AdResponse> getAds(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.API_AD_REPORT)
    Observable<BaseResponse> reportAdAction(@Body RequestBody requestBody);

    // -------------- 用户 --------------
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.REGISTER)
    Observable<LoginResponse> register(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.LOGIN)
    Observable<LoginResponse> login(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.ACTIVE_INVITE_CODE)
    Observable<BaseResponse> activeInviteCode(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.OTP)
    Observable<BaseResponse> otp(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.PAYMENT)
    Observable<PaymentResponse> payment(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.FORGOT)
    Observable<BaseResponse> forgot(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.LOGOUT)
    Observable<BaseResponse> logout(@Body RequestBody requestBody);

    // =============== 视频和演员 ==================

    // ----- 选择喜欢的标签 ----------

    /**
     * 选择标签-未选-可上下滑动
     * 选择你喜欢的类型
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_ALL_TAGS)
    Observable<TagsResponse> getAllTags(@Body RequestBody requestBody);

    /**
     * 选择标签-未选-可上下滑动
     * 已选择 凌辱 人妻 犯罪
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.SET_FAVOR_TAGS)
    Observable<BaseResponse> setFavorTags(@Body RequestBody requestBody);

    // ----- HOME ----------

    /**
     * 首页-圆角
     * 推荐
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_RECOMMEND_VIDOES)
    Observable<VideoResponse> getHomeRecommendVideos(@Body RequestBody requestBody);

    /**
     * 热门
     * 首页热门视频列表
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_HOT_VIDEOS)
    Observable<VideoResponse> getHomeHotVideos(@Body RequestBody requestBody);

    /**
     * 播放排行
     * 首页播放排名视频列表
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_TOP_WATCH_VIDEOS)
    Observable<VideoResponse> getTopWatchVideos(@Body RequestBody requestBody);

    /**
     * 点赞榜单
     * 首页点赞排名视频列表
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_TOP_ZAN_VIDEOS)
    Observable<VideoResponse> getTopZanVideos(@Body RequestBody requestBody);

    /**
     * 首页收藏排名视频列表
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_TOP_FAV_VIDEOS)
    Observable<VideoResponse> getTopFavorVideos(@Body RequestBody requestBody);

    // ---- 找片 -------

    /**
     * 找片-影片-已登录用户
     * <p>
     * 找片大分类，其中values就是标签，英文逗号,分割。
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.FIND_VIDEO_CATEGORIES)
    Observable<FindVideoCategoriesResponse> getFindVideoCategories(@Body RequestBody requestBody);

    /**
     * 找片-影片-已登录用户
     * <p>
     * 找片大分类，其中values就是标签，英文逗号,分割。
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.FIND_GUESS_VIDEOS)
    Observable<VideoResponse> getGuessVideos(@Body RequestBody requestBody);

    /**
     * 找片-影片-已登录用户
     * 无码·热播
     * 有码·热播
     * 可爱·热播
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.FIND_TOPIC_VIDEOS)
    Observable<FindVideoTopicResponse> getFindVideoTopic(@Body RequestBody requestBody);

    /**
     * 找片-女优-已登录-最近看过
     * 影片最多
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_ACTRESSES_MOST_VIDEOS)
    public Observable<ArtistsResponse> getMostVideosArtist(@Body RequestBody requestBody);

    /**
     * 找片-女优-已登录-最近看过
     * 热门女优
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_ACTRESSES_HOT)
    public Observable<ArtistsResponse> getHotArtist(@Body RequestBody requestBody);

    /**
     * 找片-女优-已登录-最近看过
     * 新人推荐
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_ACTRESSES_NEW)
    public Observable<ArtistsResponse> getNewArtist(@Body RequestBody requestBody);

    /**
     * 女优参演的视频分页列表
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_ACTRESS_VIDEO)
    public Observable<VideoResponse> getArtistVideos(@Body RequestBody requestBody);

    /**
     * 演员详情
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_ACTRESS_DETAIL)
    public Observable<ArtistDetailResponse> getArtistDetail(@Body RequestBody requestBody);

    /**
     * 演员列表详情
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_ACTRESSES)
    Observable<ArtistsResponse> getArtists(@Body RequestBody requestBody);

    /**
     * 收藏演员列表详情
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.FAVOR_VIDEOS)
    Observable<VideoResponse> getFavorVideos(@Body RequestBody requestBody);

    /**
     * 收藏演员列表详情
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.FAVOR_ARTISTS)
    Observable<ArtistsResponse> getFavorArtists(@Body RequestBody requestBody);

    // ------- 搜索 --------

    /**
     * 热搜词
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_HOT_SEARCH_KEYS)
    Observable<SearchTagHotResponse> getSearchHotKeys(@Body RequestBody requestBody);

    /**
     * 搜索发现
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_HOT_SEARCH_DISCOVER)
    Observable<SearchTagDiscoverResponse> getSearchDiscover(@Body RequestBody requestBody);

    /**
     * 搜索slogan
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.SEARCH_VIDEOS_BY_SLOGAN)
    Observable<VideoResponse> searchBySlogan(@Body RequestBody requestBody);
    /**
     * 搜索标题
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.SEARCH_VIDEOS_BY_TITLE)
    Observable<VideoResponse> searchByTitle(@Body RequestBody requestBody);

    /**
     * 搜索系列
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.SEARCH_VIDEOS_BY_SERIES)
    Observable<VideoResponse> searchBySeries(@Body RequestBody requestBody);

    /**
     * 搜索标签
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.SEARCH_VIDEOS_BY_TAG)
    Observable<VideoResponse> searchByTags(@Body RequestBody requestBody);

    /**
     * 搜索女优
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.SEARCH_BY_ARTIST)
    Observable<ArtistsResponse> searchArtists(@Body RequestBody requestBody);

    /**
     * 综合搜索
     * 这个接口返回值比较复杂，也很动态。
     * 暂时无法造数据，只能靠想象力。
     * 返回的是一个map
     * {
     * "actress": [
     * {
     * "actress":{actress对象}，
     * "streams":[
     * {StreamPo对象},
     * {StreamPo对象},
     * {StreamPo对象}
     * ]
     * }
     * <p>
     * ],
     * "stream":[
     * {streamPO对象},
     * {streamPO对象},
     * {streamPO对象}
     * ]
     * }
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.SEARCH_ALL)
    Observable<SearchAllResponse> searchAll(@Body RequestBody requestBody);

    // ----观看 ----
    /**
     * 视频详情 : values传slogan
     *
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_VIDEO_DETAIL)
    Observable<VideoDetailsResponse> getVideoDetails(@Body RequestBody requestBody);

    // ---- 收藏/点赞 -----

    /**
     * 查询点赞数据
     *
     * @param requestBody
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.SET_LIKE)
    Observable<BaseResponse> likeSet(@Body RequestBody requestBody);

    /**
     * 上报：
     * 视频丢失：
     *      type=stream
     *      openid;
     *      localid;
     *      slogan;
     *      message;
     * @return
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.GET_LIKE)
    Observable<BaseResponse> likeGet(@Body RequestBody requestBody);

    /**
     * 上报点赞
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST(UrlConstants.REPORT)
    Observable<BaseResponse> reportData(@Body RequestBody requestBody);

}
