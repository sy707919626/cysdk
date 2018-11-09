package com.cysdk.ui.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.cysdk.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity {
    private static final String TAG = "BaseActivity";
    Unbinder mUnbinder;

    public Context mContext;

    /**
     * 获取到前台进程的Activity  就是当前在运行的activity
     */
    private static Activity mForegroundActivity = null;
    /**
     * 用户保存所有用户操作过的activity
     */
    private static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏

        setContentView(setLayoutId());
        mUnbinder = ButterKnife.bind(this);
        synchronized (mActivities) {
            mActivities.add(this);
        }
        initBarStatus();
        init();
    }

    private void initBarStatus() {
        Window window=getWindow();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            //5.0以上的手机
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            ViewGroup content= (ViewGroup) findViewById(android.R.id.content);
            ViewGroup childView= (ViewGroup) content.getChildAt(0);
            if(childView!=null){
                childView.setFitsSystemWindows(true);
            }
            window.setStatusBarColor(getResources().getColor(R.color.btn_blue));
        }else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //4.4以上5.0一下的手机
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ViewGroup content= (ViewGroup) findViewById(android.R.id.content);
            ViewGroup childView= (ViewGroup) content.getChildAt(0);
            if(childView!=null){
                childView.setFitsSystemWindows(true);
            }

            View view=new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getStatusHeight()));
            view.setBackgroundColor(Color.GREEN);
            content.addView(view);
        }
    }

    //获取状态栏高度
 private int getStatusHeight() {
        int statusHeight=-1;
        int resourceId=getResources().getIdentifier("status_bar_height","dimen","android");
        if(resourceId>0){
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mForegroundActivity = null;
    }

    /**
     * 设置布局ID
     *
     * @return
     */
    protected abstract int setLayoutId();


    /**
     * 初始化操作
     */
    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        mActivities.remove(this);
    }

    /**
     * Activity的返回操作调用此方法
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * 获得当前Activity
     *
     * @return
     */
    public static Activity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 关闭所有Activity，除了参数传递的Activity
     */
    public static void finishAll(BaseActivity except) {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            if (activity != except) {
                activity.finish();
            }
        }
    }


    /**
     * 关闭所有Activity
     */
    public static void finishAll() {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
    }
}
