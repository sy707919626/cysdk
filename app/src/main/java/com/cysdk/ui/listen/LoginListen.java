package com.cysdk.ui.listen;


import com.cysdk.Bean.PlatfromUser;

/**
 * @author xh E-mail:825378291@qq.com
 * @version 创建时间：2017-3-29 上午11:11:40
 * 登陆监听
 */
public interface LoginListen {
	/**
	 * 登陆失败
	 * xh
	 * 2017-3-29 下午3:36:46
	 * @param reason
	 */
	public void login_failure(String reason);
	/**
	 * 登陆成功
	 * xh
	 * 2017-3-29 下午3:36:54
	 * @param user
	 */
	public void login_success(PlatfromUser user);

}
