package org.com.vn.loxleycolour.views.wiget;

import org.com.vn.loxleycolour.v1.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog {

	public CustomDialog(Context context) {
		super(context, R.style.Theme_Dialog_Translucent);
		setContentView(R.layout.dialog);

		findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						dismiss();
					}
				});
	}

	public void setTitle(String title) {
		getTextView(R.id.textView1).setText(title);
	}

	public void setMessage(String title) {
		getTextView(R.id.textView2).setText(title);
	}

	private TextView getTextView(int res) {
		return (TextView) findViewById(res);
	}

	public void setTextButton(String text) {
		((Button) findViewById(R.id.button1)).setText(text);
	}
}
