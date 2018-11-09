package com.cysdk.Bean;

/**
 * @description：
 * @author：bux on 2018/9/10 23:14
 * @email: 471025316@qq.com
 */
public class LoginBean {

    @Override
    public String toString() {
        return "LoginBean{" +
                "userName='" + userName + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }

    /**
     * userName : 七区
     * Phone : 111111111
     */

    private String userName;
    private String Phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
}
