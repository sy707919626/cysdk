package com.cysdk.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cysdk.InitManager;
import com.cysdk.R;
import com.cysdk.common.rx.RxHttpResponseCompat;
import com.cysdk.common.rx.subscriber.ErrorHandlerSubscriber;
import com.cysdk.common.rx.subscriber.ProgressDialogHandler;
import com.cysdk.common.widget.ClearEditText;
import com.cysdk.common.widget.MyCountDownTimer;
import com.cysdk.common.widget.ProjectUtil;
import com.cysdk.common.widget.RxToast;
import com.cysdk.common.widget.SPUtils;
import com.cysdk.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PhoneLoginActivity extends BaseActivity {

    @BindView(R.id.phone_edit_name)
    ClearEditText phoneEditName;
    @BindView(R.id.login_phone_edit_code)
    ClearEditText loginPhoneEditCode;
    @BindView(R.id.login_phone_btn_getcode)
    Button loginPhoneBtnGetcode;
    @BindView(R.id.text_forgetpwd)
    TextView textForgetpwd;
    @BindView(R.id.text_phone_register)
    TextView textPhoneRegister;
    @BindView(R.id.btn_phone_login)
    Button btnPhoneLogin;
    @BindView(R.id.login_txt_pwd)
    TextView loginTxtPwd;
    @BindView(R.id.ll_phone_login)
    LinearLayout llPhoneLogin;

    private String tempMobile;
    private String Code = "";
    @Override
    protected int setLayoutId() {
        return R.layout.activity_phone_login;
    }

    private ProgressDialogHandler dialogHandler;

    @Override
    protected void init() {
        dialogHandler = new ProgressDialogHandler(this);

        loginPhoneBtnGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000, loginPhoneBtnGetcode);
                tempMobile = phoneEditName.getText().toString().trim();
                if (tempMobile.equals("")) {
                    RxToast.warning("请输入手机号码");

                } else if (ProjectUtil.isMobileNO(tempMobile)) {
                    //处理手机验证码获取
//                    InitManager.mApi.sendVerifySms(GlobalParams.sToken, tempMobile, "2")
//                            .compose(RxHttpResponseCompat.<String>compatResult())
//                            .subscribe(new ErrorHandlerSubscriber<String>() {
//                                @Override
//                                public void onNext(String s) {
//                                    JSONObject jsonObject = JSONObject.parseObject(s);
//                                    Code = jsonObject.getString("VerifyCode");
//                                    myCountDownTimer.start();
//                                }
//                            });
                } else {
                    RxToast.warning("请输入正确的手机号码");
                }
            }
        });

    }

    @OnClick({R.id.text_forgetpwd, R.id.text_phone_register, R.id.btn_phone_login,
            R.id.login_txt_pwd})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_forgetpwd:
                //忘记密码
                startActivity(new Intent(this, ForgetPwdActivity.class));

                break;
            case R.id.text_phone_register:
                //手机注册
                startActivity(new Intent(this, RegisterPhoneActivity.class));

                break;

            case R.id.btn_phone_login:
                //登录
                if (phoneEditName.getText().toString().trim().equals("")) {
                    RxToast.warning("手机号码不能为空");
                } else if (loginPhoneEditCode.getText().toString().trim().equals("")) {
                    RxToast.warning("验证码不能为空");
                } else if (loginPhoneEditCode.getText().toString().trim().equals(Code)) {
                    //手机注册逻辑
                    login();
                } else {
                    RxToast.error("验证码有误，请重新输入");
                }
                break;

            case R.id.login_txt_pwd:
                //密码登录
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;

            default:
                break;
        }
    }

    private void login() {

    }
}
