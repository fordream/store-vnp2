package org.com.shoppie;

import java.util.ArrayList;
import java.util.List;

import org.com.shoppie.adapter.MBaseAdapter;
import org.com.shoppie.model.Merchant;
import org.com.shoppie.model.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.ict.library.common.CommonLog;
import com.ict.library.service.RestClient;
import com.ict.library.service.RestClient.RequestMethod;
import com.ict.library.util.XMLfunctions;

public class ProductActivity extends MBaseActivity {
	private ImageView imageView;
	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		imageView = getView(R.id.imageView1);
		gridView = getView(R.id.gridView1);
		List<Object> objects = new ArrayList<Object>();
		gridView.setAdapter(new MBaseAdapter(this, objects) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = new ImageView(parent.getContext());
				}
				Product product = (Product) getItem(position);
				imageLoader.DisplayImage(product.getProductImage(),
						ProductActivity.this, (ImageView) convertView);

				return convertView;
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Product product = (Product) arg0.getItemAtPosition(arg2);

				Intent intent = new Intent(arg1.getContext(),
						ProductDetailActivity.class);
				String id = "merchId_" + getIntent().getStringExtra("merchId");
				intent.putExtra("merchId", id);
				intent.putExtra("productId", product.getProductId());
				startActivity(intent);
			}
		});

		execute();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Merchant merchant = getMerchant(getIntent().getStringExtra("merchId"));
		if (merchant != null) {
			imageLoader.DisplayImage(merchant.getMerchImage(), this, imageView);
		}
	}

	@Override
	protected Object _doInBackground() {
		String url = "http://ws.shoppie.com.vn/index.php/api/webservice/merchproducts?merchId="
				+ getIntent().getStringExtra("merchId");
		RestClient restClient = new RestClient(url);
		try {
			restClient.execute(RequestMethod.GET);
		} catch (Exception e) {
		}
		return restClient;
	}

	@Override
	protected void _onPostExecute(Object data) {
		String id = "merchId_" + getIntent().getStringExtra("merchId");

		if (((RestClient) data).getResponseCode() == 200) {
			dataStore.save(id, ((RestClient) data).getResponse());
		}

		List<Object> list = new ArrayList<Object>();
		String dataXml = dataStore.get(id, "");

		Document document = XMLfunctions.XMLfromString(dataXml);

		if (document != null) {
			NodeList nodes = document.getElementsByTagName("Product");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				Product merchant = new Product(e);
				list.add(merchant);
			}
		}

		MBaseAdapter mBaseAdapter = (MBaseAdapter) gridView.getAdapter();
		mBaseAdapter.setData(list);
		mBaseAdapter.notifyDataSetChanged();
	}
}
