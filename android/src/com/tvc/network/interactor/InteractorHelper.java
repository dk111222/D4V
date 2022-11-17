package com.tvc.network.interactor;

import android.text.TextUtils;

import com.tvc.network.ServiceGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Random;

public class InteractorHelper {

    public static String APPID;
    // TODO FXIME SHA1 instead
    public static String APPSECRET = "r0wdjup1klxiyga4";
    public final static String SIGN_VERSION = "1";

    private final static String KEY_OPENID = "openid";
    private final static String KEY_UUID = "localid";

    public static JSONObject newBasicJson() {
        JSONObject requestData = new JSONObject();
        try {
            if (!TextUtils.isEmpty(ServiceGenerator.OPENID)) {
                requestData.put(KEY_OPENID, ServiceGenerator.OPENID);
            }
            requestData.put(KEY_UUID, ServiceGenerator.LOCAL_UUID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestData;
    }

    public static String sign(String random) {
        String format = "appId=%s&appSecret=%s&random=%s";
        String sign = md5(String.format(format, APPID, APPSECRET, random));
        return sign;
    }

    public static String sign(String appId, String version, String appSecret, String random) {
        String format = "appId=%s&appSecret=%s&version=%s&random=%s";
        String sign = md5(String.format(format, appId, appSecret, version, random));
        return sign;
    }

    private static String md5(String text) {
        if (text == null) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String s = Integer.toHexString(0xff & bytes[i]);
                if (s.length() == 1) {
                    sb.append("0").append(s);
                } else {
                    sb.append(s);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("no shit");
        }
    }

    public static String randomStr() {
        return Long.toHexString(new Random().nextLong());
    }

    public static String randomInt() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    public static long expire(long ms) {
        return System.currentTimeMillis() + ms;
    }

    public static boolean expired(long ms) {
        return System.currentTimeMillis() > ms;
    }

    /**
     * unique order id
     *
     * @param number
     * @return
     */
    public static String id(long number) {
        return new DecimalFormat("UOI00000000").format(number);
    }
}
