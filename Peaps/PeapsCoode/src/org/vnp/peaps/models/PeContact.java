package org.vnp.peaps.models;

public class PeContact extends BaseItem {

	public PeContact() {
	}

	private int socre;

	public int getSocre() {
		return socre;
	}

	public void setSocre(int socre) {
		this.socre = socre;
	}

	private boolean isChecked;

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}