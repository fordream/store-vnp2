package org.vnp.storeapp;

import org.vnp.storeapp.activity.SplashActivity;
import org.vnp.storeapp.blocklist.BlockCallBack;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.vnp.core.activity.BaseApplication;
import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.ImageLoader;
import com.vnp.core.datastore.DataStore;

public class UIAplication extends BaseApplication {
	public void onCreate() {
		super.onCreate();
		VNPLocationUtils.getInstance().init(this);
		VNPLocationUtils.getInstance().requestLocationUpdate();

		DataStore.getInstance().init(this);

		if (!DataStore.getInstance().get("createdShortCut", false)) {
			CommonAndroid.SHORTCUT shortCut = new CommonAndroid.SHORTCUT(this);
			shortCut.autoCreateShortCut(SplashActivity.class,
					R.string.app_name, R.drawable.icon);
			DataStore.getInstance().save("createdShortCut", true);
			String responseInit = StoreAppUtils.getStringFromAsset(this);
			new BlockCallBack(this).saveResponse(responseInit);
		}

		/*
		 * clear cache when update or install new
		 */
		int version = CommonAndroid.getVersionApp(this);

		if (version > DataStore.getInstance().get("VERSIONCODE", 0)) {
			DataStore.getInstance().save("VERSIONCODE", version);
			ImageLoader imageLoader = new ImageLoader(this);
			imageLoader.clearCache();
		}

		IntentFilter filter = new IntentFilter("MCallBack");
		registerReceiver(broadcastReceiver, filter);
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			
		}
	};
}