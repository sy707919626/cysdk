package com.cysdk.ui.listen;
/**
 * @author xh E-mail:825378291@qq.com
 * @version 创建时间：2017-3-29 下午3:44:19
 * 支付监听
 */
public interface PayListen {
	/**
	 * 支付失败回调
	 * @param reason
	 */
	public void pay_failure(String json);
	/**
	 * 支付成功回调
	 * @param json
	 */
	public void pay_success(String json);
}
