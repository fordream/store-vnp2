package vnp.mr.mintmedical.service;

import java.util.List;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.callback.S9Callback;
import vnp.mr.mintmedical.clinic.ClinicCallback;
import vnp.mr.mintmedical.config.ConfigCallback;
import vnp.mr.mintmedical.config.DBConfig;
import vnp.mr.mintmedical.doctor.DBDoctor;
import vnp.mr.mintmedical.doctor.Doctor;
import vnp.mr.mintmedical.doctor.DoctorCallback;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.viewpagerindicator.db.DBClinic;
import com.viewpagerindicator.db.DBDoctorEvent;
import com.vnp.core.callback.CallBack;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.callback.ResClientCallBack;
import com.vnp.core.service.RestClient;
import com.vnp.core.service.RestClient.RequestMethod;

public class MintService extends Service {
	public static void startService(Context context) {
		context.startService(new Intent(context, MintService.class));
	}

	public MintService() {
		super();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new ExeCallBack().executeAsynCallBack(new DoctorCallback(this));
		new ExeCallBack().executeAsynCallBack(new ClinicCallback(this));
		new ExeCallBack().executeAsynCallBack(new S9Callback(this));
		new ExeCallBack().executeAsynCallBack(new ConfigCallback(this));
		return super.onStartCommand(intent, flags, startId);
	}

	// private void loadEventDoctor() {
	// DBDoctor dbDoctor = new DBDoctor(this);
	// List<Object> list = (List<Object>) dbDoctor.getData();
	// for (Object object : list) {
	// if (object instanceof Doctor) {
	// final String doctor_id = ((Doctor) object).id;
	// new ExeCallBack().executeAsynCallBack(new CallBack() {
	// @Override
	// public void onCallBack(Object object) {
	//
	// RestClient client = (RestClient) object;
	// if (client.getResponseCode() == 200) {
	// new DBDoctorEvent(MintService.this, doctor_id)
	// .save(client.getResponse());
	// }
	// }
	//
	// @Override
	// public Object execute() {
	// RestClient client = new RestClient(
	// MintUtils.URL_GETEVENT);
	// client.addParam("limit", "1000");
	// client.addParam("doctor_id", doctor_id);
	// try {
	//
	// client.execute(RequestMethod.GET);
	// } catch (Exception e) {
	// }
	// return client;
	// }
	// });
	//
	// }
	// }
	// }
}