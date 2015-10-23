package com.example.mytaken;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.vnp.core.activity.BaseFragmentActivity;
import com.vnp.core.common.VNPResize;

public class MBaseActivity extends BaseFragmentActivity {
	public VNPResize resize = VNPResize.getInstance();
	public void changeFragemtn(int r_id_content_frame, Fragment rFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(r_id_content_frame, rFragment);
		ft.commit();
	}
}