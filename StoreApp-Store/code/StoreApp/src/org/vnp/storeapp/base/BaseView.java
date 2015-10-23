package org.vnp.storeapp.base;

import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnp.core.view.CustomLinearLayoutView;

public class BaseView extends CustomLinearLayoutView implements IFunctionCommon {
	private long time = 2000;
	private long maxTime = 3000;

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public void resetTimeAnimation() {
		time = maxTime;
	}

	public BaseView(Context context) {
		super(context);
	}

	public BaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setGender() {

	}

	private Animation animation;

	public void exeEnimation(final View view) {
		final Handler handler = new Handler() {
			@Override
			public void dispatchMessage(Message msg) {
				super.dispatchMessage(msg);
				hiddenEnimation(view);
			}
		};

		if (animation != null) {
			return;
		}

		if (view.getVisibility() == GONE) {
			animation = new TranslateAnimation(0, 0, -view.getHeight(), 0);
			animation.setDuration(1000l);
			animation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {

				}

				@Override
				public void onAnimationRepeat(Animation animation) {

				}

				@Override
				public void onAnimationEnd(Animation animation1) {
					// view.setVisibility(VISIBLE);
					animation = null;
					// time = 2000;
					resetTimeAnimation();
					new Thread(new Runnable() {
						@Override
						public void run() {
							while (time > 0) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
								}

								time = time - 100;
							}
							handler.sendEmptyMessage(0);
						}
					}).start();
				}
			});
			view.setVisibility(VISIBLE);
			view.startAnimation(animation);
		}

	}

	public void hiddenEnimation(final View view) {
		if (animation != null) {
			return;
		}
		boolean isShow = view.getVisibility() == VISIBLE;
		if (isShow) {
			animation = new TranslateAnimation(0, 0, 0, -view.getHeight());
			animation.setDuration(1000l);
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {

				}

				@Override
				public void onAnimationRepeat(Animation animation) {

				}

				@Override
				public void onAnimationEnd(Animation animation1) {
					view.setVisibility(GONE);
					animation = null;
				}
			});
			view.startAnimation(animation);
		}
	}

	/**
	 * 
	 * @param id
	 * @param resid
	 */
	public void setBackgroundResource(int id, int resid) {
		getView(id).setBackgroundResource(resid);
	}

	/**
	 * 
	 * @param v
	 * @param resid
	 */
	public void setBackgroundResource(View v, int resid) {
		v.setBackgroundResource(resid);
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
		StoreImageLoaderUtils.getInstance(imageView.getContext()).displayImagege(imageView, url);
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

	public void updatePosition(int position) {

	}

	@Override
	public void showHeader(boolean isShowHeader) {
		
	}

}