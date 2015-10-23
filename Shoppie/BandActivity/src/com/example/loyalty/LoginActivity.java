package com.example.loyalty;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ict.library.common.CommonLog;
import com.ict.library.service.RestClient;
import com.ict.library.service.RestClient.RequestMethod;

public class LoginActivity extends MBaseActivity {

	private EditText txtUsername;
	private EditText txtPass;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtUsername = (EditText) findViewById(R.id.username);
		txtPass = (EditText) findViewById(R.id.password);
		final Button btnLogin = (Button) findViewById(R.id.btnLogin);
		txtUsername.setText("chicland1");
		txtPass.setText("nmtnmt");

		btnLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				execute();
			}
		});

		if (SessionInfo.isLogin) {
//			execute();
			startDetech();
		}
	}

	@Override
	public void doInBackground1() {
		super.doInBackground1();
		String username = txtUsername.getText().toString();
		String password = txtPass.getText().toString();
		String url = "http://ws.shoppie.com.vn/index.php/api/webservice/storelogin?loginId={0}&password={1}";
		url = url.replace("{0}", username);
		url = url.replace("{1}", password);
		restClient = new RestClient(url);
		try {
			restClient.execute(RequestMethod.GET);
		} catch (Exception e) {
		}
	}

	@Override
	public void onPostExecute1() {
		super.onPostExecute1();
		if (restClient.getResponseCode() == 200) {
			XMLParser parser = new XMLParser();
			Document doc = parser.getDomElement(restClient.getResponse());
			NodeList result_code = doc.getElementsByTagName("xml");
			Element e1 = (Element) result_code.item(0);
			String resultstr = parser.getValue(e1, "result");
			if (resultstr.contentEquals("1")) {
				NodeList nl = doc.getElementsByTagName("data");
				Element e = (Element) nl.item(0);
				SessionInfo.merch_id_str = parser.getValue(e, "merchId");
				SessionInfo.store_id_str = parser.getValue(e, "storeId");
				
				SessionInfo.isLogin = true;

				startDetech();
			}
		} else {
			SessionInfo.isLogin = false;

			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setTitle("Error");
			builder1.setMessage("Error");
			builder1.show();
		}
	}

	private void startDetech() {
		Intent i = new Intent(this, DeviceListActivity.class);
		startActivity(i);
		finish();
	}
}
