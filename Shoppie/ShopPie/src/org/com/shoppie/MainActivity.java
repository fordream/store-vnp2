package org.com.shoppie;

import java.util.ArrayList;
import java.util.List;

import org.com.shoppie.adapter.MPagerAdapter;
import org.com.shoppie.model.Campaign;
import org.com.shoppie.util.CommonBluetooth;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;
import com.ict.library.service.RestClient;
import com.ict.library.service.RestClient.RequestMethod;
import com.ict.library.util.XMLfunctions;

public class MainActivity extends MBaseActivity implements OnClickListener {
	private CommonBluetooth commonBluetooth;
	private ViewPager viewPager;
	private ImageView imgNearby;
	private ImageView imgPresent;
	private ImageView imgCheckIn;
	private ImageView imgProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		commonBluetooth = new CommonBluetooth(this);
		imgNearby = getView(R.id.imageView1);
		imgNearby.setOnClickListener(this);

		imgPresent = getView(R.id.imageView3);
		imgPresent.setOnClickListener(this);

		imgCheckIn = getView(R.id.imageView4);
		imgCheckIn.setOnClickListener(this);

		imgProduct = getView(R.id.imageView2);
		imgProduct.setOnClickListener(this);
		viewPager = getView(R.id.viewPager);
		viewPager.setAdapter(new MPagerAdapter(this) {
			@Override
			public Object instantiateItem(View collection, final int position) {

				final Campaign merchant = (Campaign) list.get(position);

				ImageView view = new ImageView(collection.getContext());
				view.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(final View v) {
						Intent intent = new Intent(v.getContext(),
								ProductActivity.class);
						intent.putExtra("merchId", merchant.getMerchId());
						startActivity(intent);
					}
				});

				LinearLayout linearLayout = new LinearLayout(collection
						.getContext());
				linearLayout.setOrientation(1);

				linearLayout.setGravity(Gravity.CENTER);
				linearLayout.addView(view);

				ViewGroup.LayoutParams params = view.getLayoutParams();
				params.height = 100;
				params.width = 200;

				view.setLayoutParams(params);

				ImageLoader imageLoader = new ImageLoader(collection
						.getContext(), R.drawable.ic_launcher);
				imageLoader.DisplayImage(merchant.getCampaignImage(),
						MainActivity.this, view);

				TextView textView = new TextView(collection.getContext());
				linearLayout.addView(textView);
				textView.setGravity(Gravity.CENTER);

				textView.setText(merchant.getCampaignName());
				((ViewPager) collection).addView(linearLayout);
				return linearLayout;
			}
		});

		if (!commonBluetooth.isSupportBlueTooth()) {
			toast("Bluetooth not support");
		} else if (!commonBluetooth.isEnable()) {
			commonBluetooth.showPopupBluetoothSetting(1000);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onStart() {
		super.onStart();
		execute();
	}

	@Override
	protected Object _doInBackground() {
		RestClient restClient = new RestClient(
				"http://ws.shoppie.com.vn/index.php/api/webservice/merchcampaigns");
		try {
			restClient.execute(RequestMethod.GET);
		} catch (Exception e) {
		}
		return restClient;
	}

	@Override
	protected void _onPostExecute(Object data) {
		if (((RestClient) data).getResponseCode() == 200) {
			dataStore.save("data", ((RestClient) data).getResponse());
		}
		List<Object> list = new ArrayList<Object>();
		String dataXml = dataStore.get("data", "");
		Document document = XMLfunctions.XMLfromString(dataXml);
		if (document != null) {
			NodeList nodes = document.getElementsByTagName("Campaign");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				Campaign merchant = new Campaign(e);
				list.add(merchant);
			}
		}

		((MPagerAdapter) viewPager.getAdapter()).addData(list);
		((MPagerAdapter) viewPager.getAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		if (v == imgNearby) {
			startActivity(new Intent(this, MMapActivity.class));
		} else if (v == imgPresent) {
			startActivity(new Intent(this, PresentActivity.class));
		} else if (imgCheckIn == v) {
			startActivity(new Intent(this, CheckInActivity.class));
		} else if (imgProduct == v) {
			startActivity(new Intent(this, ProductActivity.class));
		}
	}
}