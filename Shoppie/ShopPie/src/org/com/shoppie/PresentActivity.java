package org.com.shoppie;

import java.util.ArrayList;
import java.util.List;

import org.com.shoppie.adapter.MBaseAdapter;
import org.com.shoppie.model.Gift;
import org.com.shoppie.model.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

import com.ict.library.service.RestClient;
import com.ict.library.service.RestClient.RequestMethod;
import com.ict.library.util.XMLfunctions;

public class PresentActivity extends MBaseActivity {
	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_present);

		List<Object> objects = new ArrayList<Object>();
		gridView = getView(R.id.gridView1);
		gridView.setAdapter(new MBaseAdapter(this, objects) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = new ImageView(parent.getContext());
				}
				Gift product = (Gift) getItem(position);
				imageLoader.DisplayImage(product.getGiftImage(),
						PresentActivity.this, (ImageView) convertView);

				return convertView;
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Gift product = (Gift) arg0.getItemAtPosition(arg2);

				Intent intent = new Intent(arg1.getContext(),
						RedeemActivity.class);
				intent.putExtra("GiftId", product.getGiftId());
				intent.putExtra("MerchId", product.getMerchId());
				startActivity(intent);
			}
		});

		execute();
	}

	@Override
	protected Object _doInBackground() {
		String url = "http://ws.shoppie.com.vn/index.php/api/webservice/allgifts";
		RestClient restClient = new RestClient(url);
		try {
			restClient.execute(RequestMethod.GET);
		} catch (Exception e) {
			// Log.e("sss", "bbb", e);
		}
		return restClient;
	}

	@Override
	protected void _onPostExecute(Object data) {
		if (((RestClient) data).getResponseCode() == 200) {
			dataStore.save("allgifts", ((RestClient) data).getResponse());
		}
		List<Object> list = new ArrayList<Object>();
		String dataXml = dataStore.get("allgifts", "");
		Document document = XMLfunctions.XMLfromString(dataXml);
		if (document != null) {
			NodeList nodes = document.getElementsByTagName("Gift");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				Gift merchant = new Gift(e);
				list.add(merchant);
			}
		}
		MBaseAdapter mBaseAdapter = (MBaseAdapter) gridView.getAdapter();
		mBaseAdapter.setData(list);
		mBaseAdapter.notifyDataSetChanged();
	}

}
