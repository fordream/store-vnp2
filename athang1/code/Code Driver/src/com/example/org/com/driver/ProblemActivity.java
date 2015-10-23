package com.example.org.com.driver;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.org.com.driver.view.BaseTextView;
import com.example.org.com.driver.view.ErrorItemView;
import com.example.org.com.driver.view.Proclass;

public class ProblemActivity extends MBaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.problem);
		handler.post(this);
		getView(R.id.imageView1).setOnClickListener(this);
		findViewById(R.id.imageView5).setOnClickListener(this);
		findViewById(R.id.imageView6).setOnClickListener(this);
		int count = getIntent().getIntExtra("count", 0);
		indexError = getIntent().getIntExtra("index", 0);
		errorList = new int[count];

		LinearLayout linearLayout = getView(R.id.error_001);
		linearLayout.removeAllViews();

		BaseTextView baseTextView1 = new BaseTextView(this);
		baseTextView1.setTextColor(Color.parseColor("#ffffff"));
		baseTextView1.setText("Issue:");
		setTextSize(baseTextView1, 18);
		linearLayout.addView(baseTextView1);
		for (int i = 0; i < errorList.length; i++) {
			errorList[i] = i;
			BaseTextView baseTextView = new BaseTextView(this);
			if (i == indexError) {
				baseTextView.setTextColor(Color.parseColor("#ffffff"));
			} else {
				baseTextView.setTextColor(Color.parseColor("#666666"));
			}

			setTextSize(baseTextView, 18);

			baseTextView.setText(" " + (1 + errorList[i]) + " ");
			linearLayout.addView(baseTextView);

		}
		update(0);
	}

	private void update(int index) {
		int newIndex = indexError + index;

		if (newIndex >= 0 && newIndex < errorList.length) {
			indexError = newIndex;
		}

		LinearLayout linearLayout = getView(R.id.error_001);

		for (int i = 1; i < linearLayout.getChildCount(); i++) {
			View view = linearLayout.getChildAt(i );

			if (view instanceof TextView) {
				TextView textView = (TextView) view;

				if (i - 1 == indexError ) {
					textView.setTextColor(Color.parseColor("#ffffff"));
				} else {
					textView.setTextColor(Color.parseColor("#666666"));
				}
			}
		}

		((TextView) getView(R.id.BaseTextViewBold02)).setText(String.format(
				ProUtils.HEADER, indexError + 1));

		((TextView) getView(R.id.BaseTextView01)).setText((indexError + 1)
				+ " SUBHEADING");

		TextView t2 = getView(R.id.baseTextView2);
		t2.setText((indexError + 1) + " " + getString(R.string.error));
	}

	private int indexError = -1;
	private int[] errorList = new int[0];

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.imageView1) {
			finish();
		}
		if (R.id.imageView5 == v.getId()) {
			update(-1);
		} else if (R.id.imageView6 == v.getId()) {
			update(+1);
		}
	}

	@Override
	public void run() {

		setTextSize(R.id.BaseTextView02, 18);
		
		resize(R.id.problem_content, 270, LayoutParams.WRAP_CONTENT);
		resize(R.id.problem_m1, 270, LayoutParams.WRAP_CONTENT);
		resize(R.id.imageView1, 50, 50);
		setTextSize(R.id.baseTextView1, 22);
		resize(R.id.imageView4, 139, 95);
		setTextSize(R.id.baseTextViewBold1, 25);
		setTextSize(R.id.baseTextViewBold1_1, 18);
		setTextSize(R.id.baseTextViewBold1_2, 18);

		resize(R.id.error_is, 297, 170);
		resize(R.id.error_is1, 270, LayoutParams.MATCH_PARENT);
		setTextSize(R.id.BaseTextViewBold01, 18);
		resize(R.id.imageView5, 15, 29);
		resize(R.id.imageView6, 15, 29);
		setTextSize(R.id.BaseTextViewBold02, 20);

		resize(R.id.button1, 270, 58);

		setTextSize(R.id.BaseTextView01, 25);
		setTextSize(R.id.baseTextView2, 22);
		
		

	}
}