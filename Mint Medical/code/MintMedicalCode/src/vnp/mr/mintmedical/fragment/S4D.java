package vnp.mr.mintmedical.fragment;

import java.util.ArrayList;
import java.util.List;

import vnp.mr.mintmedical.DateUtils;
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.S4ManagerActivity;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.doctor.Doctor;
import vnp.mr.mintmedical.item.S4DItem;
import vnp.mr.mintmedical.service.VNPLocationUtils;
import vnp.mr.mintmedical.view.AvatarView;
import vnp.mr.mintmedical.view.BaseTextView;
import vnp.mr.mintmedical.view.MintProgessDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.location.Location;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.viewpagerindicator.db.DBS4DItem;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.callback.ResClientCallBack;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.common.LogUtils;
import com.vnp.core.service.RestClient;
import com.vnp.core.view.CustomLinearLayoutView;
import com.vnp.core.view.wheel.WheelView;
import com.vnp.core.view.wheel.WheelView.OnWheelChangedListener;
import com.vnp.core.view.wheel.WheelViewAdapter;

public class S4D extends MBaseFragment {
	// Appointment
	private ListView listView;

	@Override
	public void onResume() {
		super.onResume();
		resize.resizeSacle(findViewById(R.id.av_1),
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, 107 / 2);
		resize.resizeSacle(findViewById(R.id.w1),
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, 112 / 2);
		resize.resizeSacle(findViewById(R.id.button1), 134 / 2, 68 / 2);
		resize.resizeSacle(findViewById(R.id.button2), 134 / 2, 68 / 2);
		resize.resizeSacle(findViewById(R.id.w2),
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, 400 / 2);

		resize.resizeSacle(findViewById(R.id.whel1), 200, 180);
		// resize.resizeSacle(findViewById(R.id.whel2), 100, 180);
		// resize.resizeSacle(findViewById(R.id.whel3), 60, 180);
		// resize.resizeSacle(findViewById(R.id.whel4), 100, 180);

		resize.resizeSacle(findViewById(R.id.av_2), 450 / 2, 68 / 2);

		setupWhel();
		findViewById(R.id.av_2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openDialog();
			}
		});

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				closeDialog(false);
			}
		});

		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				closeDialog(true);
			}
		});

		findViewById(R.id.whel_main).setOnClickListener(null);
		findViewById(R.id.whel_main).setVisibility(View.GONE);

		listView = (ListView) findViewById(R.id.s4dlistView);

		udapteUi();
	}

	WheelView wheelView1;
	WheelView wheelView2;
	WheelView wheelView3;
	WheelView wheelView4;

	// int month[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	// String[] strmon = new String[] { "January", "February", "March", "April",
	// "May", "June", "July", "August", "September", "October",
	// "November", "December" };
	// int year[] = new int[] { 2014, 2015 };
	// int day[] = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	// String[] dayonWeek = new String[] { "", "Monday", "Tuesday", "Wednesday",
	// "Thursday", "Friday", "Saturday", "Sunday" };
	private class WheelViewAdapter1 implements WheelViewAdapter {
		List<String> mData = new ArrayList<String>();

		public WheelViewAdapter1(Context context) {
			super();
			DBS4DItem dbs4dItem = new DBS4DItem(getActivity());
			List<Object> list = (List<Object>) dbs4dItem.getData();
			mData.clear();
			mData.add("");

			for (Object o : list) {
				S4DItem item = (S4DItem) o;
				if (!mData.contains(item.time)) {
					mData.add(item.time);
				}
			}
		}

		public String getItemAtPosition(int i) {
			return mData.get(i);
		}

		@Override
		public View getEmptyItem(View arg0, ViewGroup arg1) {
			return null;
		}

		@Override
		public View getItem(int arg0, View arg1, ViewGroup arg2) {
			if (arg1 == null)
				arg1 = new ItemView(arg2.getContext());
			((TextView) (arg1)).setText(mData.get(arg0));
			return arg1;
		}

		@Override
		public int getItemsCount() {
			return mData.size();
		}

		@Override
		public void registerDataSetObserver(DataSetObserver arg0) {

		}

		@Override
		public void unregisterDataSetObserver(DataSetObserver arg0) {

		}
	}

	WheelViewAdapter1 adapter1;

	private void setupWhel() {

		wheelView1 = (WheelView) findViewById(R.id.whel1);
		wheelView2 = (WheelView) findViewById(R.id.whel2);
		wheelView3 = (WheelView) findViewById(R.id.whel3);
		wheelView4 = (WheelView) findViewById(R.id.whel4);
		wheelView2.setVisibility(View.GONE);
		wheelView3.setVisibility(View.GONE);
		wheelView4.setVisibility(View.GONE);
		// TODO
		// Time today = new Time(Time.getCurrentTimezone());
		// today.setToNow();
		// year = new int[] { today.year, today.year + 1 };

		wheelView1.setViewAdapter(adapter1 = new WheelViewAdapter1(
				getActivity()));

		// wheelView2.setViewAdapter(new WheelViewAdapter() {
		//
		// @Override
		// public void unregisterDataSetObserver(DataSetObserver arg0) {
		//
		// }
		//
		// @Override
		// public void registerDataSetObserver(DataSetObserver arg0) {
		//
		// }
		//
		// @Override
		// public int getItemsCount() {
		// return month.length;
		// }
		//
		// @Override
		// public View getItem(int arg0, View arg1, ViewGroup arg2) {
		// if (arg1 == null)
		// arg1 = new ItemView(arg2.getContext());
		//
		// ((TextView) (arg1)).setText(strmon[arg0] + "");
		// return arg1;
		// }
		//
		// @Override
		// public View getEmptyItem(View arg0, ViewGroup arg1) {
		// return null;
		// }
		// });
		// wheelView4.setViewAdapter(new WheelViewAdapter() {
		// @Override
		// public void unregisterDataSetObserver(DataSetObserver arg0) {
		//
		// }
		//
		// @Override
		// public void registerDataSetObserver(DataSetObserver arg0) {
		//
		// }
		//
		// @Override
		// public int getItemsCount() {
		// return dayonWeek.length;
		// }
		//
		// @Override
		// public View getItem(int arg0, View arg1, ViewGroup arg2) {
		// if (arg1 == null)
		// arg1 = new ItemView(arg2.getContext());
		// ((TextView) (arg1)).setText(dayonWeek[arg0] + "");
		// return arg1;
		// }
		//
		// @Override
		// public View getEmptyItem(View arg0, ViewGroup arg1) {
		// return null;
		// }
		// });
		//
		// setUpWheel3();
		//
		// wheelView1.setCurrentItem(0);
		// wheelView2.setCurrentItem(today.month);
		// wheelView3.setCurrentItem(today.monthDay);
		// wheelView4.addChangingListener(new OnWheelChangedListener() {
		// @Override
		// public void onChanged(WheelView arg0, int arg1, int arg2) {
		// setUpWheel3();
		// }
		// });
		// wheelView2.addChangingListener(new OnWheelChangedListener() {
		//
		// @Override
		// public void onChanged(WheelView arg0, int arg1, int arg2) {
		// wheelView4.setCurrentItem(0);
		// setUpWheel3();
		// }
		// });
		//
		wheelView1.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView arg0, int arg1, int arg2) {

			}
		});
	}

	// private void setUpWheel3() {
	// int _year = year[wheelView1.getCurrentItem()];
	// int _month = month[wheelView2.getCurrentItem()];
	//
	// int _dayOnWeek = wheelView4.getCurrentItem();
	//
	// if (_year % 4 == 0 && _year % 100 != 0 || _year % 400 == 0) {
	// day[1] = 29;
	// } else {
	// day[1] = 28;
	// }
	//
	// int index = wheelView2.getCurrentItem();
	// final int _count = day[index] + 1;
	//
	// if (_dayOnWeek != 0) {
	//
	// }
	//
	//
	// wheelView3.setViewAdapter(new WheelViewAdapter() {
	//
	// @Override
	// public void unregisterDataSetObserver(DataSetObserver arg0) {
	//
	// }
	//
	// @Override
	// public void registerDataSetObserver(DataSetObserver arg0) {
	//
	// }
	//
	// @Override
	// public int getItemsCount() {
	//
	// return _count;
	// }
	//
	// @Override
	// public View getItem(int arg0, View arg1, ViewGroup arg2) {
	// if (arg1 == null) {
	// arg1 = new ItemView(arg2.getContext());
	// }
	//
	// if (arg0 == 0) {
	// ((TextView) (arg1)).setText("-");
	// } else {
	// ((TextView) (arg1)).setText(arg0 + "");
	// }
	// return arg1;
	// }
	//
	// @Override
	// public View getEmptyItem(View arg0, ViewGroup arg1) {
	// return null;
	// }
	// });
	//
	// wheelView3.setCurrentItem(0);
	// }

	private class ItemView extends BaseTextView {

		public ItemView(Context context) {
			super(context);
			setSingleLine(true);
			setEllipsize(TruncateAt.END);
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			setGravity(Gravity.CENTER);
			resize.resizeSacle(this,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT, 50);
			resize.setTextsize(this, 30);
		}
	}

	private void openDialog() {
		findViewById(R.id.whel_main).setVisibility(View.VISIBLE);
	}

	private void closeDialog(boolean b) {
		if (b) {
			try {
				String date = adapter1.getItemAtPosition(wheelView1
						.getCurrentItem());
				DBS4DItem dbs4dItem = new DBS4DItem(getActivity());
				List<Object> list = (List<Object>) dbs4dItem.getData();
				List<Object> mData = new ArrayList<Object>();

				for (Object o : list) {
					S4DItem item = (S4DItem) o;
					if (date.equals("") || date.equals(item.time)) {
						mData.add(o);
					}
				}

				updateUI(mData);
			} catch (Exception exception) {

			}
			// DBS4DItem dbs4dItem = new DBS4DItem(getActivity());
			// List<Object> list = (List<Object>) dbs4dItem.getData();
			// _updateUI(list, year[wheelView1.getCurrentItem()],
			// wheelView2.getCurrentItem(), wheelView3.getCurrentItem());
		} else {

		}

		findViewById(R.id.whel_main).setVisibility(View.GONE);
	}

	private void udapteUi() {
		// List<Object> list = new ArrayList<Object>();

		// type = 1 : A-> B -> C -> D -> E -> F
		// 2 : A-> B -> G -> C -> D -> E -> F
		// 3 : A-> H -> B -> C -> D -> E -> F
		String url = MintUtils.URL_AVAILABILITY;
		url = String.format(url, dataSendToServer.typeS4BVisitType,
				dataSendToServer.typeS4AAppointment);
		if (dataSendToServer.typeS4AAppointment == 1) {
		} else if (dataSendToServer.typeS4AAppointment == 2) {
			String idOffice = dataSendToServer.typeS4GSelectOffice.id;
			// all Event of clinc
			url = url + "&office_id=" + idOffice;
		} else if (dataSendToServer.typeS4AAppointment == 3) {
			Doctor doctor = dataSendToServer.typeS4HSelectDoctor;
			String idDoctor = doctor.id;
			// all event of a doctor
			url = url + "&doctor_id=" + idDoctor;
		}

		final String URL = url;
		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.setExeCallBackOption(new ExeCallBackOption(getActivity(),
				true, R.string.loadding,new MintProgessDialog(getActivity())));
		exeCallBack.executeAsynCallBack(new ResClientCallBack() {
			@Override
			public void onCallBack(Object arg0) {
				RestClient restClient = (RestClient) arg0;
				if (restClient.getResponseCode() == 200) {
					String data = restClient.getResponse();
					LogUtils.e("ABCD", restClient.getResponse());
					DBS4DItem dbs4dItem = new DBS4DItem(getActivity());
					dbs4dItem.save(data);

					List<Object> list = (List<Object>) dbs4dItem.getData();
					updateUI(list);
					setupWhel();
				}
			}

			@Override
			public String getUrl() {
				return URL;
			}
		});

	}

	private void updateUI(List<Object> list) {
		setupListView(listView);

		listView.setAdapter(new BaseAdapter(getActivity(), list,
				new CommonGenderView() {
					@Override
					public CustomLinearLayoutView getView(Context arg0,
							Object arg1) {
						return new S4DItemView(arg0);
					}
				}) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);

				if (position == 0) {
					((S4DItemView) view).showHeadder(true);
				} else {
					String start1 = ((S4DItem) getItem(position)).time;
					String start2 = ((S4DItem) getItem(position - 1)).time;
					if (start1.equals(start2)) {
						((S4DItemView) view).showHeadder(false);
					} else {
						((S4DItemView) view).showHeadder(true);
					}
				}
				return view;
			}
		});

		setupListView(listView);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getOnClickListener().onClick(
						parent.getItemAtPosition(position), 0);
			}
		});
	}

	@Override
	public int getLayout() {
		return R.layout.s4d;
	}

	@Override
	public int getHeaderRes() {
		return R.string.s4d_header;
	}

	private class S4DItemView extends CustomLinearLayoutView {

		public S4DItemView(Context context) {
			super(context);
			init(R.layout.s4ditemview);
		}

		public void showHeadder(boolean b) {
			findViewById(R.id.s4ditemview_header).setVisibility(
					b ? View.VISIBLE : View.GONE);
		}

		@Override
		public void init(int res) {
			super.init(res);
			AvatarView avatarView = (AvatarView) findViewById(R.id.s4ditemviewavatarView1);
			avatarView.setType(AvatarView.S4D);
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();

			resize.resizeSacle(findViewById(R.id.s4ditemview_header),
					MintUtils.WIDTH_ITEM, 42);
			resize.resizeSacle(findViewById(R.id.s4ditem_1),
					MintUtils.WIDTH_ITEM, LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.s4itemview_content_main),
					MintUtils.WIDTH_ITEM, 90);
			resize.setTextsize(findViewById(R.id.s4ditemview_textheader),
					MintUtils.TEXTSIZE_S4_HEADER);

			resize.setTextsize(findViewById(R.id.s4ditemview_textview1),
					MintUtils.TEXTSIZE_ITEM1);
			resize.setTextsize(findViewById(R.id.TextView01),
					MintUtils.TEXTSIZE_ITEM1);

			resize.setTextsize(findViewById(R.id.s4ditemview_textview2),
					MintUtils.TEXTSIZE_ITEM2);

			resize.setTextsize(findViewById(R.id.s4ditemview_textview2_1),
					MintUtils.TEXTSIZE_ITEM2);
			resize.setTextsize(findViewById(R.id.TextView02),
					MintUtils.TEXTSIZE_ITEM2);

			resize.setTextsize(findViewById(R.id.s4ditemview_textview3),

			MintUtils.TEXTSIZE_ITEM2);
			resize.setTextsize(findViewById(R.id.s4ditemview_textview4),
					MintUtils.TEXTSIZE_ITEM2);

			resize.resizeSacle(findViewById(R.id.s4ditemview_img2),
					MintUtils.IMGITEM_WIDTH2, MintUtils.IMGITEM_WIDTH2);
		}

		@Override
		public void setGender() {
			S4DItem item = (S4DItem) getData();

			setText(R.id.s4ditemview_textheader, item.time);
			setText(R.id.s4ditemview_textview1,
					DateUtils.getHHMM(item.getString("start")));

			// setText(R.id.s4ditemview_textview2,String.format("%s ,%s",
			// item.getString("doctor_name"),item.getString("doctor_level")));

			findViewById(R.id.s4ditemview_textview2).setVisibility(View.VISIBLE);
			if (!MintUtils.isMeberShip(getActivity())&& MintUtils.isInsurance(getActivity())) {
				setText(R.id.s4ditemview_textview2_1,
						String.format("%s", item.getString("professional")));
				setText(R.id.s4ditemview_textview2, String.format("%s", ""));// item.getString("doctor_level")
				findViewById(R.id.s4ditemview_textview2).setVisibility(View.GONE);
			} else {
				setText(R.id.s4ditemview_textview2_1,
						String.format("%s", item.getString("professional")));
				setText(R.id.s4ditemview_textview2, String.format("%s ,%s",
						item.getString("doctor_name"),
						item.getString("doctor_level")));
			}
			

			if (!MintUtils.isInsurance(getActivity())) {
				setText(R.id.s4ditemview_textview2_1,
						String.format("%s", item.getString("professional")));
				setText(R.id.s4ditemview_textview2, String.format("%s ,%s",
						item.getString("doctor_name"),
						item.getString("doctor_level")));
			}

			setText(R.id.s4ditemview_textview3,
					item.getString("office_address"));

			AvatarView avatarView = (AvatarView) findViewById(R.id.s4ditemviewavatarView1);
			avatarView.loadAvartar(item.getString("avatar"));
			setText(R.id.s4ditemview_textview4, "");

			try {
				Location last = VNPLocationUtils.getInstance().lastKnownLocation;
				LatLng latLng1 = new LatLng(last.getLatitude(),
						last.getLongitude());
				LatLng latLng2 = new LatLng(Double.parseDouble(item
						.getString("latitude")), Double.parseDouble(item
						.getString("longitude")));
				setText(R.id.s4ditemview_textview4,
						String.format("%s km",
								VNPLocationUtils.getDistance(latLng1, latLng2)));
			} catch (Exception exception) {
				setText(R.id.s4ditemview_textview4, String.format(""));
			}

		}

		private void setText(int s4ditemviewTextheader, String day) {
			((TextView) findViewById(s4ditemviewTextheader)).setText(day);
		}

		@Override
		public void showHeader(boolean arg0) {

		}
	}

	S4ManagerActivity.DataSendToServer dataSendToServer;

	public void setDataSendToServer(
			S4ManagerActivity.DataSendToServer dataSendToServer) {
		this.dataSendToServer = dataSendToServer;
	}

}