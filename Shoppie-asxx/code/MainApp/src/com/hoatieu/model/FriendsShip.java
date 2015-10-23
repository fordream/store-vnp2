package com.hoatieu.model;

import org.json.JSONObject;

import android.util.Log;

import com.hoatieu.Utility;

public class FriendsShip {
	private String localId;
	private String localName;
	private String datetime;
	private String latitude;
	private String longitude;
	private String mobile;
	private String localType;
	private String status;
	private String deviceId;
	private String deviceToken;
	private String deviceType;

	public FriendsShip() {
	}

	public FriendsShip(JSONObject jsonObject) {
		try {
			localId = jsonObject.getString("localId");
			localName = jsonObject.getString("localName");
			datetime = jsonObject.getString("datetime");
			latitude = jsonObject.getString("latitude");
			longitude = jsonObject.getString("longitude");
			mobile = jsonObject.getString("mobile");
			localType = jsonObject.getString("localType");
			status = jsonObject.getString("status");
			deviceId = jsonObject.getString("deviceId");
			deviceToken = jsonObject.getString("deviceToken");
			deviceType = jsonObject.getString("deviceType");
		} catch (Exception exception) {
			Log.e("AAAAAA", "ssss", exception);
		}
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLocalType() {
		return localType;
	}

	public void setLocalType(String localType) {
		this.localType = localType;
	}

	public boolean canParseToDuble() {
		return Utility.canParseToDuble(getLongitude()) && Utility.canParseToDuble(getLatitude());
	}

}
