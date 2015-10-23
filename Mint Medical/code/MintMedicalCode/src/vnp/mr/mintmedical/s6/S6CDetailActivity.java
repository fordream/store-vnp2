package vnp.mr.mintmedical.s6;

import java.util.List;

import com.vnp.core.common.LogUtils;
import com.vnp.core.v2.BaseAdapter;
import com.vnp.core.view.CustomLinearLayoutView;

import vnp.mr.mintmedical.HeaderView;
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.BaseItem;
import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.view.S6BItemView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class S6CDetailActivity extends MBaseActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HeaderView headerView = (HeaderView) findViewById(R.id.activitymain_headerview);
		headerView.showButton(true, false);
		headerView.enableScale();
		headerView.updateText(R.string.s6cdetail);
		headerView.setOnClickListenerButtonBack(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		listView = (ListView) findViewById(R.id.s6listview);
		setupListView(listView);

		updateUI(getIntent().getStringExtra("id"));
	}

	private void updateUI(String id) {

		S6CItem s6cItem = (S6CItem) new DBS6CPrecentprescription(this)
				.getData(id);
		if (s6cItem != null) {
			setText(R.id.textView1, s6cItem.getString("respond_time"));
			setText(R.id.textView1_1, s6cItem.getString("status"));
			setText(R.id.textView1_1, "");

			setText(R.id.textView2, s6cItem.getString("conclusion"));// note
			setText(R.id.textView4, s6cItem.getString("note"));//

			ListView listView = (ListView) findViewById(R.id.s6listview);

			List<Object> list = s6cItem.getDetailList();
			listView.setAdapter(new S6CDetailApdater(this, list));

			if (list.size() == 0) {
				// listView.setVisibility(View.GONE);
			} else {
				// listView.setVisibility(View.VISIBLE);
				resize.resizeSacle(findViewById(R.id.s6listview),
						MintUtils.WIDTH_ITEM, 35 * list.size() + 60);

			}

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		int wContent = LayoutParams.WRAP_CONTENT;
		int mParent = LayoutParams.MATCH_PARENT;
		int textSize = MintUtils.TEXTSIZE_ITEM1;

		resize.resizeSacle(findViewById(R.id.id001), MintUtils.WIDTH_ITEM,
				wContent);
		resize.resizeSacle(findViewById(R.id.id002), MintUtils.WIDTH_ITEM,
				wContent);

		resize.resizeSacle(findViewById(R.id.textView4), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);
		resize.setTextsize(findViewById(R.id.textView1), textSize);
		resize.setTextsize(findViewById(R.id.textView1_1), textSize);
		resize.setTextsize(findViewById(R.id.textView2), textSize);
		resize.setTextsize(findViewById(R.id.textView3), textSize);
		resize.setTextsize(findViewById(R.id.textView4), textSize);
	}

	private class S6CDetailApdater extends BaseAdapter {

		public S6CDetailApdater(Context context, List<Object> lData) {
			super(context, lData);
		}

		@Override
		public CustomLinearLayoutView getView(Context arg0, Object arg1) {
			return new S6CDetailItemView(arg0);
		}

		@Override
		public boolean isShowHeader(int position) {
			return position == 0;
		}

	}

	private class S6CDetailItemView extends S6BItemView {

		public S6CDetailItemView(Context context) {
			super(context);
		}

		@Override
		public void init(int res) {
			super.init(res);
			final CheckBox box = (CheckBox) findViewById(R.id.s6bcheckbok);
			box.setOnCheckedChangeListener(null);
			box.setEnabled(false);
			findViewById(R.id.s6bitemtext).setOnClickListener(null);
			box.setChecked(true);
		}

		@Override
		public void setGender() {
			update(((BaseItem) getData()).getString("name"));
		}

		@Override
		public void showHeader(boolean showHeader) {
			if (showHeader) {
				findViewById(R.id.id001_s6item).setVisibility(VISIBLE);
			} else {
				findViewById(R.id.id001_s6item).setVisibility(GONE);
			}

			((TextView) findViewById(R.id.s6bitem__textView1))
					.setText("Medication");
		}
	}

	private void setText(int textview1, String string) {
		((TextView) findViewById(textview1)).setText(string);
	}

	@Override
	public int getLayout() {
		return R.layout.s6cdetailctivity;
	}
}