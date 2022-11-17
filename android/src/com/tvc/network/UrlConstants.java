package com.tvc.network;

public class UrlConstants {
    public static final String APP_VERSION_CODE = "/" + 80;

    /**
     * 请求token(服务端2小时刷新一次，token失败后返回400或401)
     * String sign;    签名算法
     * String version; 后台配置提供
     * String appId;   后台配置提供
     * String random;  随机字符串
     *
     * @param version
     * @param dto
     */
    public final static String API_TOKEN = "token" + APP_VERSION_CODE;
    /**
     * 应用升级检查
     */
    public final static String API_APP_UPGRADE = "ad_list" + APP_VERSION_CODE;
    /**
     * 广告：
     * values：（开屏、首页banner）
     * 1. 开屏；
     * 说明：应用启动，全屏展示，点击跳转三方链接，倒计时结束，需点击关闭；
     * 2. 首页banner；
     * 说明：首页置顶，轮播展示，点击跳转三方链接、或者影片播放页；
     * 3. 前贴片；
     * 说明：视频播页，播放前，强制观看，倒计时结束需点击关闭；
     * 4. 横幅banner；
     * 说明：女优页面、播放页面（客户端设计还未确定），轮播展示，点击跳转三方链接；
     */
    public final static String API_AD_LIST = "ad_list" + APP_VERSION_CODE;
    /**
     * 上报广告位操作
     */
    public final static String API_AD_REPORT = "ad_report" + APP_VERSION_CODE;


    // 设置偏好
    public final static String GET_ALL_TAGS = "yourHobby" + APP_VERSION_CODE;
    public final static String SET_FAVOR_TAGS = "selectHobby" + APP_VERSION_CODE;

    // -----用户登录 begin------
    // 获取验证码
    public final static String ACTIVE_INVITE_CODE = "user_invite" + APP_VERSION_CODE;
    // 获取验证码
    public final static String OTP = "user_otp" + APP_VERSION_CODE;
    // 用户注册
    public final static String REGISTER = "user_register" + APP_VERSION_CODE;
    // 用户登陆
    public static final String LOGIN = "user_login" + APP_VERSION_CODE;
    // 找回密码
    public static final String FORGOT = "user_forgot" + APP_VERSION_CODE;
    // 绑定其他账户
    public static final String BIND = "user_bind" + APP_VERSION_CODE;
    // 绑定其他账户
    public static final String PAYMENT = "paypage" + APP_VERSION_CODE;
    // 退出
    public static final String LOGOUT = "logout" + APP_VERSION_CODE;

    // 获取喜爱的视频列表
    public final static String GET_MYFAVOR_VIDEOS = "yourHobby" + APP_VERSION_CODE;

    // 设置喜爱的视频
    public final static String SET_MYFAVOR_VIDEO = "selectHobby" + APP_VERSION_CODE;

    // 首页推荐视频列表
    public final static String GET_RECOMMEND_VIDOES = "recommendStreams" + APP_VERSION_CODE;

    // 首页热门视频列表
    public final static String GET_HOT_VIDEOS = "hotStreams" + APP_VERSION_CODE;

    // 首页播放排名视频列表
    public final static String GET_TOP_WATCH_VIDEOS = "topStreams" + APP_VERSION_CODE;

    // 首页点赞排名视频列表
    public final static String GET_TOP_ZAN_VIDEOS = "likeStreams" + APP_VERSION_CODE;

    // 首页收藏排名视频列表
    public final static String GET_TOP_FAV_VIDEOS = "heartStreams" + APP_VERSION_CODE;

    // 找片: 大分类，其中values就是标签，英文逗号,分割。
    public final static String FIND_VIDEO_CATEGORIES = "assortment" + APP_VERSION_CODE;
    //  找片首页，专题视频列表（全部）
    public final static String FIND_GUESS_VIDEOS = "guessStreams" + APP_VERSION_CODE;
    //  找片首页，专题视频列表（全部）
    public final static String FIND_TOPIC_VIDEOS = "topicStream" + APP_VERSION_CODE;

    //  根据标签查询视频列表

    // 收藏 - 視頻
    public final static String FAVOR_VIDEOS = "favoriteStream" + APP_VERSION_CODE;
    // 收藏 - 演員
    public final static String FAVOR_ARTISTS = "favoriteActress" + APP_VERSION_CODE;

    // --------演员相关----
    // 影片最多的演员
    public final static String GET_ACTRESSES_MOST_VIDEOS = "fruitfulActress" + APP_VERSION_CODE;

    // 热门的演员
    public final static String GET_ACTRESSES_HOT = "hotActress" + APP_VERSION_CODE;

    // 新人推荐
    public final static String GET_ACTRESSES_NEW = "newActress" + APP_VERSION_CODE;

    // ------- 演员 ------
    // 女优详情
    public final static String GET_ACTRESS_DETAIL = "actressDetail" + APP_VERSION_CODE;

    // 女优参演的视频分页列表
    public final static String GET_ACTRESS_VIDEO = "actressStreams" + APP_VERSION_CODE;

    // 全部女优
    public final static String GET_ACTRESSES = "actress" + APP_VERSION_CODE;

    // 热搜词
    public final static String GET_HOT_SEARCH_KEYS = "hotSearch" + APP_VERSION_CODE;

    // 搜索发现
    public final static String GET_HOT_SEARCH_DISCOVER = "discover" + APP_VERSION_CODE;

    //slogan查询视频
    public final static String SEARCH_VIDEOS_BY_SLOGAN = "searchSlogan" + APP_VERSION_CODE;
    //片名查询视频
    public final static String SEARCH_VIDEOS_BY_TAG = "searchTags" + APP_VERSION_CODE;
    //片名查询视频
    public final static String SEARCH_VIDEOS_BY_TITLE = "searchTitle" + APP_VERSION_CODE;
    // 搜索系列
    public final static String SEARCH_VIDEOS_BY_SERIES = "searchSeries" + APP_VERSION_CODE;
    // 搜索女优
    public final static String SEARCH_BY_ARTIST = "searchActress" + APP_VERSION_CODE;
    // 搜索所有
    public final static String SEARCH_ALL = "searchAll" + APP_VERSION_CODE;

    // 视频详情
    public final static String GET_VIDEO_DETAIL = "streamDetail" + APP_VERSION_CODE;
    //参演女优+3视频
    public final static String GET_ACTRESS_AND_3VIDEO = "streamActress" + APP_VERSION_CODE;

    // 相关视频
    public final static String GET_RELATE_VIDEOS = "relateStream" + APP_VERSION_CODE;

    // 点赞和收藏事件
    public final static String GET_LIKE = "like_get" + APP_VERSION_CODE;

    // 点赞和收藏
    public final static String SET_LIKE = "like_set" + APP_VERSION_CODE;

    // 事件上报，通用请求
    public final static String REPORT = "report" + APP_VERSION_CODE;

    // 实时获取网站中视频的文件地址 （三方视频库）
    public final static String GET_VIDEO_SOURCE = "api/source/{ID}";
}
