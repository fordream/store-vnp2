package org.com.shoppie.model;

import org.w3c.dom.Element;

import com.ict.library.util.XMLfunctions;

public class Merchant extends MResponse {
	private String merchId;
	private String merchName;
	private String merchCatId;
	private String merchImage;

	public Merchant(Element e) {
		merchId = XMLfunctions.getValue(e, "merchId");
		merchName = XMLfunctions.getValue(e, "merchName");
		merchCatId = XMLfunctions.getValue(e, "merchCatId");
		merchImage = XMLfunctions.getValue(e, "merchImage");
	}

	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getMerchCatId() {
		return merchCatId;
	}

	public void setMerchCatId(String merchCatId) {
		this.merchCatId = merchCatId;
	}

	public String getMerchImage() {
		return merchImage;
	}

	public void setMerchImage(String merchImage) {
		this.merchImage = merchImage;
	}

	@Override
	protected String toJSon() {
		return super.toJSon();
	}
}
