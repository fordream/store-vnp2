package org.core.store.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public abstract class StoreBaseFragment extends Fragment implements StoreIFunctionCommon {
	private boolean canRunExecute = false;
	private Object data;

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setCanRunExecute(boolean canRunExecute) {
		this.canRunExecute = canRunExecute;
	}

	public StoreBaseFragment() {
		super();
	}

	public void updateUi(Object result) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = new TextView(getActivity());
		if (getResourceLayout() != 0)
			v = inflater.inflate(getResourceLayout(), container, false);
		callBack = this.getClass().getName() + System.currentTimeMillis();
		onInitCreateView(v);
		return v;
	}

	public void makeText(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}

	public void makeText(int res) {
		Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
	}

	public void clearText(int res) {
		((EditText) getView().findViewById(res)).setText("");
	}

	public String getStrText(int edittext2) {
		return ((EditText) getView().findViewById(edittext2)).getText().toString();
	}

	public void onInitCreateView(View v) {

	}

	public abstract int getResourceLayout();

	public abstract String getTitleBar();

	private String callBack;

	public String getCallBackStr() {
		return callBack;
	}

	public boolean needBack() {
		return false;
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