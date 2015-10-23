package org.com.vn.loxleycolour.views.orderinprogress;

import org.com.vn.loxleycolour.CommonApp;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IActivity;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour.items.order1.ItemOrder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemProgressView extends LinearLayout implements OnClickListener,
		Const, IView {
	private TextView tvOrder;
	private TextView tvRef;
	private TextView tvDudate;
	private TextView tvDate;

	ItemOrder itemOrder;

	public ItemProgressView(Context context) {
		super(context);
		init();
	}

	public ItemProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.itemprogress, this);
		reset();
		tvOrder = (TextView) findViewById(R.id.textView1);
		tvDate = (TextView) findViewById(R.id.textView2);
		tvRef = (TextView) findViewById(R.id.textView3);
		tvDudate = (TextView) findViewById(R.id.textView4);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	public void onClick(View view) {
		((IActivity) getContext()).gotoDetailOrderInProgess(itemOrder);
	}

	public void reset() {
	}

	public View setData(ItemOrder itemOrder) {
		this.itemOrder = itemOrder;
		tvOrder.setText(itemOrder.OrderNumber);
		String OrderDate = CommonApp.convertDueDate(itemOrder.OrderDate);
		OrderDate = CommonApp.convertDate(OrderDate);
		tvDate.setText(OrderDate);

		tvRef.setText(itemOrder.OrderReference);

		String duedate = CommonApp.convertDueDate(itemOrder.DueDate);

		tvDudate.setText(duedate);
		return this;
	}
}
