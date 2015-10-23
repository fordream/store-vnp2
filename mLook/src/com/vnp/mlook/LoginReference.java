package com.vnp.mlook;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.vnp.core.facebook.DialogError;
import com.vnp.core.facebook.Facebook;
import com.vnp.core.facebook.Facebook.DialogListener;
import com.vnp.core.facebook.FacebookError;
import com.vnp.core.twitter.TwitterApp;
import com.vnp.core.twitter.TwitterApp.TwDialogListener;
import com.vnp.mlook.utils.GmailGender;

public class LoginReference {
	public interface LoginLogoutCallBack {
		public void error(String arg0);

		public void onComplete(String arg0);

		public void onCancel();
	}

	private LoginLogoutCallBack loginLogoutCallBack;

	private TwitterApp twitterApp;
	private Facebook facebook;
	private GmailGender gmailGender;
	public Activity mContext;

	private static LoginReference loginReference = new LoginReference();

	public static LoginReference getInStance() {
		return loginReference;
	}

	public void init(Activity context) {
		mContext = context;
		System.setProperty("http.keepAlive", "false");
		twitterApp = new TwitterApp(context, "fnubma6onRcwbdjPW51A",
				"m9ZRaibyeTvyMSHFREDNtqg3dOHMRjQuLkVHaTwiYpo");

		twitterApp.setListener(new TwDialogListener() {
			@Override
			public void onError(String arg0) {
				if (loginLogoutCallBack != null)
					loginLogoutCallBack.error(arg0);
			}

			@Override
			public void onComplete(String arg0) {
				if (loginLogoutCallBack != null)
					loginLogoutCallBack.onComplete(arg0);
			}
		});

		gmailGender = new GmailGender(mContext);
		gmailGender.setCallBack(new GmailGender.CallBack() {

			@Override
			public void sucess() {
				if (loginLogoutCallBack != null)
					loginLogoutCallBack.onComplete(null);
			}

			@Override
			public void error(String message) {
				if (loginLogoutCallBack != null)
					loginLogoutCallBack.error(null);
			}
		});

		facebook = new Facebook("429606567129217");

	}

	public enum LOGIN_TYPE {
		LOGIN_FACE, //
		LOGIN_TWITTER, //
		LOGIN_GMAIL, //
		LOGIN_NORMAL,
	}

	public void login(LOGIN_TYPE type, LoginLogoutCallBack mloginLogoutCallBack) {
		this.loginLogoutCallBack = mloginLogoutCallBack;
		if (type == LOGIN_TYPE.LOGIN_TWITTER) {
			twitterApp.authorize();
		} else if (type == LOGIN_TYPE.LOGIN_FACE) {
			facebook.authorize(mContext, new DialogListener() {
				@Override
				public void onFacebookError(FacebookError arg0) {
					if (loginLogoutCallBack != null)
						loginLogoutCallBack.error(null);
				}

				@Override
				public void onError(DialogError arg0) {
					if (loginLogoutCallBack != null)
						loginLogoutCallBack.error(null);
				}

				@Override
				public void onComplete(Bundle arg0) {
					if (loginLogoutCallBack != null)
						loginLogoutCallBack.onComplete(null);
				}

				@Override
				public void onCancel() {
					if (loginLogoutCallBack != null)
						loginLogoutCallBack.onCancel();
				}
			});
		} else if (type == LOGIN_TYPE.LOGIN_GMAIL) {
			final EditText eTUser = new EditText(mContext);
			eTUser.setHint("user");
			final EditText eTUserPassword = new EditText(mContext);
			eTUserPassword.setHint("password");

			LinearLayout linearLayout = new LinearLayout(mContext);
			linearLayout.setOrientation(LinearLayout.VERTICAL);
			linearLayout.addView(eTUser);
			linearLayout.addView(eTUserPassword);

			Builder builder = new Builder(mContext);
			builder.setView(linearLayout);
			builder.setPositiveButton("YES",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							GmailGender gmailGender = new GmailGender(mContext);
							gmailGender.setCallBack(new GmailGender.CallBack() {

								@Override
								public void sucess() {
									loginLogoutCallBack.onComplete(null);
								}

								@Override
								public void error(String message) {
									loginLogoutCallBack.error(message);
								}
							});
							gmailGender.authent(eTUser.getText().toString(),
									eTUserPassword.getText().toString());
						}
					});
			builder.show();
		}

	}

	public void logout(LoginLogoutCallBack oginLogoutCallBack) {
		loginLogoutCallBack = oginLogoutCallBack;
		if (twitterApp.hasAccessToken()) {
			twitterApp.resetAccessToken();
			loginLogoutCallBack.onComplete(null);
		} else if (facebook.getAccessToken() != null
				&& !"".equals(facebook.getAccessToken())) {
			try {
				facebook.logout(mContext);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (!gmailGender.getGoogleToken().equals("")) {
			gmailGender.clear();
			loginLogoutCallBack.onComplete(null);
		}
	}

	public boolean isLogin() {
		if (twitterApp.hasAccessToken()) {
			return true;
		} else if (facebook.getAccessToken() != null
				&& !"".equals(facebook.getAccessToken())) {
			return true;
		} else if (!gmailGender.getGoogleToken().equals("")) {
			return true;
		}
		return false;
	}

	public String getUserName() {
		if (!gmailGender.getGoogleToken().equals(""))
			return gmailGender.getUserInfor();
		return null;
	}

	public String getToken() {
		return "";
	}

	private LoginReference() {

	}
}
