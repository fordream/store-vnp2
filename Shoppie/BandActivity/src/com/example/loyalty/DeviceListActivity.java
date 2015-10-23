/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.loyalty;

import java.util.Timer;
import java.util.TimerTask;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ict.library.common.CommonBlueTooth;

public class DeviceListActivity extends MBaseActivity {
	// Debugging
	private static final String TAG = "DeviceListActivity";
	private static final boolean D = true;

	private Timer myTimer;

	public static String EXTRA_DEVICE_ADDRESS = "device_address";

	private BluetoothAdapter mBtAdapter;
	private ArrayAdapter<String> mNewDevicesArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.device_list);

		CommonBlueTooth commonBlueTooth = new CommonBlueTooth(this) {

			@Override
			public void boardCastFindDevice(BluetoothDevice arg0) {

			}
		};

		commonBlueTooth.startEnableBlueToothPopup(0);
		// setResult(Activity.RESULT_CANCELED);

		mNewDevicesArrayAdapter = new ArrayAdapter<String>(this,
				R.layout.device_name);
		mNewDevicesArrayAdapter.notifyDataSetChanged();

		ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
		newDevicesListView.setAdapter(mNewDevicesArrayAdapter);

		mBtAdapter = BluetoothAdapter.getDefaultAdapter();

		myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				doDiscovery();
			}

		}, 0, 5000);

		IntentFilter filter = new IntentFilter(
				MApplication.ACTION_SEND_BLUETOOD_DEVICE_SUCESS);

		registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				mNewDevicesArrayAdapter.add(intent.getStringExtra("content"));
			}
		}, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mBtAdapter != null) {
			mBtAdapter.cancelDiscovery();
		}

	}

	private void doDiscovery() {
		if (D)
			Log.d(TAG, "doDiscovery()");

		findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

		if (mBtAdapter.isDiscovering()) {
			mBtAdapter.cancelDiscovery();
		}

		mBtAdapter.startDiscovery();
	}

}