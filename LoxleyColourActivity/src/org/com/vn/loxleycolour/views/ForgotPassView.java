package org.com.vn.loxleycolour.views;

import org.com.cnc.common.android.CommonNetwork;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;
import org.com.vn.loxleycolour.database.DataStore;
import org.com.vn.loxleycolour.soap.SoapCommon;
import org.com.vn.loxleycolour.views.wiget.DialogNetwork;
import org.com.vn.loxleycolour.views.wiget.DialogResetPassword;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ForgotPassView extends LinearLayout implements OnClickListener,
		Const, IView {
	private EditText etPassword;

	public ForgotPassView(Context context) {
		super(context);
		init();
	}

	public ForgotPassView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.resetpassord, this);

		findViewById(R.id.button1).setOnClickListener(this);
		etPassword = (EditText) findViewById(R.id.editText1);

		etPassword.setText(DataStore.getInstance().getConfig(
				USER_LOGIN_REMEMBER_STRING, ""));
		findViewById(R.id.editText2).requestFocus();
		reset();
		
		//new DialogNetwork(getContext()).show();
	}

	public void onClick(View view) {

		if (!CommonNetwork.haveConnectTed(getContext())) {
			//CommonView.viewDialog(getContext(), "", "Check your network.");
			new DialogNetwork(getContext()).show();
			return;
		}
		
		new AsyncTask<String, String, String>() {
			ProgressDialog dialog;
			Handler handler = new Handler();

			@Override
			protected String doInBackground(String... params) {
				handler.post(new Runnable() {
					public void run() {
						dialog = ProgressDialog.show(getContext(), "",
								"Loading ...");
					}
				});

				SoapCommon.SendUserAndPass(etPassword.getText().toString());
				return null;
			}

			protected void onPostExecute(String result) {
				dialog.dismiss();
				new DialogResetPassword(getContext()).show();
				//CommonView.viewDialog(getContext(), "Reset Password", "A new password has been sent to your inbox.");
			};
		}.execute("");
	}

	public void reset() {
		((IheaderLabel) getContext()).setTextHeader("Orders");
	}
}
