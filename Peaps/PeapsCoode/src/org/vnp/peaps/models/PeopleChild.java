package org.vnp.peaps.models;

import org.vnp.peaps.R;

public class PeopleChild extends BaseItem {

	private int resimg = R.drawable.email;
	private int resStr;

	public PeopleChild(int email, int peopleChild1) {
		resimg = email;
		resStr = peopleChild1;
	}

	
	public int getResStr() {
		return resStr;
	}


	public void setResStr(int resStr) {
		this.resStr = resStr;
	}


	public int getResimg() {
		return resimg;
	}

	public void setResimg(int resimg) {
		this.resimg = resimg;
	}
}