package org.com.vn.loxleycolour.views.orderinprogress;

import org.com.cnc.common.android.CommonNetwork;
import org.com.vn.loxleycolour.CommonApp;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;
import org.com.vn.loxleycolour.adapter.ProgressDetailAdapter;
import org.com.vn.loxleycolour.items.order1.GetMyOrderDetails;
import org.com.vn.loxleycolour.items.order1.GetMyOrderItems;
import org.com.vn.loxleycolour.items.order1.ItemOrder;
import org.com.vn.loxleycolour.soap.SoapCommon;
import org.com.vn.loxleycolour.views.wiget.DialogNetwork;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class OrderInProgressDetailView extends LinearLayout implements
		OnClickListener, Const, IView, OnTouchListener, OnFocusChangeListener {
	ItemOrder itemOrder;

	public OrderInProgressDetailView(Context context) {
		super(context);
		init();
	}

	public OrderInProgressDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.order_inprogress_detail, this);
	}

	public void onClick(View view) {
	}

	public void reset() {
		((IheaderLabel) getContext()).setTextHeader("Order in progress");
	}

	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	public void onFocusChange(View arg0, boolean arg1) {
	}

	private String orderNumber;

	public void setOrderNumber(ItemOrder orderNumber2) {
		itemOrder = orderNumber2;

		String OrderDate = CommonApp.convertDueDate(itemOrder.OrderDate);
		OrderDate = CommonApp.convertDate(OrderDate);
		getTextView(R.id.textView5).setText(OrderDate);

		setOrderNumber(orderNumber2.OrderNumber);
	}

	public void setOrderNumber(final String orderNumber) {

		this.orderNumber = orderNumber;

		if (!CommonNetwork.haveConnectTed(getContext())) {
			new DialogNetwork(getContext()).show();
		} else {
			new AsyncTask<String, String, String>() {
				Handler handler = new Handler();
				ProgressDialog dialog;

				protected String doInBackground(String... params) {
					handler.post(new Runnable() {

						public void run() {
							dialog = ProgressDialog.show(getContext(), "",
									"Loadding ...");
						}
					});
					final GetMyOrderItems getMyOrderItems = SoapCommon
							.GetMyOrderItems(orderNumber);

					handler.post(new Runnable() {
						public void run() {
							update(getMyOrderItems);
						}
					});

					final GetMyOrderDetails details = SoapCommon
							.GetMyOrderDetails(orderNumber);

					handler.post(new Runnable() {
						public void run() {
							update(details);
						}

					});
					return null;
				}

				protected void onPostExecute(String result) {
					dialog.dismiss();
				};

			}.execute("");
		}
	}

	private void update(GetMyOrderDetails details) {
		getTextView(R.id.textView1).setText("Order # " + orderNumber);

		getTextView(R.id.textView2).setText(
				"Due date : "
						+ CommonApp.convertDueDate(details.details.DueDate));
		getTextView(R.id.textView3).setText(
				"Refrence : " + details.details.CustomerReference);

		// getTextView(R.id.textView5).setText(details.details.OrderDate);
		String OrderDate = CommonApp.convertDueDate(itemOrder.OrderDate);
		OrderDate = CommonApp.convertDate(OrderDate);
		getTextView(R.id.textView5).setText(OrderDate);

		getTextView(R.id.TextView04).setText(
				CommonApp.convertMoney(itemOrder.TotalPrice));
		getTextView(R.id.TextView02).setText(CommonApp.convertMoney("0"));
		getTextView(R.id.textView9).setText(
				CommonApp.convertMoney(itemOrder.TotalPrice));

		getTextView(R.id.textView6).setText(
				"Shipping Method: " + itemOrder.ShippingMethod);

	}

	private TextView getTextView(int res) {
		return (TextView) findViewById(res);
	}

	private void update(GetMyOrderItems getMyOrderItems) {
		ProgressDetailAdapter adapter = new ProgressDetailAdapter(getContext(),
				getMyOrderItems.lGetMyOrderItems);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}

}
