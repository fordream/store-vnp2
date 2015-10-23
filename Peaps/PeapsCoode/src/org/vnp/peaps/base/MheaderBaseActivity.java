package org.vnp.peaps.base;

import java.util.ArrayList;
import java.util.List;

import org.vnp.peaps.R;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.vnp.core.activity.BaseActivity;
import com.vnp.core.v2.BaseAdapter;
import com.vnp.core.view.CustomLinearLayoutView;

public abstract class MheaderBaseActivity extends BaseActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactscreen);
		HeaderView headerView = (HeaderView) findViewById(R.id.headerView1);
		headerView.setShowBack(isShowBack());
		listView = (ListView) findViewById(R.id.contactscreen_listview);
		adapter = new BaseAdapter(this, new ArrayList<Object>()) {

			@Override
			public boolean isShowHeader(int arg0) {
				return false;
			}

			@Override
			public CustomLinearLayoutView getView(Context arg0, Object arg1) {
				return getViewItem(arg0, arg1);
			}
		};

		listView.setAdapter(adapter);
		adapter.addAll(addExampleData());
		adapter.notifyDataSetChanged();
	}

	public abstract List<Object> addExampleData();

	public abstract CustomLinearLayoutView getViewItem(Context arg0, Object arg1);

	public boolean isShowBack() {
		return false;
	}

	private BaseAdapter adapter;
}