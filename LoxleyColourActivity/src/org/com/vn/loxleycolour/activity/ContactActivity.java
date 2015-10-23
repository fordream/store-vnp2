package org.com.vn.loxleycolour.activity;

import org.com.vn.loxleycolour.views.ContactView;

import android.os.Bundle;

public class ContactActivity extends MActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addView(new ContactView(this));
		// llContent.addView(new ContactView(this));
	}
}
