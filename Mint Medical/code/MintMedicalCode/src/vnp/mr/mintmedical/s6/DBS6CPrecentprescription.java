package vnp.mr.mintmedical.s6;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;
import android.util.Log;

import com.viewpagerindicator.db.BaseDB;
import com.vnp.core.callback.CallBack;

public class DBS6CPrecentprescription extends BaseDB {

	public DBS6CPrecentprescription(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		List<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(getDataStr());
			for (int i = 0; i < array.length(); i++) {
				list.add(new S6CItem(array.getJSONObject(i)));
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
		return "DBS6Brecentprescription";
	}

	@Override
	public CallBack getCallBack() {
		return new S6BrecentprescriptionCalBack(context);
	}

	public class S6BrecentprescriptionCalBack extends MBaseCallback {
		public S6BrecentprescriptionCalBack(Context context) {
			super(context);
			addParam("limit", "10");
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

}
