package com.vnp.mlook.fragment.account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vnp.mlook.R;
import com.vnp.mlook.fragment.BaseFragment;
import com.vnp.mlook.fragment.MLookAction;
import com.vnp.mlook.utils.MLookServiceAction;
import com.vnp.mlook.utils.MLookServiceAction.MLookServiceStatus;

public class SupportFragment extends BaseFragment {
	public SupportFragment() {
		super();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.supportfragment, container, false);


		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStop() {
		super.onStop();

	}

}
