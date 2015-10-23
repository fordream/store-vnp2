package com.vnp.mlook.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import com.vnp.mlook.acticities.MainActivity;

@SuppressLint("NewApi")
public class BaseFragment extends Fragment {
	public BaseFragment() {
		super();
	}

	public void callAction(MLookAction mLookAction) {
		((MainActivity) getActivity()).callAction(mLookAction);
	}

	public boolean isLogin() {
		return true;
	}

	public void callActionBack() {
		((MainActivity) getActivity()).onBackPressed();
	}

}
