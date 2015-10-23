package org.vnp.peaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.vnp.peaps.base.MheaderBaseActivity;
import org.vnp.peaps.models.PeContact;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnp.core.common.ImageLoaderUtils;
import com.vnp.core.view.CustomLinearLayoutView;

public class ContactScreenActivity extends MheaderBaseActivity {
	public boolean isShowBack() {
		return true;
	}

	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getStringExtra("id");
	}

	@Override
	public CustomLinearLayoutView getViewItem(Context arg0, Object arg1) {
		return new ContactView(arg0);
	}

	public List<Object> addExampleData() {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < 100; i++) {
			PeContact pcontact = new PeContact();
			pcontact.setId("000" + i);
			pcontact.setImg("http://files.jimeh.me/.blog/jimeh_2.0_large-20100205-042205.jpg");
			pcontact.setName("Chris Sculin");
			pcontact.setSocre(new Random().nextInt(100) + 1);
			pcontact.setChecked(new Random().nextBoolean());
			list.add(pcontact);
		}

		Comparator<Object> comparator = new Comparator<Object>() {

			@Override
			public int compare(Object lhs, Object rhs) {
				try {
					return ((PeContact) lhs).getSocre() > ((PeContact) rhs)
							.getSocre() ? -1 : 1;
				} catch (Exception exception) {
					return 0;
				}
			}
		};
		Collections.sort(list, comparator);
		return list;
	}

	private class ContactView extends CustomLinearLayoutView implements
			OnClickListener {

		public ContactView(Context context) {
			super(context);
			init(R.layout.contactitem);
		}

		@Override
		public void init(int res) {
			super.init(res);

			findViewById(R.id.contactitem_check).setOnClickListener(this);

			// resize(findViewById(R.id.contactitem_main), 300, 60, 0);
			// resize(findViewById(R.id.contactitem_avatar), 40, 40, 0);
			// resize(findViewById(R.id.contactitem_check), 30, 30, 0);
			// resize(findViewById(R.id.contactitem_text),
			// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20);
			// resize(findViewById(R.id.contactitem_text_per),
			// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20);

		}

		@Override
		public void setGender() {
			PeContact contact = (PeContact) getData();
			settext(R.id.contactitem_text, contact.getName());
			settext(R.id.contactitem_text_per, contact.getSocre() + "%");
			ImageLoaderUtils.getInstance(getContext()).DisplayImage(
					contact.getImg(),
					(ImageView) findViewById(R.id.contactitem_avatar));
			updateChecked(contact);
		}

		private void updateChecked(PeContact contact) {
			findViewById(R.id.contactitem_check).setBackgroundResource(
					contact.isChecked() ? R.drawable.p1 : R.drawable.p2);
		}

		private void settext(int peopleitemName, String name) {
			((TextView) findViewById(peopleitemName)).setText(name);
		}

		@Override
		public void showHeader(boolean arg0) {

		}

		@Override
		public void onClick(View v) {
			PeContact contact = (PeContact) getData();
			contact.setChecked(!contact.isChecked());
			updateChecked(contact);
		}
	}

}
