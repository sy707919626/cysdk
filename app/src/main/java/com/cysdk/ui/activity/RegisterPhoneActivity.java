package com.cysdk.ui.activity;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cysdk.R;
import com.cysdk.common.widget.ClearEditText;
import com.cysdk.common.widget.IDCard;
import com.cysdk.common.widget.MyCountDownTimer;
import com.cysdk.common.widget.ProjectUtil;
import com.cysdk.common.widget.RxToast;
import com.cysdk.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MARK on 2018/6/8.
 */

public class RegisterPhoneActivity extends BaseActivity {

    @BindView(R.id.image_back_title_bar)
    ImageView imageBackTitleBar;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.register_phone_edit_name)
    ClearEditText registerPhoneEditName;
    @BindView(R.id.register_phone_edit_code)
    ClearEditText registerPhoneEditCode;
    @BindView(R.id.register_phone_btn_getcode)
    Button registerPhoneBtnGetcode;
    @BindView(R.id.register_phone_new_pwd)
    EditText registerPhoneNewPwd;
    @BindView(R.id.play_Password)
    CheckBox playPassword;
    @BindView(R.id.register_phone_btn_submit)
    Button registerPhoneBtnSubmit;
    @BindView(R.id.register_user_text)
    TextView registerUserText;
    @BindView(R.id.register_user_protocol)
    LinearLayout registerUserProtocol;

    private String tempMobile;
    private String Code = "";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_registerphone;
    }

    @Override
    protected void init() {
        textContent.setText("手机注册");

        playPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //选择状态 显示明文--设置为可见的密码
                    registerPhoneNewPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    registerPhoneNewPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        registerPhoneBtnGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000, registerPhoneBtnGetcode);
                tempMobile = registerPhoneBtnGetcode.getText().toString().trim();
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

    @OnClick({R.id.register_phone_btn_submit, R.id.register_user_text, R.id.register_user_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_phone_btn_submit:
                String pwd = registerPhoneNewPwd.getText().toString().trim();

                if (registerPhoneEditName.getText().toString().trim().equals("")) {
                    RxToast.warning("请输入手机号");
                } else if (registerPhoneEditCode.getText().toString().trim().equals("")) {
                    RxToast.warning("请输入验证码");
                } else if (pwd.equals("")) {
                    RxToast.warning("请输入新密码");
                } else if (pwd.length() < 6 || pwd.length() > 12) {
                    RxToast.warning("请输入6到12位的密码!");
                } else if (IDCard.isNumeric(pwd) || IDCard.isLetter(pwd)) {
                    RxToast.warning("密码不能为纯数字或纯字母!");
                }else if (registerPhoneEditCode.getText().toString().trim().equals(Code)) {
                    //手机注册逻辑

                } else {
                    RxToast.error("验证码有误，请重新输入");
                }
                break;

            case R.id.register_user_text:
                //用户注册
                startActivity(new Intent(this, RegisterUserActivity.class));
                finish();
                break;

            case R.id.register_user_protocol:
                //用户协议
                break;

            default:
                break;
        }
    }

}
