package org.vnp.peaps;

import java.util.ArrayList;
import java.util.List;

import org.vnp.peaps.base.MheaderBaseActivity;
import org.vnp.peaps.models.People;
import org.vnp.peaps.models.PeopleChild;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnp.core.common.ImageLoaderUtils;
import com.vnp.core.v2.BaseAdapter;
import com.vnp.core.view.CustomLinearLayoutView;

public class PeopleScreenActivity extends MheaderBaseActivity {

	public boolean isShowBack() {
		return false;
	}

	@Override
	public CustomLinearLayoutView getViewItem(Context arg0, Object arg1) {
		return new PeopleView(arg0);
	}

	@Override
	public List<Object> addExampleData() {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < 10; i++) {
			People people = new People();
			people.setId("000" + i);
			people.setImg("http://files.jimeh.me/.blog/jimeh_2.0_large-20100205-042205.jpg");
			people.setName("Chris Sculin");
			people.setTime("3");
			people.setEmail("exam" + i + "@gmail.com");
			people.addChild(new PeopleChild(R.drawable.email,
					R.string.people_child1));
			people.addChild(new PeopleChild(R.drawable.like,
					R.string.people_child2));
			people.addChild(new PeopleChild(R.drawable.bidthday,
					R.string.people_child3));
			people.addChild(new PeopleChild(R.drawable.chris,
					R.string.people_child4));
			people.addChild(new PeopleChild(R.drawable.remind,
					R.string.people_child5));

			list.add(people);
		}
		return list;
	}

	private class PeopleView extends CustomLinearLayoutView implements
			OnItemClickListener, OnClickListener {

		public PeopleView(Context context) {
			super(context);
			init(R.layout.peopleitem);
		}

		@Override
		public void init(int res) {
			super.init(res);
			findViewById(R.id.peopleitem_content).setOnClickListener(this);
//			resize(findViewById(R.id.peopleitem_content), 300,
//					LayoutParams.WRAP_CONTENT, 0);
//			resize(findViewById(R.id.peopleitem_name), 290,
//					LayoutParams.WRAP_CONTENT, 25);
//			resize(findViewById(R.id.peopleitem_time), 290,
//					LayoutParams.WRAP_CONTENT, 22);
//			resize(findViewById(R.id.peopleitem_img), 170, 170, 20);
//			resize(findViewById(R.id.gallery1), 290, 130, 20);
		}

		private BaseAdapter adapter;

		@Override
		public void setGender() {
			People people = (People) getData();
			settext(R.id.peopleitem_name, people.getName());
			settext(R.id.peopleitem_time,
					String.format("It's been [%s] months...", people.getTime()));
			ImageLoaderUtils.getInstance(getContext()).DisplayImage(
					people.getImg(),
					(ImageView) findViewById(R.id.peopleitem_img));

			List<Object> list = people.getList();

			Gallery gallery = (Gallery) findViewById(R.id.gallery1);
			gallery.setOnItemClickListener(this);

			if (adapter == null) {
				adapter = new BaseAdapter(getContext(), list) {

					@Override
					public boolean isShowHeader(int arg0) {
						return false;
					}

					@Override
					public CustomLinearLayoutView getView(Context arg0,
							Object arg1) {
						return new PeopleItemView(arg0);
					}
				};
				gallery.setAdapter(adapter);
			}
		}

		private void settext(int peopleitemName, String name) {
			((TextView) findViewById(peopleitemName)).setText(name);
		}

		@Override
		public void showHeader(boolean arg0) {

		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			if (position == 0) {
				try {
					People people = (People) getData();
					Intent emailIntent = new Intent(Intent.ACTION_SEND);
					emailIntent.setData(Uri.parse("mailto:"));
					emailIntent.setType("text/plain");
					emailIntent.putExtra(Intent.EXTRA_EMAIL,
							new String[] { people.getEmail() });
					emailIntent.putExtra(Intent.EXTRA_SUBJECT,
							"subject of email");
					emailIntent.putExtra(Intent.EXTRA_TEXT, "body of email");
					startActivity(emailIntent);
				} catch (Exception exception) {

				}
			}
		}

		@Override
		public void onClick(View v) {
		}
	}

	private class PeopleItemView extends CustomLinearLayoutView {
		public PeopleItemView(Context context) {
			super(context);
			init(R.layout.peoplevitem);
//			resize(findViewById(R.id.peoplevitem_main), 290, 130, 20);
//			resize(findViewById(R.id.peoplevitem_main_img), 50, 50, 20);
//			resize(findViewById(R.id.peoplevitem_main_text), 290,
//					LayoutParams.WRAP_CONTENT, 35);
		}

		@Override
		public void setGender() {

			PeopleChild child = (PeopleChild) getData();
			settext(R.id.peoplevitem_main_text, child.getResStr());
			findViewById(R.id.peoplevitem_main_img).setBackgroundResource(
					child.getResimg());
		}

		private void settext(int peopleitemName, int name) {
			((TextView) findViewById(peopleitemName)).setText(name);
		}

		@Override
		public void showHeader(boolean arg0) {

		}
	}
}