package com.example.detechbluetooth;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {

	
	private Timer myTimer;
    private ArrayList<Device> listDevice;
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listDevice = new ArrayList<Device>();
        
        mNewDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
        mNewDevicesArrayAdapter.notifyDataSetChanged();

        // Find and set up the ListView for newly discovered devices
        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
        //newDevicesListView.setOnItemClickListener(mDeviceClickListener);

        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        
        myTimer = new Timer();
		myTimer.schedule(new TimerTask() {			
			@Override
			public void run() {
				doDiscovery();
			}
			
		}, 0, 5000);
    }
    
    private void doDiscovery() {
        //if (D) Log.d(TAG, "doDiscovery()");

        // Indicate scanning in the title
//        setProgressBarIndeterminateVisibility(true);
//        setTitle(R.string.scanning);

        // Turn on sub-title for new devices
        //findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

        // If we're already discovering, stop it
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }

        // Request discover from BluetoothAdapter
        mBtAdapter.startDiscovery();
    }
    
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
//                	if (listDevice.indexOf(device.getAddress()) >= 0) {
                	if (!isExistAddress(device.getAddress())) {
                		mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                		Device de = new Device(device.getName(), device.getAddress());
                		listDevice.add(de);
                	}
//                	}
                    
                }
            // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                if (mNewDevicesArrayAdapter.getCount() == 0) {
                    String noDevices = getResources().getText(R.string.none_found).toString();
                    mNewDevicesArrayAdapter.add(noDevices);
                }
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            	setProgressBarIndeterminateVisibility(false);
            	BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            	mNewDevicesArrayAdapter.remove(device.getName() + "\n" + device.getAddress());
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
}
