package vnp.mr.mintmedical.s6;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

public class S6BRequestpresCalBack extends MBaseCallback {
	public S6BRequestpresCalBack(Context context) {
		super(context);
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
	}

	@Override
	public String getApiName() {
		return "request_pres";
	}
}