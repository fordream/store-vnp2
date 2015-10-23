package com.vnp.mlook.models;

import com.vnp.mlook.fragment.MLookAction;

public class ItemMenu {

	public ItemMenu(String name, String img, MLookAction tagAction) {
		super();
		this.name = name;
		this.img = img;
		this.tagAction = tagAction;
	}

	public String name;
	public String img;
	public MLookAction tagAction;
}
