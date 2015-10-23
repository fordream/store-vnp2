/**
 * 
 */
package com.example.loyalty;

import java.util.LinkedList;
import java.util.Queue;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;

import com.ict.library.common.CommonLog;
import com.ict.library.service.RestClient;
import com.ict.library.service.RestClient.RequestMethod;

/**
 * @author truongvv
 * 
 */
public class MApplication extends Application implements Runnable {
	private boolean isRun = false;
	private Queue<BluetoothDevice> bluetoothDevices = new LinkedList<BluetoothDevice>();

	public static final String ACTION_SEND_BLUETOOD_DEVICE_SUCESS = "com.example.loyalty.ACTION_SEND_BLUETOOD_DEVICE_SUCESS";

	@Override
	public void onCreate() {
		super.onCreate();
		isRun = false;
		// ------------------------------------------
		registerReceiver(BluetoothDevice.ACTION_FOUND);
		registerReceiver(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

		// ------------------------------------------
		registerReceiver(BluetoothDevice.ACTION_ACL_CONNECTED);
		registerReceiver(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
		registerReceiver(BluetoothDevice.ACTION_ACL_DISCONNECTED);
		registerReceiver(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
		registerReceiver(BluetoothDevice.ACTION_CLASS_CHANGED);
		registerReceiver(BluetoothDevice.ACTION_NAME_CHANGED);

		new Thread(this).start();

	}

	private void registerReceiver(String actionFound) {
		IntentFilter filter = new IntentFilter(actionFound);
		this.registerReceiver(mReceiver, filter);
	}

	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		private String deviceList = "";

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				final BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (device.getBondState() != BluetoothDevice.BOND_BONDED) {

					bluetoothDevices.add(device);

					if ("".equals(deviceList)) {
						deviceList += device.getAddress();
					} else {
						deviceList += ";" + device.getAddress();
					}
				}

			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
					.equals(action)) {
				deviceList = "";
			} else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
				// setProgressBarIndeterminateVisibility(false);
				// BluetoothDevice device = intent
				// .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				// mNewDevicesArrayAdapter.remove(device.getName() + "\n"
				// + device.getAddress());
			}
		}
	};

	@Override
	public void run() {
		while (!isRestricted()) {
			BluetoothDevice device = bluetoothDevices.poll();
			if (device != null) {
				// http://ws.shoppie.com.vn/index.php/api/webservice/batchcheckin?merchId=1&storeId=1&custId=AAA,223,sdsd
				// http://ws.shoppie.com.vn/index.php/api/webservice/batchcheckin?merchId={0}&storeId={1}&custId={2}
				String url = "http://ws.shoppie.com.vn/index.php/api/webservice/batchcheckin?merchId={0}&storeId={1}&custId={2}";
				url = url.replace("{0}", SessionInfo.merch_id_str);
				url = url.replace("{1}", SessionInfo.store_id_str);
				url = url.replace("{2}", device.getAddress().replace(":", ""));

				Log.e("URL", url);
				RestClient restClient = new RestClient(url);
				try {
					restClient.execute(RequestMethod.GET);
				} catch (Exception e) {
				}

				if (restClient.getResponseCode() == 200) {
					String response = restClient.getResponse();

					if (response.contains("<dataValue>1</dataValue>")) {
						CommonLog.e("SEND TO SERVER", "THANH CONG");
					} else {
						CommonLog.e("SEND TO SERVER", "THAT BAI");
					}

					Intent intent = new Intent(
							ACTION_SEND_BLUETOOD_DEVICE_SUCESS);
					intent.putExtra("content",
					// device.getBondState()
					// + "\n"
							device.getAddress()
									+ "  "
									+ device.getName()
									+ " ("
									+ response
											.contains("<dataValue>1</dataValue>")
									+ ")");
					sendBroadcast(intent);
				} else {
					CommonLog.e("SEND TO SERVER",
							"THAT BAI" + restClient.getResponseCode());
				}
			}
		}
	}
}