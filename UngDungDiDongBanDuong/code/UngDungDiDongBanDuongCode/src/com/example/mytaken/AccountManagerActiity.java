package com.example.mytaken;

import com.example.mytaken.db.DBBanDuong;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

public class AccountManagerActiity extends MBaseActivity implements OnClickListener, Runnable {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountmanager);
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