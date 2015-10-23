package com.google.android.gms.auth.sample.helloauth;

import org.vnp.peaps.PeapsUtils;
import org.vnp.peaps.R;
import org.vnp.peaps.base.AccountDB;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.vnp.core.datastore.DataStore;

public class GoogleApi {
	public static final String TAG = "PlayHelloActivity";
	public static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
	public static final String EXTRA_ACCOUNTNAME = "extra_accountname";
	public static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
	public static final int REQUEST_CODE_RECOVER_FROM_AUTH_ERROR = 1001;
	public static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1002;
	private IAbstractGetNameTaskCallBack abstractGetNameTaskCallBack;
	private Activity mContext;

	private String mEmail;

	public GoogleApi(Activity mContext, TextView mOut) {
		this.mContext = mContext;
		DataStore.getInstance().init(mContext);
		abstractGetNameTaskCallBack = new IAbstractGetNameTaskCallBack(
				mContext, mOut);
	}

	public static enum Type {
		FOREGROUND
	}

	public void onCreate(Intent intent, String mEmail) {
		// Bundle extras = intent.getExtras();
		// if (extras.containsKey(GoogleApi.EXTRA_ACCOUNTNAME)) {
		// mEmail = extras.getString(GoogleApi.EXTRA_ACCOUNTNAME);
		// getTask(mContext, mEmail, GoogleApi.SCOPE).execute();
		// }
	}

	public AbstractGetNameTask getTask(Activity activity, String email,
			String scope,
			IAbstractGetNameTaskCallBack iAbstractGetNameTaskCallBack) {
		AccountDB accountDB = new AccountDB(activity);
		accountDB.saveEmail(email);
		accountDB.saveScope(scope);
		return new AbstractGetNameTask(activity, email, scope,
				iAbstractGetNameTaskCallBack);
	}

	public void pickUserAccount() {
		String[] accountTypes = new String[] { "com.google" };
		Intent intent = AccountPicker.newChooseAccountIntent(null, null,
				accountTypes, false, null, null, null, null);
		mContext.startActivityForResult(intent,
				GoogleApi.REQUEST_CODE_PICK_ACCOUNT);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == GoogleApi.REQUEST_CODE_PICK_ACCOUNT) {
			if (resultCode == Activity.RESULT_OK) {
				mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
				getUsername();
			} else if (resultCode == Activity.RESULT_CANCELED) {
				Toast.makeText(mContext, "You must pick an account",
						Toast.LENGTH_SHORT).show();
			}
		} else if ((requestCode == GoogleApi.REQUEST_CODE_RECOVER_FROM_AUTH_ERROR || requestCode == GoogleApi.REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR)
				&& resultCode == Activity.RESULT_OK) {
			if (data == null) {
				abstractGetNameTaskCallBack
						.showErrorMessage("Unknown error, click the button again");
				return;
			}
			if (resultCode == Activity.RESULT_OK) {
				getTask(mContext, mEmail, GoogleApi.SCOPE,
						abstractGetNameTaskCallBack).execute();
				return;
			}
			if (resultCode == Activity.RESULT_CANCELED) {
				abstractGetNameTaskCallBack
						.showErrorMessage("User rejected authorization.");
				return;
			}
			abstractGetNameTaskCallBack
					.showErrorMessage("Unknown error, click the button again");
			return;
		}
	}

	public void getUsername() {
		if (mEmail == null) {
			pickUserAccount();
		} else {
			if (PeapsUtils.isDeviceOnline(mContext)) {
				getTask(mContext, mEmail, GoogleApi.SCOPE,
						abstractGetNameTaskCallBack).execute();
			} else {
				Toast.makeText(mContext, "No network connection available",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void greetTheUser() {

		// Intent intent = new Intent(this, HelloActivity.class);
		// intent.putExtra(HelloActivity.TYPE_KEY,
		// Type.FOREGROUND.name());
		// startActivity(intent);
		int statusCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(mContext);
		if (statusCode == ConnectionResult.SUCCESS) {
			getUsername();
		} else if (GooglePlayServicesUtil.isUserRecoverableError(statusCode)) {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(statusCode,
					mContext, 0 /* request code not used */);
			dialog.show();
		} else {
			Toast.makeText(mContext, R.string.unrecoverable_error,
					Toast.LENGTH_SHORT).show();
		}
	}
}