/**
 * 
 */
package org.vnp.storeapp.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.vnp.storeapp.R;
import org.vnp.storeapp.VNPLocationUtils;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.fragment.contact.ContactMapFragment;
import org.vnp.storeapp.utils.StoreAppUtils;
import org.vnp.storeapp.utils.StoreAppUtils.TEXTSIZE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnp.core.base.BaseItem;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

/**
 * @author teemo
 * 
 */
@SuppressLint("ValidFragment")
public class ContactFragment extends BaseFragment {
	private LinearLayout linearLayout;

	/**
	 * 
	 */
	public ContactFragment() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vnp.storeapp.base.BaseFragment#getResourceLayout()
	 */
	@Override
	public int getResourceLayout() {
		return R.layout.contactfragment;
	}

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);
	}

	@Override
	public void onResume() {
		super.onResume();

		linearLayout = (LinearLayout) getView().findViewById(R.id.listView1);
		changeFragemtn(R.id.map_content_main, new ContactMapFragment());

		VNPResize vnpResize = VNPResize.getInstance();
		// vnpResize.resizeSacle(getView().findViewById(R.id.map_content),
		// LayoutParams.MATCH_PARENT, 230 * 3);

		vnpResize.resizeSacle(getView().findViewById(R.id.map_content),
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		vnpResize.resizeSacle(getView().findViewById(R.id.button1), 40 * 3,
				40 * 3);
		getView().findViewById(R.id.button1).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intentDirections = new Intent(
								android.content.Intent.ACTION_VIEW);

						String url = "http://maps.google.com/maps?saddr=%s,%s&daddr=%s,%s&mode=driving";

						Location a = VNPLocationUtils.getInstance().lastKnownLocation;
						String lat = "";
						String lon = "";
						if (a != null) {
							lat = a.getLatitude() + "";
							lon = a.getLongitude() + "";
						}

						url = String.format(url, lat, lon, "0.1", "0.3");

						intentDirections = new Intent(
								android.content.Intent.ACTION_VIEW, Uri
										.parse(url));
						startActivity(intentDirections);
					}
				});

		updateUi(null);
		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.executeAsynCallBack(new ContactCallBack(getActivity()));
	}

	@Override
	public void updateUi(Object result) {
		List<Object> list = (List<Object>) new ContactCallBack(getActivity())
				.parseResponse();
		linearLayout.removeAllViews();

		for (int i = 0; i < list.size(); i++) {
			ContactItemView contactItemView = new ContactItemView(getActivity());
			contactItemView.setData(list.get(i));

			linearLayout.addView(contactItemView);
			contactItemView.setGender();
		}
		// ((ListView) getView().findViewById(R.id.listView1))
		// .setSelector(R.drawable.tranfer);
		// ((ListView) getView().findViewById(R.id.listView1))
		// .setAdapter(new Adapter(getActivity(), list));
	}

	/*
	 * private class Adapter extends BaseAdapter { public Adapter(Context
	 * context, List<Object> lData) { super(context, lData); }
	 * 
	 * @Override public boolean isShowHeader(int position) { return false; }
	 * 
	 * @Override public CustomLinearLayoutView getView(Context context, Object
	 * data) { return new ContactItemView(context); }
	 * 
	 * }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vnp.storeapp.base.BaseFragment#getTitleBar()
	 */
	@Override
	public String getTitleBar() {
		return null;
	}

	/*
	 * private class ContactMapFragment extends MapFagment { public
	 * ContactMapFragment() { super(); }
	 * 
	 * @Override public void onResume() { super.onResume(); addMaker(0.1, 0.3,
	 * null); } }
	 */
	private class ContactItemItemView extends CustomLinearLayoutView {

		public ContactItemItemView(Context context) {
			super(context);
			init(R.layout.contact_item_link);
			this.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (getData() != null) {
						String url = getData().toString();
						if (!url.startsWith("http://")) {
							url = "http://" + url;
						}

						CommonAndroid.callWeb(getContext(), url);
					}
				}
			});
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			VNPResize resize = VNPResize.getInstance();

			resize.setTextsize(findViewById(R.id.contact_item_linktextView1),
					TEXTSIZE.HEADER_ITEM_SUB);
		}

		@Override
		public void showHeader(boolean isShowHeader) {

		}

		@Override
		public void setGender() {
			((TextView) findViewById(R.id.contact_item_linktextView1))
					.setText(Html.fromHtml("<u>" + getData() + "</u>"));
		}

	}

	private class ContactItemView extends CustomLinearLayoutView implements
			OnClickListener {

		public ContactItemView(Context context) {
			super(context);
			init(R.layout.item_contact);
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			VNPResize resize = VNPResize.getInstance();
			resize.resizeSacle(findViewById(R.id.imageView1), 22 * 3, 22 * 3);
			resize.resizeSacle(findViewById(R.id.contact_item_content),
					200 * 3, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.setTextsize(findViewById(R.id.textView1),
					TEXTSIZE.HEADER_ITEM_SUB);
			resize.setTextsize(findViewById(R.id.textView2),
					TEXTSIZE.HEADER_ITEM_SUB);
			resize.setTextsize(findViewById(R.id.textView3),
					TEXTSIZE.HEADER_ITEM_SUB);
		}

		@Override
		public void showHeader(boolean isShowHeader) {

		}

		@Override
		public void setGender() {
			BaseItem baseItem = (BaseItem) getData();

			int res = adapterImg(baseItem.getString("img"));

			if (res != 0) {
				findViewById(R.id.imageView1).setBackgroundResource(res);
			}

			findViewById(R.id.textView1).setVisibility(View.GONE);
			findViewById(R.id.textView2).setVisibility(View.GONE);
			findViewById(R.id.textView3).setVisibility(View.GONE);

			((TextView) findViewById(R.id.textView1)).setText(baseItem
					.getString("name"));
			((TextView) findViewById(R.id.textView2)).setText(baseItem
					.getString("name"));

			/*
			 * ((TextView) findViewById(R.id.textView3)).setText(Html
			 * .fromHtml(baseItem.getString("name")));
			 */
			setOnClickListener(null);
			if ("none".equals(baseItem.getString("type"))) {
				findViewById(R.id.textView1).setVisibility(View.VISIBLE);
			} else if ("phone".equals(baseItem.getString("type"))) {
				findViewById(R.id.textView1).setVisibility(View.VISIBLE);
				setOnClickListener(this);
			} else if ("email".equals(baseItem.getString("type"))) {
				findViewById(R.id.textView1).setVisibility(View.VISIBLE);
				setOnClickListener(this);
			} else if ("link".equals(baseItem.getString("type"))) {
				findViewById(R.id.textView3).setVisibility(View.VISIBLE);

				// config

				LinearLayout linearLayout = (LinearLayout) findViewById(R.id.textView3);
				linearLayout.removeAllViews();
				try {
					JSONArray array = new JSONArray(baseItem.getString("link"));
					for (int i = 0; i < array.length(); i++) {
						ContactItemItemView itemView = new ContactItemItemView(
								getContext());
						itemView.setData(array.getString(i));
						linearLayout.addView(itemView);
						itemView.setGender();
					}
				} catch (JSONException e) {
				}

			}
		}

		private int adapterImg(String str) {
			if ("1".equals(str))
				return R.drawable.contact1;
			if ("2".equals(str))
				return R.drawable.contact2;
			if ("3".equals(str))
				return R.drawable.contact3;
			if ("4".equals(str))
				return R.drawable.contact4;
			if ("5".equals(str))
				return R.drawable.contact5;
			return 0;
		}

		@Override
		public void onClick(View v) {
			BaseItem baseItem = (BaseItem) getData();
			String name = baseItem.getString("name");
			String message = "";
			try {
				if ("phone".equals(baseItem.getString("type"))) {
					message = "can not call phone : " + name;
					CommonAndroid.callPhone(getContext(), name);
				} else if ("email".equals(baseItem.getString("type"))) {
					message = "can not send mail : " + name;
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("message/rfc822");
					i.putExtra(Intent.EXTRA_EMAIL, new String[] { name });
					i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
					i.putExtra(Intent.EXTRA_TEXT, "body of email");
					startActivity(Intent.createChooser(i, "Send mail..."));
				}
			} catch (Exception exception) {
				CommonAndroid.toast(getContext(), message);
			}
		}
	}

	private class ContactCallBack extends BaseCallBack {

		public ContactCallBack(Context context) {
			super(context);
			tag = "ContactCallBack";
			url = StoreAppUtils.URLCONTACT;
		}

		@Override
		public void onCallBack(Object arg0) {
			super.onCallBack(arg0);
			updateUi(null);
		}

		@Override
		public Object parseResponse() {
			List<BaseItem> baseItems = new ArrayList<BaseItem>();
			try {
				JSONArray array = new JSONArray(getResponse());
				for (int i = 0; i < array.length(); i++) {
					baseItems.add(new BaseItem(array.getJSONObject(i)));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return baseItems;
		}

		@Override
		public boolean canSaveResponse(String response) {
			try {
				JSONArray array = new JSONArray(response);
				return true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
}