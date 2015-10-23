package vnp.mr.mintmedical.callback;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

public class S53Callback extends MBaseCallback {
	public S53Callback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "treatmenow";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
	}
}