package com.hoatieu.service;

import com.hoatieu.service.HoatieuService.HoatieuBinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class HoatieuServerManager {
	private static HoatieuServerManager intance = new HoatieuServerManager();

	private HoatieuServerManager() {
	}

	public static HoatieuServerManager getIntance() {
		return intance == null ? (intance = new HoatieuServerManager()) : intance;
	}

	private Context mContext;

	public void init(Context context) {
		mContext = context;
	}

	/**
	 * Serice
	 */
	public HoatieuService mService;
	private boolean mBound = false;

	public void onStart() {
		Intent intent = new Intent(mContext, HoatieuService.class);
		mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	public void onStop() {
		if (mBound) {
			mContext.unbindService(mConnection);
			mBound = false;
		}
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			HoatieuBinder binder = (HoatieuBinder) service;
			mService = binder.getService();
			mBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}
	};
}