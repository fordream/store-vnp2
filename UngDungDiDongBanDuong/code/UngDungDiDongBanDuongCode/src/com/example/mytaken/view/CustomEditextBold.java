package com.example.mytaken.view;

import com.example.mytaken.R;
import com.example.mytaken.util.BanDuongUtils;
import com.vnp.core.common.VNPResize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditextBold extends EditText implements Runnable {

	public CustomEditextBold(Context context) {
		super(context);
		BanDuongUtils.setTypefaceBold(this);
		post(this);
	}

	public CustomEditextBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		BanDuongUtils.setTypefaceBold(this);
		post(this);
	}

	public CustomEditextBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		BanDuongUtils.setTypefaceBold(this);
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
