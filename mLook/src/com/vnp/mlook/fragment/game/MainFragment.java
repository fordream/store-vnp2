package com.vnp.mlook.fragment.game;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnp.mlook.R;
import com.vnp.mlook.acticities.MainActivity;
import com.vnp.mlook.fragment.BaseFragment;
import com.vnp.mlook.views.GameTab1Fragment;
import com.vnp.mlook.views.GameTab2Fragment;
import com.vnp.mlook.views.GameTab3Fragment;
import com.vnp.mlook.views.MLookViewPager;

public class MainFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mainfragment, container, false);
		MLookViewPager lookViewPager = (MLookViewPager) view
				.findViewById(R.id.mLookViewPager1);
		lookViewPager.setEnalbleScroll(false);
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(new GameTab1Fragment());
		fragments.add(new GameTab2Fragment());
		fragments.add(new GameTab3Fragment());
		FragmentManager fragmentManager = ((MainActivity) getActivity())
				.getSupportFragmentManager();
		// PagerAdapter pagerAdapter = new PagerAdapter(fragmentManager,
		// fragments);
		// lookViewPager.setAdapter(pagerAdapter);
		return view;
	}
}
