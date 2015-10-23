package vnp.mr.mintmedical.callback;

import com.viewpagerindicator.db.DBHistory;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

public class HistoryCallback extends MBaseCallback {

	public HistoryCallback(Context context) {
		super(context);

	}

	public String getApiName() {
		return "get_historyevents";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
		new DBHistory(mContext).save(response);
	}
}