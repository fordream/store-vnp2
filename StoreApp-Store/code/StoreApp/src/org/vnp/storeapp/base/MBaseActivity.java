package org.vnp.storeapp.base;

import org.vnp.storeapp.R;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vnp.core.common.VNPResize;

public class MBaseActivity extends FragmentActivity implements
		AnimationListener, OnPageChangeListener, IFunctionCommon {
	protected VNPResize resize = VNPResize.getInstance();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		resize.init(this, StoreAppUtils.BASE_WIDTH, StoreAppUtils.BASE_HEIGHT,
				new VNPResize.ICompleteInit() {
					@Override
					public void complete() {

					}
				}, null);
	}

	public void changeFragemtn(int r_id_content_frame, Fragment rFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(r_id_content_frame, rFragment);
		ft.commit();
	}

	@Override
	public void onAnimationStart(Animation animation) {

	}

	@Override
	public void onAnimationEnd(Animation animation) {

	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {

	}

	public void updateUI() {
	}

	public void makeText(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void resize(View view, int res, int width, int height) {
		StoreAppUtils.resizeW960(view.findViewById(res), width, height);
	}

	@Override
	public String getTextFromView(int res) {
		View view = findViewById(res);
		if (view instanceof TextView) {
			return ((TextView) view).getText().toString();
		}

		return null;
	}

	@Override
	public String getTextFromView(View view, int res) {
		View _view = view.findViewById(res);
		if (_view instanceof TextView) {
			return ((TextView) _view).getText().toString();
		}

		return null;
	}

	@Override
	public void setImageUrl(ImageView imageView, String url) {
		StoreImageLoaderUtils.getInstance(imageView.getContext())
				.displayImagege(imageView, url);
	}

	@Override
	public void setImageUrl(int res, String url) {
		setImageUrl((ImageView) findViewById(res), url);
	}

	@Override
	public void setImageUrl(View v, int res, String url) {
		setImageUrl((ImageView) v.findViewById(res), url);
	}

	/**
	 * 
	 * @param id
	 * @param width
	 * @param height
	 */
	public void resize(int id, int width, int height) {
		StoreAppUtils.resizeW960(findViewById(id), width, height);
	}

	/**
	 * 
	 * @param view
	 * @param width
	 * @param height
	 */
	public void resize(View view, int width, int height) {
		StoreAppUtils.resizeW960(view, width, height);
	}

	public void setText(int res, String text) {

		View view = findViewById(res);

		if (view instanceof TextView) {
			((TextView) view).setText(text);
		} else if (view instanceof EditText) {
			((EditText) view).setText(text);
		}

	}

	public void setText(View v, int textview1, String text) {
		View view = v.findViewById(textview1);

		if (view instanceof TextView) {
			((TextView) view).setText(text);
		} else if (view instanceof EditText) {
			((EditText) view).setText(text);
		}
	}

	public void showloaderview(boolean show) {
		View v = findViewById(R.id.loaderview);
		if (v != null)
			v.setVisibility(show ? View.VISIBLE : View.GONE);
	}
}