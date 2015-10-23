package org.vnp.flyingtiger.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.flyingtiger.arcamera.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.vnp.core.base.activity.BaseActivity;

public class AboutActitiy extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.a_bot_to_top, R.anim.a_nothing);
		setContentView(R.layout.about);
		findViewById(R.id.abount_close).setOnClickListener(this);
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setTypeface(Typeface.createFromAsset(getAssets(), "Hiragino Sans GB W3.otf"));

		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(getAssets().open("abount.html")));
			String mLine = null;
			while ((mLine = reader.readLine()) != null) {
				builder.append(mLine);
			}
		} catch (IOException e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}

		textView.setText(Html.fromHtml(builder.toString()));
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	@Override
	public void onClick(View v) {
		finish();
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.a_nothing, R.anim.a_top_to_bot);
	}
}