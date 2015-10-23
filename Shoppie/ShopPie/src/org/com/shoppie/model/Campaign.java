package org.com.shoppie.model;

import org.w3c.dom.Element;

import com.ict.library.util.XMLfunctions;

public class Campaign {
	private String campaignId;
	private String merchId;
	private String campaignName;
	private String campaignDesc;
	private String campaignImage;
	private String pieQty;

	public Campaign(Element e) {
		campaignId = XMLfunctions.getValue(e, "campaignId");
		merchId = XMLfunctions.getValue(e, "merchId");
		campaignName = XMLfunctions.getValue(e, "campaignName");
		campaignDesc = XMLfunctions.getValue(e, "campaignDesc");
		campaignImage = XMLfunctions.getValue(e, "campaignImage");
		pieQty = XMLfunctions.getValue(e, "pieQty");
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignDesc() {
		return campaignDesc;
	}

	public void setCampaignDesc(String campaignDesc) {
		this.campaignDesc = campaignDesc;
	}

	public String getCampaignImage() {
		return campaignImage;
	}

	public void setCampaignImage(String campaignImage) {
		this.campaignImage = campaignImage;
	}

	public String getPieQty() {
		return pieQty;
	}

	public void setPieQty(String pieQty) {
		this.pieQty = pieQty;
	}

}
