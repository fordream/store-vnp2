package org.vnp.peaps;

import org.vnp.peaps.base.AccountDB;
import org.vnp.peaps.base.MBaseActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.android.gms.auth.sample.helloauth.GoogleApi;
import com.google.android.gms.auth.sample.helloauth.IAbstractGetNameTaskCallBack;
import com.vnp.core.common.CommonAndroid;

public class MainActivity extends MBaseActivity implements OnClickListener {
	private GoogleApi googleApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		googleApi = new GoogleApi(this, (TextView) findViewById(R.id.main_txt));
		findViewById(R.id.imageView2).setOnClickListener(this);

		registerReceiver(broadcastReceiver, new IntentFilter(
				IAbstractGetNameTaskCallBack.ACTION_CALLBACK));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String str = intent
					.getStringExtra(IAbstractGetNameTaskCallBack.CALLBACK_VALUE);
			String email = intent
					.getStringExtra(IAbstractGetNameTaskCallBack.CALLBACK_EMAIL);
			int type = intent.getIntExtra(
					IAbstractGetNameTaskCallBack.CALLBACK_TYPE,
					IAbstractGetNameTaskCallBack.CALLBACK_TYPEEROR);

			if (type == IAbstractGetNameTaskCallBack.CALLBACK_TYPEEROR) {
				CommonAndroid.showDialog(MainActivity.this, str, null);
			} else {
				// CommonAndroid.showDialog(MainActivity.this, email + str,
				// null);

				AccountDB db = new AccountDB(MainActivity.this);
				db.saveEmail(email);
				db.saveMyInfor(str);
				startActivity(new Intent(MainActivity.this,
						PeopleScreenActivity.class));

				finish();

			}
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
//		resize(findViewById(R.id.imageView2), 191, 36, 0);
//		resize(findViewById(R.id.main_spacing), 191, 100, 0);
//		resize(findViewById(R.id.imageView1), 166, 64, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		googleApi.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.imageView2) {
			googleApi.greetTheUser();
		}
	}
}
