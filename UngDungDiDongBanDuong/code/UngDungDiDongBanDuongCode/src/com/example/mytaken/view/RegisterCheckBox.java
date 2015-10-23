/**
 * 
 */
package com.example.mytaken.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.mytaken.LoginActivity;
import com.example.mytaken.R;
import com.example.mytaken.RegisterActivity;
import com.example.mytaken.util.BanDuongUtils;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

/**
 * @author teemo
 * 
 */
public class RegisterCheckBox extends CustomLinearLayoutView implements Runnable, OnClickListener {

	/**
	 * @param context
	 */
	public RegisterCheckBox(Context context) {
		super(context);
		init(R.layout.registercheckbox);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public RegisterCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.registercheckbox);
	}

	@Override
	public void init(int res) {
		super.init(res);
		post(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnp.core.view.CustomLinearLayoutView#setGender()
	 */
	@Override
	public void setGender() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnp.core.view.CustomLinearLayoutView#showHeader(boolean)
	 */
	@Override
	public void showHeader(boolean arg0) {

	}

	@Override
	public void run() {
		VNPResize resize = VNPResize.getInstance();
		try {
			resize.resizeSacle(this, android.view.ViewGroup.LayoutParams.MATCH_PARENT, BanDuongUtils.SIZE.HEIGHT_HEADER);
			resize.resizeSacle(findViewById(R.id.checkBox1), BanDuongUtils.SIZE.HEIGHT_REGISTER_CHECKBOX, BanDuongUtils.SIZE.HEIGHT_REGISTER_CHECKBOX);
			resize.resizeSacle(findViewById(R.id.customTextView1_registercheckbox), BanDuongUtils.SIZE.WIDTH_REGISTER_CHECKBOX_TEXT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		} catch (Exception exception) {
		} catch (Error error) {
		}
	}

	@Override
	public void onClick(View v) {
	}

	public void setText(int register7) {
		((TextView)findViewById(R.id.customTextView1_registercheckbox)).setText(register7);	
	}
}