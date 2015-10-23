package com.example.loyalty;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.ict.library.activity.BaseActivity;
import com.ict.library.service.RestClient;

public class MBaseActivity extends BaseActivity {
	protected RestClient restClient;
	private boolean isRun = false;

	@Override
	protected Dialog onCreateDialog(int id) {
		return ProgressDialog.show(this, "", "loading");
	}

	public void execute() {
		showDialog(0);
		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... params) {
				isRun = true;
				doInBackground1();
				return null;
			}

			protected void onPostExecute(String result) {
				dismissDialog(0);
				onPostExecute1();
				isRun = false;
			}
		}.execute("");
	}

	public void execute(final Object object) {
		showDialog(0);
		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... params) {
				isRun = true;
				// doInBackground1();
				doInBackground1(object);
				return null;
			}

			protected void onPostExecute(String result) {
				dismissDialog(0);
				onPostExecute1();
				isRun = false;
			}
		}.execute("");
	}

	protected boolean isRun() {
		return isRun;
	}

	public void onPostExecute1() {

	}

	public void doInBackground1() {

	}

	public void doInBackground1(Object object) {

	}
}
