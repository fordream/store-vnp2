/**
 * 
 */
package org.vnp.storeapp.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.base.BaseView;
import org.vnp.storeapp.utils.StoreAppUtils;
import org.vnp.storeapp.utils.StoreAppUtils.TEXTSIZE;
import org.vnp.storeapp.views.MainHeaderView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

/**
 * @author tvuong1pc
 * 
 */
public class ProfolioFagment extends BaseFragment implements
		OnItemClickListener {

	PortfolioCallBack newCallBack;

	public ProfolioFagment() {
		super();
		setType(BlockType.PORTFOLIO);
	}

	private ListView listView;

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);
		listView = (ListView) v.findViewById(R.id.listView1);

		MainHeaderView headerView = new MainHeaderView(getActivity());
		headerView.clear();
		listView.addHeaderView(headerView);
		newCallBack = new PortfolioCallBack(getActivity());
		updateUi(null);

		new ExeCallBack().executeAsynCallBack(newCallBack);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int getResourceLayout() {
		return R.layout.newfragment;
	}

	@Override
	public void updateUi(Object result) {
		super.updateUi(result);
		List<Object> list = new ArrayList<Object>();

		if (newCallBack != null) {
			try {
				JSONArray jsonObject = new JSONObject(newCallBack.getResponse())
						.getJSONArray("portfolios");
				for (int i = 0; i < jsonObject.length(); i++) {
					try {
						ItemPortfolio itemNew = new ItemPortfolio(
								jsonObject.getJSONObject(i));
						list.add(itemNew);
					} catch (Exception exception) {

					}
				}
			} catch (JSONException e) {
			}
		}

		if (listView != null) {
			listView.setAdapter(new com.vnp.core.common.BaseAdapter(
					getActivity(), list, new CommonGenderView() {

						@Override
						public CustomLinearLayoutView getView(Context arg0,
								Object arg1) {
							return new ItemPortfolioiew(arg0);
						}
					}));
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
	}

	private class ItemPortfolio {
		String title;
		String subtitle;
		String backgroundCorlor;
		String link;

		public ItemPortfolio(JSONObject jsonObject) throws Exception {
			title = jsonObject.getString("title");
			subtitle = jsonObject.getString("sub_title");
			backgroundCorlor = jsonObject.getString("background-color");
			link = jsonObject.getString("link");
		}
	}

	private class PortfolioCallBack extends BaseCallBack {

		public PortfolioCallBack(Context context) {
			super(context);
			tag = "PortfolioCallBack";
			url = StoreAppUtils.URLPORTFOLIO;

		}

		@Override
		public void onCallBack(Object arg0) {
			super.onCallBack(arg0);
			updateUi(null);
		}

		@Override
		public Object parseResponse() {
			return null;
		}
	}

	private class ItemPortfolioiew extends BaseView implements OnClickListener {

		/**
		 * @param context
		 */
		public ItemPortfolioiew(Context context) {
			super(context);
			init(R.layout.newitemview);
		}

		/**
		 * @param context
		 * @param attrs
		 */
		public ItemPortfolioiew(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(R.layout.newitemview);
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			VNPResize resize = VNPResize.getInstance();
			resize.resizeSacle(findViewById(R.id.image_new_item_content),
					android.view.ViewGroup.LayoutParams.MATCH_PARENT, 10 * 3);
			resize.resizeSacle(findViewById(R.id.newitemview_header),
					LayoutParams.MATCH_PARENT, 80 * 3);
			resize.resizeSacle(findViewById(R.id.img_up_down), 20 * 3, 20 * 3);

			resize.resizeSacle(findViewById(R.id.newitemview_txt1), 260 * 3,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.newitemview_txt2), 260 * 3,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.setTextsize(findViewById(R.id.newitemview_txt1),
					TEXTSIZE.HEADER_ITEM);
			resize.setTextsize(findViewById(R.id.newitemview_txt2),
					TEXTSIZE.HEADER_ITEM_SUB);
		}

		@Override
		public void init(int res) {
			super.init(res);
			findViewById(R.id.newitemview_header).setOnClickListener(this);
			WebView webView = (WebView) findViewById(R.id.webView1);
			webView.setWebChromeClient(new WebChromeClient());
			webView.setWebViewClient(new WebViewClient());
			webView.getSettings().setJavaScriptEnabled(true);
			findViewById(R.id.image_new_item_content_re).setVisibility(
					View.GONE);
		}

		@Override
		public void setGender() {
			super.setGender();
			ItemPortfolio itemNew = (ItemPortfolio) getData();
			WebView webView = (WebView) findViewById(R.id.webView1);
			webView.loadUrl(itemNew.link);
			setText(R.id.newitemview_txt1, itemNew.title);
			setText(R.id.newitemview_txt2, itemNew.subtitle);

			String backgroundColor = itemNew.backgroundCorlor;

			if (!StoreAppUtils.isBlank(backgroundColor)
					&& backgroundColor.startsWith("#")) {
				findViewById(R.id.newitemview_header).setBackgroundColor(
						Color.parseColor(backgroundColor));
			}
		}

		@Override
		public void onClick(View v) {
			if (R.id.newitemview_header == v.getId()) {
				View webView = findViewById(R.id.image_new_item_content_re);
				
				if (vChecked == null) {
					vChecked = this;
				} else if (vChecked == this) {

				} else {
					((ItemPortfolioiew) vChecked).gone();
					vChecked = this;
				}

				
				if (webView.getVisibility() == View.VISIBLE) {
					webView.setVisibility(View.GONE);
					findViewById(R.id.img_up_down).setBackgroundResource(
							R.drawable.down);
				} else {
					webView.setVisibility(View.VISIBLE);
					findViewById(R.id.img_up_down).setBackgroundResource(
							R.drawable.up);

					ItemPortfolio itemNew = (ItemPortfolio) getData();
					WebView _webView = (WebView) findViewById(R.id.webView1);
					final String mimeType = "text/html";
					final String encoding = "UTF-8";
					_webView.loadDataWithBaseURL("", StoreAppUtils.decodeHtml(itemNew.link), mimeType, encoding, "");
					//_webView.loadUrl(itemNew.link);
				}
			}
		}
		
		private void gone() {
			View webView = findViewById(R.id.image_new_item_content_re);
			webView.setVisibility(View.GONE);
			findViewById(R.id.img_up_down).setBackgroundResource(
					R.drawable.down);
		}
	}
	
	private View vChecked = null;
}
