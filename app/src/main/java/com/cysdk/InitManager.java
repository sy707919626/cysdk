package com.cysdk;


import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.cysdk.common.GlobalParams;
import com.cysdk.data.http.ApiService;
import com.cysdk.di.module.HttpModule;
import com.cysdk.ui.activity.LoginActivity;
import com.cysdk.ui.base.Constant;
import com.cysdk.ui.listen.BroadCastUtil;
import com.cysdk.ui.listen.LoginListen;
import com.cysdk.ui.listen.SdkBroadcast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/9.
 */

public class InitManager {

    public static ApiService mApi;
    public static Application mApplication;
    private static Context mContext;
    private static InitManager init_manager;
    private SdkBroadcast receiver;

    public static InitManager init(Application application, Context context, boolean isDebug) {//调试模式
        mApplication = application;
        mContext = context;
        if (init_manager == null)
            synchronized (InitManager.class.getName()) {
                GlobalParams.setIsDebug(isDebug);

                if (init_manager == null) {
                    init_manager = new InitManager(application);
                }
            }
        return init_manager;
    }

    private InitManager(Application application) {
        this.mApplication = application;
        mApi = new HttpModule().getHttp();
        receiver = new SdkBroadcast();

        List<String> actions = new ArrayList<String>();
        actions.add(Constant.GAME_SERVICE_FAILURE_ACTION);
        actions.add(Constant.GAME_SERVICE_SUCCESS_ACTION);
        actions.add(Constant.LOGIN_LISTEN_FAILURE);
        actions.add(Constant.LOGIN_LISTEN_SUCCESS_ACTION);
        actions.add(Constant.MODIFY_GAME_INFO_FAILURE_ACTION);
        actions.add(Constant.MODIFY_GAME_INFO_SUCCESS_ACTION);
        actions.add(Constant.PAY_FAILURE_ACTION);
        actions.add(Constant.PAY_SUCCESS_ACTION);
        actions.add(Constant.LOGOUT_FAILURE);
        actions.add(Constant.LOGOUT_SUCCESS);
        actions.add(Constant.LOGOUT_GAME_FAILURE);
        actions.add(Constant.LOGOUT_GAME_SUCCESS);
        actions.add(Constant.GET_USER_INFO_FAILURE);
        actions.add(Constant.GET_USER_INFO_SUCCESS);

        BroadCastUtil.register_broadcast(mApplication, receiver, actions, 500,null, null);
    }

    public void login(LoginListen loginListen){
        receiver.register_login_listen(loginListen);
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }
}
