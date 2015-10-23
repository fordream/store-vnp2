package org.com.shoppie;

import org.com.shoppie.model.Gift;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RedeemActivity extends MBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem);
		getIntent().getStringExtra("GiftId");
		getIntent().getStringExtra("MerchId");
		
		Gift gift = getGift(getIntent().getStringExtra("MerchId"),getIntent().getStringExtra("GiftId"));
		
		imageLoader.DisplayImage(gift.getGiftImage(), this, (ImageView)getView(R.id.imageView1));
		
		TextView textView = getView(R.id.textView1);
		textView.setText(gift.getPieQty());
	}

	@Override
	protected Object _doInBackground() {
		return null;
	}

	@Override
	protected void _onPostExecute(Object data) {
		
	}


}
