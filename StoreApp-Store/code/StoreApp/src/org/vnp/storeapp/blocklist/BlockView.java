/**
 * 
 */
package org.vnp.storeapp.blocklist;

import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseView;
import org.vnp.storeapp.utils.StoreAppUtils;
import org.vnp.storeapp.utils.StoreAppUtils.TEXTSIZE;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.vnp.core.common.VNPResize;

/**
 * @author teemo
 * 
 */
public class BlockView extends BaseView {

	public BlockView(Context context) {
		super(context);
		init(R.layout.blockview);
	}

	public BlockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.blockview);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(this, 120 * StoreAppUtils.BASE_SCALE , 150 * StoreAppUtils.BASE_SCALE );
		resize.resizeSacle(findViewById(R.id.imageView_about_background),120 * StoreAppUtils.BASE_SCALE, 120 * StoreAppUtils.BASE_SCALE);
		resize.resizeSacle(findViewById(R.id.blockview_img_background),120 * StoreAppUtils.BASE_SCALE, 120 * StoreAppUtils.BASE_SCALE);
		
		resize.resizeSacle(findViewById(R.id.imageView_about), 50 * StoreAppUtils.BASE_SCALE,50 * StoreAppUtils.BASE_SCALE);
		resize.resizeSacle(findViewById(R.id.textView1), 120 * StoreAppUtils.BASE_SCALE,30 * StoreAppUtils.BASE_SCALE);
		resize.setTextsize(findViewById(R.id.textView1), TEXTSIZE.BLOCKMAIN);
	}

	@Override
	public void setGender() {
		ImageView img = getView(R.id.imageView_about);
		Block bin = (Block) getData();
		if (bin.getType() != BlockType.NONE) {
			String url = bin.getImg();

			if (url == null || (url != null && url.trim().equals("")))
				url = bin.getSub_icon();

			setImageUrl(img, url);
			setText(R.id.textView1, bin.getHeader());

			View view = findViewById(R.id.blockview_img_background);

			if (view != null) {
				String backgroundColor = bin.getBackgoundColor();
				if (backgroundColor != null && backgroundColor.startsWith("#")) {
					view.setBackgroundColor(Color.parseColor(backgroundColor));
				}

				String opacity = bin.getOpacity();
				try {
					float f = Float.parseFloat(opacity);
					Animation animation = new AlphaAnimation(1, f);
					animation.setFillAfter(true);
					view.setAnimation(animation);
				} catch (Exception exception) {
				}

				String backgroundUrl = bin.getBackgroundImg();

				if (!StoreAppUtils.isBlank(backgroundUrl)) {
					setImageUrl(R.id.blockview_img_background, backgroundUrl);
				}
			}
		}
	}

	public void hidden() {
		setVisibility(INVISIBLE);
	}
}