/**
 * 
 */
package com.xing.joy.play;

import java.io.File;

import com.xing.joy.play.util.CopyUtil;
import com.xing.joy.play.util.MemoryUtils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

/**
 * @author tvuong1pc
 * 
 */
public class StartUpActivity extends Activity {

	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = ProgressDialog.show(this, "", "loading ...");
		return dialog;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showDialog(0);
		new AsyncTask<String, String, String>() {
			@Override
			protected String doInBackground(String... params) {
				CopyUtil copyUtil = new CopyUtil();

				copyUtil.copyAssets(StartUpActivity.this, "content",
						MemoryUtils.getFolderContent());
				return null;
			}

			protected void onPostExecute(String result) {
				dismissDialog(0);
				finish();
				startActivity(new Intent(StartUpActivity.this,
						PlayMovieActivity.class));
			};
		}.execute("");
	}
}