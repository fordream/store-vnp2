package vnp.mr.mintmedical.s6;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vnp.mr.mintmedical.base.BaseItem;

public class S6CItem extends BaseItem {
	public S6CItem(JSONObject jsonObject) {
		super(jsonObject);
	}

	// total
	// taken
	// name
	// unit

	public List<Object> getDetailList() {
		List<Object> list = new ArrayList<Object>();
		String detail = getString("detail");
		try {
			JSONArray jsonArray = new JSONArray(detail);
			for (int i = 0; i < jsonArray.length(); i++) {
				BaseItem baseItem = new BaseItem(jsonArray.getJSONObject(i));
				list.add(baseItem);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}