package com.example.detechbluetooth;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	int resCode;
	Boolean isLoginStatus;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final EditText txtUsername = (EditText) findViewById(R.id.username);
		final EditText txtPass = (EditText) findViewById(R.id.password);
		final Button btnLogin = (Button) findViewById(R.id.btnLogin);
		txtUsername.setText("store12");
		txtPass.setText("123456");

		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				final String username = txtUsername.getText().toString();
				final String password = txtPass.getText().toString();
				
				try {
					final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "", getResources().getString(R.string.message_login), true);
					final Handler handler = new Handler() {
						@Override
						public void handleMessage(Message msg) {
							progressDialog.dismiss();

							if (isLoginStatus) {
								Intent i = new Intent(LoginActivity.this, DeviceListActivity.class);
								startActivity(i);

								finish();
							}
						}
					};

					new Thread() {
						public void run() {
							login(username, password);
							handler.sendEmptyMessage(0);
						}
					}.start();
				} catch(Exception ex) {
					
				}
			}
		});
	}
	
	private void login(String username, String password) {
		try {
			String url = "http://shoppie.top50.vn/index.php/api/webservice/storelogin?login_id=" + username + "&password=" + password;
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
				NodeList nl = doc.getElementsByTagName("data");
				Element e = (Element) nl.item(0);
				SessionInfo.merch_id_str = parser.getValue(e, "merch_id"); 
				SessionInfo.store_id_str = parser.getValue(e, "store_id");
				isLoginStatus = true;
			} else {
				isLoginStatus = false;
			}

		} catch (Exception e) {
			AlertDialog.Builder builder1 = new AlertDialog.Builder(
					LoginActivity.this);
			builder1.setTitle("Error");
			builder1.setMessage(e.toString());
			isLoginStatus = false;

		}
	}
}
