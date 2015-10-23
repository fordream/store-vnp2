package org.com.vn.loxleycolour.views;

import org.com.cnc.common.android.CommonDeviceId;
import org.com.cnc.common.android.CommonNetwork;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IActivity;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;
import org.com.vn.loxleycolour.database.DataStore;
import org.com.vn.loxleycolour.items.Login;
import org.com.vn.loxleycolour.soap.SoapCommon;
import org.com.vn.loxleycolour.views.wiget.CustomDialog;
import org.com.vn.loxleycolour.views.wiget.DialogNetwork;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginView extends LinearLayout implements OnClickListener, Const,
		IView {
	private Button btnLogin;
	private EditText etUser;
	private EditText etPassword;
	private CheckBox cbRemember;
	private TextView tvForgot;

	public LoginView(Context context) {
		super(context);
		init();
	}

	public LoginView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.login, this);
		btnLogin = (Button) findViewById(R.id.login_btn);
		btnLogin.setOnClickListener(this);
		etUser = (EditText) findViewById(R.id.etEmail);
		etPassword = (EditText) findViewById(R.id.etpassword);
		reset();

		tvForgot = (TextView) findViewById(R.id.textView2);
		tvForgot.setOnClickListener(this);
		cbRemember = (CheckBox) findViewById(R.id.checkBox1);
		cbRemember.setChecked(DataStore.getInstance()
				.getConfig(REMEMBER, false));

		if (cbRemember.isChecked()) {
			etUser.setText(DataStore.getInstance().getConfig(USER, null));
			etPassword.setText(DataStore.getInstance()
					.getConfig(PASSWORD, null));

		}

		((EditText) findViewById(R.id.editText1)).requestFocus();

		int wight = CommonDeviceId.getWidth((Activity) getContext());
		wight = (int) (300.0 / 480.0 * wight);
		int height = wight * 300 / 252;
		LayoutParams params = new LayoutParams(wight, height);
		findViewById(R.id.llLoginForm).setLayoutParams(params);
	}

	public void onClick(View view) {
		if (tvForgot == view) {
			DataStore.getInstance().saveConfig(USER_LOGIN_REMEMBER_STRING,
					etUser.getText().toString());
			((IActivity) getContext()).gotoForgotPass();
			return;
		}

		if (!CommonNetwork.haveConnectTed(getContext())) {
			// CommonView.viewDialog(getContext(), "Message",
			// "Check your network!");
			new DialogNetwork(getContext()).show();
			return;
		}

		DataStore.getInstance().saveConfig(REMEMBER, cbRemember.isChecked());

		if (cbRemember.isChecked()) {
			DataStore.getInstance().saveConfig(USER,
					etUser.getText().toString());
			DataStore.getInstance().saveConfig(PASSWORD,
					etPassword.getText().toString());
		}

		new AsyncTask<String, String, String>() {
			Login login;
			private ProgressDialog dialog;
			Handler handler = new Handler();

			protected String doInBackground(String... params) {

				handler.post(new Runnable() {

					public void run() {
						;
						dialog = ProgressDialog.show(getContext(), "",
								getResources().getString(R.string.loadding));
					}
				});
				login = SoapCommon.login(etUser.getText().toString(),
						etPassword.getText().toString());
				return null;
			}

			protected void onPostExecute(String result) {
				dialog.dismiss();
				if (login.isSuccess()) {
					DataStore.getInstance().saveConfig(LOGIN, true);
					DataStore.getInstance().saveConfig(REMEMBER_USER,
							login.getData());
					((IActivity) getContext()).gotoOrderMenu();
				} else {
					// CommonView.viewDialog(getContext(), "Message",
					// "Login fail!");

					CustomDialog dialog = new CustomDialog(getContext());
					dialog.setTitle("Message");
					dialog.setMessage("Login fail!");
					dialog.setTextButton("Ok");

					dialog.show();
				}

			}

		}.execute("");
	}

	public void reset() {
		((IheaderLabel) getContext()).setTextHeader("Orders");
	}

	// public void test(){
	// DefaultHttpClient httpClient = new DefaultHttpClient();
	// String responseString = null;
	// try {
	//
	// HttpPost httppost = new
	// HttpPost("http://api.loxleycolour.com/loxleyservice.asmx");
	// httppost.setHeader("SOAPAction", "http://LoxleyService/LoginWebUser");
	// httppost.setHeader("Content-Type",
	// "text/xml; charset=utf-8");
	//
	// String strEnvelope =
	// "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:lox=\"http://LoxleyService/\"><soapenv:Header/><soapenv:Body><lox:LoginWebUser><!--Optional:--><lox:UserName>shaunferry86s@gmail.com</lox:UserName><!--Optional:--><lox:Password>password10</lox:Password></lox:LoginWebUser></soapenv:Body></soapenv:Envelope>";
	//
	// HttpEntity entity = new StringEntity(strEnvelope);
	// httppost.setEntity(entity);
	// ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
	// responseString = httpClient.execute(httppost,
	// strResponseHandler);
	// Log.d("Search", responseString + "---------------");
	//
	// } catch (Exception objException) {
	// } finally {
	// httpClient.getConnectionManager().shutdown();
	// }
	// }
}
