package org.com.vn.loxleycolour.c2dm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.com.vn.loxleycolour.LoxleyColourActivity;
import org.com.vn.loxleycolour.v1.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class MyC2dmReceiver extends BroadcastReceiver {
	private static String KEY = "c2dmPref";
	private static String REGISTRATION_KEY = "registrationKey";

	private Context context;

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.d("C2DM---------------------------", "AAAAAAAAAAAAAAAAAAAAAAAAAAA");
		this.context = context;
		if (intent.getAction().equals(
				"com.google.android.c2dm.intent.REGISTRATION")) {
			handleRegistration(context, intent);
		} else if (intent.getAction().equals(
				"com.google.android.c2dm.intent.RECEIVE")) {
			handleMessage(context, intent);
		}
	}

	// Better do this in an asynchronous thread
	private void sendRegistrationIdToServer(String deviceId,
			String registrationId) {

		Log.d("C2DM--------------------------------",
				"Sending registration ID to my application server");
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://your_url/register");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			// Get the deviceID
			nameValuePairs.add(new BasicNameValuePair("deviceid", deviceId));
			nameValuePairs.add(new BasicNameValuePair("registrationid",
					registrationId));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				Log.e("HttpResponse", line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleRegistration(Context context, Intent intent) {
		String registration = intent.getStringExtra("registration_id");
		if (intent.getStringExtra("error") != null) {
			// Registration failed, should try again later.
			Log.d("c2dm", "registration failed");
			String error = intent.getStringExtra("error");
			if (error == "SERVICE_NOT_AVAILABLE") {
				Log.d("c2dm", "SERVICE_NOT_AVAILABLE");
			} else if (error == "ACCOUNT_MISSING") {
				Log.d("c2dm", "ACCOUNT_MISSING");
			} else if (error == "AUTHENTICATION_FAILED") {
				Log.d("c2dm", "AUTHENTICATION_FAILED");
			} else if (error == "TOO_MANY_REGISTRATIONS") {
				Log.d("c2dm", "TOO_MANY_REGISTRATIONS");
			} else if (error == "INVALID_SENDER") {
				Log.d("c2dm", "INVALID_SENDER");
			} else if (error == "PHONE_REGISTRATION_ERROR") {
				Log.d("c2dm", "PHONE_REGISTRATION_ERROR");
			}
		} else if (intent.getStringExtra("unregistered") != null) {
			Log.d("c2dm", "unregistered");

		} else if (registration != null) {
			Log.d("c2dm", registration);
			Editor editor = context.getSharedPreferences(KEY,
					Context.MODE_PRIVATE).edit();
			editor.putString(REGISTRATION_KEY, registration);
			editor.commit();

			// Send the registration ID to the 3rd party site that is sending
			// the messages.
			// This should be done in a separate thread.
			// When done, remember that all registration is done.
		}
	}

	private void handleMessage(Context context, Intent intent) {
		String data = intent.getStringExtra("data.payload");
		addNotifiyCation(data);
		// Do whatever you want with the message
	}

	private void addNotifiyCation(String text) {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) this.context
				.getSystemService(ns);
		int icon = R.drawable.icon;
		CharSequence tickerText = text;
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, tickerText, when);

		Context context = this.context;
		CharSequence contentTitle = this.context.getResources().getString(
				R.string.app_name);
		CharSequence contentText = text;
		Intent notificationIntent = new Intent(this.context,
				LoxleyColourActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this.context,
				0, notificationIntent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);

		int HELLO_ID = 1;

		mNotificationManager.notify(HELLO_ID, notification);

	}
}