package com.example.loyalty;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class UC5Activity extends Activity {

	int resCode;
	private ArrayAdapter<String> mSaleArrayAdapter;
	ListView newSaleListView;
	//private ArrayList<String> listSale;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uc5);

		try {
			mSaleArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
			mSaleArrayAdapter.notifyDataSetChanged();
			
			newSaleListView = (ListView) findViewById(R.id.sale_day_listview);
			newSaleListView.setAdapter(mSaleArrayAdapter);
	        
			String url = "http://shoppie.top50.vn/index.php/api/webservice/dailytxns?merch_id=2&store_id=2";

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			resCode = response.getStatusLine().getStatusCode();

			// Toast.makeText(getApplicationContext(),response.getStatusLine().getStatusCode()+"",
			// Toast.LENGTH_LONG).show();

			XMLParser parser = new XMLParser();
			String xml = parser.getXmlFromHttpResponse(response); // getting
																	// XML
			Document doc = parser.getDomElement(xml); // getting DOM
														// element
			//NodeList result_code = doc.getElementsByTagName("xml");

			//listSale = new ArrayList<String>();
				NodeList nl = doc.getElementsByTagName("item");
				
				for (int idx = 0; idx<nl.getLength(); idx++) {
					Element e = (Element) nl.item(0);
					String txn_type = parser.getValue(e, "txn_type");
					String pie_qty = parser.getValue(e, "pie_qty");
					String bonus_qty = parser.getValue(e, "bonus_qty");
					String array = "";
					if (txn_type.equalsIgnoreCase("1")) {
						array = "Checkin: Pie point " + pie_qty + " Brand point " + bonus_qty; 
					} else if (txn_type.equalsIgnoreCase("2")) {
						array = "Ban hang: Pie point " + pie_qty + " Brand point " + bonus_qty; 
					}
					mSaleArrayAdapter.add(array);
				}
				
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(),
					"Nhập sai giá trị mã khách hàng", Toast.LENGTH_LONG).show();

			AlertDialog.Builder builder1 = new AlertDialog.Builder(
					UC5Activity.this);
			builder1.setTitle("Sai o day");
			builder1.setMessage(e.toString());

		}

		final Button btnRedeem = (Button) findViewById(R.id.redeem_gilf_sale_day);

		btnRedeem.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(UC5Activity.this, UC4Activity.class);
				startActivity(i);

				// giai phong activity
				finish();
			}
		});

		final Button btnSaleProduct = (Button) findViewById(R.id.sale_product_sale_day);

		btnSaleProduct.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(UC5Activity.this, UC2Activity.class);
				startActivity(i);

				// giai phong activity
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_uc5, menu);
		return true;
	}

}
