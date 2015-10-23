package org.vnp.storeapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.base.BasePagerAdapter;
import org.vnp.storeapp.base.MBaseActivity;
import org.vnp.storeapp.blocklist.Block;
import org.vnp.storeapp.blocklist.BlockCallBack;
import org.vnp.storeapp.fragment.BlockListFragment;
import org.vnp.storeapp.utils.StoreAppUtils.TEXTSIZE;
import org.vnp.storeapp.views.MainHeaderView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
import com.vnp.core.callback.ExeCallBack;

public class BlocksListActivity extends MBaseActivity {
	private PageIndicator mIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blockslistactivity);
		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);

		showloaderview(true);
		final View view = findViewById(R.id.main_content);
		view.setVisibility(View.GONE);
		updateUI();

		BlockCallBack blockCallBack = new BlockCallBack(this) {
			@Override
			public void onCallBack(Object arg0) {
				super.onCallBack(arg0);
				updateUI();
				view.setVisibility(View.VISIBLE);
				showloaderview(false);
			}
		};

		new ExeCallBack().executeAsynCallBack(blockCallBack);
	}

	@Override
	protected void onResume() {
		super.onResume();
		resize.resize(findViewById(R.id.main_top_content),
				LayoutParams.MATCH_PARENT, resize.getHeightScreen() * (120 * 2)
						/ 960);

		int width = 520;
		resize.resizeSacle(findViewById(R.id.main_top_content_main),
				width / 2 * 3, (200) / 2 * 3);

		resize.resizeSacle(findViewById(R.id.main_top_b1), 170 / 2 * 3,
				240 / 2 * 3);

		resize.resizeSacle(findViewById(R.id.imgsocial), 170 / 2 * 3,
				170 / 2 * 3);
		resize.resizeSacle(findViewById(R.id.imageView1_border), 170 / 2 * 3,
				170 / 2 * 3);

		resize.resizeSacle(findViewById(R.id.scrollView1),
				(width - 170) / 2 * 3, 200 / 2 * 3);
		resize.resizeSacle(findViewById(R.id.textView1), (300) / 2 * 3,
				LayoutParams.WRAP_CONTENT);

		resize.resizeSacle(findViewById(R.id.textView2), (300) / 2 * 3,
				LayoutParams.WRAP_CONTENT);

		resize.setTextsize(findViewById(R.id.textView1), TEXTSIZE.BLOCKLIST1);
		resize.setTextsize(findViewById(R.id.textView2), TEXTSIZE.BLOCKLIST2);
	}

	public void updateUI() {
		BlockCallBack blockCallBack = new BlockCallBack(this);
		Block mBlock = blockCallBack.getBlockMain();

		String headerTitle = blockCallBack.getHeaderTitle();
		String headerBackgroundCorlor = blockCallBack
				.getHeaderBackgroundCorlor();

		MainHeaderView headerView = (MainHeaderView) findViewById(R.id.mainHeaderView1);
		headerView.update(headerTitle, headerBackgroundCorlor, BlockType.HOME,
				"");

		if (mBlock.getType() == BlockType.MAIN) {
			ImageView img = (ImageView) findViewById(R.id.imgsocial);
			String url = mBlock.getImg();

			if (url == null || (url != null & url.trim().equals(""))) {
				url = mBlock.getSub_icon();
			}

			setImageUrl(img, url);
			setText(R.id.textView1, mBlock.getHeader());
			setText(R.id.textView2, mBlock.getSub_header());
			String backgroundColor = mBlock.getBackgoundColor();
			if (backgroundColor != null && backgroundColor.startsWith("#")) {
				findViewById(R.id.imageView1_border).setBackgroundColor(
						Color.parseColor(backgroundColor));
			}
		}

		List<Fragment> fragments = new ArrayList<Fragment>();
		@SuppressWarnings("unchecked")
		List<Object> obs = (List<Object>) blockCallBack.parseResponse();
		// calculator page
		for (int i = 0; i < obs.size(); i++) {
			Block block = (Block) obs.get(i);
			BaseFragment fragment = (BaseFragment) Fragment.instantiate(this,
					BlockListFragment.class.getName());
			// get data for page
			List<Object> lData = new ArrayList<Object>();
			lData.add(block);
			int coutItemOfPage = 4;

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

		BasePagerAdapter mPagerAdapter = new BasePagerAdapter(
				getSupportFragmentManager(), fragments);
		((ViewPager) findViewById(R.id.pager)).setAdapter(mPagerAdapter);
		if (fragments.size() > 1)
			mIndicator.setViewPager((ViewPager) findViewById(R.id.pager));

		if (fragments.size() >= 1) {
			findViewById(R.id.main_content).setVisibility(View.VISIBLE);
		}
	}

}