package com.example.org.com.driver;

import com.example.org.com.driver.view.ProDB;
import com.vnp.core.common.CommonAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class LoginScreen extends MBaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		handler.post(this);

		ProDB proDB = new ProDB(this);
		if (!proDB.getEmail().equals("")) {
			startActivity(new Intent(this, RegisterActivity.class));
			finish();
		}

		getView(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void run() {
		resize(R.id.login_id_1, LayoutParams.MATCH_PARENT, 50);
		resize(R.id.imageView1, 128, 31);
		resize(R.id.ImageView01, 51, 19);
		resize(R.id.login_id_2, 240, LayoutParams.WRAP_CONTENT);

		setTextSize(R.id.BaseTextView01, 22);
		setTextSize(R.id.baseTextView1, 22);
		setTextSize(R.id.BaseEditTextView01, 22);
		setTextSize(R.id.baseEditTextView1, 22);

		resize(R.id._BaseEditTextView01, 240, 46);
		resize(R.id._baseEditTextView1, 240, 46);
		resize(R.id.button1, 270, 60);
	}

	@Override
	public void onClick(View v) {
		String email = ((TextView) getView(R.id.BaseEditTextView01)).getText()
				.toString().trim();
		String password = ((TextView) getView(R.id.baseEditTextView1))
				.getText().toString().trim();

		if (email.equals("") || password.equals("")) {
			CommonAndroid.showDialog(this, "Please input email and password",null);
		} else {
			ProDB db = new ProDB(this);
			db.saveEmail(email);
			db.savePasswrod(password);

			startActivity(new Intent(this, RegisterActivity.class));
			finish();
		}

	}
}