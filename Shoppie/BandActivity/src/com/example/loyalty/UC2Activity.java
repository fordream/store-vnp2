package com.example.loyalty;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;

public class UC2Activity extends Activity {

	int resCode;
	String pie_qty;
	String bonus_qty;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uc2);

		final EditText txtCusCode = (EditText) findViewById(R.id.customer_code);
		final EditText txtAmount = (EditText) findViewById(R.id.amount);
		final Button btnSend = (Button) findViewById(R.id.send);

		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				String cusCode = txtCusCode.getText().toString();
				String amount = txtAmount.getText().toString();

				try {
					String txn_type = "";
					String txn_amt = amount;
					RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioSale);
					int selectedId = radioSexGroup.getCheckedRadioButtonId();
					switch (selectedId) {
					case R.id.mua_hang:
						txn_type = "2";
						break;
					case R.id.checkin:
						txn_type = "1";
						txn_amt = "0";
						break;
					}

					String link = "http://shoppie.top50.vn/index.php/api/webservice/newtxncampaign?merch_id=%s&store_id=%s&cust_id=%s&txn_type=%s&txn_amt=%s";
					String url = String.format(link, SessionInfo.merch_id_str,
							SessionInfo.store_id_str, cusCode, txn_type,
							txn_amt);
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
						pie_qty = parser.getValue(e, "pie_qty");
						bonus_qty = parser.getValue(e, "bonus_qty");
						
						Toast.makeText(getApplicationContext(), "Pie Point: " + pie_qty + ", Brand Point: " + bonus_qty,
								Toast.LENGTH_LONG).show();
					}

				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(), "Nhập sai giá trị mã khách hàng",
							Toast.LENGTH_LONG).show();
					
					AlertDialog.Builder builder1 = new AlertDialog.Builder(
							UC2Activity.this);
					builder1.setTitle("Sai o day");
					builder1.setMessage(e.toString());

				}

			}
		});

		final Button btnRedeem = (Button) findViewById(R.id.redeem_gilf);

		btnRedeem.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(UC2Activity.this, UC4Activity.class);
				startActivity(i);

				// giai phong activity 
				finish();
			}
		});
		
		final Button btnSaleDay = (Button) findViewById(R.id.list_sale_day);

		btnSaleDay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(UC2Activity.this, UC5Activity.class);
				startActivity(i);

				// giai phong activity 
				finish();
			}
		});
	}
}
