package vnp.mr.mintmedical.item;

import org.json.JSONObject;

import vnp.mr.mintmedical.base.BaseItem;

public class S11 extends BaseItem {
	public S11(JSONObject object) {
		super(object);
		id = getString(object, "id");
		info = getString(object, "info");
		url = getString(object, "url");
		image = getString(object, "image");
		info_datetime = getString(object, "info_datetime");
		sender = getString(object, "sender");
	}

	public String id, info, url, image, info_datetime, sender;
}