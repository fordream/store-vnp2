package com.vnp.mlook;

import android.app.Application;
import android.content.Intent;

public class MLookApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		startService(new Intent(this, MLookService.class));
	}
}
