package org.com.vn.loxleycolour.items;

public class ItemNew {
	private String title;
	private String date;
	private String detail;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public static ItemNew create() {
		ItemNew itemNew = new ItemNew();
		itemNew.setUrl("http://www.google.com");
		itemNew.setTitle("New Products for 2012");
		itemNew.setDate("30 Apr");
		itemNew.setDetail("4 NEW PRODUCTS FOR 2012: the Print Wrap, the Float Frame, Clusters & splits and Album Bundless.");
		return itemNew;
	}
}
