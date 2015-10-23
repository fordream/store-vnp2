package org.com.vn.loxleycolour.views.wiget;

import org.com.vn.loxleycolour.v1.R.drawable;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class CustomRadioButton extends RadioButton {

	public CustomRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CustomRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomRadioButton(Context context) {
		super(context);
		init();
	}

	private void init() {
		try {
			setButtonDrawable(drawable.tranfer);
			setBackgroundResource(drawable.checkorder);
		} catch (Exception e) {
		}
	}
	
	@Override
	public void setChecked(boolean checked) {
		super.setChecked(checked);
		if(checked){
			setTextColor(Color.WHITE);
		}else{
			setTextColor(Color.GRAY);
		}
	}

}
