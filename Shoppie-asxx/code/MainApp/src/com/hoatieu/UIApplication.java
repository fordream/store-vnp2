package com.hoatieu;

import com.hoatieu.location.VNPCompassUtils;
import com.hoatieu.location.VNPLocationUtils;
import com.hoatieu.service.HoatieuServerManager;
import com.vnp.core.activity.BaseApplication;
import com.vnp.core.datastore.DataStore;

public class UIApplication extends BaseApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		VNPLocationUtils.getInstance().init(this);
		VNPLocationUtils.getInstance().requestLocationUpdate();
		VNPCompassUtils.getInstance().init(this);
		DataStore.getInstance().init(this);
		HoatieuServerManager.getIntance().init(this);
		HoatieuServerManager.getIntance().onStart();
	}
}