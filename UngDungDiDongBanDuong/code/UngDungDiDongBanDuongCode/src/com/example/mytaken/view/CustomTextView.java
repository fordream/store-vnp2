package com.example.mytaken.view;

import com.example.mytaken.util.BanDuongUtils;
import com.vnp.core.common.VNPResize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView implements Runnable {

	public CustomTextView(Context context) {
		super(context);
		BanDuongUtils.setTypeface(this);
		post(this);
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		BanDuongUtils.setTypeface(this);
		post(this);
	}

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		BanDuongUtils.setTypeface(this);
		post(this);
	}

	@Override
	public void run() {
		try {
			VNPResize.getInstance().setTextsize(this, BanDuongUtils.SIZE.TEXTSIZENORMAL);
		} catch (Exception exception) {
		} catch (Error e) {
		}
	}

}