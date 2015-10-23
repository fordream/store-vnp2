package org.com.vn.loxleycolour.views.orderinprogress;

import java.util.ArrayList;
import java.util.List;

import org.com.cnc.common.android.Common;
import org.com.cnc.common.android.CommonNetwork;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;
import org.com.vn.loxleycolour._interface.Isearch;
import org.com.vn.loxleycolour.adapter.ProgressAdapter;
import org.com.vn.loxleycolour.items.order1.ItemOrder;
import org.com.vn.loxleycolour.items.order1.OrderProgress;
import org.com.vn.loxleycolour.soap.SoapCommon;
import org.com.vn.loxleycolour.views.SearchHeaderView;
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

public class OrderInProgressView extends LinearLayout implements
		OnClickListener, Const, IView, OnTouchListener, OnFocusChangeListener,
		Isearch {

	private SearchHeaderView searchHeaderView;
	ListView listView;
	ProgressAdapter adapter;

	public OrderInProgressView(Context context) {
		super(context);
		init();
	}

	public OrderInProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	OrderProgress orderProgress = new OrderProgress();

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.order_inprogress, this);

		searchHeaderView = (SearchHeaderView) findViewById(R.id.searchHeaderView1);
		searchHeaderView.setFocus();

		searchHeaderView.setSearch(this);
		searchHeaderView.setHint("Order number/reference");
		listView = (ListView) findViewById(R.id.listView1);
		reset();

		List<ItemOrder> list = new ArrayList<ItemOrder>();
		adapter = new ProgressAdapter(getContext(), list);

		listView.setAdapter(adapter);

		if (!CommonNetwork.haveConnectTed(getContext())) {
			new DialogNetwork(getContext()).show();
		} else {
			new AsyncTask<String, String, String>() {
				Handler handler = new Handler();
				ProgressDialog dialog;

				@Override
				protected String doInBackground(String... params) {
					handler.post(new Runnable() {

						public void run() {
							dialog = ProgressDialog.show(getContext(), "",
									"Loadding ...");
						}
					});
					orderProgress = SoapCommon.GetMyCurrentOrders("");
					handler.post(new Runnable() {
						public void run() {
							adapter.setData(orderProgress.lData);
							adapter.notifyDataSetChanged();
							dialog.dismiss();
						}
					});
					return null;
				}

			}.execute("");
		}
	}

	public void search(String search) {

		List<ItemOrder> objects = new ArrayList<ItemOrder>();

		for (int i = 0; i < orderProgress.lData.size(); i++) {

			ItemOrder itemOrder = orderProgress.lData.get(i);

			if (Common.contains(search, itemOrder.OrderNumber)
					|| Common.contains(search, itemOrder.OrderReference)) {
				objects.add(itemOrder);
			}
		}

		adapter = new ProgressAdapter(getContext(), objects);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	public void onClick(View view) {
	}

	public void reset() {
		((IheaderLabel) getContext()).setTextHeader("Order in progress");
		searchHeaderView.setFocus();
	}

	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	public void onFocusChange(View arg0, boolean arg1) {
	}

	public void setOrderNumber(String orderNumber) {

	}
}
