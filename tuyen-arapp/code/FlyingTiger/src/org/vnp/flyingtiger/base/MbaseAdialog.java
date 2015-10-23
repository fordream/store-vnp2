package org.vnp.flyingtiger.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

public abstract class MbaseAdialog extends Dialog {
	public DialogInterface.OnClickListener clickListener;

	public MbaseAdialog(Context context) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCancelable(false);
		setContentView(getLayout());
	}

	public abstract int getLayout();

}