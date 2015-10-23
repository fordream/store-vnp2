package org.vnp.peaps.base;

import org.vnp.peaps.ContactScreenActivity;
import org.vnp.peaps.R;
import org.vnp.peaps.models.People;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

import com.vnp.core.view.CustomLinearLayoutView;

public class HeaderView extends CustomLinearLayoutView implements
		OnClickListener {

	public HeaderView(Context context) {
		super(context);
		init(R.layout.headerview);
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.headerview);
	}

	@Override
	public void init(int res) {
		super.init(res);
//		resize(findViewById(R.id.header_mmm), LayoutParams.FILL_PARENT, 50, 0);
//		resize(findViewById(R.id.headerview_back), 46, 50, 0);
//		resize(findViewById(R.id.headerview_logo), 83, 32, 0);
//		resize(findViewById(R.id.headerview_back_1), 10, 50, 0);
//		resize(findViewById(R.id.headerview_contact_1), 10, 50, 0);
		
//		resize(findViewById(R.id.headerview_contact), 32, 32, 0);

		findViewById(R.id.headerview_back).setOnClickListener(this);
		findViewById(R.id.headerview_contact).setOnClickListener(this);
		
		if(getContext() instanceof ContactScreenActivity){
			findViewById(R.id.headerview_contact).setVisibility(GONE);
		}
	}

	@Override
	public void setGender() {

	}

	@Override
	public void showHeader(boolean arg0) {

	}

	public void setShowBack(boolean showBack) {
		findViewById(R.id.headerview_back).setVisibility(
				showBack ? View.VISIBLE : View.GONE);
		findViewById(R.id.headerview_back_1).setVisibility(
				showBack ? View.GONE : View.VISIBLE);
	}

	@Override
	public void onClick(View arg0) {
		if (R.id.headerview_back == arg0.getId())
			((Activity) getContext()).finish();
		else {
			People people = (People) getData();
			Intent intent = new Intent(getContext(),
					ContactScreenActivity.class);
			// intent.putExtra("id", people.getId());
			getContext().startActivity(intent);
		}
	}
}