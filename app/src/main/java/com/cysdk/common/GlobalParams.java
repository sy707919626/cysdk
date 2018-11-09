package com.cysdk.common;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class GlobalParams {
    public static String sToken;
    public static String sUserPhone;
    public static  String sUserId;
    public static String sUserName;
    public static String sUserType;
    public static boolean sIsDebug;

    public static void setIsDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    public static void setToken(String token) {
        sToken = token;
    }

    public static void setuserPhone(String userPhone) {
        sUserPhone = userPhone;
    }

    public static void setuserName(String userName) {
        sUserName = userName;
    }

    public static void setuserId(String userId) {
        sUserId = userId;
    }

    public static void setuserType(String userType) {
        sUserType = userType;
    }

    public static String getUserHeader(){
        JSONObject user = new JSONObject();
        try {
            user.put("userid", sUserId);
            user.put("name", sUserName);
            user.put("usertype", sUserType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user.toString();
    }
    /**
     * 是否登录
     */
    public static boolean isLogin() {
        return !TextUtils.isEmpty(sToken);
    }

}
