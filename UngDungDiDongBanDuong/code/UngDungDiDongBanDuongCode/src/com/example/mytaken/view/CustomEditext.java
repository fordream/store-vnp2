package com.example.mytaken.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.mytaken.R;
import com.example.mytaken.util.BanDuongUtils;
import com.vnp.core.common.VNPResize;

public class CustomEditext extends EditText implements Runnable {

	public CustomEditext(Context context) {
		super(context);
		BanDuongUtils.setTypeface(this);

		post(this);
	}

	public CustomEditext(Context context, AttributeSet attrs) {
		super(context, attrs);
		BanDuongUtils.setTypeface(this);
		post(this);
	}

	public CustomEditext(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		BanDuongUtils.setTypeface(this);
		post(this);
	}

	@Override
	public void run() {
		setBackgroundResource(R.drawable.transfer);
		try {
			VNPResize.getInstance().setTextsize(this, BanDuongUtils.SIZE.TEXTSIZEEDITTEXT);
		} catch (Exception exception) {
		} catch (Error e) {
		}
	}
}