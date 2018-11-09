package com.cysdk.common.widget;

import android.content.Intent;

import com.cysdk.InitManager;
import com.cysdk.ui.activity.LoginActivity;


public class LoginUtil {
    public static void loginOut() {
//        GlobalParams.clear();
        Intent intent = new Intent(InitManager.mApplication, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        InitManager.mApplication.startActivity(intent);
    }

}
