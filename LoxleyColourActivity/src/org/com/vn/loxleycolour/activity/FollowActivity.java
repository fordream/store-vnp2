package org.com.vn.loxleycolour.activity;

import org.com.vn.loxleycolour.views.FollowView;

import android.os.Bundle;

public class FollowActivity extends MActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		llContent.addView(new FollowView(this));
		addView(new FollowView(this));
	}
}
