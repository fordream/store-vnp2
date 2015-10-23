package org.vnp.storeapp.block.map;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.Item;
import org.vnp.storeapp.service.StoreAppService.BlockType;

public class Map extends Item {
	public String title, description, lat, lng, numTxt;

	public Map(BlockType type) {
		super(type);
	}

	public Map(JSONObject object) {
		super(BlockType.BASE);
		try {
			title = object.getString("title");
			description = object.getString("description");
			lat = object.getString("lat");
			lng = object.getString("lng");
			numTxt = object.getString("numTxt");
		} catch (JSONException e) {
		}
	}

	public double getLat() {
		return Double.parseDouble(lat);
	}

	public double getLng() {
		return Double.parseDouble(lng);
	}
}