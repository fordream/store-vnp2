package vnp.mr.mintmedical.callback;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

public class S4DetailtCallback extends MBaseCallback {
	public S4DetailtCallback(Context context, String event_id) {
		super(context);
		addParam("event_id", event_id);
	}

	public String getApiName() {
		return "cancelevent";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
	}
}