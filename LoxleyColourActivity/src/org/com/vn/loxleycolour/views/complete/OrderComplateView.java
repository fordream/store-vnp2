package org.com.vn.loxleycolour.views.complete;

import java.util.ArrayList;
import java.util.List;

import org.com.cnc.common.android.Common;
import org.com.cnc.common.android.CommonNetwork;
import org.com.cnc.common.android.CommonView;
import org.com.vn.loxleycolour.CommonApp;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;
import org.com.vn.loxleycolour._interface.Isearch;
import org.com.vn.loxleycolour.adapter.CompleteProgressAdapter;
import org.com.vn.loxleycolour.items.order2.GetMyCurrentInvoices;
import org.com.vn.loxleycolour.items.order2.ItemGetMyCurrentInvoices;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class OrderComplateView extends LinearLayout implements OnClickListener,
		Const, IView, OnTouchListener, OnFocusChangeListener, Isearch {

	private SearchHeaderView searchHeaderView;
	ListView listView;
	CompleteProgressAdapter adapter;
	private RadioGroup radioGroup;

	public OrderComplateView(Context context) {
		super(context);
		init();
	}

	public OrderComplateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	GetMyCurrentInvoices orderProgress = new GetMyCurrentInvoices();
	int index = 0;

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.order_compalte, this);

		searchHeaderView = (SearchHeaderView) findViewById(R.id.searchHeaderView1);
		searchHeaderView.setFocus();

		searchHeaderView.setSearch(this);
		searchHeaderView.setHint("Order number/reference");
		listView = (ListView) findViewById(R.id.listView1);
		reset();

		List<ItemGetMyCurrentInvoices> list = new ArrayList<ItemGetMyCurrentInvoices>();
		adapter = new CompleteProgressAdapter(getContext(), list);

		listView.setAdapter(adapter);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				if (((RadioButton) radioGroup.getChildAt(0)).isChecked()) {
					index = 0;
				} else if (((RadioButton) radioGroup.getChildAt(1)).isChecked()) {
					index = 1;
				} else if (((RadioButton) radioGroup.getChildAt(2)).isChecked()) {
					index = 2;
				} else if (((RadioButton) radioGroup.getChildAt(3)).isChecked()) {
					index = 3;
				}
				// index = arg1;
				search(search);
			}
		});

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
					orderProgress = SoapCommon.GetMyCurrentInvoices("");
					handler.post(new Runnable() {
						public void run() {
							search("");
							dialog.dismiss();
						}
					});
					return null;
				}

			}.execute("");
		}
	}

	public void onClick(View view) {
	}

	public void reset() {
		((IheaderLabel) getContext()).setTextHeader("Completed Orders");
		searchHeaderView.setFocus();
	}

	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	public void onFocusChange(View arg0, boolean arg1) {
	}

	String search = "";

	public void search(String search) {
		this.search = search;
		List<ItemGetMyCurrentInvoices> objects = new ArrayList<ItemGetMyCurrentInvoices>();
		// int date = 14;

		for (int i = 0; i < orderProgress.lCurrentInvoices.size(); i++) {
			ItemGetMyCurrentInvoices itemOrder = orderProgress.lCurrentInvoices
					.get(i);

			if ((Common.contains(search, itemOrder.OrderNumber) || Common
					.contains(search, itemOrder.OrderReference))
					&& CommonApp.compareDate(itemOrder, index)) {
				objects.add(itemOrder);
			}
		}

		adapter = new CompleteProgressAdapter(getContext(), objects);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
