package vnp.mr.mintmedical.s5;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vnp.mr.mintmedical.base.BaseItem;
import vnp.mr.mintmedical.base.MBaseCallback;
import vnp.mr.mintmedical.item.S4ItemForShow;
import android.content.Context;

import com.viewpagerindicator.db.BaseDB;
import com.vnp.core.callback.CallBack;

public class DBS5CheckRequestAnStatus extends BaseDB {

	public DBS5CheckRequestAnStatus(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		List<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(getDataStr());
			for (int i = 0; i < array.length(); i++) {
				BaseItem baseItem = new BaseItem(array.getJSONObject(i));
				list.add(baseItem);
			}
		} catch (Exception exception) {
		}

		return list;
	}

	public Object getData(String doctor_id) {
		List<Object> list = (List<Object>) getData();

		for (Object object : list) {
			BaseItem baseItem = (BaseItem) object;

			if (doctor_id != null && doctor_id.equals(baseItem.getString("id"))) {
				return baseItem;
			}
		}
		return new BaseItem();
	}

	@Override
	public CallBack getCallBack() {
		return new MBaseCallback(context) {
			@Override
			public void loadSucess(String response) {
				if (canUpdate(response)) {
					save(response);
				}

				_loadSucesṣ̣̣̣();
			}

			@Override
			public void loadError(int responseCode, String errorMessage) {

			}

			@Override
			public String getApiName() {
				return "get_treatmenow";
			}

			@Override
			public boolean canUpdate(String response) {
				try {
					JSONArray array = new JSONArray(response);
					return true;
				} catch (JSONException e) {
				}
				return false;
			}
		};
	}

	public void _loadSucesṣ̣̣̣() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getKey() {
		return "DBS5CheckRequestAnStatus";
	}

}
