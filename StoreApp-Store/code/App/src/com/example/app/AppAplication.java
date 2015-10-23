package com.example.app;

import org.core.store.app.service.StoreAppService;

import com.example.app.block.BlockListCallBack;
import com.vnp.core.activity.BaseApplication;

public class AppAplication extends BaseApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		StoreAppService.addInforShortcut(R.drawable.ic_launcher, R.string.app_name, MainActivity.class);
		StoreAppService.initAction("org.core.store.app.service.StoreAppService");
		StoreAppService.addBlockExecute(new BlockListCallBack(this));
	}
}