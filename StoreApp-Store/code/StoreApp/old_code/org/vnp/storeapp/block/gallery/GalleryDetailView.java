/**
 * 
 */
package org.vnp.storeapp.block.gallery;

import java.util.List;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseView;
import org.vnp.storeapp.base.MViewPagerNoScroll;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;

import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.common.ImageLoader;
import com.vnp.core.view.CustomLinearLayoutView;

/**
 * @author tvuong1pc
 * 
 */
@SuppressLint("ValidFragment")
public class GalleryDetailView extends BaseView implements OnClickListener, OnItemClickListener {
	private Gallery gallery;
	private MViewPagerNoScroll mViewPagerNoScroll;

	public GalleryDetailView(Context context) {
		super(context);
		init(R.layout.gallerydetailview);
	}

	public GalleryDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.gallerydetailview);
	}

	@Override
	public void init(int res) {
		super.init(res);
		setOnClickListener(this);
		resize(R.id.button1, StoreAppUtils.WIDTH_BUTTON_NEXT_GALLERY, StoreAppUtils.WIDTH_BUTTON_NEXT_GALLERY);
		resize(R.id.button2, StoreAppUtils.WIDTH_BUTTON_NEXT_GALLERY, StoreAppUtils.WIDTH_BUTTON_NEXT_GALLERY);
		resize(R.id.gallery1_1, StoreAppUtils.WIDTH_MAX, StoreAppUtils.GALLERY_DETAIL_HEIGHT_GALLERY);
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		gallery = getView(R.id.gallery1_1);
		gallery.setOnItemClickListener(this);
		mViewPagerNoScroll = (MViewPagerNoScroll) findViewById(R.id.mViewPagerNoScroll1);
	}

	@Override
	public void setGender() {
		super.setGender();
		final List<Object> lDatas = (List<Object>) getData();
		gallery.setAdapter(new BaseAdapter(getContext(), lDatas, new CommonGenderView() {
			@Override
			public CustomLinearLayoutView getView(Context arg0, Object arg1) {
				ImageLoader imageLoader = new ImageLoader(getContext());
				GalleryView galleryView = new GalleryView(arg0, imageLoader);
				galleryView.setLayout(StoreAppUtils.GALLERY_DETAIL_HEIGHT_GALLERY - 20);
				return galleryView;
			}
		}));

		mViewPagerNoScroll.setAdapter(new PagerAdapter() {
			// ImageLoader imageLoader;

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// if (imageLoader == null)
				// imageLoader = new ImageLoader(container.getContext());
				ImageView layout = new ImageView(container.getContext());

				((ViewPager) container).addView(layout, 0);
				String profolio = lDatas.get(position).toString();
				// imageLoader.DisplayImage(profolio, layout);
				setImageUrl(layout, profolio);
				return layout;
			}

			@Override
			public void destroyItem(View arg0, int arg1, Object arg2) {
				((MViewPagerNoScroll) arg0).removeView((View) arg2);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == ((View) arg1);
			}

			@Override
			public int getCount() {
				return lDatas.size();
			}
		});
		
		mViewPagerNoScroll.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				gallery.setSelection(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}

	int position = 0;

	public void updatePosition(int pos) {

		if (pos >= 0 && pos < ((List<Object>) getData()).size()) {
			position = pos;
		}

		findViewById(R.id.button2).setVisibility(position == 0 ? GONE : GONE);
		findViewById(R.id.button1).setVisibility(position == ((List<Object>) getData()).size() - 1 ? GONE : GONE);
		mViewPagerNoScroll.setCurrentItem(position);
		gallery.setSelection(position);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			updatePosition(position + 1);
		} else if (v.getId() == R.id.button2) {
			updatePosition(position - 1);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		updatePosition(position);
	}
}