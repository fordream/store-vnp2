package org.com.shoppie;

import java.util.ArrayList;
import java.util.List;

import org.com.shoppie.model.Campaign;
import org.com.shoppie.model.Gift;
import org.com.shoppie.model.Merchant;
import org.com.shoppie.model.Product;
import org.com.shoppie.service.MService;
import org.com.shoppie.util.CommonSIZE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fedorvlasov.lazylist.ImageLoader;
import com.ict.library.activity.BaseActivity;
import com.ict.library.database.DataStore;
import com.ict.library.util.XMLfunctions;

public abstract class MBaseActivity extends BaseActivity {
	protected static final int ID_DIALOG_LOADING_DATA = 1;
	protected static final int ID_DIALOG_NOT_NETWORK = 1;
	protected DataStore dataStore = DataStore.getInstance();
	protected ImageLoader imageLoader;
	protected static final int REQUEST_ENABLE_BT = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataStore.init(this);
		imageLoader = new ImageLoader(this, R.drawable.tran);
		startService(new Intent(this, MService.class));
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (ID_DIALOG_LOADING_DATA == id) {
			return ProgressDialog.show(this, null, "loading");
		}
		return null;
	}

	protected void execute() {
		showDialog(ID_DIALOG_LOADING_DATA);

		new AsyncTask<String, String, String>() {
			Object data;

			@Override
			protected String doInBackground(String... params) {
				data = _doInBackground();
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				_onPostExecute(data);
				dismissDialog(ID_DIALOG_LOADING_DATA);

			}

		}.execute("");
	}

	protected abstract Object _doInBackground();

	protected abstract void _onPostExecute(Object data);

	public void convertView(final View v, int left, int top) {
		v.post(new Runnable() {
			@Override
			public void run() {
				ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
				layoutParams.height = CommonSIZE.getSize(v.getContext(),
						v.getHeight());
				layoutParams.width = CommonSIZE.getSize(v.getContext(),
						v.getWidth());
				v.setLayoutParams(layoutParams);
			}
		});
	}

	protected List<Merchant> getListMerchants() {
		List<Merchant> list = new ArrayList<Merchant>();
		String dataXml = dataStore.get("logo", "");
		Document document = XMLfunctions.XMLfromString(dataXml);
		if (document != null) {
			NodeList nodes = document.getElementsByTagName("Merchant");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				Merchant merchant = new Merchant(e);
				list.add(merchant);
			}
		}

		return list;
	}

	protected Merchant getMerchant(String merchantId) {
		List<Merchant> list = getListMerchants();
		for (Merchant merchant : list) {
			if (merchant.getMerchId().equals(merchantId)) {
				return merchant;
			}
		}
		return null;
	}

	protected List<Product> getListProducts(String merchId) {
		String dataXml = dataStore.get(merchId, "");
		List<Product> list = new ArrayList<Product>();
		Document document = XMLfunctions.XMLfromString(dataXml);

		if (document != null) {
			NodeList nodes = document.getElementsByTagName("Product");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				Product merchant = new Product(e);
				list.add(merchant);
			}
		}

		return list;
	}

	protected Product getProduct(String merchId, String productId) {
		List<Product> list = getListProducts(merchId);
		for (Product product : list) {
			if (product.getProductId().equals(productId)) {
				return product;
			}
		}

		return null;
	}

	protected Gift getGift(String merchId, String Gift) {
		String dataXml = dataStore.get("allgifts", "");
		Document document = XMLfunctions.XMLfromString(dataXml);
		if (document != null) {
			NodeList nodes = document.getElementsByTagName("Gift");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element) nodes.item(i);
				Gift merchant = new Gift(e);
				if (merchant.getGiftId().equals(Gift)
						&& merchant.getMerchId().equals(merchId)) {
					return merchant;
				}
			}
		}

		return null;
	}

	protected void toast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
