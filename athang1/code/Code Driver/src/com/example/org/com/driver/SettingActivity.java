package com.example.org.com.driver;

import com.example.org.com.driver.view.ProDB;
import com.example.org.com.driver.view.Proclass;
import com.vnp.core.common.CommonAndroid;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class SettingActivity extends MBaseActivity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		handler.post(this);

		ProDB proDB = new ProDB(this);

		((TextView) getView(R.id.baseEditTextView1)).setText(proDB.getEmail());
		((TextView) getView(R.id.baseEditTextView1_2)).setText(proDB
				.getRepairShop());
		((TextView) getView(R.id.BaseEditTextView01)).setText(proDB
				.getFullName());
		((TextView) getView(R.id.baseEditTextView1_1))
				.setText(proDB.getPhone());
		getView(R.id.button1).setOnClickListener(this);
		getView(R.id.button2).setOnClickListener(this);
		// getView(R.id._baseEditTextView1_2).setOnClickListener(this);
	}

	@Override
	public void run() {
		resize(R.id.login_id_1, LayoutParams.MATCH_PARENT, 50);
		resize(R.id.imageView1, 128, 31);
		//resize(R.id.ImageView01, 196, 19);
		resize(R.id.login_id_2, 240, LayoutParams.WRAP_CONTENT);
		setTextSize(R.id.BaseTextView01, 22);
		setTextSize(R.id.baseTextView1, 22);
		setTextSize(R.id.baseTextView1_1, 22);
		setTextSize(R.id.baseEditTextView1_1, 22);
		setTextSize(R.id.baseTextView1_2, 22);
		setTextSize(R.id.baseEditTextView1_2, 22);
		setTextSize(R.id.BaseEditTextView01, 22);
		setTextSize(R.id.baseEditTextView1, 22);
		resize(R.id._BaseEditTextView01, 240, 46);
		resize(R.id._baseEditTextView1, 240, 46);
		resize(R.id._baseEditTextView1_1, 240, 46);
		resize(R.id._baseEditTextView1_2, 240, 46);
		resize(R.id.button1, 270, 60);
		resize(R.id.button2, 270, 60);
		
		resize(R.id.setting_m1_m1, 270, LayoutParams.WRAP_CONTENT);
		resize(R.id.setting_m1_m2, 240, LayoutParams.WRAP_CONTENT);
		resize(R.id.imageView2, 25, 25);
		setTextSize(R.id.baseTextView2, 18);
		resize(R.id.ImageView01, 119, 31);
	}

	@Override
	public void onClick(View v) {
		if (R.id._baseEditTextView1_2 == v.getId()) {
			Builder builder = new Builder(this);
			builder.setItems(ProUtils.REPAIRSHPES,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							update(which);
						}
					});
			builder.show();

		} else if (v.getId() == R.id.button1) {
			startActivity(new Intent(this, ChangasswordActivity.class));
		} else if (v.getId() == R.id.button2) {
			new ProDB(this).clear();
			setResult(RESULT_OK);
			finish();
		}
	}

	private void update(int which) {
		if (which == -1) {
			return;
		}
		// which = 0;
		TextView textView = getView(R.id.baseEditTextView1_2);
		textView.setText(ProUtils.REPAIRSHPES[which]);

		ProDB db = new ProDB(this);
		db.saveRepairShop(ProUtils.REPAIRSHPES[which]);
		db.savePhoneShop(ProUtils.PHONELIST[which]);
	}
}