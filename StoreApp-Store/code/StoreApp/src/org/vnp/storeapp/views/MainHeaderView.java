package org.vnp.storeapp.views;

import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseView;
import org.vnp.storeapp.utils.StoreAppUtils;
import org.vnp.storeapp.utils.StoreAppUtils.TEXTSIZE;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.vnp.core.common.VNPResize;

public class MainHeaderView extends BaseView {

	private int resText = R.id.mainheaderview_text;

	public MainHeaderView(Context context) {
		super(context);
		init(R.layout.mainheaderview);
	}

	public MainHeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.mainheaderview);
	}

	@Override
	public void init(int res) {
		super.init(res);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if(StoreAppUtils.ENALBLE){
			VNPResize.getInstance().resizeSacle(this,android.view.ViewGroup.LayoutParams.MATCH_PARENT, 50 * StoreAppUtils.BASE_SCALE);
			VNPResize.getInstance().setTextsize(findViewById(resText),TEXTSIZE.HEADER);
			VNPResize.getInstance().resizeSacle(findViewById(R.id.mainheaderview_img),  50 * StoreAppUtils.BASE_SCALE,  50 * StoreAppUtils.BASE_SCALE);
		}
	}

	@Override
	public void setGender() {
	}

	public void setTextHeader(String header, String url) {
		setText(resText, header);

		if (!StoreAppUtils.isBlank(url)) {
			setImageUrl(R.id.mainheaderview_img, url);
		}
	}

	public void clear() {
		setTextHeader("", null);
		findViewById(resText).setBackgroundResource(R.drawable.tranfer);

	}

	public void update(String headerTitle, String headerBackgroundCorlor,
			BlockType type, String url) {
		setText(resText, headerTitle);
		if (headerBackgroundCorlor != null
				&& headerBackgroundCorlor.startsWith("#")) {
			findViewById(R.id.mainheaderview_content).setBackgroundColor(
					Color.parseColor(headerBackgroundCorlor));
		}

		ImageView imageView = (ImageView) findViewById(R.id.mainheaderview_img);

		if (imageView != null) {
			if (BlockType.HOME == type) {
				imageView.setBackgroundResource(R.drawable.home);
			} else {
				setImageUrl(R.id.mainheaderview_img, url);
			}
		}
	}

	public void showIcon(boolean b) {
		ImageView imageView = (ImageView) findViewById(R.id.mainheaderview_img);
		if(b){
			imageView.setVisibility(View.VISIBLE);
		}else{
			imageView.setVisibility(GONE);
		}
	}
}