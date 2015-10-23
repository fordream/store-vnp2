package org.vnp.storeapp.block.map;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.service.StoreAppService.BlockType;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;

public class MapCallBack extends BaseCallBack {

	public MapCallBack(Context context) {
		super(context);
		url = StoreAppUtils.URLAMAP;
		tag = MapCallBack.class.getName();
	}


	@Override
	public Object parseResponse() {
		Map itemMap = new Map(BlockType.BASE);
		try {
			itemMap = new Map(new JSONObject(getResponse()).getJSONObject("map"));
		} catch (JSONException e) {
		}
		return itemMap;
	}
}