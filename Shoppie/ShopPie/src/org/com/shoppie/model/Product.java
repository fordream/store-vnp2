package org.com.shoppie.model;

import org.w3c.dom.Element;

import com.ict.library.util.XMLfunctions;

public class Product {
	private String productId;
	private String merchId;
	private String productName;
	private String longDesc;
	private String price;
	private String oldPrice;
	private String productImage;

	
	public Product(Element e) {
		productId = XMLfunctions.getValue(e, "productId");
		merchId = XMLfunctions.getValue(e, "merchId");
		productName = XMLfunctions.getValue(e, "productName");
		longDesc = XMLfunctions.getValue(e, "longDesc");
		price = XMLfunctions.getValue(e, "price");
		oldPrice = XMLfunctions.getValue(e, "oldPrice");
		productImage = XMLfunctions.getValue(e, "productImage");
	}
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(String oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

}
