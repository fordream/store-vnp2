package vnp.mr.mintmedical.doctor;

import org.json.JSONObject;

import vnp.mr.mintmedical.base.BaseItem;

public class Doctor extends BaseItem {
	public Doctor() {
	}

	public Doctor(JSONObject jsonObject) {
		id = getString(jsonObject,"id");
		name =  getString(jsonObject,"name");
		email =  getString(jsonObject,"email");
		block =  getString(jsonObject,"block");
		avatar =  getString(jsonObject,"avatar");
		clinic_id =  getString(jsonObject,"clinic_id");
		contact =  getString(jsonObject,"contact");
		theme =  getString(jsonObject,"theme");
		level =  getString(jsonObject,"level");
		professional =  getString(jsonObject,"professional");
	}

	public String id, clinic_id, contact;
	public String name;
	public String theme;
	public String level;
	public String username;
	public String email;
	public String block;
	public String avatar;
	public String professional;
}
