package com.tvc.network;


import android.location.Location;
import android.os.Build;
import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.tvc.network.interactor.AppInteractor;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public final static String HTTPS_365HOST_URL = // BuildConfig.DEBUG ? "https://104.168.147.30:8443/av365/api/" :
            "https://91avhd.com:8444/av365/api/";  //  测试地址，端口号8444
//            "https://91avhd.com:8443/av365/api/";  // 正式地址，端口号8443


    private static final long DEFAULT_TIMEOUT =  15;

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

    private static String sToken = ""; //""3188fbbcb7406329"; // for test

    public static void setsToken(String t) {
        if (sToken!= null && sToken.equals(t)) {
            return;
        }

        sToken = t;
        AppInteractor.getInstance().resetService();
    }

    public static String OPENID = "";  // 登录后返回的用户id
    public static String LOCAL_UUID = String.valueOf(System.currentTimeMillis()); // 未登录时，本地的唯一ID, 应用启动时候初始化，不能为空
    public static String sLanguage = "en";
    public static Location sLocation;


    public static <S> S create365Service(Class<S> serviceClass) {
        // 忽略证书
        httpClient.interceptors().add(chain -> {
            Request original = chain.request();
            // Request customization: add request headers
            Request.Builder requestBuilder;
            requestBuilder = original.newBuilder()
                    .header("token", sToken)
                    .header("Authorization", sToken)
                    .header("language", sLanguage)
                    .header("version", String.valueOf(80));
            if (!TextUtils.isEmpty(Build.BRAND)) {
                requestBuilder.header("brand", Build.BRAND);
            }
            if (!TextUtils.isEmpty(Build.MODEL)) {
                requestBuilder.header("model", Build.MODEL);
            }
            if (sLocation != null) {
                requestBuilder.header("longitude", String.valueOf(sLocation.getLongitude()));
                requestBuilder.header("altitude", String.valueOf(sLocation.getAltitude()));
            }
            requestBuilder.method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(HTTPS_365HOST_URL)
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());


        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        // 证书
        httpClient.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        httpClient.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        OkHttpClient client = httpClient.build();


        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }

    public static <S> S createDhtService(Class<S> serviceClass) {
        // 忽略证书
        httpClient.interceptors().add(chain -> {
            Request original = chain.request();
            // Request customization: add request headers
            Request.Builder requestBuilder;
            requestBuilder = original.newBuilder()
                    .header("language", sLanguage)
                    .header("whitelist","spider_man")
                    .header("version", String.valueOf(80));
            if (!TextUtils.isEmpty(Build.BRAND)) {
                requestBuilder.header("brand", Build.BRAND);
            }
            if (!TextUtils.isEmpty(Build.MODEL)) {
                requestBuilder.header("model", Build.MODEL);
            }
            if (sLocation != null) {
                requestBuilder.header("longitude", String.valueOf(sLocation.getLongitude()));
                requestBuilder.header("altitude", String.valueOf(sLocation.getAltitude()));
            }
            requestBuilder.method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.netfly.tv/")
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());


        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        // 证书
        httpClient.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        httpClient.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        OkHttpClient client = httpClient.build();


        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }

}
