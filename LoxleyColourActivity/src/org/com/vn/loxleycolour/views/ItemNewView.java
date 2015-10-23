package org.com.vn.loxleycolour.views;

import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IActivity;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour.items.ItemNew;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemNewView extends LinearLayout implements OnClickListener,
		Const, IView {
	private ItemNew itemNew;
	private TextView tvheader;
	private TextView tvDate;
	private TextView tvDetail;

	public ItemNewView(Context context) {
		super(context);
		init();
	}

	public ItemNewView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.itemnew, this);
		tvheader = (TextView) findViewById(R.id.textView1);
		tvDate = (TextView) findViewById(R.id.textView2);
		tvDetail = (TextView) findViewById(R.id.textView3);

		findViewById(R.id.textView4).setOnClickListener(this);
		reset();
	}

	public void onClick(View view) {
		((IActivity) getContext()).gotoWeb(itemNew.getUrl());
	}

	public void reset() {
	}

	public View setData(ItemNew itemNew) {
		this.itemNew = itemNew;
		tvheader.setText(this.itemNew.getTitle());
		tvDate.setText(this.itemNew.getDate());
		tvDetail.setText(this.itemNew.getDetail());
		return this;
	}
}
