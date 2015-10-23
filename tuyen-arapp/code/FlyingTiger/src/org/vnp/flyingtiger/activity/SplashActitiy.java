package org.vnp.flyingtiger.activity;

import java.io.IOException;
import java.util.List;

import jp.flyingtiger.arcamera.BuildConfig;
import jp.flyingtiger.arcamera.R;

import org.vnp.flyingtiger.TutorialHelloWorld;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.metaio.sdk.MetaioDebug;
import com.metaio.tools.io.AssetsManager;
import com.vnp.core.base.activity.BaseActivity;

public class SplashActitiy extends BaseActivity implements OnClickListener {
	/**
	 * This task extracts all the assets to an external or internal location to
	 * make them accessible to metaio SDK
	 */
	private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean> {

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected Boolean doInBackground(Integer... params) {
			try {
				AssetsManager.extractAllAssets(getApplicationContext(), BuildConfig.DEBUG);
			} catch (IOException e) {
				MetaioDebug.printStackTrace(Log.ERROR, e);
				return false;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
			} else {
				MetaioDebug.log(Log.ERROR, "Error extracting assets, closing the application...");
			}
		}
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.a_nothing, R.anim.a_top_to_bot);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		overridePendingTransition(R.anim.a_bot_to_top, R.anim.a_nothing);
		// MetaioDebug.enableLogging(BuildConfig.DEBUG);
		// TODO enable log
		MetaioDebug.enableLogging(false);

		findViewById(R.id.splash_btn_abount).setOnClickListener(this);
		findViewById(R.id.splash_btn_camera).setOnClickListener(this);

		findViewById(R.id.Button01).setOnClickListener(this);
		findViewById(R.id.Button02).setOnClickListener(this);

		new AssetsExtracter().execute(0);
//		PackageManager packageManager = getPackageManager();
//		List<PackageInfo> lPackageInfos = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
//		for (PackageInfo s : lPackageInfos) {
//			if (s.applicationInfo.packageName.contains("skype"))
//				Log.e("AAAAA", s.applicationInfo.packageName);
//		}
	}

	public void startChannel(int channelId) {
		startActivity(new Intent(this, TutorialHelloWorld.class));
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.splash_btn_abount || v.getId() == R.id.Button02) {
			startActivity(new Intent(this, AboutActitiy.class));
		} else if (v.getId() == R.id.splash_btn_camera || v.getId() == R.id.Button01) {
			startChannel(261634);
		}
	}
}