package com.vnp.mlook.fragment.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnp.mlook.R;
import com.vnp.mlook.fragment.BaseFragment;

public class ForgotPasswordFragment extends BaseFragment {
	public ForgotPasswordFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.forgotpassword_fragment,
				container, false);
		return view;
	}
}