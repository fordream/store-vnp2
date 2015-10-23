package org.com.vn.loxleycolour;

import org.com.cnc.common.android.activity.CommonTabActivity;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour.activity.ContactActivity;
import org.com.vn.loxleycolour.activity.FollowActivity;
import org.com.vn.loxleycolour.activity.NewsActivity;
import org.com.vn.loxleycolour.activity.OrdersActivity;
import org.com.vn.loxleycolour.database.DataStore;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour.v1.R.drawable;
import org.com.vn.loxleycolour.views.TabViews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class CustomTabsActivity extends CommonTabActivity implements
		OnTabChangeListener, Const {
	private TabViews[] views = new TabViews[4];
	public TabHost tabHost;
	boolean isSecond = false;
	boolean isNetwork = false;
	public static Handler HandlerTab = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		isSecond = true;
		setTabs();

		HandlerTab = new MyHandeler();
	}

	private class MyHandeler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			DataStore.getInstance().saveConfig(LOGIN, false);
			tabHost.setCurrentTab(1);
		}
	}

	private void setTabs() {
		tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);

		// home
		Intent intent = new Intent(this, NewsActivity.class);
		addTab(intent, 0, drawable.tab1_on, drawable.tab1_off, "News");

		// search
		intent = new Intent(this, OrdersActivity.class);
		addTab(intent, 1, drawable.tab2_on, drawable.tab2_off, "Orders");

		// calculator
		intent = new Intent(this, FollowActivity.class);
		addTab(intent, 2, drawable.tab3_on, drawable.tab3_off, "Follow");

		// setting
		intent = new Intent(this, ContactActivity.class);
		addTab(intent, 3, drawable.tab4_on, drawable.tab4_off, "Contacts");

		int tabCurent = 0;
		config(tabCurent == 0, 0);
		config(tabCurent == 1, 1);
		config(tabCurent == 2, 2);
		config(tabCurent == 3, 3);
	}

	private void addTab(Intent intent, int index, int resource, int resource1,
			String text) {
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + index);
		TabViews tabIndicator = new TabViews(this);
		tabIndicator.setText(text);
		tabIndicator.setImageResource(resource, resource1);
		views[index] = tabIndicator;
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}

	private void config(boolean b, int index) {
		if (views[index] != null) {
			views[index].setImageResource(b);
		}
	}

	public void onTabChanged(String tabId) {
		int tabCurent = 0;
		if (tabId.equals("tab0")) {
			tabCurent = 0;
		}

		if (tabId.equals("tab1")) {
			tabCurent = 1;
		}

		if (tabId.equals("tab2")) {
			tabCurent = 2;
		}

		if (tabId.equals("tab3")) {
			tabCurent = 3;
		}

		config(tabCurent == 0, 0);
		config(tabCurent == 1, 1);
		config(tabCurent == 2, 2);
		config(tabCurent == 3, 3);
	}
}