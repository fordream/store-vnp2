/**
 * 
 */
package org.vnp.storeapp.block.gallery;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.vnp.core.common.ImageLoader;

/**
 * @author teemo
 * 
 */
public class GalleryView extends BaseView {
	// private ImageLoader imageLoader;

	public GalleryView(Context context, ImageLoader imageLoader) {
		super(context);
		init(R.layout.galleryview);
	}

	public GalleryView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.galleryview);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (width == 0) {
			rezise(this, 480, 480);
			rezise(getView(R.id.gracenter), 480, 480);
			rezise(getView(R.id.imageView_about), 480, 480);
		} else {
			rezise(getView(R.id.gracenter), width, width);
			rezise(getView(R.id.imageView_about), width, width);
		}
	}

	@Override
	public void setGender() {
		String url = getData().toString();
		ImageView imageView = getView(R.id.imageView_about);
		// imageLoader.DisplayImage(url, imageView);
		setImageUrl(imageView, url);
	}

	int width = 0;

	public void setLayout(int width) {
		this.width = width;
		// rezise(this, width, width);
		rezise(getView(R.id.gracenter), width, width);
		rezise(getView(R.id.imageView_about), width, width);
	}
}
