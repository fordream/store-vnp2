package com.hoatieu;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;

import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class BaseView extends CustomLinearLayoutView {
	private ProgressDialog dialog;

	/**
	 * 
	 * @param show
	 */
	protected void showProgressDialog(boolean show) {
		if (show) {
			if (dialog == null) {
				dialog = ProgressDialog.show(getContext(), null, "loadding ... ");
			}
		} else {
			if (dialog != null)
				dialog.dismiss();

			dialog = null;
		}

	}

	protected VNPResize resize = VNPResize.getInstance();

	public BaseView(Context context) {
		super(context);
	}

	public BaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setGender() {

	}

}
