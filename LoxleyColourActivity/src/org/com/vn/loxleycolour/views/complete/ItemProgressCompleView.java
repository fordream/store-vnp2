package org.com.vn.loxleycolour.views.complete;

import org.com.vn.loxleycolour.CommonApp;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IActivity;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour.items.order2.ItemGetMyCurrentInvoices;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemProgressCompleView extends LinearLayout implements
		OnClickListener, Const, IView {

	ItemGetMyCurrentInvoices itemOrder;

	public ItemProgressCompleView(Context context) {
		super(context);
		init();
	}

	public ItemProgressCompleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.itemprogresscomplete, this);
		reset();

		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
	}

	public void onClick(View view) {
		if (view.getId() == R.id.button1) {
			((IActivity) getContext()).gotoDetailOrderComplete(itemOrder);
		} else if (view.getId() == R.id.button2) {
			((IActivity) getContext()).gotoTrack(itemOrder);
		}
	}

	public void reset() {
	}

	public View setData(ItemGetMyCurrentInvoices itemOrder) {
		this.itemOrder = itemOrder;
		getTextView(R.id.textView1).setText("Order # " + itemOrder.OrderNumber);

		getTextView(R.id.textView6).setText(itemOrder.OrderReference);

		
		String duedate = CommonApp.convertDueDate(itemOrder.DueDate);
		getTextView(R.id.textView7).setText(duedate);

		getTextView(R.id.textView5).setText(
				"Shipping method : " + itemOrder.ShippingMethod);
		
		
		String OrderDate = CommonApp.convertDueDate(itemOrder.OrderDate);
		OrderDate = CommonApp.convertDate(OrderDate);
		getTextView(R.id.textView2).setText(OrderDate);
		return this;
	}

	private TextView getTextView(int res) {
		return (TextView) findViewById(res);
	}
}
