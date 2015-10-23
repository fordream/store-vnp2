package org.core.store.app;

import android.content.Context;
import android.widget.ImageView;

import com.vnp.core.common.ImageLoaderUtils;

public class StoreImageLoaderUtils {
	private static StoreImageLoaderUtils storeImageLoaderUtils;
	private ImageLoaderUtils imageLoader;

	private StoreImageLoaderUtils(Context context) {
		imageLoader = ImageLoaderUtils.getInstance(context);
	}

	public static StoreImageLoaderUtils getInstance(Context context) {
		StoreImageLoaderUtils _storeImageLoaderUtils = storeImageLoaderUtils == null ? (storeImageLoaderUtils = new StoreImageLoaderUtils(context))
				: storeImageLoaderUtils;
		_storeImageLoaderUtils.init(context);
		return _storeImageLoaderUtils;
	}

	private void init(Context context) {
		if (imageLoader == null) {
			imageLoader = ImageLoaderUtils.getInstance(context);
		}
	}

	public void displayImagege(ImageView imageView, String url) {
		imageLoader.DisplayImage(url, imageView);
	}

}