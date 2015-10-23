package com.example.org.com.driver.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.org.com.driver.R;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class MainItemView extends CustomLinearLayoutView implements Runnable {

	public MainItemView(Context context) {
		super(context);
		init(R.layout.itemmain);
	}

	public MainItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.itemmain);
	}

	@Override
	public void init(int res) {
		super.init(res);
		post(this);
	}

	@Override
	public void setGender() {
		TextView textView = getView(R.id.erorrbaseTextView1);
		textView.setText(getData().toString());

		TextView textView2 = getView(R.id.erorrbaseTextView1_1);
		textView2.setText(getData().toString());
	}

	@Override
	public void showHeader(boolean arg0) {

	}

	@Override
	public void run() {
		VNPResize.getInstance().resize(findViewById(R.id.erorrbaseTextView1),
				29, 50);
		VNPResize.getInstance().resize(findViewById(R.id.erorrbaseTextView1_1),
				29, 50);
		VNPResize.getInstance().setTextsize(
				findViewById(R.id.erorrbaseTextView1), Proclass.MAIN_M2_TEXT3);
		VNPResize.getInstance()
				.setTextsize(findViewById(R.id.erorrbaseTextView1_1),
						Proclass.MAIN_M2_TEXT3);
	}

	public void setColor(boolean b) {

		if (b) {
			// textView.setTextColor(R.color.white);
			getView(R.id.erorrbaseTextView1).setVisibility(GONE);

			getView(R.id.erorrbaseTextView1_1).setVisibility(VISIBLE);
		}
	}
}
