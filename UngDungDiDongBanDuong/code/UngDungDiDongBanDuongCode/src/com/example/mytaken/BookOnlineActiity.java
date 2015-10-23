package com.example.mytaken;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

public class BookOnlineActiity extends MBaseActivity implements OnClickListener, Runnable {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookonline);
		new Handler().post(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void run() {
		try {

		} catch (Exception e) {
		} catch (Error e) {
		}
	}

	@Override
	public void onClick(View arg0) {

	}

}