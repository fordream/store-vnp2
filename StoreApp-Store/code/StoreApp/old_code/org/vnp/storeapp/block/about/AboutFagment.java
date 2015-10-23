/**
 * 
 */
package org.vnp.storeapp.block.about;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.service.StoreAppService.BlockType;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.vnp.core.common.ImageLoader;

/**
 * @author tvuong1pc
 * 
 */
public class AboutFagment extends BaseFragment {

	/**
	 * 
	 */
	public AboutFagment() {
		super();
		setType(BlockType.BLOCK_ABOUT);
		setCanRunExecute(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vnp.storeapp.fragment.BaseFragment#getResourceLayout()
	 */
	@Override
	public int getResourceLayout() {
		return R.layout.aboutfragment;
	}


	@Override
	public void updateUi(Object result) {
		super.updateUi(result);

	}

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);
		//StoreAppUtils.resizeW960(v.findViewById(R.id.icon_company_bor), 480, 480);
	//	StoreAppUtils.resizeW960(v.findViewById(R.id.imageView_about), 370, 370);
		//StoreAppUtils.setTextSize(v.findViewById(R.id.textView1), 30);

		About about = (About) new AboutCallBack(getActivity()).parseResponse();
		if (about != null) {
			//setImageUrl((ImageView) v.findViewById(R.id.imageView_about), about.img);
		//	setText(v, R.id.textView1, about.text);
			WebView view = (WebView)v.findViewById(R.id.webView1);
			view.setWebChromeClient(new WebChromeClient());
			view.setWebViewClient(new WebViewClient());
			view.loadUrl(about.text);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vnp.storeapp.fragment.BaseFragment#getTitleBar()
	 */
	@Override
	public String getTitleBar() {
		return null;
	}

}
