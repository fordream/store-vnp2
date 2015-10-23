package vnp.mr.mintmedical.config;

import org.json.JSONException;
import org.json.JSONObject;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

public class ConfigCallback extends MBaseCallback {
	public ConfigCallback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "get_config";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
		if (canUpdate(response))
			new DBConfig(mContext).save(response);

	}

	@Override
	public boolean canUpdate(String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			return jsonObject.get("appointment_type") != null;
		} catch (JSONException e) {
		}
		return false;
	}
}