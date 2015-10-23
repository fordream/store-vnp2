package org.com.vn.loxleycolour.views;

import org.com.cnc.common.android._interface.IHeaderView;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HeaderView extends RelativeLayout implements OnClickListener,
		Const {

	private Button btnBack;
	private Button btnLogout;
	private TextView tvHeader;

	public HeaderView(Context context) {
		super(context);

		init();
	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.header, this);

		btnBack = (Button) findViewById(R.id.header_btn_back);
		btnLogout = (Button) findViewById(R.id.btn_header_logout);
		tvHeader = (TextView) findViewById(R.id.header_textview_headet);

		btnBack.setOnClickListener(this);
		btnLogout.setOnClickListener(this);

		RelativeLayout rrheader = (RelativeLayout) findViewById(R.id.rrHeader);
		rrheader.setLayoutParams( new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public HeaderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void onClick(View arg0) {
		if (btnBack == arg0) {
			((IHeaderView) getContext()).onClickBackHeader(BACK);
		} else {
			((IHeaderView) getContext()).onClickBackHeader(LOGOUT);
		}
	}

	public void setTextHeader(String text) {
		tvHeader.setText(text);
	}

	public void setTextHeader(int res) {
		tvHeader.setText(res);

	}

	public void setHiddenBtnLogout(boolean b) {
		btnLogout.setVisibility(b ? GONE : VISIBLE);
	}

	public void hiddenButtonback(boolean b) {
		btnBack.setVisibility(b ? GONE : VISIBLE);

	}
}
