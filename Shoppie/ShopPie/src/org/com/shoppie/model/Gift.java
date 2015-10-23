package org.com.shoppie.model;

import org.w3c.dom.Element;

import com.ict.library.util.XMLfunctions;

public class Gift {
	private String giftId;
	private String merchId;
	private String description;
	private String pieQty;
	private String giftImage;

	public Gift(Element e) {
		giftId = XMLfunctions.getValue(e, "giftId");
		merchId = XMLfunctions.getValue(e, "merchId");
		description = XMLfunctions.getValue(e, "description");
		pieQty = XMLfunctions.getValue(e, "pieQty");
		giftImage = XMLfunctions.getValue(e, "giftImage");
	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPieQty() {
		return pieQty;
	}

	public void setPieQty(String pieQty) {
		this.pieQty = pieQty;
	}

	public String getGiftImage() {
		return giftImage;
	}

	public void setGiftImage(String giftImage) {
		this.giftImage = giftImage;
	}

}
