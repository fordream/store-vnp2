package vnp.mr.mintmedical.callback;

import com.viewpagerindicator.db.DBS9Item;

import vnp.mr.mintmedical.base.MBaseCallback;
import android.content.Context;

/**
 * get profile
 * 
 * @author teemo
 * 
 */
public class S9Callback extends MBaseCallback {
	public S9Callback(Context context) {
		super(context);
	}

	public String getApiName() {
		return "get_profile";
	}

	@Override
	public void loadError(int responseCode, String errorMessage) {

	}

	@Override
	public void loadSucess(String response) {
		if (canUpdate(response))
			new DBS9Item(mContext).save(response);
	}
}