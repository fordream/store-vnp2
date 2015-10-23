package vnp.mr.mintmedical.clinic;

import com.viewpagerindicator.db.DBClinic;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

public class ClinicCallback extends MBaseCallback {
	public ClinicCallback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "get_clinic";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
		new DBClinic(mContext).save(response);
	}
}