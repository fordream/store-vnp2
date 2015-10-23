package vnp.mr.mintmedical.s5;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

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

import com.vnp.core.v2.BaseAdapter;
import com.vnp.core.view.CustomLinearLayoutView;

public class S5DetailActivity extends MBaseActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HeaderView headerView = (HeaderView) findViewById(R.id.activitymain_headerview);
		headerView.showButton(true, false);
		headerView.enableScale();
		headerView.updateText(R.string.s5detail);
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
		BaseItem s6cItem = (BaseItem) new DBS5CheckRequestAnStatus(this)
				.getData(id);
		if (s6cItem != null) {
			setText(R.id.textView1, s6cItem.getString("request_time"));
			setText(R.id.textView1_1, s6cItem.getString("status"));
			setText(R.id.textView1_1, "");

			setText(R.id.textView2, s6cItem.getString("conclusion"));
			setText(R.id.textView4, s6cItem.getString("note"));

			ListView listView = (ListView) findViewById(R.id.s6listview);

			List<Object> list = getDetailList(s6cItem);
			listView.setAdapter(new S6CDetailApdater(this, list));

			if (list.size() == 0) {
				listView.setVisibility(View.GONE);
			} else {
				listView.setVisibility(View.VISIBLE);
				resize.resizeSacle(findViewById(R.id.s6listview),
						MintUtils.WIDTH_ITEM, 35 * list.size() + 60);
			}
		}
	}

	private List<Object> getDetailList(BaseItem s6cItem) {
		List<Object> list = new ArrayList<Object>();
		String detail = s6cItem.getString("detail");

		try {
			JSONArray array = new JSONArray(detail);
			for (int i = 0; i < array.length(); i++) {
				BaseItem baseItem = new BaseItem(array.getJSONObject(i));
				list.add(baseItem);
			}
		} catch (JSONException e) {
		}

		return list;
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
//		resize.resizeSacle(findViewById(R.id.s6listview), MintUtils.WIDTH_ITEM,
//				150);
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
		return R.layout.s5detailctivity;
	}
}