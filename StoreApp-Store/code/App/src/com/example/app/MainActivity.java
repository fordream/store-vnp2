package com.example.app;

import org.core.store.app.StoreBaseActivity;
import org.core.store.app.service.StoreAppService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.app.block.BlockListCallBack;
import com.example.app.blocklist.BlocksListActivity;
import com.example.app.utils.MemoryUtils;
import com.vnp.core.common.VnpMemoryUtils;

public class MainActivity extends StoreBaseActivity {
	private BlockListCallBack blockListCallBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		StoreAppService.startSerice(this, broadcastReceiver,
				blockListCallBack = new BlockListCallBack(this));
	}

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	};

	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			MemoryUtils memoryUtils = new MemoryUtils(MainActivity.this);
			if (memoryUtils.haveBlockContent()) {
				startActivity(new Intent(MainActivity.this,BlocksListActivity.class));
				finish();
			} else {
				makeText("false");
			}
		}

	};

}