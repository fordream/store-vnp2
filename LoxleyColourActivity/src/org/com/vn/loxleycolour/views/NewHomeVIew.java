package org.com.vn.loxleycolour.views;

import java.util.ArrayList;
import java.util.List;

import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;
import org.com.vn.loxleycolour.adapter.NewAdapter;
import org.com.vn.loxleycolour.items.ItemNew;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;

public class NewHomeVIew extends LinearLayout implements Const, IView {

	private ListView listView;
	private WebViewController header;
	NewAdapter adapter;

	public NewHomeVIew(Context context) {
		super(context);
		init();
	}

	public NewHomeVIew(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.newhome, this);

		reset();
		listView = (ListView) findViewById(R.id.listView1);

		header = new WebViewController(getContext());

		listView.addHeaderView(header);

		//ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
		//		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT * 2);
		// header.setLayoutParams(params);

		adapter = new NewAdapter(getContext(), new ArrayList<ItemNew>());
		listView.setAdapter(adapter);

		header.loadUrl(URL_NEW);
		addDataExample();
	}

	private void addDataExample() {
		List<ItemNew> lItemNews = new ArrayList<ItemNew>();
		
		for(int i = 0; i < 10; i ++){
			ItemNew itemNew = ItemNew.create();
			lItemNews.add(itemNew);
		}
		
		adapter.setData(lItemNews);
		adapter.notifyDataSetChanged();
	}

	public void reset() {
		((IheaderLabel) getContext()).setTextHeader("Contact");
	}
}
