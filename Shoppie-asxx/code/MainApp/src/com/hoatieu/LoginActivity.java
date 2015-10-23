package com.hoatieu;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;

import com.hoatieu.service.HoatieuServerManager;

@SuppressLint("NewApi")
public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText txtphoneNumber;
	private EditText txtshipName;
	/**
	 * tvuong1
	 */
	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			showProgressDialog(false);
			if (HoatieuServerManager.getIntance().mService.isLogined()) {
				Intent _intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(_intent);
				finish();
			} else {
				String message = intent.getStringExtra("message");
				Utility.showDialogAlert(null, message, getActivity());
			}
		}
	};

	@Override
	public void onClick(View v) {
		if (Utility.checkInternetConnection(getActivity()) == true) {
			if (checkValidation()) {
				checkLogin();
			}
		}
	}

	private void checkLogin() {
		showProgressDialog(true);
		String localName = txtphoneNumber.getText().toString().trim();
		String localMobile = txtshipName.getText().toString().trim();
		String localID = getStringFromView(R.id.sohieu);
		HoatieuServerManager.getIntance().mService.login(localName, localMobile, localID);
	}

	public void errorRegis(Throwable exception) {

	}

	private boolean checkValidation() {
		if (Utility.isblankOrNull(getStringFromView(R.id.sohieu))
				|| Utility.isblankOrNull(txtphoneNumber.getText().toString())
				|| Utility.isblankOrNull(txtshipName.getText().toString())) {
			Utility.showDialogAlert(getString(R.string.error),
					getString(R.string.error_meesage_login), getActivity());
			return false;
		}

		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			unregisterReceiver(broadcastReceiver);
		} catch (Exception ex) {
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginfragment);
		// add resize infor
		int width = 300;
		int height = 35;
		int width_input = 205;

		addResizeInfor(R.id.id_main_login, LayoutParams.MATCH_PARENT, resize.getHeightScreen());
		addResizeInfor(R.id.login_content, LayoutParams.MATCH_PARENT, resize.getHeightScreen());
		addResizeInfor(R.id.login_content_phone, width, height);
		addResizeInfor(R.id.login_content_name, width, height);
		addResizeInfor(R.id.login_content_sohieu, width, height);
		addResizeInfor(R.id.btn_login, width, height);
		addResizeInfor(R.id.shipName, width_input, height);
		addResizeInfor(R.id.phone, width_input, height);
		addResizeInfor(R.id.sohieu, width_input, height);
		addResizeInfor(R.id.login_load_center, 200, 200);
		addResizeInfor(R.id.login_load_center_bot, 200, 200 * 80 / 480);

		txtphoneNumber = (EditText) findViewById(R.id.phone);
		txtshipName = (EditText) findViewById(R.id.shipName);

		findViewById(R.id.btn_login).setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(broadcastReceiver, new IntentFilter("LOGIN"));
	}

	@Override
	protected void onStart() {
		super.onStart();
		int textSize = 21;
		resize.setTextsize(findViewById(R.id.shipName), textSize);
		resize.setTextsize(findViewById(R.id.phone), textSize);
		resize.setTextsize(findViewById(R.id.sohieu), textSize);
		resize.setTextsize(findViewById(R.id.btn_login), textSize);
	}
}