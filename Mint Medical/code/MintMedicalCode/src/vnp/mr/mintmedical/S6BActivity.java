package vnp.mr.mintmedical;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.s6.DBS6BMedicationList;
//import vnp.mr.mintmedical.s6.DBS6Blastprescription;
import vnp.mr.mintmedical.s6.DBS6Brecentprescription;
import vnp.mr.mintmedical.s6.S6BItem;
import vnp.mr.mintmedical.s6.S6BMedicationConfig;
import vnp.mr.mintmedical.s6.S6BRequestpresCalBack;
import vnp.mr.mintmedical.view.MintProgessDialog;
import vnp.mr.mintmedical.view.S6BItemView;
import vnp.mr.mintmedical.view.S6BItemView.ICallBack;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vnp.core.callback.CallBack;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S6BActivity extends MBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HeaderView headerView = (HeaderView) findViewById(R.id.activitymain_headerview);
		headerView.showButton(true, false);
		headerView.updateText(R.string.s6_2);
		headerView.updateText(R.string.s6_2_1);
		headerView.updateText(R.string.s6_2_1_sub);
		headerView.enableScale();
		headerView.setOnClickListenerButtonBack(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		setupListView(findViewById(R.id.listView1));
		/*
		 * findViewById(R.id.s6bbutton).setOnClickListener(this);
		 */
		updateUi();

		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.setExeCallBackOption(new ExeCallBackOption(this, true,
				R.string.loadding, new MintProgessDialog(this)));
		exeCallBack.executeAsynCallBack(new CallBack() {
			@Override
			public void onCallBack(Object arg0) {
				updateUi();
			}

			@Override
			public Object execute() {
				new DBS6Brecentprescription(S6BActivity.this).executeCallBack();
				new DBS6BMedicationList(S6BActivity.this).executeCallBack();
				return null;
			}
		});

		// S6BMedicationConfig medicationConfig = new
		// S6BMedicationConfig(S6BMedicationConfig.SENDREQUEST, 0);
		// S6BItemView itemView = new S6BItemView(getParent());
		// itemView.setData(medicationConfig);
		// itemView.setGender();
		// ((ListView) findViewById(R.id.listView1)).addFooterView(itemView);

		updateUi();
	}

	private List<Object> list = new ArrayList<Object>();

	private void updateUi() {
		list.clear();

		List<Object> l1 = (List<Object>) new DBS6Brecentprescription(
				S6BActivity.this).getData();
		List<Object> l2 = (List<Object>) new DBS6BMedicationList(
				S6BActivity.this).getData();

		list.add(new S6BMedicationConfig(S6BMedicationConfig.PASTMEDICATION, l1
				.size()));
		list.addAll(l1);

		list.add(new S6BMedicationConfig(S6BMedicationConfig.MEDICATIONLIST, l2
				.size()));
		list.addAll(l2);

		// resize.resizeSacle(findViewById(R.id.listView1),
		// LayoutParams.MATCH_PARENT, list.size() * 35 + 150);
		list.add(new S6BMedicationConfig(S6BMedicationConfig.SENDREQUEST, 0));
		S6BAdapter adapter = new S6BAdapter(this, list, genderView);

		((ListView) findViewById(R.id.listView1)).setAdapter(adapter);

		// adapter.notifyDataSetChanged();
	}

	private class S6BAdapter extends BaseAdapter {

		public S6BAdapter(Context context, List<Object> lData,
				CommonGenderView genderView) {
			super(context, lData, genderView);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = super.getView(position, convertView, parent);
			// S6BItem item2 = (S6BItem) getItem(position);

			boolean showHeader = position == 0;
			if (!showHeader) {
				// S6BItem item1 = (S6BItem) getItem(position - 1);
				// if (item1.isLastRequest != item2.isLastRequest) {
				// showHeader = true;
				// }
			}

			((S6BItemView) v).showHeader(showHeader, getItem(position));

			return v;
		}
	}

	private CommonGenderView genderView = new CommonGenderView() {
		@Override
		public CustomLinearLayoutView getView(Context arg0, Object arg1) {
			S6BItemView itemView = new S6BItemView(arg0);
			itemView.setCallBack(new ICallBack() {

				@Override
				public void onCallBack(String str) {
					onClick(str);
				}
			});
			return itemView;
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

		/*
		 * resize.resizeSacle(findViewById(R.id.s6bactivity__textView1),
		 * MintUtils.WIDTH_ITEM, LayoutParams.WRAP_CONTENT);
		 * resize.resizeSacle(findViewById(R.id.s6bbutton),
		 * MintUtils.WIDTH_ITEM, 45);
		 * resize.resizeSacle(findViewById(R.id.s6btextview),
		 * MintUtils.WIDTH_ITEM, 100);
		 * resize.setTextsize(findViewById(R.id.s6btextview),
		 * MintUtils.TEXTSIZE_ITEM1);
		 * resize.setTextsize(findViewById(R.id.s6bactivity__textView1),
		 * MintUtils.TEXTSIZE_ITEM1);
		 */
	}

	@Override
	public int getLayout() {
		return R.layout.s6bactivity;
	}

	public void onClick(String str) {
		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.setExeCallBackOption(new ExeCallBackOption(this, true,
				R.string.loadding, new MintProgessDialog(this)));
		S6BRequestpresCalBack s6bRequestpresCalBack = new S6BRequestpresCalBack(
				this) {
			@Override
			public void loadSucess(String response) {
				try {
					String status = new JSONArray(response).getJSONObject(0)
							.getString("dataValue");

					if (status.equals("1")) {
						String message = "Mint Medical. Request sent. Please wait for doctor's confirmation";
						// message =
						// "Mint Medical. Request success! Please wait docter confirm";
						MintUtils.showDialog(S6BActivity.this, message,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										finish();
									}
								});
					} else {
						MintUtils.toastMakeText(S6BActivity.this,
								"Request not success");
					}
				} catch (JSONException e) {
					MintUtils.toastMakeText(S6BActivity.this,
							"Request not success");
				}
			}

			@Override
			public void loadError(int responseCode, String errorMessage) {
				super.loadError(responseCode, errorMessage);

			}
		};

		String oldmed = "";
		for (Object object : list) {
			if (object instanceof S6BItem) {
				S6BItem s6bItem = (S6BItem) object;
				if (s6bItem.isChecked) {
					if (oldmed.equals("")) {
						oldmed = s6bItem.getString("name");
					} else {
						oldmed = oldmed + "," + s6bItem.getString("name");
					}
				}
			}
		}

		s6bRequestpresCalBack.addParam("lastid", new DBS6Brecentprescription(
				S6BActivity.this).getLastId());
		s6bRequestpresCalBack.addParam("oldmed", oldmed);
		s6bRequestpresCalBack.addParam("newmed", str);
		exeCallBack.executeAsynCallBack(s6bRequestpresCalBack);
	}
}