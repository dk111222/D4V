package com.tvc.network.interactor;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.tvc.network.ServiceGenerator;
import com.tvc.network.api.AppService;
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

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * token, 升级，广告相关接口
 */
public class AppInteractor {
    private static AppInteractor sInstance;

    public static AppInteractor getInstance() {
        if (sInstance == null) {
            sInstance = new AppInteractor();
        }
        return sInstance;
    }

    private final static String KEY_VALUES = "values";

    private final static String KEY_ADPID = "adpid";
    private final static String KEY_ACTION = "action";
    private final static String KEY_ID = "id";
    private final static String KEY_SN = "sn";

    private AppService appService = ServiceGenerator.create365Service(AppService.class);

    private AppInteractor() {
    }

    public void resetService() {
        appService = ServiceGenerator.create365Service(AppService.class);
    }

    /**token*/
    public Observable<TResponse> getToken() {
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put("sign", InteractorHelper.sign(ServiceGenerator.LOCAL_UUID));
            requestData.put("appId", InteractorHelper.APPID);
            requestData.put("random", ServiceGenerator.LOCAL_UUID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getToken(requestBody);
    }

    /**value: 广告位*/
    public Observable<AdResponse> getAds(String value) {
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_VALUES, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getAds(requestBody);
    }

    /** 广告的用户操作：   /**
     * adpid-广告位
     * action-操作什么：比如点赞like,收藏heart
     * sn-广告
     */
    public Observable<BaseResponse> reportAdAction(String adpid, String action, String sn) {
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_ADPID, adpid);
            requestData.put(KEY_ACTION, action);
            requestData.put(KEY_SN, sn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.reportAdAction(requestBody);
    }

    // ----------------- 用户 ---------------------
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_INVITE_CODE = "invitecode";

    /**
     * 注册
     *
     @NotBlank(message = "本地id：比如IMEI")
     String localid;
     @NotBlank(message = "用户名必填")
     String username;
     @NotBlank(message = "来源：比如appId")
     String source;
     String name;
     String otp;
     String otp_key;
     @NotBlank(message = "类型：User")
     String type;
     @NotBlank(message = "密码必填")
     String password;
     @NotBlank(message = "重复密码必填")
     String password2;

      * @return
     */
    public Observable<LoginResponse> register(String userName, String password, String inviteCodeBy) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_USERNAME, userName);
            requestData.put(KEY_PASSWORD, password);
            requestData.put("password2", password);
            if (!TextUtils.isEmpty(inviteCodeBy)) {
                requestData.put(KEY_INVITE_CODE, inviteCodeBy);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.register(requestBody);
    }

    /**
     * 登陆
     *
     * @param userName
     * @param password
     * @return
     */
    public Observable<LoginResponse> login(String userName, String password) {
        // 登陆
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_USERNAME, userName);
            requestData.put(KEY_PASSWORD, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.login(requestBody);

    }

    /**
     * 获取验证码
     *
     * @param inviteCode
     * @return
     */
    public Observable<BaseResponse> activeInviteCode(String inviteCode) {
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_INVITE_CODE, inviteCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.activeInviteCode(requestBody);
    }


    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @return
     */
    public Observable<BaseResponse> otp(String phoneNumber) {
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_USERNAME, phoneNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.otp(requestBody);
    }

    /**
     * 忘记密码-重置
     *
     * @param phoneNumber
     * @param newPwd
     * @param otp
     * @return
     */
    public Observable<BaseResponse> forgot(String phoneNumber, String newPwd, String otp) {
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put("phone", phoneNumber);
            requestData.put("new_password", newPwd);
            requestData.put("otp", otp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.forgot(requestBody);
    }

    /**
     *
     * @return
     */
    public Observable<PaymentResponse> payment() {
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.payment(requestBody);
    }

    /**
     * 忘记密码-重置
     * @return
     */
    public Observable<BaseResponse> logout() {
        JSONObject requestData = new JSONObject();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.logout(requestBody);
    }

    // ==================== 视频，演员 ===========================
    private final static String KEY_SEARCH = "search";
    private final static String KEY_PAGE_SIZE = "pageSize";
    private final static String KEY_PAGE_INDEX = "page";
    private final static String KEY_SLOGAN = "slogan";

    /**
     * 值：视频-stream，演员-actress
     */
    private final static String KEY_HEAD = "head";
    private final static String HEAD_VALUE_VIDEO = "stream";
    private final static String HEAD_VALUE_ARTIST = "actress";
    /**
     * 值：点赞-like，收藏-heart
     */
    private final static String KEY_NAME = "name";
    private final static String ACTION_VALUE_ZAN = "like";
    private final static String ACTION_VALUE_FAVOR = "heart";


    /**
     * 获取所有标签
     *
     * @return
     */
    public Observable<TagsResponse> getAllTags() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getAllTags(requestBody);
    }

    /**
     * 选择喜欢的标签
     *
     * @return
     */
    public Observable<BaseResponse> setFavorTags(@NonNull String tags) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_VALUES, tags);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.setFavorTags(requestBody);
    }


    /**
     * 首页：推荐
     *
     * @return
     */
    public Observable<VideoResponse> getHomeRecommendVideos(int pageSize, int pageIndex, String favorTags) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
            if (!TextUtils.isEmpty(favorTags)) {
                requestData.put(KEY_SEARCH, favorTags);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getHomeRecommendVideos(requestBody);
    }

    /**
     * 首页：热门
     *
     * @return
     */
    public Observable<VideoResponse> getHomeHotVideos(int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getHomeHotVideos(requestBody);
    }

    /**
     * @param pageSize 需要返回的视频个数
     * @param pageIndex
     * @return
     */
    public Observable<VideoResponse> getTopZanVideos(int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getTopZanVideos(requestBody);
    }

    /**
     * @param pageSize 需要返回的视频个数
     * @param pageIndex
     * @return
     */
    public Observable<VideoResponse> getTopWatchVideos(int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getTopWatchVideos(requestBody);
    }

    /**
     * @param pageSize 需要返回的视频个数
     * @param pageIndex
     * @return
     */
    public Observable<VideoResponse> getTopFavorVideos(int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getTopFavorVideos(requestBody);
    }

    // ----- HOME end ----------

    /**
     * 找片-影片-已登录用户
     * 找片大分类，其中values就是标签，英文逗号,分割。
     *
     * @return
     */
    public Observable<FindVideoCategoriesResponse> getFindVideoCategories() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getFindVideoCategories(requestBody);
    }


    /**
     * 找片-影片-已登录用户
     * 猜你想撸。
     *
     * @return
     */
    public Observable<VideoResponse> getGuessVideos() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getGuessVideos(requestBody);
    }


    /**
     * 找片-影片-已登录用户
     * 无码·热播
     * 有码·热播
     * 可爱·热播
     *
     * @return
     */
    public Observable<FindVideoTopicResponse> getFindVideoTopic() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getFindVideoTopic(requestBody);
    }

    /**
     * 找片-女优-已登录-最近看过
     * 影片最多
     *
     * @return
     */
    public Observable<ArtistsResponse> getMostVideosArtist() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getMostVideosArtist(requestBody);
    }

    /**
     * 找片-女优-已登录-最近看过
     * 热门女优
     *
     * @return
     */
    public Observable<ArtistsResponse> getHotArtist() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getHotArtist(requestBody);
    }

    /**
     * 找片-女优-已登录-最近看过
     * 新人推荐
     *
     * @return
     */
    public Observable<ArtistsResponse> getNewArtist() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getNewArtist(requestBody);
    }

    /**
     * 参演的视频分页列表
     *
     * @return
     */
    public Observable<VideoResponse> getArtistVideos() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getArtistVideos(requestBody);
    }

    /**
     * 女优参演的视频分页列表
     *
     * @return
     */
    public Observable<ArtistDetailResponse> getArtistDetail(@NonNull String name, int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SEARCH, name);
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getArtistDetail(requestBody);
    }

    /**
     * 获取演员列表
     *
     * @param pageSize
     * @param pageIndex
     * @return
     */
    public Observable<ArtistsResponse> getArtists(int pageSize,  int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getArtists(requestBody);
    }


    // ------------ 搜索 -------------

    /**
     * 热搜词
     */
    public Observable<SearchTagHotResponse> getHotSearchKeys() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getSearchHotKeys(requestBody);
    }

    /**
     * 搜索发现
     */
    public Observable<SearchTagDiscoverResponse> getSearchDiscover() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getSearchDiscover(requestBody);
    }

    /**
     * 搜索标题
     *
     * @return
     */
    public Observable<VideoResponse> searchBySlogan(@NonNull String search, int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SEARCH, search);
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.searchBySlogan(requestBody);
    }

    /**
     * 搜索标题
     *
     * @return
     */
    public Observable<VideoResponse> searchByTitle(@NonNull String search, int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SEARCH, search);
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.searchByTitle(requestBody);
    }

    /**
     * 搜索系列
     *
     * @return
     */
    public Observable<VideoResponse> searchBySeries(@NonNull String search, int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SEARCH, search);
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.searchBySeries(requestBody);
    }

    /**
     * 搜索标签
     *
     * @return
     */
    public Observable<VideoResponse> searchByTags(@NonNull String tags, int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SEARCH, tags);
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.searchByTags(requestBody);
    }

    /**
     * 搜索女优
     *
     * @return
     */
    public Observable<ArtistsResponse> searchArtists(@NonNull String search, int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SEARCH, search);
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.searchArtists(requestBody);
    }

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
    public Observable<SearchAllResponse> searchAll(@NonNull String search, int pageSize, int pageIndex) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SEARCH, search);
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.searchAll(requestBody);
    }

    /**
     * 视频详情 :
     * values传slogan
     *
     * @return
     */
    public Observable<VideoDetailsResponse> getVideoDetails(@NonNull String sn, @NonNull String slogan) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SLOGAN, slogan);
            requestData.put(KEY_SN, sn);
            requestData.put(KEY_VALUES, slogan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getVideoDetails(requestBody);
    }

    //  ----------- 收藏 --------------
    public Observable<BaseResponse> getFavorArtistNames() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_HEAD, HEAD_VALUE_ARTIST);
            requestData.put(KEY_ACTION, ACTION_VALUE_FAVOR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.likeGet(requestBody);
    }


    public Observable<VideoResponse> getFavorVideoSlogans() {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getFavorVideos(requestBody);
    }

    // 收藏的视频列表
    public Observable<VideoResponse> getFavorVideos(int pageSize, int pageIndex) {

        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getFavorVideos(requestBody);
    }

    // 收藏的女优列表
    public Observable<ArtistsResponse> getFavorArtists(int pageSize, int pageIndex) {

        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_PAGE_SIZE, pageSize);
            requestData.put(KEY_PAGE_INDEX, pageIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.getFavorArtists(requestBody);
    }

    /**
     *
     * @param videoId slogan
     * @param value 1代表收藏，0代表取消收藏
     * @return
     */
    public Observable<BaseResponse> favorVideo(@NonNull String videoId, int value) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_HEAD, HEAD_VALUE_VIDEO);
            requestData.put(KEY_ACTION, ACTION_VALUE_FAVOR);
            requestData.put(KEY_ID, videoId);
            requestData.put(KEY_VALUES, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.likeSet(requestBody);
    }

    /**
     *
     * @param artistId artist名字作为id
     * @param value 1代表收藏，0代表取消收藏
     * @return
     */
    public Observable<BaseResponse> favorArtist(@NonNull String artistId, int value) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_HEAD, HEAD_VALUE_ARTIST);
            requestData.put(KEY_ACTION, ACTION_VALUE_FAVOR);
            requestData.put(KEY_ID, artistId);
            requestData.put(KEY_VALUES, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.likeSet(requestBody);
    }

    // ------- 点赞 -------
    public Observable<BaseResponse> likeSet(@NonNull String sn, int value) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_HEAD, HEAD_VALUE_VIDEO);
            requestData.put(KEY_ACTION, HEAD_VALUE_ARTIST);
            requestData.put(KEY_SN, sn);
            requestData.put(KEY_VALUES, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.likeSet(requestBody);
    }

    public Observable<BaseResponse> likeGet(@NonNull String sn) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_HEAD, HEAD_VALUE_VIDEO);
            requestData.put(KEY_ACTION, ACTION_VALUE_ZAN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.likeGet(requestBody);
    }

    /**
     * 统计数据： 点赞信息，同likeset同一个接口，不同参数
     * String openid; 用户的openid
     * String head; 固定值：static
     * String action; 具体操作，比如点赞like，恶心hate，之类的。
     * String sn; 具体视频对象的sn唯一值。
     *
     * @return
     */
    public Observable<BaseResponse> zanSet(String videoId, String action, String value) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_ID, videoId);
            requestData.put("head", "static");
            requestData.put(KEY_ACTION, action);
            requestData.put(KEY_VALUES, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.likeSet(requestBody);
    }

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

    public Observable<BaseResponse> reportGetPlayUrlsFailed(String slogan,String url, String message) {
        // 新品推荐
        JSONObject requestData = InteractorHelper.newBasicJson();
        try {
            requestData.put(KEY_SLOGAN, slogan);
            requestData.put("url", url);
            requestData.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        return appService.reportData(requestBody);
    }

}
