package org.com.shoppie;

import java.util.ArrayList;
import java.util.List;

import org.com.shoppie.adapter.MBaseAdapter;
import org.com.shoppie.model.MerchantCategory;
import org.com.shoppie.model.Store;
import org.com.shoppie.util.CommonURL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.google.android.maps.MapView;
import com.ict.library.database.DataStore;
import com.ict.library.service.RestClient;
import com.ict.library.service.RestClient.RequestMethod;
import com.ict.library.util.XMLfunctions;

public class MMapActivity extends com.google.android.maps.MapActivity implements
		OnItemClickListener {
	private RadioGroup radioGroup;
	private ListView listView;
	private DataStore dataStore;
	private MapView mapView;
	private String storeId;
	private String merchantCategoryId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setVisibility(View.GONE);

		dataStore = DataStore.getInstance();
		dataStore.init(this);

		listView = (ListView) findViewById(R.id.listView1);
		List<Object> objects = new ArrayList<Object>();
		listView.setAdapter(new MBaseAdapter(this, objects) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				if (convertView == null) {
					convertView = new TextView(parent.getContext());
				}

				Object object = getItem(position);
				if (object instanceof Store) {
					((TextView) convertView).setText(((Store) object)
							.getStoreName());
				} else {
					((TextView) convertView)
							.setText(((MerchantCategory) object)
									.getMerchCatName());
				}

				return convertView;
			}
		});

		listView.setOnItemClickListener(this);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				update();
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		showDialog(0);
		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... params) {
				RestClient client = new RestClient(CommonURL.URL_DANHMUC);

				try {
					client.execute(RequestMethod.GET);
				} catch (Exception e) {
				}

				if (client.getResponseCode() == 200) {
					dataStore.save(CommonURL.URL_DANHMUC, client.getResponse());
				}

				client = new RestClient(CommonURL.URL_DANHSACH_MAP);

				try {
					client.execute(RequestMethod.GET);
				} catch (Exception e) {
				}

				if (client.getResponseCode() == 200) {
					dataStore.save(CommonURL.URL_DANHSACH_MAP,
							client.getResponse());
				}

				return null;
			}

			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				update();
				dismissDialog(0);
			};
		}.execute("");

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		return ProgressDialog.show(this, "", "loading");
	}

	private void update() {
		listView.setVisibility((getIndexChecked() == 1) ? View.GONE
				: View.VISIBLE);

		mapView.setVisibility((getIndexChecked() == 1) ? View.VISIBLE
				: View.GONE);
		List<Object> list = new ArrayList<Object>();

		if (getIndexChecked() == 0) {
			((MBaseAdapter) listView.getAdapter()).clear();
			list.addAll(getLMerchantCategories());
			((MBaseAdapter) listView.getAdapter()).setData(list);
		} else if (getIndexChecked() == 2) {
			((MBaseAdapter) listView.getAdapter()).clear();
			list.addAll(getLStores());
			((MBaseAdapter) listView.getAdapter()).setData(list);
		}

		((MBaseAdapter) listView.getAdapter()).notifyDataSetChanged();

		if (mapView.getVisibility() == View.VISIBLE) {
			// storeId
			// merchantCategoryId

		}
	}

	private int getIndexChecked() {
		if (radioGroup.getCheckedRadioButtonId() == R.id.radio0) {
			return 0;
		} else if (radioGroup.getCheckedRadioButtonId() == R.id.radio1) {
			return 1;
		}

		return 2;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private List<MerchantCategory> getLMerchantCategories() {
		String xml = dataStore.get(CommonURL.URL_DANHMUC, "");

		List<MerchantCategory> list = new ArrayList<MerchantCategory>();
		Document document = XMLfunctions.XMLfromString(xml);
		if (document != null) {
			NodeList nodes = document.getElementsByTagName("MerchantCategory");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				MerchantCategory merchant = new MerchantCategory(e);
				list.add(merchant);
			}
		}

		return list;
	}

	private List<Store> getLStores() {
		String xml = dataStore.get(CommonURL.URL_DANHSACH_MAP, "");

		List<Store> list = new ArrayList<Store>();
		Document document = XMLfunctions.XMLfromString(xml);
		if (document != null) {
			NodeList nodes = document.getElementsByTagName("Store");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				Store merchant = new Store(e);
				list.add(merchant);
			}
		}

		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Object object = arg0.getItemAtPosition(arg2);

		if (object instanceof Store) {
			storeId = ((Store) object).getStoreId();
			merchantCategoryId = null;
		} else if (object instanceof MerchantCategory) {
			merchantCategoryId = ((MerchantCategory) object).getMerchCatId();
			storeId = null;
		}

		radioGroup.check(R.id.radio1);
	}
}