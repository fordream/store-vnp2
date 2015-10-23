package org.vnp.storeapp.block;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.core.store.app.StoreBaseActivity;
import org.core.store.app.StoreBaseFragment;
import org.core.store.app.StoreBasePagerAdapter;
import org.vnp.storeapp.block.HeaderDetailView.IClickPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.app.R;
import com.example.app.blocklist.Block;
import com.example.app.utils.MemoryUtils;
import com.example.app.utils.Utils;
import com.vnp.core.common.VnpMultiDexClassLoader;

public class BlockActivity extends StoreBaseActivity implements IClickPage {
	StoreBasePagerAdapter mPagerAdapter;
	private List<Block> lBins;
	private ViewPager mViewPager;
	private HeaderDetailView headerDetailView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blockactivity);
		headerDetailView = (HeaderDetailView) findViewById(R.id.bindInDetailView2);
		headerDetailView.setIClickPage(this);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setOnPageChangeListener(this);

		// create header
		lBins = Utils.getListBlocks(this);
		updateService();
	}

	public void updateService() {

		headerDetailView.setData(lBins);
		List<Fragment> fragments = new Vector<Fragment>();
		int index = 0;
		MemoryUtils memoryUtils = new MemoryUtils(this);
		String jarPath = String.format("%sjava.jar",
				memoryUtils.getPathContent());
		makeText(new File(jarPath).isFile() + "");
		VnpMultiDexClassLoader.getInstance().install(this, jarPath);
		for (int i = 0; i < lBins.size(); i++) {
			Block bin = lBins.get(i);

			try {
				Class<Fragment> clzz = (Class<Fragment>)VnpMultiDexClassLoader.getInstance().loadClass(
						String.format("com.example.app.%s", bin.getFolder()));
				Log.e("ABC", clzz.getConstructors()[0] + "");
				// Fragment fragment = Fragment.instantiate(this,
				// clzz.getName());
				Fragment fragment = (Fragment) clzz.getConstructors()[0]
						.newInstance();
				fragments.add(fragment);
			} catch (Exception exception) {
				// Log.e("ABC", fragments.size() +"",exception);

			}

		}
		Log.e("ABC", fragments.size() +"");

		mPagerAdapter = new StoreBasePagerAdapter(getSupportFragmentManager(),
				fragments);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setCurrentItem(index);
		headerDetailView.setCurrentTab(index);
	}

	@Override
	public void onPageSelected(int position) {
		headerDetailView.setCurrentTab(position);
		headerDetailView.setGender();
	}

	@Override
	public void onClickpage(int pos) {
		// storeAppService.haveBlock(type)
		// TODO
		mViewPager.setCurrentItem(pos);
	}

	@Override
	public void onBackPressed() {
		int index = mViewPager.getCurrentItem();
		Fragment fragment = mPagerAdapter.getItem(index);
		if (fragment instanceof StoreBaseFragment
				&& ((StoreBaseFragment) fragment).needBack()) {

		} else {
			super.onBackPressed();
		}
	}

}