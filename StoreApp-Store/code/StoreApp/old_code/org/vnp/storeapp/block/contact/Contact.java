package org.vnp.storeapp.block.contact;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.Item;
import org.vnp.storeapp.service.StoreAppService.BlockType;

public class Contact extends Item {
	public String img;
	public String text;

	public Contact(BlockType type) {
		super(type);
	}

	public Contact(JSONObject object) {
		super(BlockType.BASE);
		try {
			img = object.getString("img");
			text = object.getString("text");
		} catch (JSONException e) {
		}
	}

}