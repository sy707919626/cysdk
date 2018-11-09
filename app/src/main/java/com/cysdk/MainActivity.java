package com.cysdk;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cysdk.Bean.PlatfromUser;
import com.cysdk.common.widget.RxToast;
import com.cysdk.ui.base.BaseActivity;
import com.cysdk.ui.listen.LoginListen;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.logout)
    Button logout;
    @BindView(R.id.enter_game)
    Button enterGame;
    @BindView(R.id.update_gameInfo)
    Button updateGameInfo;
    @BindView(R.id.Exit_game)
    Button ExitGame;
    @BindView(R.id.pay)
    Button pay;
    @BindView(R.id.get_gameInfo)
    Button getGameInfo;
    private InitManager initManager;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initManager = InitManager.init(getApplication(), this,true);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxToast.warning("点击了");
                initManager.login(new LoginListen() {
                    @Override
                    public void login_failure(String reason) {
                        RxToast.warning(reason);
                    }

                    @Override
                    public void login_success(PlatfromUser user) {

                    }
                });
            }
        });
    }

    /**
     * 主页监听按两次返回键退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finishAll();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
