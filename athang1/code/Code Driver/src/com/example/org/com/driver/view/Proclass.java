package com.example.org.com.driver.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.widget.TextView;

import com.vnp.core.common.CommonAndroid;

@SuppressLint("ResourceAsColor")
public class Proclass {
	public static final boolean ISDEBUG = false;

	public static final int MAIN_M1_TEXT1 = 23;

	public static final int MAIN_M1_TEXT2 = 18;

	public static final int MAIN_M2_TEXT1 = 30;
	public static final int MAIN_M2_TEXT2 = 20;
	public static final int MAIN_M2_TEXT3 = 25;
	public static final int MAIN_M2_TEXT4 = 16;

	public static final int SETTING_TEXT_HEADER = 23;

	public static final int PROPLEM_CONTENT_HEADER = 50;

	public static final int PROPLEM_CONTENT_HEADER_2 = 25;

	public static final int PROPLEM_LIST_ERROR_TEXT = 28;

	public static CharSequence cutDate(String info_datetime) {
		try {
			return info_datetime.subSequence(0, 10);
		} catch (Exception e) {
			return info_datetime;
		}
	}

	public static final void setTypeface(TextView textView) {
		CommonAndroid.FONT.setTypefaceFromAsset(textView, "arial.ttf");
	}

	public static boolean isNullOrBlank(String doctor) {
		if (doctor == null)
			return true;

		if (doctor.equalsIgnoreCase("null") || doctor.equals("")) {
			return true;
		}

		return false;
	}

	public static void sendEmail(Context mainActivity) {
		Builder builder = new Builder(mainActivity);
		builder.setMessage("Request sent to Server");
		builder.setPositiveButton("OK", null);
		builder.setCancelable(false);
		builder.show();
	}
}