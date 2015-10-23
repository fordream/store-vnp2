package vnp.mr.mintmedical.doctor;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

public class DoctorCallback extends MBaseCallback {
	public DoctorCallback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "get_doctor";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
		new DBDoctor(mContext).save(response);
	}
}