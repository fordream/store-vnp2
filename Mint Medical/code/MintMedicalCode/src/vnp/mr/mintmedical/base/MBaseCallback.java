package vnp.mr.mintmedical.base;

import vnp.mr.mintmedical.item.UserLogin;
import android.content.Context;

import com.viewpagerindicator.db.DBUserLogin;
import com.vnp.core.callback.ResClientCallBack;
import com.vnp.core.common.LogUtils;
import com.vnp.core.service.RestClient;

public abstract class MBaseCallback extends ResClientCallBack {

	protected Context mContext;

	public MBaseCallback(Context context) {
		mContext = context;
		DBUserLogin dbUserLogin = new DBUserLogin(mContext);
		addParam("custId", ((UserLogin) dbUserLogin.getData()).custId + "");
		addParam("custid", ((UserLogin) dbUserLogin.getData()).custId + "");
	}

	@Override
	public void onCallBack(Object arg0) {
		RestClient restClient = (RestClient) arg0;
		LogUtils.e("ABC", restClient.getResponse() + " : " + restClient.getResponseCode());
		if (restClient.getResponseCode() == 200) {
			loadSucess(restClient.getResponse());
		} else {
			loadError(restClient.getResponseCode(),
					restClient.getErrorMessage());
		}
	}

	@Override
	final public String getUrl() {
		return String.format(MintUtils.BASEURL, getApiName());
	}

	public abstract void loadError(int responseCode, String errorMessage);

	public abstract void loadSucess(String response);

	public abstract String getApiName();

	public boolean canUpdate(String response) {
		return true;
	}
}