package com.vnp.mlook.fragment.account;

import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vnp.mlook.LoginReference;
import com.vnp.mlook.LoginReference.LOGIN_TYPE;
import com.vnp.mlook.LoginReference.LoginLogoutCallBack;
import com.vnp.mlook.R;
import com.vnp.mlook.fragment.BaseFragment;
import com.vnp.mlook.fragment.MLookAction;
import com.vnp.mlook.utils.MLookServiceAction;
import com.vnp.mlook.utils.MLookServiceAction.MLookServiceStatus;

public class LoginFragment extends BaseFragment {
	public LoginFragment() {
		super();
	}

	private EditText etuser;
	private EditText etPassword;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (isLogin()) {

		}

		View view = inflater.inflate(R.layout.login_fragment, container, false);

		etuser = (EditText) view.findViewById(R.id.editText1);
		etPassword = (EditText) view.findViewById(R.id.editText2);

		view.findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// login
						Bundle bundle = new Bundle();
						bundle.putString("user", etuser.getText().toString()
								.trim());
						bundle.putString("password", etPassword.getText()
								.toString().trim());
						MLookServiceAction mLookServiceAction = new MLookServiceAction(
								getActivity());
						mLookServiceAction.callServer(MLookServiceStatus.LOGIN,
								bundle);
					}
				});

		view.findViewById(R.id.button3).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// login gmail
						LoginReference.getInStance().init(getActivity());
						if (!LoginReference.getInStance().isLogin()) {
							LoginReference.getInStance().login(
									LOGIN_TYPE.LOGIN_GMAIL,
									new LoginLogoutCallBack() {

										@Override
										public void onComplete(String arg0) {

										}

										@Override
										public void onCancel() {

										}

										@Override
										public void error(String arg0) {

										}
									});
						} else {
							Builder builder = new Builder(getActivity());
							LoginReference.getInStance().init(getActivity());
							builder.setMessage(LoginReference.getInStance().getUserName());
							builder.show();
						}
					}
				});

		view.findViewById(R.id.button2).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// login face
						LoginReference.getInStance().init(getActivity());
						if (!LoginReference.getInStance().isLogin())
							LoginReference.getInStance().login(
									LOGIN_TYPE.LOGIN_FACE,
									new LoginLogoutCallBack() {

										@Override
										public void onComplete(String arg0) {

										}

										@Override
										public void onCancel() {

										}

										@Override
										public void error(String arg0) {

										}
									});
					}
				});

		view.findViewById(R.id.button4).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// login twitter

						LoginReference.getInStance().init(getActivity());
						if (!LoginReference.getInStance().isLogin())
							LoginReference.getInStance().login(
									LOGIN_TYPE.LOGIN_TWITTER,
									new LoginLogoutCallBack() {

										@Override
										public void onComplete(String arg0) {

										}

										@Override
										public void onCancel() {

										}

										@Override
										public void error(String arg0) {

										}
									});
					}
				});

		view.findViewById(R.id.button5).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// for get password
						callAction(MLookAction.FORGOTPASSWORK);
					}
				});

		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onResume() {
		super.onResume();
		getActivity().registerReceiver(broadcastReceiver,
				new IntentFilter("login"));
	}

	@Override
	public void onStop() {
		getActivity().unregisterReceiver(broadcastReceiver);
		super.onStop();

	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			callActionBack();
		}
	};

}
