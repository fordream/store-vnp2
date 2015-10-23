package com.hoatieu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.hoatieu.service.HoatieuHistoryPush;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class PushHistotyActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pushhistoryactivity);
		hoatieuHistoryPush = new HoatieuHistoryPush(this);
		// "1");
		updateUi();

	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(broadcastReceiver);
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			updateUi();
		}
	};

	private void updateUi() {
		int count = hoatieuHistoryPush.getCount();
		List<Object> objects = new ArrayList<Object>();

		for (int i = 0; i < count; i++) {
			int index = count - i - 1;
			ItemPush itemPush = new ItemPush(hoatieuHistoryPush.getTime(index),
					hoatieuHistoryPush.getStatus(index), hoatieuHistoryPush.getMessage(index));
			objects.add(itemPush);
		}

		((ListView) findViewById(R.id.listView1)).setAdapter(new com.vnp.core.common.BaseAdapter(
				this, objects, new CommonGenderView() {
					@Override
					public CustomLinearLayoutView getView(Context arg0, Object arg1) {
						return new HistoryViewItem(arg0);
					}
				}));

	}

	class ItemPush {
		public ItemPush(long time2, String status2, String message2) {
			time = time2;
			status = status2;
			message = message2;
		}

		long time;
		String status;
		String message;
	}

	@Override
	protected void onStart() {
		super.onStart();
		resize.resize(findViewById(R.id.LinearLayout1), resize.getWidthScreen() * 80 / 100,
				resize.getHeightScreen() * 80 / 100);
		resize.resizeSacle(findViewById(R.id.textView1), LayoutParams.MATCH_PARENT, 40);
		resize.setTextsize(findViewById(R.id.textView1), 40);
	}

	private HoatieuHistoryPush hoatieuHistoryPush;

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(broadcastReceiver, new IntentFilter("UPDATE_PUSH"));

	}

	class HistoryViewItem extends CustomLinearLayoutView {

		public HistoryViewItem(Context context) {
			super(context);
			init(R.layout.pushhistoryitem);
		}

		@SuppressWarnings("deprecation")
		public void setText(long time, String message, String status) {
			android.text.format.DateFormat df = new android.text.format.DateFormat();
			df.format("yyyy-MM-dd kk:mm:ss", new Date(time));

			// Date date = new Date(time);

			// String _time = String.format("%s:%s:%s - %s:%s:%s",
			// date.getYear() + 1900, date.getMonth(), date.getDay(),
			// date.getHours(),
			// date.getMinutes(), date.getSeconds());
			String _time = df.format("yyyy-MM-dd kk:mm:ss", new Date(time)).toString();

			((TextView) findViewById(R.id.textView1)).setText(_time);
			((TextView) findViewById(R.id.textView2)).setText(message);

			if ("3".equals(status)) {
				findViewById(R.id.imageView1).setBackgroundResource(R.drawable.sos);
			} else if ("0".equals(status)) {
				findViewById(R.id.imageView1).setBackgroundResource(R.drawable.safety);
			} else if ("1".equals(status)) {
				findViewById(R.id.imageView1).setBackgroundResource(R.drawable.sos);
			} else {
				findViewById(R.id.imageView1).setBackgroundResource(R.drawable.safety);
			}
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			resize.resizeSacle(this, android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.imageView1), 35, 35);
			resize.setTextsize(findViewById(R.id.textView1), 35);
			resize.setTextsize(findViewById(R.id.textView2), 20);
		}

		@Override
		public void setGender() {
			ItemPush itemPush = (ItemPush) getData();
			setText(itemPush.time, itemPush.message, itemPush.status);
		}

	}
}