/**
 * 
 */
package com.xing.joy.play.abs;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * @author truongvv
 * 
 */
public abstract class AbsAnimationListener implements AnimationListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.animation.Animation.AnimationListener#onAnimationEnd(android
	 * .view.animation.Animation)
	 */
	@Override
	public abstract void onAnimationEnd(Animation animation);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.animation.Animation.AnimationListener#onAnimationRepeat(
	 * android.view.animation.Animation)
	 */
	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.animation.Animation.AnimationListener#onAnimationStart(android
	 * .view.animation.Animation)
	 */
	@Override
	public void onAnimationStart(Animation animation) {
	}

}
