package org.vnp.peaps.models;

import java.util.ArrayList;
import java.util.List;

public class People extends BaseItem {

	public People() {
	}

	private List<Object> list = new ArrayList<Object>();
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addChild(PeopleChild peopleChild) {
		list.add(peopleChild);
	}

	public List<Object> getList() {
		return list;
	}

}