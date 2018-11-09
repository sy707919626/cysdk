package com.cysdk.ui.listen;


/**
 * @author xh E-mail:825378291@qq.com
 * @version 创建时间：2017-3-29 上午11:11:40
 * 退出游戏监听
 */
public interface LogoutGameListen {
	/**
	 * 退出游戏失败
	 * xh
	 * 2017-3-29 下午3:36:46
	 * @param reason
	 */
	public void logout_game_failure(String reason);
	/**
	 * 退出游戏成功
	 * xh
	 * 2017-3-29 下午3:36:54
	 * @param user
	 */
	public void logout_game_success(String json);
}
