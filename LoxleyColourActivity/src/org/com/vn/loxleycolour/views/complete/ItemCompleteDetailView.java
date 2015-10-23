package org.com.vn.loxleycolour.views.complete;

import org.com.vn.loxleycolour.CommonApp;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour.items.order2.ItemGetMyInvoiceItems;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemCompleteDetailView extends LinearLayout implements
		OnClickListener, Const, IView {

	public ItemCompleteDetailView(Context context) {
		super(context);
		init();
	}

	public ItemCompleteDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.item_detail_progress, this);

	}

	public void onClick(View view) {
	}

	public void reset() {
	}

	public View setData(ItemGetMyInvoiceItems itemOrder) {
		getTextView(R.id.TextView05).setText("");
		getTextView(R.id.TextView04).setText(CommonApp.convertQty(itemOrder.Quantity));
		getTextView(R.id.TextView03).setText(CommonApp.convertMoney(itemOrder.UnitCost));
		getTextView(R.id.TextView02).setText(CommonApp.convertMoney(itemOrder.DiscountAmount));
		getTextView(R.id.TextView01).setText(CommonApp.convertMoney(itemOrder.Amount));
		getTextView(R.id.textView2).setText(CommonApp.convertPer(itemOrder.AmountIncVAT));
		
		getTextView(R.id.textView1).setText(itemOrder.ItemDescription);
		return this;
	}

	private TextView getTextView(int res) {
		return (TextView) findViewById(res);
	}
}
