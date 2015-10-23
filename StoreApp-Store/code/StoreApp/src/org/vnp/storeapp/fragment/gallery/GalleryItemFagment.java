package org.vnp.storeapp.fragment.gallery;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseFragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;

import com.vnp.core.base.BaseItem;
import com.vnp.core.common.ImageLoaderUtils;

@SuppressLint("ResourceAsColor")
public class GalleryItemFagment extends BaseFragment {

	public GalleryItemFagment() {
		super();
	}

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);
		BaseItem baseItem = (BaseItem) getData();
		ImageLoaderUtils.getInstance(getActivity()).DisplayImage(
				baseItem.getString("img"),
				(ImageView) v.findViewById(R.id._imageView1));
	}

	@Override
	public int getResourceLayout() {
		return R.layout.galleryitemfragment;
	}

	@Override
	public String getTitleBar() {
		return null;
	}

}