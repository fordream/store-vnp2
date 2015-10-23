package com.example.mytaken.view;

import com.example.mytaken.util.BanDuongUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {

	public CustomButton(Context context) {
		super(context);
		BanDuongUtils.setTypeface(this);
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		BanDuongUtils.setTypeface(this);
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		BanDuongUtils.setTypeface(this);
	}
}