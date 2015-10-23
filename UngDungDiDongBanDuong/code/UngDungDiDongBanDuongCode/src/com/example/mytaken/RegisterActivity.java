package com.example.mytaken;

import com.example.mytaken.view.RegisterCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

public class RegisterActivity extends MBaseActivity implements Runnable, OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		new Handler().post(this);

		findViewById(R.id.customButton1).setOnClickListener(this);
		findViewById(R.id.CustomButton02).setOnClickListener(this);
		
		((RegisterCheckBox)findViewById(R.id.registerCheckBox1)).setText(R.string.register7);
		((RegisterCheckBox)findViewById(R.id.registerCheckBox2)).setText(R.string.register8);
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
			resize.resizeSacle(findViewById(R.id.customEditext3), 220, 45);
			resize.resizeSacle(findViewById(R.id.customEditext4), 220, 45);
		} catch (Exception e) {
		} catch (Error e) {
		}
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == R.id.CustomButton02) {
			finish();
		} else if (arg0.getId() == R.id.customButton1) {
			// login
			//
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		}
	}
}