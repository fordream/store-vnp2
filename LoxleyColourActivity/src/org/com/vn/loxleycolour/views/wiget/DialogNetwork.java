package org.com.vn.loxleycolour.views.wiget;

import android.content.Context;

public class DialogNetwork extends CustomDialog {

	public DialogNetwork(Context context) {
		super(context);
		setTextButton("Continue");
		setTitle("Check network");
		setMessage("Network is not enable.");
	}
}
