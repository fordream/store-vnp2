package org.vnp.storeapp.views;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseView;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class HeaderView extends BaseView implements android.view.View.OnClickListener {

	public interface IClickNextPrevious {

		public void onClickPrevious();

		public void onClickNext();
	}

	private IClickNextPrevious clickNextPrevious;

	public void setIClickNextPrevious(IClickNextPrevious clickNextPrevious) {
		this.clickNextPrevious = clickNextPrevious;
	}

	private int resText = R.id.textView1_header;

	public HeaderView(Context context) {
		super(context);
		init(R.layout.headerview);
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.headerview);
	}

	@Override
	public void init(int res) {
		super.init(res);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		findViewById(R.id.mcontrol).setOnClickListener(this);
		rezise(findViewById(R.id.mcontrol), 960, 200);
		rezise(findViewById(R.id.progressBar1), 45, 45);
		rezise(findViewById(R.id.button2), 135, 135);
		rezise(findViewById(R.id.button3), 135, 135);
		
		hiddenEnimation(findViewById(R.id.RelativeLayout1));
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		rezise(this, 960, 200);
		StoreAppUtils.setTextSize(findViewById(resText), 30);
	}

	public void setVisibilityforBack(int visibility) {
		findViewById(R.id.button1).setVisibility(visibility);
	}

	public boolean isOnProcess() {
		return findViewById(R.id.progressBar1).getVisibility() == VISIBLE;
	}

	public void setVisibilityforProcess(int visibility) {
		findViewById(R.id.progressBar1).setVisibility(visibility);
	}

	@Override
	public void setGender() {
	}

	public void setText(String string) {
		((TextView) findViewById(R.id.textView1_header)).setText(string);
	}

	/**
	 * 
	 * @param isShowNext
	 * @param isShowPrevious
	 */
	public void showButtonNextPrevious(boolean isShowNext, boolean isShowPrevious) {
		findViewById(R.id.button3).setVisibility(isShowNext ? VISIBLE : GONE);
		findViewById(R.id.button2).setVisibility(isShowPrevious ? VISIBLE : GONE);
	}

	@Override
	public void onClick(View v) {
		resetTimeAnimation();
		if (v.getId() == R.id.button2) {
			clickNextPrevious.onClickPrevious();
		} else if (v.getId() == R.id.button3) {
			clickNextPrevious.onClickNext();
		} else if (v.getId() == R.id.mcontrol) {
			exeEnimation(findViewById(R.id.RelativeLayout1));
		}
	}
}