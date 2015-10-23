package com.example.mytaken;

import com.example.mytaken.db.DBBanDuong;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

public class LoginActivity extends MBaseActivity implements OnClickListener, Runnable {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		new Handler().post(this);

		findViewById(R.id.customButton1).setOnClickListener(this);
		findViewById(R.id.CustomButton02).setOnClickListener(this);
		findViewById(R.id.customTextView2).setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void run() {
		try {
			resize.resizeSacle(findViewById(R.id.customButton1), 228, 38);
			resize.resizeSacle(findViewById(R.id.CustomButton02), 85, 29);
			resize.resizeSacle(findViewById(R.id.register_block_e), 226, LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.register_block_e), 226, LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.customEditext1), 220, 45);
			resize.resizeSacle(findViewById(R.id.customEditext2), 220, 45);
		} catch (Exception e) {
		} catch (Error e) {
		}
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == R.id.CustomButton02) {
			// finish();
			new DBBanDuong(this).saveLogin(true);
			finish();
		} else if (arg0.getId() == R.id.customButton1) {
			// facebook
			//
			// startActivity(new Intent(this, LoginActivity.class));
			// finish();
		} else if (R.id.customTextView2 == arg0.getId()) {
			//forgot pass
		}
	}

}