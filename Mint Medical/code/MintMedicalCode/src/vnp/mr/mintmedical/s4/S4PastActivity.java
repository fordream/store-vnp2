package vnp.mr.mintmedical.s4;

import java.util.ArrayList;
import java.util.List;

import vnp.mr.mintmedical.HeaderView;
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.S4DetailActivity;
import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.callback.HistoryCallback;
import vnp.mr.mintmedical.item.S4ItemForShow;
import vnp.mr.mintmedical.view.MintProgessDialog;
import vnp.mr.mintmedical.view.S4ItemView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.viewpagerindicator.db.DBHistory;
import com.vnp.core.callback.CallBack;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S4PastActivity extends MBaseActivity {
	// Appointment
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HeaderView headerView = getView(R.id.activitymain_headerview);
		headerView.updateTextButtonRight(R.string.s4new);
		headerView.showButton(true, false);
		headerView.updateText(R.string.s4_history);
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
		/*
		resize.resizeSacle(findViewById(R.id.s4first), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);
		
		resize.resizeSacle(findViewById(R.id.s4first1),
				LayoutParams.MATCH_PARENT, 180);
		resize.resizeSacle(findViewById(R.id.s4_button1), 530 / 2, 90 / 2);
		resize.resizeSacle(findViewById(R.id.s4_text2), 580 / 2, 192 / 2);
		resize.setTextsize(findViewById(R.id.s4_text1), 17);
		*/
		
		setupListView(findViewById(R.id.s4_listview));
		final ExeCallBack callBack = new ExeCallBack();
		ExeCallBackOption exeCallBackOption = new ExeCallBackOption(this, true,
				R.string.loadding,new MintProgessDialog(this));
		callBack.setExeCallBackOption(exeCallBackOption);
		callBack.executeAsynCallBack(new CallBack() {
			@Override
			public void onCallBack(Object arg0) {
				updateUi();
			}

			public Object execute() {
				callBack.executeCallBack(new HistoryCallback(
						S4PastActivity.this));
				return null;
			}
		});

	}

	private void updateUi() {
		List<Object> list = new ArrayList<Object>();
		List<Object> lHistory = (List<Object>) new DBHistory(this).getData();
		list.addAll(lHistory);

		ListView listView = (ListView) findViewById(R.id.s4_listview);
		setupListView(listView);

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
					Intent intent = new Intent(S4PastActivity.this,
							S4DetailActivity.class);
					intent.putExtra("data", item.toJson());
					startActivity(intent);
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
			if (position == 0) {
				((S4ItemView) view).showHeadder(true);
			} else if (((S4ItemForShow) getItem(position)).isUpcoming == ((S4ItemForShow) getItem(position - 1)).isUpcoming) {
				((S4ItemView) view).showHeadder(false);
			} else {
				((S4ItemView) view).showHeadder(true);
			}

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