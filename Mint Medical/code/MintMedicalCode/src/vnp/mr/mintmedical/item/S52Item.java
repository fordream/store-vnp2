package vnp.mr.mintmedical.item;

import org.json.JSONObject;

import vnp.mr.mintmedical.base.BaseItem;

public class S52Item extends BaseItem {
	public S52Item(JSONObject object) {
		code = getString(object, "code");
		question = getString(object, "question");
	}

	public String code, question;
	public boolean isCheced;

	public void setChecked(boolean checked) {
		isCheced = checked;
	}
}