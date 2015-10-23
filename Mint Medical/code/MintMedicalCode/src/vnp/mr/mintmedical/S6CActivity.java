package vnp.mr.mintmedical;

import java.util.ArrayList;
import java.util.List;

import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.s6.DBS6CPrecentprescription;
import vnp.mr.mintmedical.s6.S6CDetailActivity;
import vnp.mr.mintmedical.s6.S6CItem;
import vnp.mr.mintmedical.view.MintProgessDialog;
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

import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S6CActivity extends MBaseActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		HeaderView headerView = (HeaderView) findViewById(R.id.activitymain_headerview);
		headerView.showButton(true, false);
		headerView.enableScale();
		headerView.updateText(R.string.s6_3_sub);
		headerView.setOnClickListenerButtonBack(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		listView = (ListView) findViewById(R.id.s6listview);
		setupListView(listView);

		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.setExeCallBackOption(new ExeCallBackOption(this, true,
				R.string.loadding,new MintProgessDialog(this)));
		exeCallBack.executeAsynCallBack(new DBS6CPrecentprescription(this) {
			public void save(String data) {
				super.save(data);

				updateUI();
			};
		}.getCallBack());
		updateUI();
	}

	private List<Object> list = new ArrayList<Object>();

	private void updateUI() {
		list.clear();
		list.addAll((List<Object>) new DBS6CPrecentprescription(this).getData());
		listView.setAdapter(new BaseAdapter(this, list, new CommonGenderView() {
			@Override
			public CustomLinearLayoutView getView(Context arg0, Object arg1) {
				return new S6cItemView(arg0);
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

				S6CItem s6cItem = (S6CItem) parent.getItemAtPosition(position);

				if ("pending".equalsIgnoreCase(s6cItem.getString("status"))) {
					MintUtils.showDialog(view.getContext(), "Mint Medical.Request sent. Please wait for doctor's confirmation");
				} else {
					String _id = s6cItem.getString("id");
					Intent intent = new Intent(S6CActivity.this,
							S6CDetailActivity.class);
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

	private class S6cItemView extends CustomLinearLayoutView {

		public S6cItemView(Context context) {
			super(context);
			init(R.layout.s6citemview);
		}

		public S6cItemView(Context context, AttributeSet attrs) {
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
			if (getData() instanceof S6CItem) {
				S6CItem item = (S6CItem) getData();
				((TextView) findViewById(R.id.s11itemview_text1)).setText(item
						.getString("respond_time"));

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