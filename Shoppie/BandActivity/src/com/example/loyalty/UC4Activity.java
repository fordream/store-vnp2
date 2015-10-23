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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UC4Activity extends Activity {

	int resCode;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uc4);
        
        final EditText txtCusCode = (EditText) findViewById(R.id.customer_code_redeem);
		final EditText txtQuantity = (EditText) findViewById(R.id.gilf_quantity);
		final Button btnSend = (Button) findViewById(R.id.send_redeem);

		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				String cusCode = txtCusCode.getText().toString();
				String quantity = txtQuantity.getText().toString();

				try {
					String point_type = "";
					RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioSale);
					int selectedId = radioSexGroup.getCheckedRadioButtonId();
					switch (selectedId) {
					case R.id.pie_point:
						point_type = "1";
						break;
					case R.id.brand_point:
						point_type = "2";
						break;
					}

					String link = "http://shoppie.top50.vn/index.php/api/webservice/redeemtxn?merch_id=%s&store_id=%s&gift_id=1&cust_id=%s&redeem_qty=%s&point_type=%s";
					String url = String.format(link, SessionInfo.merch_id_str,
							SessionInfo.store_id_str, cusCode, quantity,
							point_type);
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
					NodeList result_code = doc.getElementsByTagName("xml");
					Element e1 = (Element) result_code.item(0);

					// doc.get
					String resultstr = parser.getValue(e1, "result");
					if (resultstr.contentEquals("1")) {
						NodeList nl = doc.getElementsByTagName("item");
						Element e = (Element) nl.item(0);
						String pie_qty = parser.getValue(e, "pie_qty");
						String bonus_qty = parser.getValue(e, "brand_qty");
						
						Toast.makeText(getApplicationContext(), "Pie Point: " + pie_qty + ", Brand Point: " + bonus_qty,
								Toast.LENGTH_LONG).show();
					}

				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(), "Nhập sai giá trị mã khách hàng",
							Toast.LENGTH_LONG).show();
					
					AlertDialog.Builder builder1 = new AlertDialog.Builder(
							UC4Activity.this);
					builder1.setTitle("Sai o day");
					builder1.setMessage(e.toString());

				}

			}
		});

		final Button btnRedeem = (Button) findViewById(R.id.sale_product_redeem);

		btnRedeem.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(UC4Activity.this, UC2Activity.class);
				startActivity(i);

				// giai phong activity 
				finish();
			}
		});
		
		final Button btnSaleDay = (Button) findViewById(R.id.list_sale_day_redeem);

		btnSaleDay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(UC4Activity.this, UC5Activity.class);
				startActivity(i);

				// giai phong activity 
				finish();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_uc4, menu);
        return true;
    }

    
}
