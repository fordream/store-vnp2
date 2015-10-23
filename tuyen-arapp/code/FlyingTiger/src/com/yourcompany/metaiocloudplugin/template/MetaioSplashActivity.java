package com.yourcompany.metaiocloudplugin.template;

import jp.flyingtiger.arcamera.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.metaio.cloud.plugin.MetaioCloudPlugin;
import com.metaio.sdk.jni.IMetaioSDKAndroid;

public class MetaioSplashActivity extends Activity
{
	static
	{
		IMetaioSDKAndroid.loadNativeLibs();
	}

	/**
	 * Progress dialog
	 */
	private ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		JunaioStarterTask junaioStarter = new JunaioStarterTask();
		junaioStarter.execute(1);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		super.onStop();

		if (progressDialog != null)
		{
			progressDialog.dismiss();
		}
	}

	/**
	 * Launch junaio live view
	 */
	private void launchLiveView()
	{
		// Set your channel id in /res/values/channelid.xml
		int myChannelId = getResources().getInteger(R.integer.channelid);

		// if you have set a channel ID, then load it directly
		if (myChannelId != -1)
		{
			startChannel(myChannelId, true);
		}
	}

	public void startAREL(View v)
	{
		startChannel(174470, false);// start AREL test
	}

	public void startLb(View v)
	{
		startChannel(4796, false);// start Wikipedia EN
	}

	public void startChannel(int channelId, boolean andFinishActivity)
	{
		// To use the old MetaioCloudARViewActivity use this
//		Intent intent = new Intent(SplashActivity.this, MetaioCloudARViewTestActivity.class);
		// instead of this
		Intent intent = new Intent(MetaioSplashActivity.this, MainActivity.class);
		intent.putExtra(getPackageName() + ".CHANNELID", channelId);
		startActivity(intent);

		if (andFinishActivity)
			finish();
	}

	private class JunaioStarterTask extends AsyncTask<Integer, Integer, Integer>
	{

		@Override
		protected void onPreExecute()
		{
			progressDialog = ProgressDialog.show(MetaioSplashActivity.this, "junaio", "Starting up...");
		}

		@Override
		protected Integer doInBackground(Integer... params)
		{

			// TODO Set authentication if a private channel is used
			// MetaioCloudPlugin.setAuthentication("username", "password");

			// Start junaio, this will initialize everything the plugin needs
			int result = MetaioCloudPlugin.startJunaio(this, getApplicationContext());

			return result;
		}

		@Override
		protected void onProgressUpdate(Integer... progress)
		{

		}

		@Override
		protected void onPostExecute(Integer result)
		{
			if (progressDialog != null)
			{
				progressDialog.cancel();
				progressDialog = null;
			}

			if (result == MetaioCloudPlugin.SUCCESS)
				launchLiveView();
			else
				Utils.showErrorForCloudPluginResult(result, MetaioSplashActivity.this);
		}

	}
}
