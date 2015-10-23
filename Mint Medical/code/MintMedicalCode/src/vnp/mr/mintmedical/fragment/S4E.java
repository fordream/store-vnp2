package vnp.mr.mintmedical.fragment;

import org.json.JSONArray;

import vnp.mr.mintmedical.DateUtils;
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.S4ManagerActivity;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.callback.S4ECallback;
import vnp.mr.mintmedical.config.DBConfig;
import vnp.mr.mintmedical.item.S4DItem;
import vnp.mr.mintmedical.item.S4Item;
import vnp.mr.mintmedical.item.UserLogin;
import vnp.mr.mintmedical.item.VisitType;
import vnp.mr.mintmedical.view.AvatarView;
import vnp.mr.mintmedical.view.MintProgessDialog;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.viewpagerindicator.db.DBS4Appointment;
import com.viewpagerindicator.db.DBUserLogin;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.callback.ResClientCallBack;
import com.vnp.core.service.RestClient;

@SuppressLint("ValidFragment")
public class S4E extends MBaseFragment implements OnClickListener {
	SupportMapFragment fragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragment = new S4MapFagment();
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.s4e_map, fragment).commit();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		AvatarView avatarView = (AvatarView) v
				.findViewById(R.id.s4e_avatarView1);
		avatarView.setType(AvatarView.S4E);
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();

		// s4e_content_doctor

		resize.resizeSacle(findViewById(R.id.s4e_s1), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);
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
		resize.setTextsize(findViewById(R.id.s4e_text6),
				MintUtils.TEXTSIZE_ITEM2);

		findViewById(R.id.s4e_bnt).setOnClickListener(this);

		S4DItem s4dItem = dataSendToServer.typeS4DAvailability;
		((TextView) findViewById(R.id.s4e_text1)).setText(s4dItem.time);
		((TextView) findViewById(R.id.s4e_text2)).setText(DateUtils
				.getHHMM(s4dItem.getString("start")));

		((TextView) findViewById(R.id.s4e_text3)).setText(String.format(
				"%s ,%s\n%s", s4dItem.getString("doctor_name"),
				s4dItem.getString("doctor_level"),
				s4dItem.getString("professional")));
		
		if (!MintUtils.isMeberShip(getActivity())
				&& MintUtils.isInsurance(getActivity())) {
			((TextView) findViewById(R.id.s4e_text3)).setText(String.format(
					"%s", s4dItem.getString("professional")));
		}

		((TextView) findViewById(R.id.s4e_text4)).setText(s4dItem
				.getString("office_address"));

		VisitType visitType = new DBConfig(getActivity())
				.getVisitType(dataSendToServer.typeS4BVisitType);

		String visitTYpeStr = "";
		if (visitType != null)
			visitTYpeStr = visitType.value;

		((TextView) findViewById(R.id.s4e_text5)).setText(visitTYpeStr);
		((TextView) findViewById(R.id.s4e_text6))
				.setText(dataSendToServer.typeS4CReason);
		AvatarView avatarView = (AvatarView) findViewById(R.id.s4e_avatarView1);
		avatarView.loadAvartar(s4dItem.getString("avatar"));
	}

	@Override
	public int getLayout() {
		return R.layout.s4e;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.s4e_bnt) {
			// 1 : A-> B -> C -> D -> E -> F
			// 2 : A-> B -> G -> C -> D -> E -> F
			// 3 : A-> H -> B -> C -> D -> E -> F

			String typeS4CReason = dataSendToServer.typeS4CReason;
			S4DItem typeS4DAvailability = dataSendToServer.typeS4DAvailability;
			String typeS4BVisitType = dataSendToServer.typeS4BVisitType;
			int typeS4AAppointment = dataSendToServer.typeS4AAppointment;

			if (typeS4AAppointment == 1) {
			} else if (typeS4AAppointment == 2) {
				// Clinic clinic = dataSendToServer.typeS4GSelectOffice;
			}
			if (typeS4AAppointment == 3) {
				// Doctor doctorS4H = dataSendToServer.typeS4HSelectDoctor;
			}

			// send

			S4Item s4Item = new S4Item();
			s4Item.id = "";
			s4Item.typeS4AAppointment = typeS4AAppointment + "";
			s4Item.typeS4BVisitType = typeS4BVisitType + "";
			s4Item.typeS4CReason = typeS4CReason;
			s4Item.typeS4DAvailabilityDoctorEventId = typeS4DAvailability.id;

			DBUserLogin dbUserLogin = new DBUserLogin(getActivity());
			S4ECallback s4eCallback = new S4ECallback(getActivity()) {
				@Override
				public void loadSucess(String response) {
					super.loadSucess(response);
					try {
						String status = new JSONArray(response)
								.getJSONObject(0).getString("dataValue");
						if (status.equals("1")) {
							getOnClickListener().onClick(getData(), 0);
						} else {
							MintUtils.toastMakeText(getActivity(), "Book fail");
						}
					} catch (Exception exception) {
						MintUtils.toastMakeText(getActivity(), "Book fail");
					}
				}

				@Override
				public void loadError(int responseCode, String errorMessage) {
					super.loadError(responseCode, errorMessage);
					MintUtils.toastMakeText(getActivity(), "Book fail");
				}
			};
			s4eCallback.addParam("event_id",
					s4Item.typeS4DAvailabilityDoctorEventId);
			s4eCallback.addParam("visittype", s4Item.typeS4BVisitType);
			s4eCallback.addParam("reason", s4Item.typeS4CReason);

			ExeCallBack exeCallBack = new ExeCallBack();
			ExeCallBackOption exeCallBackOption = new ExeCallBackOption(
					getActivity(), true, R.string.loadding,
					new MintProgessDialog(getActivity()));
			exeCallBack.setExeCallBackOption(exeCallBackOption);
			exeCallBack.executeAsynCallBack(s4eCallback);
		}
	}

	@Override
	public int getHeaderRes() {
		return R.string.s4e_header;
	}

	private S4ManagerActivity.DataSendToServer dataSendToServer;

	public void setDataSendToServer(
			S4ManagerActivity.DataSendToServer dataSendToServer) {
		this.dataSendToServer = dataSendToServer;
	}

	private class S4MapFagment extends MapFagment {
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
				S4DItem s4dItem = dataSendToServer.typeS4DAvailability;
				LatLng mLocation = new LatLng(Double.parseDouble(s4dItem
						.getString("latitude")), Double.parseDouble(s4dItem
						.getString("longitude")));
				// GoogleMap googleMap = getMap();
				addMaker(s4dItem.getString("latitude"),
						s4dItem.getString("longitude"), icon);

				getMap().moveCamera(
						CameraUpdateFactory.newLatLngZoom(new LatLng(
								mLocation.latitude, mLocation.longitude),
								ROOMSIZE));
			} catch (Exception exception) {
			}

		}
	}

}