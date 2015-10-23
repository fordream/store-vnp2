package com.vnp.mlook.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ict.library.view.CustomLinearLayoutView;
import com.vnp.mlook.R;

public class HeaderView extends CustomLinearLayoutView {
	private CallBack callBack;

	public HeaderView(Context context) {
		super(context);
		init(R.layout.header);
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.header);
	}

	@Override
	public void init(int res) {
		super.init(res);

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack(HeaderCallBaclItem.HEADER_MENU);
			}

		});

		findViewById(R.id.Button03).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack(HeaderCallBaclItem.HEADER_1);
			}

		});

		findViewById(R.id.Button02).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack(HeaderCallBaclItem.HEADER_2);
			}

		});

		findViewById(R.id.Button01).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack(HeaderCallBaclItem.HEADER_3);
			}

		});
	}

	private void callBack(HeaderCallBaclItem headerMenu) {
		if (callBack != null) {
			callBack.CallBackheadera(headerMenu);
		}
	}

	@Override
	public void setGender() {

	}

	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}

	public interface CallBack {
		public void CallBackheadera(HeaderCallBaclItem index);
	}

	public enum HeaderCallBaclItem {
		HEADER_MENU, //
		HEADER_1, //
		HEADER_2, //
		HEADER_3,
	}
}
