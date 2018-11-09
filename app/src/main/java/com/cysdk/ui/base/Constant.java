package com.cysdk.ui.base;

/**
 * Created by Administrator on 2018/11/9.
 */

public class Constant  {
    private final static String TAG = Constant.class.getName();
    /**
     * 签名验证key
     */
    public final static String BOOT_KEY = "892984b23632e3c09b83f37025951013";
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[0-9])|(18[0-9])|(145)|(147)|(170)|(17[6-8]))\\d{8}$";
    /**
     * 正则表达式：验证6-12位密码
     */
    public static final String REGEX_PWD = "^[^\\s]{6,12}$";
    /**
     * 登陆成功action
     */
    public final static String LOGIN_LISTEN_SUCCESS_ACTION = TAG
            + "_login_listen_success_action";
    /**
     * 登陆失败action
     */
    public final static String LOGIN_LISTEN_FAILURE = TAG
            + "_login_listen_failure_action";
    /**
     * 修改游戏信息成功action
     */
    public final static String MODIFY_GAME_INFO_SUCCESS_ACTION = TAG
            + "_modify_game_info_success_action";
    /**
     * 修改游戏信息失败action
     */
    public final static String MODIFY_GAME_INFO_FAILURE_ACTION = TAG
            + "_modify_game_info_failure_action";
    /**
     * 支付成功action
     */
    public final static String PAY_SUCCESS_ACTION = TAG + "_pay_success_action";
    /**
     * 支付失败action
     */
    public final static String PAY_FAILURE_ACTION = TAG + "_pay_failure_action";
    /**
     * 注册区服成功action
     */
    public final static String GAME_SERVICE_SUCCESS_ACTION = TAG
            + "_game_service_success_action";
    /**
     * 注册区服失败action
     */
    public final static String GAME_SERVICE_FAILURE_ACTION = TAG
            + "_game_service_failure_action";
    /**
     * 退出登陆失败
     */
    public final static String LOGOUT_FAILURE = TAG + "_logout_failure";
    /**
     * 退出登陆成功
     */
    public final static String LOGOUT_SUCCESS = TAG + "_logout_success";
    /**
     * 退出登陆失败
     */
    public final static String LOGOUT_GAME_FAILURE = TAG
            + "_logout_game_failure";
    /**
     * 退出登陆成功
     */
    public final static String LOGOUT_GAME_SUCCESS = TAG
            + "_logout_game_success";
    /**
     * 退出登陆失败
     */
    public final static String GET_USER_INFO_FAILURE = TAG
            + "_get_user_info_failure";
    /**
     * 退出登陆成功
     */
    public final static String GET_USER_INFO_SUCCESS = TAG
            + "_get_user_info_success";
    /**
     * 消息key
     */
    public final static String BROADCAST_MSG = TAG + "_broadcase_msg";

}
