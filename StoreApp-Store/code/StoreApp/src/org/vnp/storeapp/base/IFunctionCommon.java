package org.vnp.storeapp.base;

import android.view.View;
import android.widget.ImageView;

public interface IFunctionCommon {
	/**
	 * 
	 * @param id
	 * @param width
	 * @param height
	 */
	public void resize(int id, int width, int height);

	/**
	 * 
	 * @param view
	 * @param width
	 * @param height
	 */
	public void resize(View view, int width, int height);

	/**
	 * 
	 * @param view
	 * @param res
	 * @param width
	 * @param height
	 */
	public void resize(View view, int res, int width, int height);

	/**
	 * 
	 * @param res
	 * @return
	 */
	public String getTextFromView(int res);

	/**
	 * 
	 * @param view
	 * @param res
	 * @return
	 */
	public String getTextFromView(View view, int res);

	/**
	 * 
	 * @param res
	 * @param text
	 */
	public void setText(int res, String text);

	/**
	 * 
	 * @param v
	 * @param res
	 * @param text
	 */
	public void setText(View v, int res, String text);

	/**
	 * 
	 * @param imageView
	 * @param url
	 */
	public void setImageUrl(ImageView imageView, String url);

	/**
	 * 
	 * @param res
	 * @param url
	 */
	public void setImageUrl(int res, String url);

	/**
	 * 
	 * @param v
	 * @param res
	 * @param url
	 */
	public void setImageUrl(View v, int res, String url);
}