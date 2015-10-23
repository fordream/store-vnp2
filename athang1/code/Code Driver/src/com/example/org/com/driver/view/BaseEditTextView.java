package com.example.org.com.driver.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

//vnp.mr.mintmedical.view.BaseEditTextView
public class BaseEditTextView extends EditText {

	public BaseEditTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BaseEditTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BaseEditTextView(Context context) {
		super(context);
		init();
	}

	private void init() {
		try {
			Proclass.setTypeface(this);
		} catch (Exception exception) {
		} catch (Error e) {
		}
	}
}
