package org.com.shoppie.model;

import org.w3c.dom.Element;

import com.ict.library.util.XMLfunctions;

public class Store {
	private String storeId;
	private String merchId;
	private String storeName;
	private String storeAddress;
	private String storeCode;
	private String latitude;
	private String longtitude;

	public Store(Element e) {
		storeId = XMLfunctions.getValue(e, "storeId");
		merchId = XMLfunctions.getValue(e, "merchId");
		storeName = XMLfunctions.getValue(e, "storeName");
		storeAddress = XMLfunctions.getValue(e, "storeAddress");
		storeCode = XMLfunctions.getValue(e, "storeCode");
		latitude = XMLfunctions.getValue(e, "latitude ");
		longtitude = XMLfunctions.getValue(e, "longtitude");
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

}