package vnp.mr.mintmedical.s6;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

import com.viewpagerindicator.db.BaseDB;
import com.vnp.core.callback.CallBack;

public class DBS6Brecentprescription extends BaseDB {

	public DBS6Brecentprescription(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		List<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(getDataStr());
			for (int i = 0; i < array.length(); i++) {
				JSONArray array2 = null;
				Object object = array.getJSONObject(i).get("detail");

				if (object instanceof JSONArray) {
					array2 = (JSONArray) (object);
				} else if (object instanceof String) {
					array2 = new JSONArray(object.toString());
				}
				for (int j = 0; array2 != null && j < array2.length(); j++) {
					list.add(new S6BItem(true, array2.getJSONObject(j)));
				}
			}
		} catch (Exception e) {
		}
		return list;
	}

	public Object getData(String doctor_id) {
		return null;
	}

	@Override
	public String getKey() {
		return "DBS6Brecentprescription";
	}

	@Override
	public CallBack getCallBack() {
		return new S6BrecentprescriptionCalBack(context);
	}

	public class S6BrecentprescriptionCalBack extends MBaseCallback {
		public S6BrecentprescriptionCalBack(Context context) {
			super(context);
			addParam("limit", "1");
		}

		@Override
		public void loadError(int responseCode, String errorMessage) {

		}

		@Override
		public void loadSucess(String response) {
			save(response);
		}

		@Override
		public String getApiName() {
			return "get_recentprescription";
		}
	}

	public String getLastId() {
		try {
			JSONArray array = new JSONArray(getDataStr());
			return array.getJSONObject(0).getString("id");
		} catch (Exception e) {
			return "";
		}
	}

}
