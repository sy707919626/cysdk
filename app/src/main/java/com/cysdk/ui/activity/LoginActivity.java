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
import com.cysdk.common.widget.RxToast;
import com.cysdk.common.widget.SPUtils;
import com.cysdk.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @BindView(R.id.edit_name)
    ClearEditText editName;
    @BindView(R.id.edit_pwd)
    ClearEditText editPwd;
    @BindView(R.id.text_forgetpwd)
    TextView textForgetpwd;
    @BindView(R.id.text_phone_register)
    TextView textPhoneRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.login_txt_code)
    TextView loginTxtCode;
    @BindView(R.id.login_linearLayout_register)
    LinearLayout loginLinearLayoutRegister;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    private SPUtils sp;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    private ProgressDialogHandler dialogHandler;

    @Override
    protected void init() {
        dialogHandler = new ProgressDialogHandler(this);
        sp = SPUtils.getInstance(this);
        String name = sp.getString("name");
        String pwd = sp.getString("pwd");

        editName.setText(name);
        editPwd.setText(pwd);
    }

    @OnClick({R.id.text_forgetpwd, R.id.text_phone_register, R.id.btn_login,
            R.id.login_txt_code, R.id.login_linearLayout_register})
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

            case R.id.btn_login:
                //登录
                if (editName.getText().toString().trim().equals("")) {
                    RxToast.warning("用户名不能为空");
                } else if (editPwd.getText().toString().trim().equals("")) {
                    RxToast.warning("密码不能为空");
                } else {
                    login();
                }
                break;

            case R.id.login_txt_code:
                //验证码登录
                startActivity(new Intent(this, PhoneLoginActivity.class));
                finish();
                break;

            case R.id.login_linearLayout_register:
                //一键注册

                break;
            default:
                break;
        }
    }

    private void login() {
        dialogHandler.showProgressDialog();

        final String userId = editName.getText().toString();
        final String pwd = editPwd.getText().toString();


        InitManager.mApi.equipmentTypeList("")
                .compose(RxHttpResponseCompat.<String>compatResult())
                .subscribe(new ErrorHandlerSubscriber<String>() {
                    @Override
                    public void onNext(String s) {

                        dialogHandler.dismissProgressDialog();

                        sp.put("name", userId);
                        sp.put("pwd", pwd);
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
