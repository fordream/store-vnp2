package com.hoatieu;

import com.vnp.core.common.VNPResize;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

public class BaseFragment extends Fragment {
	private ProgressDialog dialog;

	/**
	 * 
	 * @param show
	 */
	protected void showProgressDialog(boolean show) {
		if (show) {
			if (dialog == null) {
				dialog = ProgressDialog.show(getActivity(), null, "loadding ... ");
			}
		} else {
			if (dialog != null)
				dialog.dismiss();

			dialog = null;
		}

	}

	protected VNPResize resize = VNPResize.getInstance();

	public BaseFragment() {
		super();
	}

	public String getStringFromView(int res) {

		View v = getView().findViewById(res);
		if (v instanceof TextView) {
			return ((TextView) v).getText().toString().trim();
		}

		return null;
	}

}
