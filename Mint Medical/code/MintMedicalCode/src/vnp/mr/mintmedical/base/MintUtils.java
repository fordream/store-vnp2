package vnp.mr.mintmedical.base;

import vnp.mr.mintmedical.item.S9Item;
import vnp.mr.mintmedical.view.MessagePopupView;
import vnp.mr.mintmedical.view.MessageToastView;
import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.db.DBHome;
import com.viewpagerindicator.db.DBS9Item;
import com.viewpagerindicator.db.DBUserLogin;
import com.vnp.core.common.CommonAndroid;

@SuppressLint("ResourceAsColor")
public class MintUtils {
	public static final boolean ISDEBUG = false;
	//http://medical.99s.vn/service
	//http://minthealth.co/
	public static final String URL_AVAILABILITY = "http://minthealth.co/service/get_availability?visit_type=%s&appointment_type=%s";

	public static final String BASEURL = "http://minthealth.co/service/%s";
	// public static final String URL_GETEVENT =
	// String.format(BASEURL,"get_events");;
	// public static final String URL_BOOKEVENT =
	// String.format(BASEURL,"bookevent");
	// public static final String URL_PUSH =
	// String.format(BASEURL,"customerupdate");
	// public static final String URL_MAP = String.format(BASEURL, "map");
	// public static final String URL_VISITYPE =
	// String.format(BASEURL,"get_config");
	// public static final String URL_GETDOCTOR =
	// String.format(BASEURL,"get_doctor");
	// public static final String URL_GETCLINIC =
	// String.format(BASEURL,"get_clinic");
	public static final String URL_LOGIN = String.format(BASEURL,
			"login_customer");

	// public static final String URL_HOME = String.format(BASEURL, "get_home");

	public static final int WIDTH_ITEM = 580 / 2;

	public static final int HEIGHT_BUTTON = 40;
	public static final int HEIGHT_EDITTEXT = 40;
	public static final int IMGITEM_WIDTH = 50;
	public static final int IMGITEM_WIDTH2 = 13;
	public static int TEXTSIZE_ITEM1 = 22;// 17
	public static final int TEXTSIZE_ITEM2 = 18;// //14
	public static int TEXTSIZE_BUTTON = 18;
	public static int TEXTSIZE_EDITTEXT = 18;
	public static final int TEXTSIZE_S4_HEADER = 22;
	public static final int TEXTSIZE_ACCOUNT_HEADER = 22;

	// TODO
	public static final int TEXTSIZE_HEADER = 28;

	public static CharSequence cutDate(String info_datetime) {
		try {
			return info_datetime.subSequence(0, 10);
		} catch (Exception e) {
			return info_datetime;
		}
	}

	public static final void setTypeface(TextView textView) {
		CommonAndroid.FONT.setTypefaceFromAsset(textView, "arial.ttf");
		// CommonAndroid.FONT.setTypefaceFromAsset(textView, "ARABTYPE.TTF");
	}

	public static boolean isMeberShip(Context s2Activity) {
		try {
			Time today = new Time(Time.getCurrentTimezone());
			today.setToNow();
			String now = today.year
					+ ""
					+ (today.month < 9 ? "0" + today.month : (today.month + ""))
					+ ""
					+ (today.monthDay < 9 ? "0" + today.monthDay
							: today.monthDay);
			String memberShip = new DBHome(s2Activity).getMemberShip();

//			if (memberShip.equalsIgnoreCase("Active membership required")) {
//				return true;
//			}
			//LogUtils.e("ABCD#", memberShip);
			memberShip = memberShip.substring(10).replace("-", "");
			return Long.parseLong(memberShip) >= Long.parseLong(now);
		} catch (Exception exception) {
		} catch (Error e) {
		}

		return false;
	}

	public static boolean isInsurance(Context context) {
		try {
			S9Item s9Item = (S9Item) new DBS9Item(context).getData();
			Log.e("1234", s9Item.getString("insurance"));

			return s9Item.getString("insurance").toLowerCase()
					.equals("YES".toLowerCase());
		} catch (Exception exception) {
			return false;
		}
	}

	public static void toastMakeText(Context s1Activity, String string) {
		// Toast.makeText(s1Activity, string, Toast.LENGTH_SHORT).show();
		Toast toast = new Toast(s1Activity);
		toast.setDuration(Toast.LENGTH_SHORT);
		MessageToastView baseTextView = new MessageToastView(s1Activity);
		baseTextView.setText(string);
		toast.setView(baseTextView);
		toast.show();
	}

	public static void showDialog(Context s7aActivity, String message) {

		Builder builder = new Builder(s7aActivity);
		MessagePopupView baseTextView = new MessagePopupView(s7aActivity);
		// baseTextView.setGravity(Gravity.CENTER);
		baseTextView.setText(message);
		// baseTextView.setTextColor(R.color.itemheader);
		// baseTextView.setTextColor(android.R.color.white);
		// baseTextView.setBackgroundColor(android.R.color.black);
		builder.setView(baseTextView);
		// builder.setMessage(message);
		//
		builder.setCancelable(false);
		builder.setPositiveButton("OK", null);
		builder.show();
	}

	public static void showDialog(Context s7aActivity, String message,
			OnClickListener object) {
		Builder builder = new Builder(s7aActivity);

		MessagePopupView baseTextView = new MessagePopupView(s7aActivity);
		baseTextView.setGravity(Gravity.CENTER);
		baseTextView.setText(message);
		// baseTextView.setTextColor(R.color.itemheader);
		// baseTextView.setBackgroundColor(android.R.color.black);
		builder.setView(baseTextView);
		// builder.setMessage(string);
		builder.setCancelable(false);
		builder.setPositiveButton("OK", object);
		builder.show();

	}

	public static void showDialog(Context s7aActivity, String message,
			OnClickListener object, OnClickListener object1) {
		Builder builder = new Builder(s7aActivity);

		MessagePopupView baseTextView = new MessagePopupView(s7aActivity);
		baseTextView.setGravity(Gravity.CENTER);
		baseTextView.setText(message);
		// baseTextView.setTextColor(R.color.itemheader);
		// baseTextView.setBackgroundColor(android.R.color.black);
		builder.setView(baseTextView);
		// builder.setMessage(string);
		builder.setCancelable(false);
		builder.setPositiveButton("OK", object);
		builder.setNegativeButton("Cancel", object1);
		builder.show();

	}

	public static void showDialogYESNO(Context s4DetailActivity, String string,
			android.content.DialogInterface.OnClickListener onClickListener,
			android.content.DialogInterface.OnClickListener object) {
		Builder builder = new Builder(s4DetailActivity);

		MessagePopupView baseTextView = new MessagePopupView(s4DetailActivity);
		baseTextView.setGravity(Gravity.CENTER);
		baseTextView.setText(string);
		// baseTextView.setTextColor(R.color.itemheader);
		// baseTextView.setBackgroundColor(android.R.color.black);
		builder.setView(baseTextView);
		// builder.setMessage(string);
		builder.setCancelable(false);
		builder.setPositiveButton("YES", onClickListener);
		builder.setNegativeButton("NO", object);
		builder.show();

	}

	public static boolean isNullOrBlank(String doctor) {
		if (doctor == null)
			return true;

		if (doctor.equalsIgnoreCase("null") || doctor.equals("")) {
			return true;
		}

		return false;

	}

	public static boolean isLogin(Context context) {
		DBUserLogin dbUserLogin = new DBUserLogin(context);
		return dbUserLogin.isLogin();
	}

}