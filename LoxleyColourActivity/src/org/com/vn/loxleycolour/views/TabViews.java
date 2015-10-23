package org.com.vn.loxleycolour.views;

import org.com.cnc.common.android.layout.CommonLinearLayout;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.IView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

public class TabViews extends CommonLinearLayout implements IView {
	private TextView tvTitle;
	private int res1 = R.drawable.ic_launcher;
	int res2 = R.drawable.btnclear;
	// private TextView tvContent;
	private ImageView imgContent;

	public TabViews(Context context) {
		super(context);
		config(R.layout.tab_indicator);
	}

	public TabViews(Context context, AttributeSet attrs) {
		super(context, attrs);
		config(R.layout.tab_indicator);
	}

	public void config(int resource_layouy) {
		// setAnimation(animationSlide.inFromRightAnimation());
		super.config(resource_layouy);
		// tvContent = getTextView(R.id.title);
		imgContent = getImageView(R.id.icon);

		tvTitle = (TextView) findViewById(R.id.title);
	}

	public void setImageResource(int res1, int res2) {
		this.res1 = res1;
		this.res2 = res2;
	}

	public void setImageResource(boolean check) {
		imgContent.setImageResource(check ? res1 : res2);
		tvTitle.setTextColor(check ? Color.WHITE : Color.GRAY);
	}

	public void setImageResource(int res) {
		imgContent.setImageResource(res);
	}

	public void setText(String tv) {
		tvTitle.setText(tv);
	}

	public void reset() {

	}
}
