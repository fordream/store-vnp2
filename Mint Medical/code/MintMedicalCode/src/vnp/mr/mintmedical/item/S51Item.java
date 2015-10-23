package vnp.mr.mintmedical.item;

import org.json.JSONObject;

import vnp.mr.mintmedical.base.BaseItem;

public class S51Item extends BaseItem {
	public S51Item(JSONObject object) {
		id = getString(object, "id");
		symp_name = getString(object, "symp_name");
		symp_desc = getString(object, "symp_desc");
	}

	public String id, symp_name,symp_desc;
}
