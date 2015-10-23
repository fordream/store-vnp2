package org.vnp.storeapp.fragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.vnp.core.base.BaseItem;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.ImageLoaderUtils;
import com.vnp.core.common.VNPResize;

@SuppressLint("ResourceAsColor")
public class AboutFagment extends BaseFragment {
	private WebView abountWebView;
	private ImageView about_img;

	public AboutFagment() {
		super();
	}

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);

		about_img = (ImageView) v.findViewById(R.id.about_img);
		abountWebView = (WebView) v.findViewById(R.id.abountwebView);
		abountWebView.setWebChromeClient(new WebChromeClient());
		abountWebView.setWebViewClient(new WebViewClient());
		abountWebView.getSettings().setJavaScriptEnabled(true);
		updateUi(null);
		galleryCallBack = new AbountCallBack(getActivity());
		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.executeAsynCallBack(galleryCallBack);
	}

	@Override
	public void onResume() {
		super.onResume();
		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(getView().findViewById(R.id.about_img),
				LayoutParams.MATCH_PARENT, 216 * 3);
	}

	@Override
	public int getResourceLayout() {
		return R.layout.aboutfragment;
	}

	@Override
	public void updateUi(Object result) {
		super.updateUi(result);
		Object object = new AbountCallBack(getActivity()).parseResponse();

		if (object != null && object instanceof BaseItem) {
			BaseItem baseItem = (BaseItem) object;
			String img = baseItem.getString("img");
			String content = baseItem.getString("content");
			if (content != null)
				abountWebView.loadData(content, "text/html; charset=UTF-8",
						null);

			ImageLoaderUtils imageLoaderUtils = ImageLoaderUtils
					.getInstance(getActivity());
			if (img != null) {
				Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.noimg);
				imageLoaderUtils.DisplayImage(img, about_img, bitmap);
			}

		}
	}

	private AbountCallBack galleryCallBack;

	private class AbountCallBack extends BaseCallBack {

		public AbountCallBack(Context context) {
			super(context);
			tag = "AbountCallBack";
			url = StoreAppUtils.URLABOUT;
		}

		@Override
		public void onCallBack(Object arg0) {
			super.onCallBack(arg0);
			updateUi(null);
		}

		@Override
		public Object parseResponse() {
			BaseItem baseItems = null;
			try {
				baseItems = new BaseItem(new JSONObject(getResponse()));
			} catch (JSONException e) {
			}

			return baseItems;
		}

		@Override
		public boolean canSaveResponse(String response) {
			return true;
		}
	}

	@Override
	public String getTitleBar() {
		return null;
	}
}