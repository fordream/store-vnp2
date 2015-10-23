package vnp.mr.mintmedical;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.callback.S4DetailtCallback;
import vnp.mr.mintmedical.fragment.MapFagment;
import vnp.mr.mintmedical.item.S4ItemForShow;
import vnp.mr.mintmedical.view.AvatarView;
import vnp.mr.mintmedical.view.MintProgessDialog;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.CommonAndroid;

@SuppressLint("ValidFragment")
public class S4DetailActivity extends MBaseActivity {
	String id = null;
	String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HeaderView headerView = getView(R.id.headerView1);
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(S4DetailActivity.this,
						S4ManagerActivity.class));
			}
		};

		headerView.updateTextButtonRight(R.string.s4new);
		headerView.setOnClickListener(l);
		headerView.showButton(true, false);
		headerView.updateText(R.string.s4);
		headerView.setOnClickListenerButtonBack(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		AvatarView avatarView = (AvatarView) findViewById(R.id.s4e_avatarView1);
		avatarView.setType(AvatarView.S4E);
		String lat = "", log = "";

		try {
			JSONObject jsonObject = new JSONObject(getIntent().getStringExtra(
					"data"));
			String time = jsonObject.getString("time");
			boolean isUpcoming = Boolean.parseBoolean(jsonObject
					.getString("isUpcoming"));

			S4ItemForShow forShow = new S4ItemForShow(time, jsonObject);
			lat = forShow.latitude;
			log = forShow.longitude;

			phone = forShow.phone;
			id = forShow.id;
			forShow.isUpcoming = isUpcoming;

			if (!forShow.isUpcoming) {
				findViewById(R.id.s4e_bnt).setVisibility(View.GONE);
			}

			((TextView) findViewById(R.id.s4e_text1)).setText(forShow.time);
			((TextView) findViewById(R.id.s4e_text2)).setText(DateUtils
					.getHHMM(forShow.start_time));

			((TextView) findViewById(R.id.s4e_text3)).setText(String.format(
					"%s ,%s", forShow.doctor_fullname, forShow.level));
			((TextView) findViewById(R.id.s4e_text4))
					.setText(forShow.office_address);

			avatarView.loadAvartar(forShow.avatar);

		} catch (JSONException e) {
		}

		changeFragemtn(R.id.s4e_map, new S4DetailtMapFagment(lat, log));

		findViewById(R.id.s4e_bnt).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cancel();
			}
		});

		findViewById(R.id.s4e_text5).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					CommonAndroid.callPhone(S4DetailActivity.this, phone);
				} catch (Exception exception) {
					MintUtils.toastMakeText(S4DetailActivity.this,
							"Can not call phone : " + phone);
				}
			}
		});

		// findViewById(R.id.s4e_text3).setVisibility(View.GONE);
		
	}

	private void cancel() {

		MintUtils.showDialogYESNO(this,
				"Mint Medical. Are you sure cancel appointment?",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						exeCancel();
					}
				}, null);
	}

	private void exeCancel() {
		ExeCallBack back = new ExeCallBack();
		back.setExeCallBackOption(new ExeCallBackOption(this, true,
				R.string.loadding, new MintProgessDialog(this)));
		back.executeAsynCallBack(new S4DetailtCallback(this, id) {
			@Override
			public void loadError(int responseCode, String errorMessage) {
				super.loadError(responseCode, errorMessage);
				MintUtils
						.toastMakeText(S4DetailActivity.this, "Can not cancel");
			}

			@Override
			public void loadSucess(String response) {
				super.loadSucess(response);

				try {
					String status = new JSONArray(response).getJSONObject(0)
							.getString("dataValue");
					if (status.equals("1")) {
						findViewById(R.id.s4e_bnt).setVisibility(View.GONE);
						MintUtils.toastMakeText(S4DetailActivity.this,
								"Mint Medical. Cancel appointment success!");
					}
				} catch (Exception exception) {
					MintUtils.toastMakeText(S4DetailActivity.this,
							"Can not cancel this appointment");
				}
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();

		resize.resizeSacle(findViewById(R.id.s4e_s1), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);

		resize.resizeSacle(findViewById(R.id.s4e_text5), MintUtils.WIDTH_ITEM,
				60);
		resize.resizeSacle(findViewById(R.id.s4e_content_doctor),
				MintUtils.WIDTH_ITEM, LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.s4e_map),
				LayoutParams.MATCH_PARENT, 200);

		resize.resizeSacle(findViewById(R.id.s4e_bnt), 538 / 2, 96 / 2);
		resize.setTextsize(findViewById(R.id.s4e_text1),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s4e_text2),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s4e_text3),
				MintUtils.TEXTSIZE_ITEM2);
		resize.setTextsize(findViewById(R.id.s4e_text4),
				MintUtils.TEXTSIZE_ITEM2);
		resize.setTextsize(findViewById(R.id.s4e_text5),
				MintUtils.TEXTSIZE_ITEM1);

	}

	@Override
	public int getLayout() {
		return R.layout.s4detailactivity;
	}

	private class S4DetailtMapFagment extends MapFagment {
		String latitude, longitude;

		public S4DetailtMapFagment(String lat, String log) {
			super();
			this.latitude = lat;
			this.longitude = log;
		}

		@Override
		public void onResume() {
			super.onResume();

			try {
				BitmapDescriptor icon = null;

				try {
					icon = BitmapDescriptorFactory
							.fromResource(R.drawable.s7bpoint);
				} catch (Exception exception) {

				}
				addMaker(latitude, longitude, icon);

				LatLng mLocation = new LatLng(Double.parseDouble(latitude),
						Double.parseDouble(longitude));

				getMap().moveCamera(
						CameraUpdateFactory.newLatLngZoom(new LatLng(
								mLocation.latitude, mLocation.longitude),
								ROOMSIZE));
			} catch (Exception exception) {
			}
		}
	}
}