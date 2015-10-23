package com.hoatieu.service;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Login service - authentication service
 * 
 * @author ThuanHM <thuanhm@vnext.vn>
 * @since 2013/12/27
 * */
public class LoginService extends BaseService {

	private static final int TIMEOUT = 30000;

	public LoginService(Context activity) {
		super(activity);
	}

	/**
	 * Execute login action
	 * 
	 * @author ThuanHM <thuanhm@vnext.vn>
	 * @since 2013/12/27
	 * 
	 * @param <String>
	 *            email - email string
	 * @param <String>
	 *            password - password string
	 * @throws UnsupportedEncodingException
	 * */

	public void getData(String url, JSONObject jsonParams, JsonHttpResponseHandler callBack)
			throws UnsupportedEncodingException {
		ByteArrayEntity entity = new ByteArrayEntity(jsonParams.toString().getBytes("UTF-8"));
		this.client.setURLEncodingEnabled(true);
		client.setTimeout(TIMEOUT);
		this.post(url, entity, callBack);
	}

	public void sendData(String url, JSONObject jsonParams, AsyncHttpResponseHandler callBack) {
		try {
			ByteArrayEntity entity = new ByteArrayEntity(jsonParams.toString().getBytes("UTF-8"));
			this.client.setURLEncodingEnabled(true);
			client.setTimeout(TIMEOUT);
			this.post(url, entity, callBack);
		} catch (Exception e) {
			Log.e("AAAAA1", "Det");
		}
	}

}