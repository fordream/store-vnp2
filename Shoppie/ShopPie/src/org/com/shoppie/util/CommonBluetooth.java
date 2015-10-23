package org.com.shoppie.util;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

public class CommonBluetooth {
	private Context context;

	public CommonBluetooth(Context context) {
		super();
		this.context = context;
	}

	public BluetoothAdapter getBluetoothAdapter() {
		return BluetoothAdapter.getDefaultAdapter();
	}

	public boolean isSupportBlueTooth() {
		return (getBluetoothAdapter() != null);
	}

	public boolean isEnable() {

		if (!isSupportBlueTooth()) {
			return false;
		}
		return getBluetoothAdapter().isEnabled();
	}

	public void showPopupBluetoothSetting(int requestCode) {
		Intent enableBtIntent = new Intent(
				BluetoothAdapter.ACTION_REQUEST_ENABLE);
		((Activity) context)
				.startActivityForResult(enableBtIntent, requestCode);
	}

	public Set<BluetoothDevice> queryingPairedDevices() {
		if (isSupportBlueTooth() && getBluetoothAdapter().isEnabled()) {
			return getBluetoothAdapter().getBondedDevices();
		}
		return new HashSet<BluetoothDevice>();
	}

	public void discoveringDevices() {
		// private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		// public void onReceive(Context context, Intent intent) {
		// String action = intent.getAction();
		// if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		// BluetoothDevice device =
		// intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		// mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		// }
		// }
		// };
		// IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		// registerReceiver(mReceiver, filter);
	}

	public void enablingDiscoverability() {
		Intent discoverableIntent = new Intent(
				BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(
				BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		((Activity) context).startActivity(discoverableIntent);
	}

	public void startServiceBluetooth() {
		// Intent enableIntent = new
		// Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		// ((Activity) context).startActivity(enableIntent);
	}
}
