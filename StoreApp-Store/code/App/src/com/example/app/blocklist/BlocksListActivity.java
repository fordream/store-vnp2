package com.example.app.blocklist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.core.store.app.StoreBaseActivity;
import org.core.store.app.StoreBaseFragment;
import org.core.store.app.StoreBasePagerAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.app.R;
import com.example.app.utils.MemoryUtils;
import com.example.app.utils.Utils;

public class BlocksListActivity extends StoreBaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blockslistactivity);

		updateUI();
	}

	public void updateUI() {
		try {
			List<Fragment> fragments = new ArrayList<Fragment>();
			List<Block> obs = Utils.getListBlocks(this);
			// calculator page
			for (int i = 0; i < obs.size(); i++) {
				Block block = (Block) obs.get(i);
				StoreBaseFragment fragment = (StoreBaseFragment) Fragment
						.instantiate(this, Blocks6Fragment.class.getName());

				if (!block.isBig()) {
					fragment = (StoreBaseFragment) Fragment.instantiate(this,
							Blocks9Fragment.class.getName());
				}

				// get data for page
				List<Object> lData = new ArrayList<Object>();
				lData.add(block);
				int coutItemOfPage = block.isBig() ? 6 : 9;

				for (int j = 1; j < coutItemOfPage; j++) {
					i++;
					if (i < obs.size()) {
						lData.add(obs.get(i));
					} else {
						break;
					}
				}

				fragment.setData(lData);
				fragments.add(fragment);
			}

			StoreBasePagerAdapter mPagerAdapter = new StoreBasePagerAdapter(
					getSupportFragmentManager(), fragments);
			((ViewPager) findViewById(R.id.pager)).setAdapter(mPagerAdapter);
		} catch (Exception exception) {
		}
	}
}