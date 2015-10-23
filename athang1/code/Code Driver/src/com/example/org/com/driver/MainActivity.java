package com.example.org.com.driver;

import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.org.com.driver.ProClassApplication.IProLoader;
import com.example.org.com.driver.view.BaseTextView;
import com.example.org.com.driver.view.ProCallMainView;
import com.example.org.com.driver.view.ProDB;
import com.example.org.com.driver.view.Proclass;
import com.vnp.core.common.CommonAndroid;

public class MainActivity extends MBaseActivity implements OnClickListener,
		IProLoader {
	ProCallMainView proCallMainView;
	Handler mHandler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			if (!isFinishing()) {
				mHandler.sendEmptyMessageDelayed(0, 1000);
				if(proCallMainView!= null){
					Random random = new Random();
					proCallMainView.setRotate((random.nextBoolean() ? 1 : -1 )*random.nextInt(90));
				}
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		proCallMainView = getView(R.id.proCallMainView1);
		
		findViewById(R.id.main_call).setOnClickListener(this);
		findViewById(R.id.baseTextViewBold1_2).setOnClickListener(this);
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.imageView5).setOnClickListener(this);
		findViewById(R.id.imageView6).setOnClickListener(this);
		findViewById(R.id.setting).setOnClickListener(this);
		Random random = new Random();

		int randomResult = random.nextInt(1000);
		boolean isError = randomResult < 300;
		if (!isError) {
			findViewById(R.id.imageView2).setVisibility(View.GONE);
		}

		if (isError) {
			findViewById(R.id.imageView3).setVisibility(View.GONE);
			indexError = 0;
			errorList = new int[new Random().nextInt(4) + 1];

			LinearLayout linearLayout = getView(R.id.error_001);
			linearLayout.removeAllViews();

			for (int i = 0; i < errorList.length; i++) {
				errorList[i] = i;
				BaseTextView baseTextView = new BaseTextView(this);
				if (i == 0) {
					baseTextView.setTextColor(Color.parseColor("#ffffff"));
				} else {
					baseTextView.setTextColor(Color.parseColor("#666666"));
				}

				setTextSize(baseTextView, 18);

				baseTextView.setText(" " + (1 + errorList[i]) + " ");
				linearLayout.addView(baseTextView);

			}

			((TextView) getView(R.id.baseTextViewBold1))
					.setText(errorList.length + " ISSUES FOUND");

			((TextView) getView(R.id.BaseTextViewBold02)).setText(String
					.format(ProUtils.HEADER, "1"));

		} else {
			findViewById(R.id.error).setVisibility(View.GONE);
		}

		handler.post(this);

		// /error_001
		mHandler.sendEmptyMessageDelayed(0, 1000);
	}

	private int indexError = -1;
	private int[] errorList = new int[0];

	private void update(int index) {
		int newIndex = indexError + index;

		if (newIndex >= 0 && newIndex < errorList.length) {
			indexError = newIndex;
		}

		LinearLayout linearLayout = getView(R.id.error_001);

		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			View view = linearLayout.getChildAt(i);

			if (view instanceof TextView) {
				TextView textView = (TextView) view;

				if (i == indexError) {
					textView.setTextColor(Color.parseColor("#ffffff"));
				} else {
					textView.setTextColor(Color.parseColor("#666666"));
				}
			}
		}

		((TextView) getView(R.id.BaseTextViewBold02)).setText(String.format(
				ProUtils.HEADER, indexError + 1));
	}

	@Override
	public void onClick(View v) {
		if (R.id.main_call == v.getId()) {
			// call
			ProDB proDB = new ProDB(this);
			try {
				CommonAndroid.callPhone(this, proDB.getPhoneShop());
			} catch (Exception exception) {
				CommonAndroid.showDialog(this,
						"Can not call : " + proDB.getPhoneShop(), null);
			}
		} else if (R.id.baseTextViewBold1_2 == v.getId()) {
			Proclass.sendEmail(this);
		} else if (R.id.imageView5 == v.getId()) {
			update(-1);
		} else if (R.id.imageView6 == v.getId()) {
			update(+1);
		} else if (R.id.button1 == v.getId()) {
			Intent intent = new Intent(this, ProblemActivity.class);
			intent.putExtra("index", indexError);
			intent.putExtra("count", errorList.length);
			startActivity(intent);
		} else if (R.id.setting == v.getId()) {
			startActivity(new Intent(this, SettingActivity.class));
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1000 && resultCode == RESULT_OK) {
			startActivity(new Intent(this, LoginScreen.class));
			finish();
		}
	}

	@Override
	public void run() {
		resize(R.id.header, LayoutParams.MATCH_PARENT, 50);
		resize(R.id.imageView1, (int) (ProUtils.scale * 112),
				(int) (ProUtils.scale * 21));

		resize(R.id.setting_m1_m1, 270, LayoutParams.WRAP_CONTENT);
		resize(R.id.ImageView01, 25, 25);
		setTextSize(R.id.baseTextView2, 18);

		resize(R.id.main_call, 268, 59);
		resize(R.id.main_call1, 59, 59);
		setTextSize(R.id.baseTextView1, 22);

		resize(R.id.imageView3, 268, 303);

		// error
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

		// setting
		resize(R.id.setting, 75, 50);
		setTextSize(R.id.baseTextView3, 13);
		resize(R.id.imageView7, 20, 20);

		resize(R.id.setting_left, 75, 50);
		setTextSize(R.id.baseTextView3_left, 13);
		resize(R.id.imageView7_left, 26, 20);
		resize(R.id.imageView2, 9, 9);
	}

	@Override
	protected void onResume() {
		super.onResume();

		ProDB proDB = new ProDB(this);
		String text = "CALL DIRECT TIRE\n" + proDB.getPhoneShop();
		((TextView) getView(R.id.baseTextView1)).setText(text);
	}

	@Override
	public void callBackStatus(int status, String[] erorrList, int frameSize) {
	}

}