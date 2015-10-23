package vnp.mr.mintmedical.s6;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;
import android.util.Log;

import com.viewpagerindicator.db.BaseDB;
import com.vnp.core.callback.CallBack;
import com.vnp.core.common.LogUtils;

public class DBS6BMedicationList extends BaseDB {

	public DBS6BMedicationList(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		List<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(getDataStr());
			LogUtils.e("ADCS", getDataStr() +"");
			for (int i = 0; i < array.length(); i++) {
				list.add(new S6BItem(false, array.getJSONObject(i)));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public Object getData(String doctor_id) {
		List<Object> list = (List<Object>) getData();
		for (Object object : list) {
			S6CItem s6cItem = (S6CItem) object;
			if (s6cItem.getString("id").equals(doctor_id)) {
				return s6cItem;
			}
		}

		return null;
	}

	@Override
	public String getKey() {
		return "DBS6CMedicationList";
	}

	@Override
	public CallBack getCallBack() {
		return new S6MedicationListCalBack(context);
	}

	public class S6MedicationListCalBack extends MBaseCallback {
		public S6MedicationListCalBack(Context context) {
			super(context);
		}

		@Override
		public void loadError(int responseCode, String errorMessage) {

		}

		@Override
		public void loadSucess(String response) {
			if (canUpdate(response))
				save(response);
		}

		@Override
		public boolean canUpdate(String response) {
			try {
				JSONArray jsonObject = new JSONArray(response);
				return true;
			} catch (JSONException e) {
			}
			return false;
		}

		@Override
		public String getApiName() {
			return "get_medicationlist";
		}
	}

}
