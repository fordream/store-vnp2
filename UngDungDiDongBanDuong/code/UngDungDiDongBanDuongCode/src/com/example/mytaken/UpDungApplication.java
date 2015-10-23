package com.example.mytaken;

import com.vnp.core.activity.BaseApplication;
import com.vnp.core.common.ImageLoaderUtils;
import com.vnp.core.common.VNPResize;
import com.vnp.core.datastore.DataStore;

public class UpDungApplication extends BaseApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		DataStore.getInstance().init(this);
		ImageLoaderUtils.getInstance(this);
		VNPResize.getInstance().init(this, 320, 480, null, null);
	}
}