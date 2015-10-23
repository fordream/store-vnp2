/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vnp.mr.mintmedical;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import vnp.mr.mintmedical.callback.CustomerUpdateCallback;
import vnp.mr.mintmedical.item.UserLogin;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.viewpagerindicator.db.DBGcm;
import com.viewpagerindicator.db.DBUserLogin;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.LogUtils;

/**
 * Handling of GCM messages.
 */
public class GcmBroadcastReceiver extends BroadcastReceiver {
	static final String TAG = "GCMDemo";
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	@Override
	public void onReceive(Context context, Intent intent) {
		LogUtils.e("ABC", "have a message");

		DBUserLogin dbUserLogin = new DBUserLogin(context);
		UserLogin login = ((UserLogin) dbUserLogin.getData());
		if (!dbUserLogin.isLogin()) {
			return;
		}
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
		String messageType = gcm.getMessageType(intent);
		new DBGcm(context).save(intent.getExtras().getString("message"));

		String message = intent.getStringExtra("message");
		if (message == null) {
			message = intent.getStringExtra("Message");
		}

		String status = intent.getStringExtra("status");
		long time = System.currentTimeMillis();
		Notification notif = new Notification(R.drawable.ic_launcher, message,
				time);

		notif.flags = Notification.FLAG_AUTO_CANCEL;
		notif.defaults |= Notification.DEFAULT_SOUND;
		notif.defaults |= Notification.DEFAULT_VIBRATE;
		Intent notificationIntent = new Intent(context, S11Activity.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		String title = context.getString(R.string.app_name);
		notif.setLatestEventInfo(context, title, message, contentIntent);
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(ns);
		mNotificationManager.notify(1, notif);

		/*
		 * 
		 * Intent trIntent = new Intent(context, S11Activity.class);
		 * trIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * context.startActivity(trIntent);
		 */
	}

	public static void register(final Context context) {
		final String TAG = "register-push";
		// final SharedPreferences preferences =
		// context.getSharedPreferences(TAG,
		// 0);

		final String SENDER_ID = "498720258430";// "27284071298";//609478506422

		// final String SERVER = "http://vnpmanager.esy.es/gcm/register.php";
		// final String SERVER = MintUtils.URL_PUSH;

		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... _params) {
				GoogleCloudMessaging gcm = GoogleCloudMessaging
						.getInstance(context);
				try {
					String regid = gcm.register(SENDER_ID);
					new ExeCallBack()
							.executeCallBack(new CustomerUpdateCallback(
									context, regid));
				} catch (IOException e) {
				}
				// String deviceId = Secure.getString(
				// context.getContentResolver(), Secure.ANDROID_ID);
				return null;
			}

			@Override
			protected void onPostExecute(String msg) {
			}
		}.execute(null, null, null);
	}

	static public enum RequestMethod {
		GET, POST, PUT, DELETE
	}

	static class RestClient {

		public static final int TIME_OUT = 10 * 1000;
		public static final int BUFFER = 1024 * 2;
		private ArrayList<NameValuePair> params;
		private ArrayList<NameValuePair> headers;

		private String url;

		private int responseCode;
		private String message;

		private String response;

		public String getResponse() {
			return response;
		}

		public String getErrorMessage() {
			return message;
		}

		public int getResponseCode() {
			return responseCode;
		}

		public RestClient(String url) {
			this.url = url;
			params = new ArrayList<NameValuePair>();
			headers = new ArrayList<NameValuePair>();
		}

		public void addParam(String name, String value) {
			params.add(new BasicNameValuePair(name, value));
		}

		public void addHeader(String name, String value) {
			headers.add(new BasicNameValuePair(name, value));
		}

		public void execute(RequestMethod method) throws Exception {
			switch (method) {
			case GET: {
				// add parameters
				String combinedParams = "";
				if (!params.isEmpty()) {
					combinedParams += "?";
					for (NameValuePair p : params) {
						String paramString = p.getName() + "="
								+ URLEncoder.encode(p.getValue(), "UTF-8");
						if (combinedParams.length() > 1) {
							combinedParams += "&" + paramString;
						} else {
							combinedParams += paramString;
						}
					}
				}

				HttpGet request = new HttpGet(url + combinedParams);

				// add headers
				for (NameValuePair h : headers) {
					request.addHeader(h.getName(), h.getValue());
				}

				this.executeRequest(request, url);
				break;
			}
			case POST: {
				HttpPost request = new HttpPost(url);

				// add headers
				for (NameValuePair h : headers) {
					request.addHeader(h.getName(), h.getValue());
				}

				if (!params.isEmpty()) {
					request.setEntity(new UrlEncodedFormEntity(params,
							HTTP.UTF_8));
				}

				this.executeRequest(request, url);
				break;
			}
			case PUT: {
				HttpPut request = new HttpPut(url);
				// add headers
				for (NameValuePair h : headers) {
					request.addHeader(h.getName(), h.getValue());
				}
				if (!params.isEmpty()) {
					request.setEntity(new UrlEncodedFormEntity(params,
							HTTP.UTF_8));
				}
				this.executeRequest(request, url);
				break;

			}
			case DELETE: {
				HttpDelete request = new HttpDelete(url);
				// add headers
				for (NameValuePair h : headers) {
					request.addHeader(h.getName(), h.getValue());
				}
				this.executeRequest(request, url);
				break;

			}
			}
		}

		private void executeRequest(HttpUriRequest request, String url) {

			int timeout = TIME_OUT;
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, timeout);
			HttpConnectionParams.setSoTimeout(httpParameters, timeout);

			HttpClient client = new DefaultHttpClient(httpParameters);

			HttpResponse httpResponse;

			try {
				httpResponse = client.execute(request);
				responseCode = httpResponse.getStatusLine().getStatusCode();
				message = httpResponse.getStatusLine().getReasonPhrase();

				HttpEntity entity = httpResponse.getEntity();

				if (entity != null) {
					response = EntityUtils.toString(entity);
				}

			} catch (ClientProtocolException e) {
				client.getConnectionManager().shutdown();
			} catch (IOException e) {
				client.getConnectionManager().shutdown();
			}
		}

	}
}
