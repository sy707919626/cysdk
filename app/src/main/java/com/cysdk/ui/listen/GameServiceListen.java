package com.cysdk.ui.listen;
/**
 * @author xh E-mail:825378291@qq.com
 * @version 创建时间：2017-3-29 下午3:51:17
 * 注册游戏区服
 */
public interface GameServiceListen {
	/**
	 * 注册区服失败
	 * @param reason
	 */
	public void game_service_failure(String reason);
	/**
	 * 注册区服成功
	 * @param json
	 */
	public void game_service_success(String json);
}
