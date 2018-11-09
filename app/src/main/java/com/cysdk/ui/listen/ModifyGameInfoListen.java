package com.cysdk.ui.listen;

/**
	修改游戏信息监听
 */
public interface ModifyGameInfoListen {
	/**
	 * 修改游戏信息失败 xh 2017-3-29 下午3:37:58
	 * @param reason
	 */
	public void modify_game_info_failure(String reason);

	/**
	 * 修改游戏成功 xh 2017-3-29 下午3:39:03
	 *
	 * @param json
	 */
	public void modify_game_info_success(String json);
}

