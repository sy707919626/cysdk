package com.cysdk.ui.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cysdk.R;
import com.cysdk.common.widget.ClearEditText;
import com.cysdk.common.widget.IDCard;
import com.cysdk.common.widget.MyCountDownTimer;
import com.cysdk.common.widget.ProjectUtil;
import com.cysdk.common.widget.RxToast;
import com.cysdk.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MARK on 2018/6/8.
 */

public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.image_back_title_bar)
    ImageView imageBackTitleBar;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.forgetpwd_edit_name)
    ClearEditText forgetpwdEditName;
    @BindView(R.id.forgetpwd_edit_code)
    ClearEditText forgetpwdEditCode;
    @BindView(R.id.forgetpwd_btn_getcode)
    Button forgetpwdBtnGetcode;
    @BindView(R.id.forgetpwd_new_pwd)
    EditText forgetpwdNewPwd;
    @BindView(R.id.play_Password)
    CheckBox playPassword;
    @BindView(R.id.forgetpwd_btn_submit)
    Button forgetpwdBtnSubmit;
    @BindView(R.id.forgetpwd_phone_unavailable)
    TextView forgetpwdPhoneUnavailable;

    private String tempMobile;
    private String Code = "";
    @Override
    protected int setLayoutId() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    protected void init() {
        textContent.setText("忘记密码");

        playPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //选择状态 显示明文--设置为可见的密码
                    forgetpwdNewPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    forgetpwdNewPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        forgetpwdBtnGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000, forgetpwdBtnGetcode);
                tempMobile = forgetpwdEditName.getText().toString().trim();
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

    @OnClick({R.id.forgetpwd_btn_submit, R.id.forgetpwd_phone_unavailable})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forgetpwd_btn_submit:
                String pwd = forgetpwdNewPwd.getText().toString().trim();

                if (forgetpwdEditName.getText().toString().trim().equals("")) {
                    RxToast.warning("请输入手机号");
                } else if (forgetpwdEditCode.getText().toString().trim().equals("")) {
                    RxToast.warning("请输入验证码");
                } else if (pwd.equals("")) {
                    RxToast.warning("请输入新密码");
                } else if (pwd.length() < 6 || pwd.length() > 12) {
                    RxToast.warning("请输入6到12位的密码!");
                } else if (IDCard.isNumeric(pwd) || IDCard.isLetter(pwd)) {
                    RxToast.warning("密码不能为纯数字或纯字母!");
                }else if (forgetpwdEditCode.getText().toString().trim().equals(Code)) {
                    //处理修改密码

                } else {
                    RxToast.error("验证码有误，请重新输入");
                }
                break;

            case R.id.forgetpwd_phone_unavailable:
                //手机不可用时
                break;

                default:
                    break;
        }
    }


}
