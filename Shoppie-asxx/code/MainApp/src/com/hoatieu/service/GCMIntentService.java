package com.hoatieu.service; //Edit this to match the name of your application

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.hoatieu.MainActivity;
import com.hoatieu.PushHistotyActivity;
import com.hoatieu.R;
import com.vnp.core.datastore.DataStore;

public class GCMIntentService extends GCMBaseIntentService {

	public static final String ME = "GCMReceiver";

	public GCMIntentService() {
		super("GCMIntentService");
	}

	private static final String TAG = "GCMIntentService";

	@Override
	public void onRegistered(Context context, String regId) {

		Log.v(ME + ":onRegistered", "Registration ID arrived!");
		Log.v(ME + ":onRegistered", regId);

		JSONObject json;

		try {
			json = new JSONObject().put("event", "registered");
			json.put("regid", regId);

			Log.v(ME + ":onRegisterd", json.toString());

			// Send this JSON data to the JavaScript application above EVENT
			// should be set to the msg type
			// In this case this is the registration ID
			// GCMPlugin.sendJavascript(json);

		} catch (JSONException e) {
			// No message to the user is sent, JSON failed
			Log.e(ME + ":onRegisterd", "JSON exception");
		}
	}

	@Override
	public void onUnregistered(Context context, String regId) {
		Log.d(TAG, "onUnregistered - regId: " + regId);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		String message = intent.getStringExtra("message");
		String status = intent.getStringExtra("status");
		long time = System.currentTimeMillis();

		new HoatieuHistoryPush(context).saveHistory(time, message, status);

		Notification notif = new Notification(R.drawable.icon_push, message, time);

		notif.flags = Notification.FLAG_AUTO_CANCEL;
		notif.defaults |= Notification.DEFAULT_SOUND;
		notif.defaults |= Notification.DEFAULT_VIBRATE;

		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

		List<ActivityManager.RunningTaskInfo> alltasks = am.getRunningTasks(1);
		boolean mRunning = false;

		//
		for (ActivityManager.RunningTaskInfo aTask : alltasks) {
			if (aTask.topActivity.getClassName().toString().contains(PushHistotyActivity.class.getName())) {
				mRunning = true;
			}
		}

		Intent notificationIntent = new Intent(context, PushHistotyActivity.class);

		if (mRunning) {
			sendBroadcast(new Intent("UPDATE_PUSH"));
		}
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		String title = context.getString(R.string.app_name);

		notif.setLatestEventInfo(context, title, message, contentIntent);
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);
		mNotificationManager.notify(1, notif);
	}

	@Override
	public void onError(Context context, String errorId) {
		Log.e(TAG, "onError - errorId: " + errorId);
	}

	/**
	 * 
	 * @param context
	 * @param message
	 * @param type
	 */
	// private void generateNotification(Context context, String message, int
	// type) {
	//
	// int icon = R.drawable.hoa_tieu_icon;
	// switch (type) {
	// case 1:
	// // icon = R.drawable.ic_launcher;
	// break;
	// case 2:
	// // icon = R.drawable.bbs_icon_cphiem;
	// break;
	//
	// default:
	// break;
	// }
	// long when = System.currentTimeMillis();
	// NotificationManager notificationManager = (NotificationManager) context
	// .getSystemService(Context.NOTIFICATION_SERVICE);
	// Notification notification = new Notification(icon, message, when);
	// String title = context.getString(R.string.app_name);
	//
	// Intent notificationIntent = new Intent(context, MainActivity.class);
	// // set intent so it does not start a new activity
	// notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
	// | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	// PendingIntent intent = PendingIntent.getActivity(context, 0,
	// notificationIntent, 0);
	// notification.setLatestEventInfo(context, title, message, intent);
	// notification.flags |= Notification.FLAG_AUTO_CANCEL;
	// notificationManager.notify(type, notification);
	//
	// }

}
