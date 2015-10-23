package org.com.vn.loxleycolour;

import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour.database.DataStore;
import org.com.vn.loxleycolour.v1.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushPreferences;

public class LoxleyColourActivity extends Activity implements Const {
	// private boolean isRun = true;
	PushPreferences pushPreferences = PushManager.shared().getPreferences();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		DataStore.init(this);
		DataStore.getInstance().saveConfig(LOGIN, false);

		boolean isEnablePush = pushPreferences.isPushEnabled();
//		if (!isEnablePush) {
			PushManager.enablePush();
			pushPreferences.setSoundEnabled(true);
			pushPreferences.setSoundEnabled(true);
//		} else {
//			PushManager.disablePush();
//			pushPreferences.setSoundEnabled(false);
//			pushPreferences.setSoundEnabled(false);
//		}

		DataStore.getInstance().saveConfig(USER, "shaunferry86@gmail.com");
		DataStore.getInstance().saveConfig(PASSWORD, "password9");

		// c2md
		/*
		 * Intent registrationIntent = new Intent(
		 * "com.google.android.c2dm.intent.REGISTER");
		 * 
		 * registrationIntent.putExtra("app", PendingIntent.getBroadcast(this,
		 * 0, new Intent(), 0));
		 * 
		 * registrationIntent.putExtra("sender", "cs@loxleycolour.com");
		 * 
		 * startService(registrationIntent);
		 */

		// register(null);
		// addNotifiyCation();

		// start service

		// Intent intent = new Intent(this, NotifycationService.class);
		// startService(intent);

		// new CustomDialog(this).show();
		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... params) {

				try {
					Thread.sleep(2000);
				} catch (Exception e) {
				}
				return null;
			}

			protected void onPostExecute(String result) {
				Intent intent = new Intent(LoxleyColourActivity.this,
						CustomTabsActivity.class);
				startActivity(intent);
				finish();

			};

		}.execute("");

	}

	private void register(View view) {
		String email = "cs@loxleycolour.com";
		email = "vuongvantruong1987@gmail.com";
		Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
		intent.putExtra("app",
				PendingIntent.getBroadcast(this, 0, new Intent(), 0));
		intent.putExtra("sender", email);
		startService(intent);
	}

	@Override
	protected void onStop() {
		// isRun = false;
		finish();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}