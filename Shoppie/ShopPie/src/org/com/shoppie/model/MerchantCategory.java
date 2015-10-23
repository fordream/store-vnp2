package org.com.shoppie.model;

import org.w3c.dom.Element;

import com.ict.library.util.XMLfunctions;

public class MerchantCategory {
	private String merchCatId;
	private String merchCatName;

	public MerchantCategory(Element e) {
		merchCatId = XMLfunctions.getValue(e, "merchCatId");
		merchCatName = XMLfunctions.getValue(e, "merchCatName");
	}

	public String getMerchCatId() {
		return merchCatId;
	}

	public void setMerchCatId(String merchCatId) {
		this.merchCatId = merchCatId;
	}

	public String getMerchCatName() {
		return merchCatName;
	}

	public void setMerchCatName(String merchCatName) {
		this.merchCatName = merchCatName;
	}
	
	
}