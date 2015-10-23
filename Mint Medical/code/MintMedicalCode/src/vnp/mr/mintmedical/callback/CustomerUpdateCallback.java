package vnp.mr.mintmedical.callback;

import org.json.JSONArray;
import org.json.JSONException;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.vnp.core.common.CommonAndroid;

public class CustomerUpdateCallback extends MBaseCallback {
	public CustomerUpdateCallback(Context context, String devicetoken) {
		super(context);
		addParam("devicetype", "Android");
		addParam("appversion", "" + CommonAndroid.getVersionApp(mContext));
		addParam("osversion", Build.VERSION.SDK_INT + "");
		addParam("devicetoken", devicetoken);
	}

	public String getApiName() {
		return "customerupdate";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}


	@Override
	public void loadSucess(String response) {
		try {
			JSONArray array = new JSONArray(response);
			if (array.getJSONObject(0).getString("dataValue").equals("1")) {
				Log.e("registerpush", "ok");
			}else{
				Log.e("registerpush", "fail" + " : " + response);
			}
		} catch (JSONException e) {
			Log.e("registerpush", "fail"+ " : " + response);
		}
	}
}