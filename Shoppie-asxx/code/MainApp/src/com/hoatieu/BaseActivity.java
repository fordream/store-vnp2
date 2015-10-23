package com.hoatieu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.vnp.core.common.VNPResize;

public class BaseActivity extends FragmentActivity {
	private ProgressDialog dialog;
	protected VNPResize resize = VNPResize.getInstance();

	public void changeFragemtn(int r_id_content_frame, Fragment rFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(r_id_content_frame, rFragment);
		ft.commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		resize.init(this, 320, 480, null);
	}

	/**
	 * 
	 * @param show
	 */
	protected void showProgressDialog(boolean show) {
		if (show) {
			if (dialog == null) {
				dialog = ProgressDialog.show(this, null, "loadding ... ");
			}
		} else {
			if (dialog != null)
				dialog.dismiss();

			dialog = null;
		}

	}

	public String getStringFromView(int res) {

		View v = findViewById(res);
		if (v instanceof TextView) {
			return ((TextView) v).getText().toString().trim();
		}

		return null;

	}

	public void addResizeInfor(int res, int width, int height) {
		ResizeInfor resizeInfor = new ResizeInfor();
		resizeInfor.res = res;
		resizeInfor.width = width;
		resizeInfor.height = height;
		list.add(resizeInfor);
	}

	@Override
	protected void onStart() {
		super.onStart();
		for (ResizeInfor resizeInfor : list) {
			resize.resizeSacle(findViewById(resizeInfor.res), resizeInfor.width, resizeInfor.height);
		}
	}

	public Activity getActivity() {
		return this;
	}

	private List<ResizeInfor> list = new ArrayList<BaseActivity.ResizeInfor>();

	private class ResizeInfor {
		int res;
		int width;
		int height;
	}
}