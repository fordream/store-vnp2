package org.com.shoppie;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CheckInActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_in);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_check_in, menu);
		return true;
	}

}
