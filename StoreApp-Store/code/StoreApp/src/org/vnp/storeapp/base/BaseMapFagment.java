package org.vnp.storeapp.base;

import org.vnp.storeapp.utils.StoreAppUtils;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

public class BaseMapFagment extends SupportMapFragment implements IFunctionCommon {

	public BaseMapFagment() {
		super();
	}

	public void execute() {
		new AsyncTask<String, String, Object>() {
			@Override
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				try {
					updateUi(result);
				} catch (Exception exception) {
				}
			}

			@Override
			protected Object doInBackground(String... params) {
				return loadData();
			}

		}.execute("");
	}

	public void updateUi(Object result) {

	}

	public Object loadData() {
		// if (canRunExecute) {
		// if (type == BlockType.BASE) {
		// return null;
		// } else {
		// BaseCallBack callBack = getBaseCallBackBlock(type);
		// callBack.execute();
		// return callBack.parseResponse();
		// }
		// } else {
		return null;
		// }
	}

	@Override
	public void onResume() {
		super.onResume();
		execute();
	}

	@Override
	public void resize(View view, int res, int width, int height) {
		StoreAppUtils.resizeW960(view.findViewById(res), width, height);
	}

	@Override
	public String getTextFromView(int res) {
		View view = getView().findViewById(res);
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
		setImageUrl((ImageView) getView().findViewById(res), url);
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
		StoreAppUtils.resizeW960(getView().findViewById(id), width, height);
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

		View view = getView().findViewById(res);

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

}