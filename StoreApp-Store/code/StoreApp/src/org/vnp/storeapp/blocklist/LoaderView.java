package org.vnp.storeapp.blocklist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

import com.vnp.core.view.CustomLinearLayoutView;

public class LoaderView extends CustomLinearLayoutView implements
		OnClickListener {

	public LoaderView(Context context) {
		super(context);
		init();
	}

	public LoaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setOnClickListener(this);
		setGravity(Gravity.CENTER);
		addView(new ProgressBar(getContext()));
	}

	@Override
	public void setGender() {

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void showHeader(boolean isShowHeader) {
		// TODO Auto-generated method stub

	}

}
