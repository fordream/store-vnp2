package org.vnp.storeapp.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.Item;
import org.vnp.storeapp.service.StoreAppService.BlockType;

public class ItemStatusPackage extends Item {
	public static final int STATUS_FREE = 0;
	public static final int STATUS_BASE = 1;
	public static final int STATUS_PROFESSIONAL = 2;

	private String status;

	public ItemStatusPackage(BlockType type) {
		super(type);
	}

	public ItemStatusPackage(JSONObject object) {
		super(BlockType.BASE);
		try {
			status = object.get("status").toString();
		} catch (JSONException e) {
		}
	}

	public int getStatusCode() {
		if (status == null) {
			return STATUS_FREE;
		} else if (status.equals("FREE")) {
			return STATUS_FREE;
		} else if (status.equals("BASIC")) {
			return STATUS_BASE;
		} else if (status.equals("PROFESSIONAL")) {
			return STATUS_PROFESSIONAL;
		}

		return STATUS_FREE;
	}
}