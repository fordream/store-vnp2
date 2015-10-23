package com.example.org.com.driver.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.org.com.driver.R;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class ErrorItemView extends CustomLinearLayoutView implements Runnable {

	public ErrorItemView(Context context) {
		super(context);
		init(R.layout.itemerror);
	}

	public ErrorItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.itemerror);
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
	}

	@Override
	public void showHeader(boolean arg0) {
		
	}

	@Override
	public void run() {
		VNPResize.getInstance().resize(findViewById(R.id.erorrbaseTextView1),
				150, 60);
		VNPResize.getInstance().setTextsize(
				findViewById(R.id.erorrbaseTextView1),
				Proclass.PROPLEM_LIST_ERROR_TEXT);
	}
}
