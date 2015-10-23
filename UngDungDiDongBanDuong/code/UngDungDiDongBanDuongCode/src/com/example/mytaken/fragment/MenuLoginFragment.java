package com.example.mytaken.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.mytaken.AccountManagerActiity;
import com.example.mytaken.BookOnlineActiity;
import com.example.mytaken.InforDriverActivity;
import com.example.mytaken.R;
import com.example.mytaken.util.BanDuongUtils;

public class MenuLoginFragment extends MBaseFragment implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, null);
		BanDuongUtils.resizeLogo(view.findViewById(R.id.logo));
		BanDuongUtils.resizeButton(view.findViewById(R.id.m1));
		BanDuongUtils.resizeButton(view.findViewById(R.id.m2));
		BanDuongUtils.resizeButton(view.findViewById(R.id.m3));

		view.findViewById(R.id.m1).setOnClickListener(this);
		view.findViewById(R.id.m2).setOnClickListener(this);
		view.findViewById(R.id.m3).setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == R.id.m1) {
			startActivity(new Intent(getActivity(),InforDriverActivity.class));
		} else if (arg0.getId() == R.id.m2) {
			startActivity(new Intent(getActivity(),BookOnlineActiity.class));
		} else if (arg0.getId() == R.id.m3) {
			startActivity(new Intent(getActivity(),AccountManagerActiity.class));
		}
	}
}