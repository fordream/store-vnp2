package org.vnp.storeapp.block.gallery;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;

public class GalleryCallBack extends BaseCallBack {
	public List<Object> lUrl = new ArrayList<Object>();
//	public String content = "";

	public GalleryCallBack(Context context) {
		super(context);
		url = StoreAppUtils.URLGALLERY;
		tag = GalleryCallBack.class.getName();
	}

	// @Override
	// public Object execute() {
	// executeLoadUrl();
	// getGalleryList(mContext);
	// return this;
	// }


	@Override
	public Object parseResponse() {
		try {
			JSONObject jsonObject = new JSONObject(getResponse());
//			content = jsonObject.getString("text");
			JSONArray array = jsonObject.getJSONArray("gallery");
			for (int i = 0; i < array.length(); i++) {
				lUrl.add(array.getJSONObject(i).getString("img"));
			}
		} catch (JSONException e) {
		}
		return lUrl;
	}
}
