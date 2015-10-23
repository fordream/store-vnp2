package com.example.mytaken.view;

import com.example.mytaken.util.BanDuongUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextViewBold extends TextView {

	public CustomTextViewBold(Context context) {
		super(context);
		BanDuongUtils.setTypefaceBold(this);
		// TODO Auto-generated constructor stub
	}

	public CustomTextViewBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		BanDuongUtils.setTypefaceBold(this);
		// TODO Auto-generated constructor stub
	}

	public CustomTextViewBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		BanDuongUtils.setTypefaceBold(this);
		// TODO Auto-generated constructor stub
	}

}
