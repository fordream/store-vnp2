package org.com.vn.loxleycolour.views.complete;

import org.com.cnc.common.android.CommonNetwork;
import org.com.vn.loxleycolour.CommonApp;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IActivity;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;
import org.com.vn.loxleycolour.adapter.CompleteDetailAdapter;
import org.com.vn.loxleycolour.items.order2.GetMyInvoiceDetails;
import org.com.vn.loxleycolour.items.order2.GetMyInvoiceItems;
import org.com.vn.loxleycolour.items.order2.ItemGetMyCurrentInvoices;
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

public class OrderCompleteDetailView extends LinearLayout implements
		OnClickListener, Const, IView, OnTouchListener, OnFocusChangeListener {
	ItemGetMyCurrentInvoices itemOrder;

	public OrderCompleteDetailView(Context context) {
		super(context);
		init();
	}

	public OrderCompleteDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.order_incomplete_detail, this);

		findViewById(R.id.button1).setOnClickListener(this);
	}

	public void onClick(View view) {

		((IActivity) getContext()).gotoTrack(itemOrder);
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

	public void setOrderNumber(ItemGetMyCurrentInvoices orderNumber2) {
		itemOrder = orderNumber2;
		setOrderNumber(orderNumber2.InvoiceNumber);
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
					final GetMyInvoiceItems getMyOrderItems = SoapCommon
							.GetMyInvoiceItems(orderNumber);

					handler.post(new Runnable() {
						public void run() {
							update(getMyOrderItems);
						}
					});

					final GetMyInvoiceDetails details = SoapCommon
							.GetMyInvoiceDetails(orderNumber);

					handler.post(new Runnable() {
						public void run() {
							update(details, getMyOrderItems);
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

	private void update(GetMyInvoiceDetails details,
			GetMyInvoiceItems getMyInvoiceItems) {
		getTextView(R.id.textView1).setText("Order # " + orderNumber);

		getTextView(R.id.textView2).setText(
				"Due date : "
						+ CommonApp.convertDueDate(details.details.DueDate));

		getTextView(R.id.textView3).setText(
				"Refrence : " + details.details.CustomerReference);

		String OrderDate = CommonApp.convertDueDate(itemOrder.OrderDate);
		OrderDate = CommonApp.convertDate(OrderDate);
		getTextView(R.id.textView5).setText(OrderDate);

		// TODO

		getTextView(R.id.TextView04).setText(
				CommonApp.convertMoney(details.details.TotalExVat));

		getTextView(R.id.TextView02).setText(CommonApp.convertMoney("0"));

		CommonApp.convertTotal(getMyInvoiceItems);
		// getTextView(R.id.TextView02).setText(CommonApp.convertMoney(itemOrder.));

		getTextView(R.id.textView9).setText(
				CommonApp.convertMoney(details.details.TotalIncVAT));

		getTextView(R.id.textView6).setText(
				"Shipping Method: " + itemOrder.ShippingMethod);

	}

	private TextView getTextView(int res) {
		return (TextView) findViewById(res);
	}

	private void update(GetMyInvoiceItems getMyOrderItems) {
		CompleteDetailAdapter adapter = new CompleteDetailAdapter(getContext(),
				getMyOrderItems.lGetMyOrderItems);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}

}
