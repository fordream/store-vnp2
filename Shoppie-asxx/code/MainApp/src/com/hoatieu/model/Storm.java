package com.hoatieu.model;

import org.json.JSONObject;

import com.hoatieu.Utility;

public class Storm {
	private String id;
	private String eventCode;
	private String longitude;
	private String latitude;
	private String eventName;
	private String eventTimeStamp;
	private String eventDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public Storm(JSONObject jsonObject) {
		try {
			eventCode = jsonObject.getString("eventCode");
			longitude = jsonObject.getString("longitude");
			latitude = jsonObject.getString("latitude");
			eventName = jsonObject.getString("eventName");
			eventTimeStamp = jsonObject.getString("eventTimeStamp");
			eventDesc = jsonObject.getString("eventDesc");
		} catch (Exception exception) {

		}
	}

	public boolean canParseToDuble() {
		return Utility.canParseToDuble(getLongitude()) && Utility.canParseToDuble(getLatitude());
	}

	public Storm() {
		// TODO Auto-generated constructor stub
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventTimeStamp() {
		return eventTimeStamp;
	}

	public void setEventTimeStamp(String eventTimeStamp) {
		this.eventTimeStamp = eventTimeStamp;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

}
