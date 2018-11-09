package com.cysdk.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;


public class PlatfromUser {
	private String token;//用户token
	private String id;//用户id和游戏方绑定时使用
	private String name;//用户名
	private long creat_time;//创建时间
	private long last_login_time;//上一次登陆时间
	private long login_time;//登陆时间
	private String json;
	private int ismobile;//是否绑定手机
	private boolean login=false;//是否登陆
	private String mobiles;	//手机号

	public int getIsmobile() {
		return ismobile;
	}


	public void setIsmobile(int ismobile) {
		this.ismobile = ismobile;
	}


	public String getMobiles() {
		return mobiles;
	}


	public void setMobiles(String mobile) {
		this.mobiles = mobile;
	}


	public boolean ismobile() {
		return ismobile==1;
	}


	public boolean isLogin() {
		return login;
	}
	public String getToken() {
		return token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public long getCreat_time() {
		return creat_time;
	}
	public long getLast_login_time() {
		return last_login_time;
	}
	public long getLogin_time() {
		return login_time;
	}

	public String getJson() {
		return json;
	}
	public static PlatfromUser init(String json) throws Exception{
		PlatfromUser platfromUser=new PlatfromUser();
		platfromUser.json=json;
		JSONObject jo=new JSONObject(json);
		platfromUser.token=jo.getString("token");
		platfromUser.name=jo.getString("username");
		platfromUser.ismobile=jo.getInt("ismobile");
		platfromUser.last_login_time=time_to_mill1(jo.getString("login_time"));
		platfromUser.login_time=System.currentTimeMillis();
		platfromUser.creat_time=time_to_mill1(jo.getString("reg_time"));
		platfromUser.id=jo.getString("uid");
		//手机号
		platfromUser.mobiles = jo.getString("mobiles");

//		UserAccount userAccount=new UserAccount();
//		userAccount.setId(jo.getString("uid"));
//		userAccount.setIs_login(true);
//		userAccount.setName(jo.getString("username"));
//		userAccount.setToken(jo.getString("token"));
//		QPSdkManager.get_sdk_manager().getSave().insert_user(userAccount);
//		QPSdkManager.get_sdk_manager().send_login();
		return platfromUser;
	}
	/**
	 * 将时间转化为时间戳 时间格式为 yyyy-MM-dd HH:mm:ss
	 *
	 * @param time
	 * @return
	 */
	public static long time_to_mill1(String time) {
		long mill = 0l;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = simpleDateFormat.parse(time);
			mill = date.getTime();
		} catch (Exception e) {
			// TODO: handle exception

		}
		return mill;
	}
}