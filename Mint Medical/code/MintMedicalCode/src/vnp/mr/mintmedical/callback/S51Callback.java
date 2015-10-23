package vnp.mr.mintmedical.callback;

import com.viewpagerindicator.db.DBS51;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

public class S51Callback extends MBaseCallback {
	public S51Callback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "get_symptoms";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
		new DBS51(mContext).save(response);
	}
}