package org.com.vn.loxleycolour.views;

import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MyWebView extends LinearLayout implements OnClickListener, Const,
		IView {

	public MyWebView(Context context) {
		super(context);
		init();
	}

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.web, this);

		reset();
	}

	public void onClick(View view) {
	}

	public void loadUrl(String url) {
		((WebViewController) findViewById(R.id.webViewController1))
				.loadUrl(url);
	}

	public void reset() {
		((IheaderLabel) getContext()).setTextHeader("Orders");
	}
}
