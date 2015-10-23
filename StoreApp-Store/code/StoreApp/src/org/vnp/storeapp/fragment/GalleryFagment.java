package org.vnp.storeapp.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.base.BasePagerAdapter;
import org.vnp.storeapp.fragment.gallery.GalleryItemFagment;
import org.vnp.storeapp.utils.StoreAppUtils;
import org.vnp.storeapp.utils.StoreAppUtils.TEXTSIZE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnp.core.base.BaseItem;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.ImageLoaderUtils;
import com.vnp.core.common.VNPResize;
import com.vnp.core.v2.BaseAdapter;
import com.vnp.core.view.CustomLinearLayoutView;

@SuppressLint("ResourceAsColor")
public class GalleryFagment extends BaseFragment implements OnItemClickListener {
	private ViewPager viewPager;
	private TextView textView;

	private Gallery gallery;
	private View l1;
	private Button button1, button2;

	public GalleryFagment() {
		super();
	}

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);
		viewPager = (ViewPager) v.findViewById(R.id.imageView1);
		textView = (TextView) v.findViewById(R.id.textView1);
		gallery = (Gallery) v.findViewById(R.id.gallery1);

		button1 = (Button) v.findViewById(R.id.button1);
		button2 = (Button) v.findViewById(R.id.button2);
		l1 = v.findViewById(R.id.l1);
		View.OnClickListener l = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v == button1) {
					updatePosition(-1);
				} else if (v == button2) {
					updatePosition(1);
				}
			}
		};

		button1.setOnClickListener(l);
		button2.setOnClickListener(l);
		galleryCallBack = new GalleryCallBack(getActivity());
		updateUi(null);
		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.executeAsynCallBack(galleryCallBack);
	}

	private void updatePosition(int index) {
		try {
			int newPosition = gallery.getSelectedItemPosition() + index;
			if (newPosition >= 0
					&& newPosition <= gallery.getLastVisiblePosition()) {
				gallery.setSelection(newPosition);
			}
		} catch (Exception exception) {
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(textView, LayoutParams.MATCH_PARENT, 120);
		resize.resizeSacle(l1, LayoutParams.MATCH_PARENT, 218 / 2 * 3);
		resize.resizeSacle(button1, 60 * 2, 60 * 2);
		resize.resizeSacle(button2, 60 * 2, 60 * 2);
		resize.setTextsize(textView, TEXTSIZE.HEADER_ITEM);
		resize.resizeSacle(getView().findViewById(R.id.mid_item), 216 / 2 * 3,
				218 / 2 * 3);

	}

	@Override
	public int getResourceLayout() {
		return R.layout.galleryfragment;
	}

	@Override
	public void updateUi(Object result) {
		super.updateUi(result);
		final List<Object> list = (List<Object>) galleryCallBack
				.parseResponse();

		gallery.setUnselectedAlpha(1.0f);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				BaseItem baseItem = (BaseItem) parent
						.getItemAtPosition(position);
				update(baseItem);
				button1.setVisibility(View.VISIBLE);
				if (gallery.getSelectedItemPosition() == 0) {
					button1.setVisibility(View.GONE);
				}

				button2.setVisibility(View.VISIBLE);
				if (gallery.getSelectedItemPosition() == gallery.getCount() - 1) {
					button2.setVisibility(View.GONE);
				}

				viewPager.setCurrentItem(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		gallery.setAdapter(new MAdapter(getActivity(), list));
		List<Fragment> fragments = new ArrayList<Fragment>();

		for (int i = 0; i < list.size(); i++) {
			GalleryItemFagment galleryItemFagment = new GalleryItemFagment();
			galleryItemFagment.setData(list.get(i));
			fragments.add(galleryItemFagment);
		}

		BasePagerAdapter mPagerAdapter = new BasePagerAdapter(getActivity()
				.getSupportFragmentManager(), fragments);
		viewPager.setAdapter(mPagerAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				update((BaseItem) list.get(arg0));
				gallery.setSelection(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		if (list.size() >= 2) {
			gallery.setSelection(1);
			viewPager.setCurrentItem(1);
		}
	}

	private void update(BaseItem baseItem) {
		textView.setText(baseItem.getString("title"));
		// ImageLoaderUtils.getInstance(getActivity()).DisplayImage(
		// baseItem.getString("img"), img);
	}

	private class MAdapter extends BaseAdapter {

		public MAdapter(Context context, List<Object> lData) {
			super(context, lData);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewItem viewItem = (ViewItem) super.getView(position, convertView,
					parent);
			return viewItem;
		}

		@Override
		public boolean isShowHeader(int position) {
			return false;
		}

		@Override
		public CustomLinearLayoutView getView(Context context, Object data) {
			ViewItem view = new ViewItem(context);
			return view;
		}
	}

	private class ViewItem extends CustomLinearLayoutView {

		public ViewItem(Context context) {
			super(context);
			init(R.layout.galleryitem);
		}

		@Override
		public void showHeader(boolean isShowHeader) {

		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			VNPResize vnpResize = VNPResize.getInstance();
			vnpResize.resizeSacle(findViewById(R.id.galleryitemimageView1),
					218 / 2 * 3, 218 / 2 * 3);
			vnpResize.resizeSacle(
					findViewById(R.id.RelativeLayout1galleryitemimageView1),
					218 / 2 * 3, 218 / 2 * 3);
		}

		@Override
		public void setGender() {
			BaseItem baseItem = (BaseItem) getData();
			String img = baseItem.getString("img");
			Bitmap id = BitmapFactory.decodeResource(getResources(),
					R.drawable.noimg);
			ImageLoaderUtils.getInstance(getContext()).DisplayImage(img,
					(ImageView) findViewById(R.id.galleryitemimageView1), id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vnp.storeapp.fragment.BaseFragment#getTitleBar()
	 */
	@Override
	public String getTitleBar() {
		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
	}

	GalleryCallBack galleryCallBack;

	private class GalleryCallBack extends BaseCallBack {

		public GalleryCallBack(Context context) {
			super(context);
			tag = "GalleryCallBack";
			url = StoreAppUtils.URLGALLERY;
		}

		@Override
		public void onCallBack(Object arg0) {
			super.onCallBack(arg0);
			updateUi(null);
		}

		@Override
		public Object parseResponse() {
			List<BaseItem> baseItems = new ArrayList<BaseItem>();
			try {
				JSONArray array = new JSONArray(getResponse());
				for (int i = 0; i < array.length(); i++) {
					baseItems.add(new BaseItem(array.getJSONObject(i)));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return baseItems;
		}

		@Override
		public boolean canSaveResponse(String response) {
			try {
				JSONArray array = new JSONArray(response);
				return true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
}