package vnp.mr.mintmedical.callback;

import org.json.JSONArray;
import org.json.JSONException;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

import com.viewpagerindicator.db.DBS11;

public class S11Callback extends MBaseCallback {
	public S11Callback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "get_newalerts";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
		// if (canUpdate(response))
		new DBS11(mContext).save(response);
	}

	@Override
	public boolean canUpdate(String response) {
		try {
			JSONArray array = new JSONArray(response);
			return true;
		} catch (JSONException e) {
		}
		return false;
	}
}