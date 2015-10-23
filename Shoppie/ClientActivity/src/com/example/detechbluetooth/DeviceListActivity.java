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

package com.example.detechbluetooth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This Activity appears as a dialog. It lists any paired devices and devices
 * detected in the area after discovery. When a device is chosen by the user,
 * the MAC address of the device is sent back to the parent Activity in the
 * result Intent.
 */
@SuppressLint("NewApi")
public class DeviceListActivity extends Activity {
	// Debugging
	private static final String TAG = "DeviceListActivity";
	private static final boolean D = true;

	private Timer myTimer;
	private ArrayList<Device> listDevice;

	// Return Intent extra
	public static String EXTRA_DEVICE_ADDRESS = "device_address";

	// Member fields
	private BluetoothAdapter mBtAdapter;
	// private ArrayAdapter<String> mPairedDevicesArrayAdapter;
	private ArrayAdapter<String> mNewDevicesArrayAdapter;
	
	private String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setup the window
		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);

		// Set result CANCELED incase the user backs out
		setResult(Activity.RESULT_CANCELED);

		listDevice = new ArrayList<Device>();

		// Initialize the button to perform device discovery

		// Initialize array adapters. One for already paired devices and
		// one for newly discovered devices
		// mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this,
		// R.layout.device_name);
		mNewDevicesArrayAdapter = new ArrayAdapter<String>(this,
				R.layout.device_name);
		mNewDevicesArrayAdapter.notifyDataSetChanged();

		// Find and set up the ListView for newly discovered devices
		ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
		newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
		// newDevicesListView.setOnItemClickListener(mDeviceClickListener);

		// Register for broadcasts when a device is discovered
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		this.registerReceiver(mReceiver, filter);

		// Register for broadcasts when discovery has finished
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		this.registerReceiver(mReceiver, filter);

		// Get the local Bluetooth adapter
		mBtAdapter = BluetoothAdapter.getDefaultAdapter();
		doDiscovery();
		myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				doDiscovery();
			}

		}, 0, 5000);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Make sure we're not doing discovery anymore
		if (mBtAdapter != null) {
			mBtAdapter.cancelDiscovery();
		}

		// Unregister broadcast listeners
		this.unregisterReceiver(mReceiver);
	}

	/**
	 * Start device discover with the BluetoothAdapter
	 */
	private void doDiscovery() {
		if (D)
			Log.d(TAG, "doDiscovery()");

		// Indicate scanning in the title
		// setProgressBarIndeterminateVisibility(true);
		// setTitle(R.string.scanning);

		// If we're already discovering, stop it
		if (mBtAdapter.isDiscovering()) {
			mBtAdapter.cancelDiscovery();
		}

		// Request discover from BluetoothAdapter
		mBtAdapter.startDiscovery();
	}

	// The BroadcastReceiver that listens for discovered devices and
	// changes the title when discovery is finished
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			try {
				String action = intent.getAction();

				// When discovery finds a device
				if (BluetoothDevice.ACTION_FOUND.equals(action)) {
					// Get the BluetoothDevice object from the Intent
					BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					// If it's already paired, skip it, because it's been listed
					// already
					if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
						// if (listDevice.indexOf(device.getAddress()) >= 0) {
						if (!isExistAddress(device.getAddress())) {
							mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
							Device de = new Device(device.getName(), device.getAddress());
							Calendar cal = Calendar.getInstance(); 
							de.setSecond(cal.get(Calendar.SECOND));
							de.setMinute(cal.get(Calendar.MINUTE));
							de.setHour(cal.get(Calendar.HOUR_OF_DAY));
							listDevice.add(de);
							handleListDevice();
							createThreadPostBluetoothId(device.getAddress());
//							postBluetoothIdTo(device.getAddress());
						}
						// }

					}
					// When discovery is finished, change the Activity title
				} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
						.equals(action)) {
					setProgressBarIndeterminateVisibility(false);
					if (mNewDevicesArrayAdapter.getCount() == 0) {
						String noDevices = getResources().getText(
								R.string.none_found).toString();
						mNewDevicesArrayAdapter.add(noDevices);
					}
				} else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
					setProgressBarIndeterminateVisibility(false);
					BluetoothDevice device = intent
							.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					mNewDevicesArrayAdapter.remove(device.getName() + "\n"
							+ device.getAddress());
				}
			} catch (Exception ex) {

			}
		}
	};

	private Boolean isExistAddress(String address) {
		for (Device device : listDevice) {
			if (device.getAddress().equalsIgnoreCase(address))
				return true;
		}
		return false;
	}

	public void createThreadPostBluetoothId(String bluetoothId) {
		
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_LONG).show();
			}
		};
		
		final String blueId = bluetoothId.replace(":", "");
		new Thread() {
			public void run() {
				postBluetoothIdTo(blueId);
				handler.sendEmptyMessage(0);
			}
		}.start();
	}
	
	public void handleListDevice() {
	
		new Thread() {
			public void run() {
				Calendar cal = Calendar.getInstance(); 
				  int second = cal.get(Calendar.SECOND);
				  int minute = cal.get(Calendar.MINUTE);
				  int hourofday = cal.get(Calendar.HOUR_OF_DAY);
				  List<Device> remove = new ArrayList<Device>();
				  for (Device device : listDevice) {
					if (hourofday - device.getHour() == 2 && minute == device.getMinute() && second == device.getSecond()) {
						remove.add(device);
					}
				  }
				  
				  for (Device device2 : remove) {
					listDevice.remove(device2);
				}
			}
		}.start();

	}

	public void postBluetoothIdTo(String bluetoothId) {
		try {
		String url = "http://shoppie.top50.vn/index.php/api/webservice/newtxncampaign";
		DefaultHttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);
		if (bluetoothId != null) {
			// httpPost.setEntity(new StringEntity(bluetoothId, "UTF-8"));
			// httpPost.setHeader("Content-type", "application/xml");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			nameValuePairs.add(new BasicNameValuePair("merch_id", SessionInfo.merch_id_str));
			nameValuePairs.add(new BasicNameValuePair("store_id", SessionInfo.store_id_str));
			nameValuePairs.add(new BasicNameValuePair("cust_id", bluetoothId));
			nameValuePairs.add(new BasicNameValuePair("txn_type", "1"));
			nameValuePairs.add(new BasicNameValuePair("txn_amt", "1"));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		}

		HttpResponse response = httpClient.execute(httpPost);
		String s = EntityUtils.toString(response.getEntity());
		parseXml(s);
		//return EntityUtils.toString(response.getEntity());
		} catch (Exception ex) {
			
		}
	}
	
	public void parseXml(String s) {
		XMLParser parser = new XMLParser();
		String xml = s; // getting
																// XML
		Document doc = parser.getDomElement(xml); // getting DOM
													// element
		NodeList result_code = doc.getElementsByTagName("xml");
		Element e1 = (Element) result_code.item(0);

		// doc.get
		String resultstr = parser.getValue(e1, "result");
		if (resultstr.contentEquals("1")) {
			NodeList nl = doc.getElementsByTagName("item");
			Element e = (Element) nl.item(0);
			String pie_qty = parser.getValue(e, "pie_qty");
			String bonus_qty = parser.getValue(e, "bonus_qty");
			result = "pie_qty: " + pie_qty + "     bonus_qty: " + bonus_qty;
		}
	}

}
