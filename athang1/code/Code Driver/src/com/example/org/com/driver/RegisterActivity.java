package com.example.org.com.driver;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.org.com.driver.view.ProDB;
import com.vnp.core.common.CommonAndroid;

public class RegisterActivity extends MBaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		handler.post(this);

		ProDB proDB = new ProDB(this);
		if (!proDB.getFullName().equals("")) {
			startActivity(new Intent(this, WalkthroughActivity.class));
			finish();
		}

		((TextView) getView(R.id.baseEditTextView1)).setText(proDB.getEmail());
		((TextView) getView(R.id.baseEditTextView1_2)).setText(proDB
				.getRepairShop());
		getView(R.id.button1).setOnClickListener(this);
		getView(R.id._baseEditTextView1_2).setOnClickListener(this);
	}

	@Override
	public void run() {
		resize(R.id.login_id_1, LayoutParams.MATCH_PARENT, 50);
		resize(R.id.imageView1, 128, 31);
		resize(R.id.ImageView01, 196, 19);
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
			String name = ((TextView) getView(R.id.BaseEditTextView01))
					.getText().toString().trim();
			String phone = ((TextView) getView(R.id.baseEditTextView1_1))
					.getText().toString().trim();

			if (name.equals("") || phone.equals("")) {
				CommonAndroid.showDialog(this,
						"Please input name and phone number", null);
			} else {
				ProDB db = new ProDB(this);
				db.saveFullName(name);
				db.savePhone(phone);

				startActivity(new Intent(this, WalkthroughActivity.class));
				finish();
			}
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