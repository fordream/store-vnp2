package com.example.org.com.driver;

import com.example.org.com.driver.view.ProDB;
import com.example.org.com.driver.view.Proclass;
import com.vnp.core.common.CommonAndroid;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class ChangasswordActivity extends MBaseActivity implements
		OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepass);
		handler.post(this);
		getView(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void run() {
		resize(R.id.login_id_1, LayoutParams.MATCH_PARENT, 50);
		resize(R.id.imageView1, 128, 31);
		resize(R.id.ImageView01, 196, 19);
		resize(R.id.login_id_2, 240, LayoutParams.WRAP_CONTENT);
		setTextSize(R.id.BaseTextView01, 22);
		setTextSize(R.id.baseTextView1, 22);
		setTextSize(R.id.baseTextView1_1, 22);
		setTextSize(R.id.baseEditTextView1_1, 22);
		setTextSize(R.id.baseTextView1_2, 22);
		setTextSize(R.id.baseEditTextView1_2, 22);
		setTextSize(R.id.BaseEditTextView01, 22);
		setTextSize(R.id.baseEditTextView1, 22);
		resize(R.id._BaseEditTextView01, 240, 46);
		resize(R.id._baseEditTextView1, 240, 46);
		resize(R.id._baseEditTextView1_1, 240, 46);
		resize(R.id._baseEditTextView1_2, 240, 46);
		resize(R.id.button1, 270, 60);
		
		resize(R.id.setting_m1_m2, 240, LayoutParams.WRAP_CONTENT);
		setTextSize(R.id.baseTextView2, 18);
		resize(R.id.ImageView01, 119, 31);
	}

	@Override
	public void onClick(View v) {
		String passwordOld = ((TextView) getView(R.id.BaseEditTextView01))
				.getText().toString();
		String newPass = ((TextView) getView(R.id.baseEditTextView1)).getText()
				.toString().trim();
		String renewPass = ((TextView) getView(R.id.baseEditTextView1_1))
				.getText().toString().trim();
		ProDB proDB = new ProDB(this);

		if (proDB.getPassword().equals(passwordOld)) {
			if (newPass.equals(renewPass)) {
				proDB.savePasswrod(renewPass);
				CommonAndroid.showDialog(this, "Change Password succes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						});
			} else {
				CommonAndroid.showDialog(this,
						"Please check new password and renew password", null);
			}
		} else {
			CommonAndroid.showDialog(this, "Please check old password", null);
		}

	}
}