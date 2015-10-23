package org.com.shoppie;

import org.com.shoppie.model.Product;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ProductDetailActivity extends MBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);
		String merchId = getIntent().getStringExtra("merchId");
		String productId = getIntent().getStringExtra("productId");
		final Product product = getProduct(merchId, productId);

		ImageView imageView = getView(R.id.imageView1);
		imageLoader.DisplayImage(getMerchant(merchId.replace("merchId_", ""))
				.getMerchImage(), this, imageView);

		imageLoader.DisplayImage(product.getProductImage(), this,
				(ImageView) getView(R.id.imageView2));
		getView(R.id.imageView2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Builder builder = new Builder(v.getContext());
				builder.setMessage(product.getPrice() + "\n" + product.getProductName());
				builder.show();
			}
		});

	}

	@Override
	protected Object _doInBackground() {
		return null;
	}

	@Override
	protected void _onPostExecute(Object data) {

	}

}
