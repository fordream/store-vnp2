package org.vnp.storeapp.block.about;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.Item;
import org.vnp.storeapp.service.StoreAppService.BlockType;

public class About extends Item {
	public String img;
	public String text;

	public About(BlockType type) {
		super(type);
	}

	public About(JSONObject object) {
		super(BlockType.BASE);
		try {
			img = object.getString("img");
			text = object.getString("text");
		} catch (JSONException e) {
		}
	}
}