package org.vnp.storeapp.blocklist;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;

public class BlockCallBack extends BaseCallBack {

	public BlockCallBack(Context context) {
		super(context);
		url = StoreAppUtils.URLBINLIST;
		tag = BlockCallBack.class.getName();
	}

	@SuppressWarnings("unchecked")
	public List<Block> getBinListRemoveAdd(Context context) {
		List<Block> lBins = (List<Block>) parseResponse();
		return lBins;
	}

	public Block getBlockMain() {
		try {
			JSONObject array = new JSONObject(getResponse()).getJSONObject("main-bin");
			return new Block(array);
		} catch (JSONException e) {
			return new Block(BlockType.NONE);
		}
	}

	@Override
	public Object parseResponse() {
		List<Block> list = new ArrayList<Block>();
		try {
			JSONArray array = new JSONObject(getResponse()).getJSONArray("bins");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				list.add(new Block(object));
			}
		} catch (JSONException e) {

		}

		return list;
	}


	public String getHeaderTitle() {

		try {
			JSONObject jsonObject = new JSONObject(getResponse());
			return jsonObject.getJSONObject("header").getString("title-header");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getHeaderBackgroundCorlor() {
		try {
			return new JSONObject(getResponse()).getJSONObject("header").getString("backgound-color");
		} catch (JSONException e) {
		}

		return null;
	}
}