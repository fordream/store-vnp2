package com.example.app.blocklist;

import org.core.store.app.StoreBaseCallBack;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Block extends StoreBaseCallBack {

	public Block(Context context) {
		super(context);
	}

	public Block(Context context, JSONObject jsonObject) {
		super(context);
		try {
			bin_type = jsonObject.getString("bin_type");
			header = jsonObject.getString("header");
			sub_header = jsonObject.getString("sub_header");
			folder = jsonObject.getString("folder");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private String bin_type, header, sub_header;
	private String folder;

	public String getHeader() {
		return header;
	}

	public String getSub_header() {
		return sub_header;
	}

	public String getFolder() {
		return folder;
	}

	public boolean isBig() {
		return "BIG".equals(bin_type);
	}

	@Override
	public Object parseResponse() {
		return null;
	}
}