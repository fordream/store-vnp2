package vnp.mr.mintmedical.base;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

public class BaseItem {
	public final String getString(JSONObject jsonObject, String krey) {
		try {
			return jsonObject.getString(krey);
		} catch (Exception e) {
			return "";
		}
	}

	public BaseItem() {
	}

	private JSONObject jsonObject;

	public BaseItem(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getString(String key) {
		try {
			return jsonObject.getString(key);
		} catch (JSONException e) {
			return null;
		}
	}

	public String toJson() {
		return "";
	}

	public Bundle toExtras() {
		return new Bundle();
	}
}