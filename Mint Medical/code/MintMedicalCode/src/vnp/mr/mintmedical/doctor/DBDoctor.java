package vnp.mr.mintmedical.doctor;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.viewpagerindicator.db.BaseDB;

import android.content.Context;

public class DBDoctor extends BaseDB {

	public DBDoctor(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		
		List<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(getDataStr());
			for (int i = 0; i < array.length(); i++) {
				Doctor item = new Doctor(array.getJSONObject(i));
				list.add(item);
			}
		} catch (JSONException e) {
		}

		return list;
	}

	@Override
	public String getKey() {
		return "DBDoctor";
	}

	public Object getData(String doctor_id) {
		List<Object> list = (List<Object> )getData();
		for(Object doctor : list){
			if(doctor_id.equals(((Doctor)doctor).id)){
				return doctor;
			}
		}
		return null;
	}

}
