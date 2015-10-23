/**
 * 
 */
package com.vnp.core.base.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * class base for all activity
 * 
 * @author tvuong1pc
 * 
 */
public class BaseActivity extends Activity {
	public static final int DIALOG_LOGIN_FAIL = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	/**
	 * convert view from resource
	 * 
	 * @param res
	 * @return
	 */
	public <T extends View> T getView(int res) {
		@SuppressWarnings("unchecked")
		T view = (T) findViewById(res);
		return view;
	}

	protected Context getContext() {
		return this;
	}

	protected Activity getActivity() {
		return this;
	}

	// show dialog
	@Override
	protected Dialog onCreateDialog(int id) {

		if (DIALOG_LOGIN_FAIL == id) {
			Builder builder = new Builder(this);
			builder.setTitle("Login Fail!");
			builder.setMessage("Please check your passwrod!");
			return builder.create();
		}
		return super.onCreateDialog(id);
	}
}