package com.cysdk.Bean;

/**
 * Created by Administrator on 2018/7/27.
 */

public class BaseBean<T> {
    private int state = -1;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return state == 1;
    }

}
