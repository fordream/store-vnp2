package vnp.mr.mintmedical.item;

import org.json.JSONObject;

import vnp.mr.mintmedical.base.BaseItem;

public class S9Item extends BaseItem {
	public S9Item(JSONObject object) {
		super(object);
		email = getString(object, "email");
		fullname = getString(object, "fullname");
		member_expire_date = getString(object, "member_expire_date");
		doctor_id = getString(object, "doctor_id");
		doctor_name = getString(object, "doctor_name");
		doctor_email = getString(object, "doctor_email");
	}

	public String email, fullname, member_expire_date, doctor_id, doctor_name;
	public String doctor_email;
}
