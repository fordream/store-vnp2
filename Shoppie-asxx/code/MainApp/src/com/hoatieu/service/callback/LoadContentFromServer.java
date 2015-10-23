/**
 * 
 */
package com.hoatieu.service.callback;

import android.content.Context;
import android.location.Location;

import com.hoatieu.Constant;
import com.hoatieu.location.VNPLocationUtils;
import com.vnp.core.callback.CallBack;
import com.vnp.core.datastore.DataStore;
import com.vnp.core.service.RestClient;
import com.vnp.core.service.RestClient.RequestMethod;

/**
 * @author teemo
 * 
 */
public class LoadContentFromServer extends CallBack {
	private Context mContext;

	/**
	 * 
	 */
	public LoadContentFromServer(Context context) {
		mContext = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnp.core.callback.CallBack#execute()
	 */
	@Override
	public Object execute() {
		Location mLocation = VNPLocationUtils.getInstance().lastKnownLocation;
		RestClient restClient = new RestClient(Constant.API_GETFRIENDS);

		try {
			restClient.execute(RequestMethod.GET);
			if (restClient.getResponseCode() == 200) {
				DataStore.getInstance().save(Constant.API_GETFRIENDS, restClient.getResponse());
			}
		} catch (Exception e) {
		}

		restClient = new RestClient(Constant.API_GETSTORM);
		try {
			restClient.execute(RequestMethod.GET);
			if (restClient.getResponseCode() == 200) {
				DataStore.getInstance().save(Constant.API_GETSTORM, restClient.getResponse());
			}
		} catch (Exception e) {
		}

		return restClient;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnp.core.callback.CallBack#onCallBack(java.lang.Object)
	 */
	@Override
	public void onCallBack(Object arg0) {

	}
}