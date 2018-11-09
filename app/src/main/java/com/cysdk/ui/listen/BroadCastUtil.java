package com.cysdk.ui.listen;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.cysdk.InitManager;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;


public class BroadCastUtil {
	/**
	 * 启动一个服务
	 *
	 * @param context
	 */
	public static void start_service(Context context, String service_name,
									 String action) {
		if (context == null || (service_name == null && action == null))
			return;
		Intent service = new Intent();
		if (service_name != null)
			service.setClassName(context, service_name);
		if (action != null)
			service.setAction(action);
		context.startService(service);
	}


	/**
	 * 启动一个服务
	 *
	 * @param context
	 */
	public static void start_service_action(Context context, String action) {
		start_service(context, null, action);
	}

	/**
	 * 启动一个服务
	 *
	 * @param context
	 */
	public static void bind_service_action(Context context, String action,
										   String service_name, ServiceConnection conn) {
		if (context == null || (action == null && service_name == null))
			return;
		Intent service = new Intent();
		if (action != null)
			service.setAction(action);
		if (service_name != null)
			service.setClassName(context, service_name);
		context.bindService(service, conn, Service.BIND_AUTO_CREATE);
	}

	/**
	 * 启动一个服务
	 *
	 * @param context
	 */
	public static void bind_service_action(Context context, String action,
										   ServiceConnection conn) {
		bind_service_action(context, action, null, conn);
	}

	/**
	 * 启动一个服务
	 *
	 * @param context
	 */
	public static void bind_service(Context context, String service_name,
									ServiceConnection conn) {
		bind_service_action(context, null, service_name, conn);
	}

	/**
	 * 停止一个服务
	 *
	 * @param context
	 * @param action
	 */
	public static void stop_service(Context context, String service_name,
									String action) {
		if (context == null || (service_name == null && action == null))
			return;
		Intent service = new Intent();
		if (service_name != null)
			service.setClassName(context, service_name);
		if (action != null)
			service.setAction(action);
		context.stopService(service);
	}

	/**
	 * 停止一个服务
	 *
	 * @param context
	 * @param action
	 */
	public static void stop_service_action(Context context, String action) {
		stop_service(context, null, action);
	}

	public static void send_broadcast(Intent intent){
		send_broadcast(InitManager.mApplication, intent);
	}

	/**
	 * 发送广播
	 * xh
	 * 2017-3-30 上午11:32:23
	 * @param context
	 * @param intent
	 */
	public static void send_broadcast(Context context,Intent intent){
		context.sendBroadcast(intent);
	}


	/**
	 * 注册广播
	 *
	 * @param context
	 * @param receiver
	 * @param filter
	 * @param broadcastPermission
	 * @param scheduler
	 */
	public static void register_broadcast(Context context,
										  BroadcastReceiver receiver, IntentFilter filter,
										  String broadcastPermission, Handler scheduler) {
		if (context == null || filter == null || filter.countActions() == 0)
			return;
		context.registerReceiver(receiver, filter, broadcastPermission,
				scheduler);
	}

	/**
	 * 注册广播
	 *
	 * @param context
	 * @param receiver
	 * @param actions
	 * @param priority
	 * @param receiver_permissions
	 * @param scheduler
	 */
	public static void register_broadcast(Context context,
										  BroadcastReceiver receiver, List<String> actions, int priority,
										  String receiver_permissions, Handler scheduler) {
		if (actions == null || actions.size() == 0||receiver==null)
			return;
		IntentFilter filter = new IntentFilter();
		if (priority < 0)
			priority = 0;
		else if (priority > 1000)
			priority = 1000;
		filter.setPriority(priority);
		for (String action : actions) {
			filter.addAction(action);
		}
		register_broadcast(context, receiver, filter, receiver_permissions,
				scheduler);
	}

	/**
	 * 注销广播
	 *
	 * @param receiver
	 */
	public static void un_register_broadcast(BroadcastReceiver receiver) {
		un_register_broadcast(InitManager.mApplication, receiver);
	}

	/**
	 * 注销广播
	 *
	 * @param receiver
	 */
	public static void un_register_broadcast(Context context,
											 BroadcastReceiver receiver) {
		if (context != null && receiver != null)
			context.unregisterReceiver(receiver);

	}

	/**
	 * 终结广播
	 *
	 * @param broadcastReceiver
	 */
	public static void end_broadcast(BroadcastReceiver broadcastReceiver) {
		broadcastReceiver.abortBroadcast();
	}
}
