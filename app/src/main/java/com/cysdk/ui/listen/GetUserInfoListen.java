package com.cysdk.ui.listen;


import com.cysdk.Bean.PlatfromUser;

/**
 * @author xh E-mail:825378291@qq.com
 * @version 创建时间：2017-3-30 上午11:16:53
 * 获取用户信息
 */
public interface GetUserInfoListen {
	/**
	 * 获取用户信息失败
	 * @param reason
	 */
	public void get_user_info_failure(String reason);

	/**
	 * 获取用户信息成功
	 */
	public void get_user_info_success(PlatfromUser platfromUser);
}

