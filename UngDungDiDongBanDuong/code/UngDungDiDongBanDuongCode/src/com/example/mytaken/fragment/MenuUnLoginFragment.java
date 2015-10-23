package com.example.mytaken.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.mytaken.LoginActivity;
import com.example.mytaken.R;
import com.example.mytaken.RegisterActivity;
import com.example.mytaken.util.BanDuongUtils;

public class MenuUnLoginFragment extends MBaseFragment implements
		OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.menuunlogin_main, null);

		BanDuongUtils.resizeLogo(view.findViewById(R.id.loginlogo));
		BanDuongUtils.resizeButton(view.findViewById(R.id.loginm1));
		BanDuongUtils.resizeButton(view.findViewById(R.id.loginm2));
		BanDuongUtils.resizeButton(view.findViewById(R.id.loginm3));
		view.findViewById(R.id.loginm1).setOnClickListener(this);
		view.findViewById(R.id.loginm2).setOnClickListener(this);
		view.findViewById(R.id.loginm3).setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == R.id.loginm1) {
			startActivity(new Intent(getActivity(), RegisterActivity.class));
		} else if (arg0.getId() == R.id.loginm2) {
			// facebook

		} else if (arg0.getId() == R.id.loginm3) {
			// login screen
			startActivity(new Intent(getActivity(), LoginActivity.class));

		}
	}
}