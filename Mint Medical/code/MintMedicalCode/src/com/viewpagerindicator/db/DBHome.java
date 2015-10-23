package com.viewpagerindicator.db;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import vnp.mr.mintmedical.base.MBaseCallback;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.doctor.Doctor;
import vnp.mr.mintmedical.item.UserLogin;
import android.content.Context;

import com.vnp.core.callback.CallBack;
import com.vnp.core.common.LogUtils;

public class DBHome extends BaseDB {
	DBUserLogin dbUserLogin;

	public DBHome(Context context) {
		super(context);
		dbUserLogin = new DBUserLogin(context);
	}

	@Override
	public Object getData() {
		List<Object> list = new ArrayList<Object>();
		return list;
	}

	@Override
	public String getKey() {
		return getId() + "DBHome";
	}

	public Object getData(String doctor_id) {
		List<Object> list = (List<Object>) getData();
		for (Object doctor : list) {
			if (doctor_id.equals(((Doctor) doctor).id)) {
				return doctor;
			}
		}
		return list;
	}

	public String getId() {
		UserLogin userLogin = (UserLogin) dbUserLogin.getData();
		return userLogin.custId + "";
	}

	@Override
	public CallBack getCallBack() {
		return new MBaseCallback(context) {
			@Override
			public void loadError(int responseCode, String errorMessage) {

			}

			@Override
			public void loadSucess(String response) {
				save(response);
			}

			@Override
			public String getApiName() {
//				return "get_home";
				// 20140925 chuyen sang api moi
				//http://minthealth.co/service/get_homeV2?custid=24
				return "get_homeV2";
			}
		};
	}

	public String getAppointments() {
		if (dbUserLogin.isLogin()) {
			try {
				JSONArray array = new JSONArray(getDataStr());
				return array.getJSONObject(0).getString("detailType");
			} catch (Exception exception) {

			}
		}
		return "0 Upcoming";
	}

	public String getPrescription() {
		if (dbUserLogin.isLogin()) {
			try {
				JSONArray array = new JSONArray(getDataStr());
				return array.getJSONObject(2).getString("detailType");
			} catch (Exception exception) {

			}
		}
		// return "Request prescription renewal";
		return "0 Active";
	}

	public String getNewAndAlerts() {
		if (dbUserLogin.isLogin()) {
			try {
				JSONArray array = new JSONArray(getDataStr());
				return array.getJSONObject(6).getString("detailType");
			} catch (Exception exception) {

			}
		}
		return "0 notification";
	}

	public String getMemberShip() {
		if (dbUserLogin.isLogin()) {
			try {
				JSONArray array = new JSONArray(getDataStr());
//				String status = array.getJSONObject(5).getString("detailType");
//				if (!MintUtils.isMeberShip(context)) {
//					return "Not yet membership";
//				} 
				return array.getJSONObject(5).getString("detailType");
			} catch (Exception exception) {

			}
		}
		return "Membership status";
	}
}