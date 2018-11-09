package com.cysdk.ui.activity;

import android.content.Intent;
import android.os.Bundle;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MARK on 2018/6/8.
 */

public class RegisterUserActivity extends BaseActivity {

    @BindView(R.id.image_back_title_bar)
    ImageView imageBackTitleBar;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.register_user_edit_name)
    ClearEditText registerUserEditName;
    @BindView(R.id.register_user_new_pwd1)
    EditText registerUserNewPwd1;
    @BindView(R.id.play_Password1)
    CheckBox playPassword1;
    @BindView(R.id.register_user_new_pwd2)
    EditText registerUserNewPwd2;
    @BindView(R.id.play_Password2)
    CheckBox playPassword2;
    @BindView(R.id.register_user_btn_submit)
    Button registerUserBtnSubmit;
    @BindView(R.id.register_phone_text)
    TextView registerPhoneText;
    @BindView(R.id.register_phone_protocol)
    LinearLayout registerPhoneProtocol;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_registeruser;
    }

    @Override
    protected void init() {
        textContent.setText("用户名注册");

        playPassword1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //选择状态 显示明文--设置为可见的密码
                    registerUserNewPwd1.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    registerUserNewPwd1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        playPassword2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //选择状态 显示明文--设置为可见的密码
                    registerUserNewPwd2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    registerUserNewPwd2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }

    @OnClick({R.id.register_user_btn_submit, R.id.register_phone_text, R.id.register_phone_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_user_btn_submit:
                String pwd = registerUserNewPwd1.getText().toString().trim();
                String pwdAgain = registerUserNewPwd2.getText().toString().trim();

                if (registerUserEditName.getText().toString().trim().equals("")) {
                    RxToast.warning("请输入用户名");
                } else if (pwd.equals("") || pwdAgain.equals("")) {
                    RxToast.warning( "密码不能为空!");
                } else if (pwd.length() < 6 || pwd.length() > 12) {
                    RxToast.warning("请输入6到12位的密码!");
                } else if (IDCard.isNumeric(pwd) || IDCard.isLetter(pwd)) {
                    RxToast.warning("密码不能为纯数字或纯字母!");
                } else if (pwd.equals(pwdAgain)) {
                    //处理注册逻辑
                }

                break;

            case R.id.register_phone_text:
                //手机注册
                startActivity(new Intent(this, RegisterPhoneActivity.class));
                finish();
                break;

            case R.id.register_phone_protocol:
                //用户协议
                break;

            default:
                break;
        }
    }


}
