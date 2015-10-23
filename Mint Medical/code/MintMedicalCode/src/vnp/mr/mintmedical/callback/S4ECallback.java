package vnp.mr.mintmedical.callback;

import vnp.mr.mintmedical.base.MBaseCallback;
import vnp.mr.mintmedical.base.MintUtils;
import android.content.Context;

import com.viewpagerindicator.db.DBS11;

public class S4ECallback extends MBaseCallback {
	public S4ECallback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "bookevent";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
	}
}