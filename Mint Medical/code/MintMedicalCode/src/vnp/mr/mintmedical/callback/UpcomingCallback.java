package vnp.mr.mintmedical.callback;

import com.viewpagerindicator.db.DBUpcomingevents;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;
import android.util.Log;

public class UpcomingCallback extends MBaseCallback {

	public UpcomingCallback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "get_upcomingevents";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
		new DBUpcomingevents(mContext).save(response);
	}
}