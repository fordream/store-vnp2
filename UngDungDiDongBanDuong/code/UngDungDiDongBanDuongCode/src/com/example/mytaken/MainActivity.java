package com.example.mytaken;

import android.os.Bundle;

import com.example.mytaken.db.DBBanDuong;
import com.example.mytaken.fragment.MenuLoginFragment;
import com.example.mytaken.fragment.MenuUnLoginFragment;
import com.example.mytaken.util.BanDuongUtils;

public class MainActivity extends MBaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (new DBBanDuong(this).isLogin()) {
			changeFragemtn(R.id.main_container, new MenuLoginFragment());
		} else {
			changeFragemtn(R.id.main_container, new MenuUnLoginFragment());
		}
	}

}