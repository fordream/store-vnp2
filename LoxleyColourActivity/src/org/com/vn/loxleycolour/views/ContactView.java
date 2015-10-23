package org.com.vn.loxleycolour.views;

import org.com.cnc.common.android.CommonDeviceId;
import org.com.cnc.common.android.CommonView;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ContactView extends LinearLayout implements OnClickListener,
		Const, IView {
	private Button btnCall;
	private Button btnEmail;
	private Button btnWeb;

	public ContactView(Context context) {
		super(context);
		init();
	}

	public ContactView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.contact, this);

		btnCall = (Button) findViewById(R.id.contact_btn_call);
		btnEmail = (Button) findViewById(R.id.contact_btn_emal);
		btnWeb = (Button) findViewById(R.id.contact_btn_visit);

		btnCall.setOnClickListener(this);
		btnEmail.setOnClickListener(this);
		btnWeb.setOnClickListener(this);

		int wight = CommonDeviceId.getWidth((Activity) getContext());
		wight = (int) (300.0 / 480.0 * wight);
		int height = wight * 300 / 252;
		LayoutParams params = new LayoutParams(wight, height);
		findViewById(R.id.llContactForm).setLayoutParams(params);

		reset();
	}

	public void onClick(View view) {
		if (view == btnCall) {
			CommonView.callPhone(getContext(), PHONE);
		} else if (view == btnWeb) {
			CommonView.callWeb(getContext(), URL);
		} else if (view == btnEmail) {
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

			String[] recipients = new String[] { "cs@loxleycolour.com", "", };

			emailIntent
					.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);

			emailIntent
					.putExtra(android.content.Intent.EXTRA_SUBJECT, "Suport");

			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Send");

			emailIntent.setType("text/plain");

			getContext().startActivity(
					Intent.createChooser(emailIntent, "Send mail..."));
		}
	}

	public void reset() {
		((IheaderLabel) getContext()).setTextHeader("Contact");
	}
}
