package org.com.vn.loxleycolour.views.wiget;

import android.content.Context;

public class DialogResetPassword extends CustomDialog {

	public DialogResetPassword(Context context) {
		super(context);

		setTextButton("Continue");
		setTitle("Reset Password");
		setMessage("A new password has been \nSend to your inbox.");
	}

}
