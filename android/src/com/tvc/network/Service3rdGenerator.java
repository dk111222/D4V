package com.tvc.network;


import com.google.gson.GsonBuilder;

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

public class Service3rdGenerator {
    private static final long DEFAULT_TIMEOUT = 6;
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).addInterceptor(loggingInterceptor)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

    private final static String test_video_token = "3188fbbcb7406329";

    public static <S> S createVideo3rService(Class<S> serviceClass, String host) {
        // 忽略证书
        httpClient.interceptors().add(chain -> {
            Request original = chain.request();
            // Request customization: add request headers
            Request.Builder requestBuilder;
            requestBuilder = original.newBuilder()
                    .header("token", test_video_token)
                    .header("Authorization", test_video_token);
            requestBuilder.method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(host)
//                    .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))  // gson转换器
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
        httpClient.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(),(X509TrustManager) trustAllCerts[0]);
        httpClient.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }

    public static String getHost(String watchUrl) {
        String[] keys = watchUrl.split("/");
        StringBuilder host = new StringBuilder();

        for (String key : keys) {
            host.append(key).append("/");
            if (key.equals("com")) {
                break;
            }
        }
        return host.toString();
    }

    public static String getId(String watchUrl) {
        String[] keys = watchUrl.split("/");
        return keys[keys.length - 1];
    }
}
