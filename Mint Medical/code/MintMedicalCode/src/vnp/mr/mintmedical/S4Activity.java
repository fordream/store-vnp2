package vnp.mr.mintmedical;

import java.util.ArrayList;
import java.util.List;

import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.callback.HistoryCallback;
import vnp.mr.mintmedical.callback.UpcomingCallback;
import vnp.mr.mintmedical.item.S4ItemForShow;
import vnp.mr.mintmedical.s4.S4ItemDataConfig;
import vnp.mr.mintmedical.s4.S4PastActivity;
import vnp.mr.mintmedical.view.MintProgessDialog;
import vnp.mr.mintmedical.view.S4ItemView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.viewpagerindicator.db.DBHistory;
import com.viewpagerindicator.db.DBUpcomingevents;
import com.vnp.core.callback.CallBack;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.LogUtils;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S4Activity extends MBaseActivity {
	// Appointment
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HeaderView headerView = getView(R.id.activitymain_headerview);
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(S4Activity.this,
						S4ManagerActivity.class));
			}
		};
		headerView.updateTextButtonRight(R.string.s4new);
		headerView.setOnClickListener(l);
		headerView.showButton(true, true);
		headerView.updateText(R.string.s4);
		headerView.setOnClickListenerButtonBack(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		updateUi();
	}

	@Override
	protected void onResume() {
		super.onResume();

		setupListView(findViewById(R.id.s4_listview));

		final ExeCallBack callBack = new ExeCallBack();
		ExeCallBackOption exeCallBackOption = new ExeCallBackOption(this, true,
				R.string.loadding, new MintProgessDialog(this));
		callBack.setExeCallBackOption(exeCallBackOption);
		callBack.executeAsynCallBack(new CallBack() {
			@Override
			public void onCallBack(Object arg0) {
				updateUi();
			}

			public Object execute() {
				callBack.executeCallBack(new UpcomingCallback(S4Activity.this));
				callBack.executeCallBack(new HistoryCallback(S4Activity.this));
				return null;
			}
		});

	}

	private void updateUi() {
		List<Object> list = new ArrayList<Object>();

		List<Object> lUpComming = (List<Object>) new DBUpcomingevents(this)
				.getData();

		List<Object> lHistory = (List<Object>) new DBHistory(this).getData();

		if (lUpComming.size() != 0) {
			list.addAll(lUpComming);
		} else {
			list.add(new S4ItemDataConfig(S4ItemDataConfig.TYPEUPCOMMING,
					lUpComming.size()));
		}

		// TODO
		// if (lHistory.size() == 0) {
		//
		// } else {
		//
		// }
		list.add(new S4ItemDataConfig(S4ItemDataConfig.TYPEHISTORY,lHistory.size()));
		// list.addAll(lHistory);

		ListView listView = (ListView) findViewById(R.id.s4_listview);
		setupListView(listView);

		/*
		 * if (list.size() > 0) {
		 * findViewById(R.id.s4_text2).setVisibility(View.GONE);
		 * findViewById(R.id.s4_listview).setVisibility(View.VISIBLE); } else {
		 * findViewById(R.id.s4_text2).setVisibility(View.VISIBLE);
		 * findViewById(R.id.s4_listview).setVisibility(View.GONE); }
		 */

		CommonGenderView com = new CommonGenderView() {
			@Override
			public CustomLinearLayoutView getView(Context arg0, Object arg1) {
				return new S4ItemView(arg0);
			}
		};

		listView.setAdapter(new S4Adapter(this, list, com));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (parent.getItemAtPosition(position) instanceof S4ItemForShow) {
					S4ItemForShow item = (S4ItemForShow) parent
							.getItemAtPosition(position);
					Intent intent = new Intent(S4Activity.this,
							S4DetailActivity.class);
					intent.putExtra("data", item.toJson());
					startActivity(intent);
				} else if (parent.getItemAtPosition(position) instanceof S4ItemDataConfig) {
					S4ItemDataConfig config = (S4ItemDataConfig) parent
							.getItemAtPosition(position);

					if (config.type == S4ItemDataConfig.TYPEHISTORY) {
						if (config.size != 0) {
							startActivity(new Intent(S4Activity.this,
									S4PastActivity.class));
						}
					}
				}
			}
		});
	}

	private class S4Adapter extends BaseAdapter {

		public S4Adapter(Context context, List<Object> lData,
				CommonGenderView genderView) {
			super(context, lData, genderView);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = super.getView(position, convertView, parent);
			((S4ItemView) view).showHeadder(false);
			if (position == 0 && getItem(position) instanceof S4ItemForShow) {
				((S4ItemView) view).showHeadder(true);
			} else if (getItem(position) instanceof S4ItemDataConfig) {
				if (((S4ItemDataConfig) getItem(position)).type == S4ItemDataConfig.TYPEHISTORY) {
					((S4ItemView) view).showHeadder(true);
				}
			}

			// else if (((S4ItemForShow) getItem(position)).isUpcoming ==
			// ((S4ItemForShow) getItem(position - 1)).isUpcoming) {
			// ((S4ItemView) view).showHeadder(false);
			// } else {
			// ((S4ItemView) view).showHeadder(true);
			// }

			return view;

		}
	}

	@Override
	public int getLayout() {
		return R.layout.s4activity;
	}

	private Context getMContext() {
		return this;
	}

}