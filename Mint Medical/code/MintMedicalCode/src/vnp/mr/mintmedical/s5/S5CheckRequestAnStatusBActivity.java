package vnp.mr.mintmedical.s5;

import java.util.ArrayList;
import java.util.List;

import vnp.mr.mintmedical.HeaderView;
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.BaseItem;
import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S5CheckRequestAnStatusBActivity extends MBaseActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		HeaderView headerView = (HeaderView) findViewById(R.id.activitymain_headerview);
		headerView.showButton(true, false);
		headerView.enableScale();
		headerView.updateText(R.string.s5_2_sub);
		headerView.setOnClickListenerButtonBack(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		listView = (ListView) findViewById(R.id.s6listview);
		setupListView(listView);

		new DBS5CheckRequestAnStatus(this) {
			public void _loadSucesṣ̣̣̣() {
				updateUI();
			};
		}.executeAsynCallBack();
		updateUI();
	}

	private List<Object> list = new ArrayList<Object>();

	private void updateUI() {
		list.clear();
		list.addAll((List<Object>) new DBS5CheckRequestAnStatus(this).getData());
		listView.setAdapter(new BaseAdapter(this, list, new CommonGenderView() {
			@Override
			public CustomLinearLayoutView getView(Context arg0, Object arg1) {
				return new S5CheckRequestAnStatusItemView(arg0);
			}
		}));
	}

	@Override
	protected void onResume() {
		super.onResume();
		listView = (ListView) findViewById(R.id.s6listview);
		setupListView(listView);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				BaseItem s6cItem = (BaseItem) parent
						.getItemAtPosition(position);

				if ("pending".equalsIgnoreCase(s6cItem.getString("status"))) {
					MintUtils.showDialog(view.getContext(),
							"Mint Medical.Request sent. Please wait for doctor's confirmation");
					return;
				} else {

					String _id = s6cItem.getString("id");
					Intent intent = new Intent(
							S5CheckRequestAnStatusBActivity.this,
							S5DetailActivity.class);
					intent.putExtra("id", _id);
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public int getLayout() {
		return R.layout.s6activity;
	}

	private class S5CheckRequestAnStatusItemView extends CustomLinearLayoutView {

		public S5CheckRequestAnStatusItemView(Context context) {
			super(context);
			init(R.layout.s6citemview);
		}

		public S5CheckRequestAnStatusItemView(Context context,
				AttributeSet attrs) {
			super(context, attrs);
			init(R.layout.s6citemview);
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			resize.resizeSacle(findViewById(R.id.s11itemview_m1),
					MintUtils.WIDTH_ITEM,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.s11itemcontent),
					MintUtils.WIDTH_ITEM, 60);
			resize.setTextsize(findViewById(R.id.s11itemview_text1),
					MintUtils.TEXTSIZE_ITEM1);

			resize.setTextsize(findViewById(R.id.s11itemview_text1_1),
					MintUtils.TEXTSIZE_ITEM1);
			resize.setTextsize(findViewById(R.id.s11itemview_text3),
					MintUtils.TEXTSIZE_ITEM1);
		}

		@Override
		public void setGender() {
			if (getData() instanceof BaseItem) {
				BaseItem item = (BaseItem) getData();
				((TextView) findViewById(R.id.s11itemview_text1)).setText(item
						.getString("request_time"));

				((TextView) findViewById(R.id.s11itemview_text1_1))
						.setText(item.getString("status"));

				((TextView) findViewById(R.id.s11itemview_text3)).setText(item
						.getString("conclusion"));

			}
		}

		@Override
		public void showHeader(boolean arg0) {

		}
	}

}