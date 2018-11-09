package com.cysdk.ui.listen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cysdk.ui.base.Constant;

/**
 * Created by Administrator on 2018/11/9.
 */

public class SdkBroadcast extends BroadcastReceiver {
    private LoginListen loginListen;
    private ModifyGameInfoListen modifyGameInfoListen;
    private PayListen payListen;
    private GameServiceListen gameServiceListen;
    private LogoutListen logoutListen;
    private LogoutGameListen logoutGameListen;
    private GetUserInfoListen getUserInfoListen;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String msg = intent.getSerializableExtra(Constant.BROADCAST_MSG).toString();

        if (action.equals(Constant.LOGIN_LISTEN_FAILURE)) {
            if (loginListen != null)
                loginListen.login_failure(msg);
        } else if (action.equals(Constant.LOGIN_LISTEN_SUCCESS_ACTION)) {
//            if (loginListen != null){
//                try {
//                    JSONObject jo=new JSONObject(msg);
//                    try {
//                        int code=jo.getInt("code");
//                        if(code==200){
//                            PlatfromUser platfromUser=PlatfromUser.init(jo.getJSONObject("data").toString());
//                            QPSdkManager.get_sdk_manager().setPlatfromUser(platfromUser);
//                            loginListen.login_success(platfromUser);
//                            if(!platfromUser.ismobile())
//                                QPSdkManager.get_sdk_manager().unbindMobile();
//                        }else
//                            loginListen.login_failure(msg);
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                        PlatfromUser platfromUser=PlatfromUser.init(jo.toString());
//                        QPSdkManager.get_sdk_manager().setPlatfromUser(platfromUser);
//                        loginListen.login_success(platfromUser);
//                    }
//                } catch (Exception e) {
//                    // TODO: handle exception
//                    loginListen.login_failure(e.getMessage());
//                }
        } else if (action.equals(Constant.MODIFY_GAME_INFO_FAILURE_ACTION)) {
            if (modifyGameInfoListen != null)
                modifyGameInfoListen.modify_game_info_failure(msg);
        } else if (action.equals(Constant.MODIFY_GAME_INFO_SUCCESS_ACTION)) {
            if (modifyGameInfoListen != null)
                modifyGameInfoListen.modify_game_info_success(msg);
        } else if (action.equals(Constant.PAY_FAILURE_ACTION)) {
            if (payListen != null)
                payListen.pay_failure(msg);
        } else if (action.equals(Constant.PAY_SUCCESS_ACTION)) {
            if (payListen != null)
                payListen.pay_success(msg);
        } else if (action.equals(Constant.GAME_SERVICE_FAILURE_ACTION)) {
            if (gameServiceListen != null)
                gameServiceListen.game_service_failure(msg);
        } else if (action.equals(Constant.GAME_SERVICE_SUCCESS_ACTION)) {
            if (gameServiceListen != null)
                gameServiceListen.game_service_success(msg);
        }else if(action.equals(Constant.LOGOUT_FAILURE)){
            if(logoutListen!=null)
                logoutListen.logout_failure(msg);
        }else if(action.equals(Constant.LOGOUT_SUCCESS)){

//            QPSdkManager.get_sdk_manager().setPlatfromUser(null);
//            QPSdkManager.get_sdk_manager().send_logout();

            if(logoutListen!=null)
                logoutListen.logout_success(msg);
        }else if(action.equals(Constant.LOGOUT_GAME_FAILURE)){
            if(logoutGameListen!=null)
                logoutGameListen.logout_game_failure(msg);
        }else if(action.equals(Constant.LOGOUT_GAME_SUCCESS)){
            if(logoutGameListen!=null)
                logoutGameListen.logout_game_success(msg);
        }else if(action.equals(Constant.GET_USER_INFO_FAILURE)){
            if(getUserInfoListen!=null)
                getUserInfoListen.get_user_info_failure(msg);
        }else if(action.equals(Constant.GET_USER_INFO_SUCCESS)){
            if(getUserInfoListen!=null){
//                getUserInfoListen.get_user_info_success(QPSdkManager.get_sdk_manager().getPlatfromUser());
            }
        }
    }

    /**
     * 注册登陆监听
     */
    public void register_login_listen(LoginListen loginListen) {
        this.loginListen = loginListen;
    }

    /**
     * 取消登陆监听
     */
    public void un_register_login_listen() {
        loginListen = null;
    }

    /**
     * 注册游戏信息修改监听
     */
    public void register_modify_game_info_listen(
            ModifyGameInfoListen modifyGameInfoListen) {
        this.modifyGameInfoListen = modifyGameInfoListen;
    }

    /**
     * 取消游戏信息修改监听
     */
    public void un_register_modify_game_info_listen() {
        modifyGameInfoListen = null;
    }

    /**
     * 注册支付监听
     */
    public void register_pay_listen(PayListen payListen) {
        this.payListen = payListen;
    }

    /**
     * 取消支付监听
     */
    public void un_register_pay_listen() {
        payListen = null;
    }

    /**
     * 注册注册区服监听
     */
    public void register_game_service_listen(GameServiceListen gameServiceListen) {
        this.gameServiceListen = gameServiceListen;
    }

    /**
     * 取消注册区服监听 xh 2017-3-29 下午3:55:27
     */
    public void un_register_game_service_listen() {
        gameServiceListen = null;
    }

    public void register_logout_listen(LogoutListen logoutListen){
        this.logoutListen=logoutListen;
    }
    public void un_register_logout_listen(){
        logoutListen=null;
    }
    public void register_logout_game_listen(LogoutGameListen logoutGameListen){
        this.logoutGameListen=logoutGameListen;
    }
    public void un_register_logout_game_listen(){
        this.logoutGameListen=null;
    }
    public void register_get_user_info_listen(GetUserInfoListen getUserInfoListen){
        this.getUserInfoListen=getUserInfoListen;
    }
    public void un_register_get_user_info_listen(){
        getUserInfoListen=null;
    }
}
