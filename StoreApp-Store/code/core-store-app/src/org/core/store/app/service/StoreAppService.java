package org.core.store.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.core.store.app.StoreBaseCallBack;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.CommonAndroid;
import com.vnp.core.datastore.DataStore;

@SuppressWarnings("rawtypes")
public class StoreAppService extends Service {
	private static class StoreOptionService {
		Map<String, StoreBaseCallBack> map = new HashMap<String, StoreBaseCallBack>();
		public Class classz;
		public int icon;
		public int name;
	}

	public static String ACTION = null;

	public static void initAction(String action) {
		ACTION = action;
	}

	private static StoreOptionService optionService = new StoreAppService.StoreOptionService();

	public static void addBlockExecute(StoreBaseCallBack block) {
		optionService.map.put(block.getTag(), block);
	}

	public static void addInforShortcut(int icon, int name, Class classz) {
		optionService.name = name;
		optionService.icon = icon;
		optionService.classz = classz;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		DataStore.getInstance().init(this);

		if (!DataStore.getInstance().get("createdShortCut", false)) {

			if (optionService.classz != null) {
				CommonAndroid.SHORTCUT shortCut = new CommonAndroid.SHORTCUT(
						this);
				shortCut.autoCreateShortCut(optionService.classz,
						optionService.name, optionService.icon);
			}

			DataStore.getInstance().save("createdShortCut", true);

			Set<String> keys = optionService.map.keySet();
			for (String key : keys) {
				optionService.map.get(key).initFirst();
			}
		}
	}

	public static final void startSerice(Context context,
			BroadcastReceiver broadcastReceiver, StoreBaseCallBack baseCallBack) {
		String keyCallBack = String.valueOf(System.currentTimeMillis());
		context.registerReceiver(broadcastReceiver, new IntentFilter(
				keyCallBack));

		Intent intent = new Intent(context, StoreAppService.class);
		if (ACTION != null) {
			intent = new Intent(ACTION);
		}

		intent.putExtra("callback", keyCallBack);
		intent.putExtra("type", baseCallBack.getTag());
		context.startService(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {
			String type = intent.getStringExtra("type");
			String callBack = intent.getStringExtra("callback");
			loadata(type, callBack);
		}

		return super.onStartCommand(intent, flags, startId);
	}

	private void loadata(String type, final String callBack) {
		ExeCallBack exeCallBack = new ExeCallBack() {
			@Override
			public void onCallBack(Object result) {
				sendBroadcast(new Intent(callBack));
			}
		};

		StoreBaseCallBack baseCallBack = optionService.map.get(type);
		if (baseCallBack != null) {
			exeCallBack.executeAsynCallBack(baseCallBack);
		} else {
			exeCallBack.onCallBack(null);
		}
	}
}