package com.example.mytaken.view;

import com.example.mytaken.util.BanDuongUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButtonBold extends Button {

	public CustomButtonBold(Context context) {
		super(context);
		BanDuongUtils.setTypefaceBold(this);
	}

	public CustomButtonBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		BanDuongUtils.setTypefaceBold(this);
	}

	public CustomButtonBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		BanDuongUtils.setTypefaceBold(this);
	}

}
