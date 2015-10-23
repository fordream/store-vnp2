package org.com.vn.loxleycolour.service;

import org.com.vn.loxleycolour.LoxleyColourActivity;
import org.com.vn.loxleycolour.v1.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class NotifycationService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	public void onCreate() {
		super.onCreate();
		// CommonView.makeText(getBaseContext(), "onCreate");
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		// CommonView.makeText(getBaseContext(), flags + " onStartCommand "
		// + startId);
		
		if(isRun){
			return 1;
		}
		isRun = true;
		
		while(isRun){
			
		}
		//addNotifiyCation();
		
		return 1;
	}

	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);

		// CommonView.makeText(getBaseContext(), "onStart" + startId);
	}

	boolean isRun = false;
	public void onDestroy() {
		// CommonView.makeText(getBaseContext(), "onDestroy");
		isRun = false;
		super.onDestroy();
		
	}

	private void addNotifiyCation() {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		int icon = R.drawable.icon;
		CharSequence tickerText = "Hello";
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, tickerText, when);

		Context context = getApplicationContext();
		CharSequence contentTitle = getResources().getString(R.string.app_name);
		CharSequence contentText = getResources().getString(R.string.update);
		Intent notificationIntent = new Intent(this, LoxleyColourActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);

		int HELLO_ID = 1;

		mNotificationManager.notify(HELLO_ID, notification);

	}
}
