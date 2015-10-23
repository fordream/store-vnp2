package com.example.app;

import org.core.store.app.StoreBaseFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class NEW  extends StoreBaseFragment {
	WebView webView;
	public NEW() {
		super();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		webView = new WebView(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return webView;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public int getResourceLayout() {
		return 0;
	}

	@Override
	public String getTitleBar() {
		return null;
	}

}
