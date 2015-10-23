package org.com.vn.loxleycolour.activity;

import org.com.vn.loxleycolour.database.DataStore;

import android.os.Bundle;

public class OrdersActivity extends MActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gotoLogin();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(!DataStore.getInstance().getConfig(LOGIN, false)){
			gotoLogin();
		}
	}
}
