package vnp.mr.mintmedical.s6;

import org.json.JSONObject;

import vnp.mr.mintmedical.base.BaseItem;

public class S6BItem extends BaseItem {
	public boolean isLastRequest;
	public boolean isChecked;

	public S6BItem(boolean isLastRequest, JSONObject jsonObject) {
		super(jsonObject);
		this.isLastRequest = isLastRequest;
	}
	// total
	// taken
	// name
	// unit
}