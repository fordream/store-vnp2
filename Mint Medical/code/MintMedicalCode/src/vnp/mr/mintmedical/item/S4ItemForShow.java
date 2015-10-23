package vnp.mr.mintmedical.item;

import org.json.JSONObject;

import android.os.Bundle;
import vnp.mr.mintmedical.base.BaseItem;

public class S4ItemForShow extends BaseItem {
	public S4ItemForShow(String time2, JSONObject jsonObject) {
		time = time2;
		start_tick = getString(jsonObject,"start_tick");
		doctor_id= getString(jsonObject,"doctor_id");
		title= getString(jsonObject,"title");
		status= getString(jsonObject,"status");
		start_date= getString(jsonObject,"start_date");
		start_time= getString(jsonObject,"start_time");
		doctor_fullname= getString(jsonObject,"doctor_fullname");
		avatar= getString(jsonObject,"avatar");
		office_id= getString(jsonObject,"office_id");
		latitude= getString(jsonObject,"latitude");
		longitude= getString(jsonObject,"longitude");
		office_address= getString(jsonObject,"office_address");
		level= getString(jsonObject,"level");
		id= getString(jsonObject,"id");
		phone= getString(jsonObject,"phone");
		professional= getString(jsonObject,"professional");
	}


	public String professional;
	public String id,phone;
	public String time
		,start_tick
		,doctor_id
		,title
		,status
		,level
		,start_date
		,start_time
		,doctor_fullname
		,avatar
		,office_id
		,latitude
		,longitude
		,office_address;
	public int count;
	public boolean isUpcoming;

	public String toJson() {
		String parttern = "	\"%s\":\"%s\"";
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		builder.append(String.format(parttern,"time",time)).append(",\n");
		builder.append(String.format(parttern,"start_tick",start_tick)).append(",\n");
		builder.append(String.format(parttern,"doctor_id",doctor_id)).append(",\n");
		builder.append(String.format(parttern,"title",title)).append(",\n");
		builder.append(String.format(parttern,"status",status)).append(",\n");
		builder.append(String.format(parttern,"level",level)).append(",\n");
		builder.append(String.format(parttern,"start_date",start_date)).append(",\n");
		builder.append(String.format(parttern,"start_time",start_time)).append(",\n");
		builder.append(String.format(parttern,"doctor_fullname",doctor_fullname)).append(",\n");
		builder.append(String.format(parttern,"avatar",avatar)).append(",\n");
		builder.append(String.format(parttern,"office_id",office_id)).append(",\n");
		builder.append(String.format(parttern,"latitude",latitude)).append(",\n");
		builder.append(String.format(parttern,"longitude",longitude)).append(",\n");
		builder.append(String.format(parttern,"office_address",office_address)).append(",\n");
		builder.append(String.format(parttern,"count",count)).append(",\n");
		builder.append(String.format(parttern,"phone",phone)).append(",\n");
		builder.append(String.format(parttern,"id",id)).append(",\n");
		builder.append(String.format(parttern,"isUpcoming",isUpcoming)).append("\n");
		builder.append("}");
		return builder.toString();
	}
	
	@Override
	public Bundle toExtras() {
		Bundle bundle =super.toExtras() ;
		
		bundle.putString("time",time);
		bundle.putString("start_tick",start_tick);
		bundle.putString("doctor_id",doctor_id);
		bundle.putString("title",title);
		bundle.putString("status",status);
		bundle.putString("level",level);
		bundle.putString("start_date",start_date);
		bundle.putString("start_time",start_time);
		bundle.putString("doctor_fullname",doctor_fullname);
		bundle.putString("avatar",avatar);
		bundle.putString("office_id",office_id);
		bundle.putString("latitude",latitude);
		bundle.putString("longitude",longitude);
		bundle.putString("office_address",office_address);
		bundle.putString("count",count + "");
		bundle.putString("isUpcoming",isUpcoming +"");
		bundle.putString("id",id +"");
		bundle.putString("phone",phone +"");
		return bundle;
	}
}
