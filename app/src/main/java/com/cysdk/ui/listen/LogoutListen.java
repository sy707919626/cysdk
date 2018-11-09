package com.cysdk.ui.listen;
/**
 * @author xh E-mail:825378291@qq.com
 * @version 创建时间：2017-3-30 上午11:16:53
 * 退出登陆监听
 */
public interface LogoutListen {
	/**
	 * 退出登陆成功
	 * xh
	 * 2017-3-30 上午11:18:42
	 * @param reason
	 */
	public void logout_failure(String reason);
	/**
	 * 退出登陆失败
	 * xh
	 * 2017-3-30 上午11:18:53
	 * @param json
	 */
	public void logout_success(String json);
}
