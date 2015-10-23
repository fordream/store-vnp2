package org.com.vn.loxleycolour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.com.cnc.common.android.Common;
import org.com.vn.loxleycolour.items.order2.GetMyInvoiceItems;
import org.com.vn.loxleycolour.items.order2.ItemGetMyCurrentInvoices;
import org.com.vn.loxleycolour.items.order2.ItemGetMyInvoiceItems;
import org.com.vn.loxleycolour.v1.R;

import android.text.format.DateFormat;
import android.text.format.Time;
import android.text.method.DateTimeKeyListener;

public class CommonApp {

	public static final String MONEY = "£";

	public static String convertDueDate(String dueDate) {
		if (Common.isNullOrBlank(dueDate)) {
			return "";
		}

		int index = dueDate.indexOf("T00");

		if (index > 0) {
			return dueDate.substring(0, index);
		}
		return dueDate;

	}

	public static String convertDate(String orderDate) {
		// 2010-04-23
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date = dateFormat.parse(orderDate);
			SimpleDateFormat f = new SimpleDateFormat("dd MMM");
			return "" + f.format(date);
		} catch (Exception e) {
		}
		return orderDate;
	}

	public static String convertMoney(String totalExVat) {
		try {
			float f = Float.valueOf(totalExVat);
			int fi = (int) (f * 100);

			return MONEY + (((float) fi) / 100);
		} catch (Exception e) {
		}
		return totalExVat;
	}

	public static CharSequence convertQty(String quantity) {
		try {
			float f = Float.valueOf(quantity);
			return "" + (int) f;
		} catch (Exception e) {
		}
		return quantity;
	}

	public static CharSequence convertPer(String amountIncVAT) {
		try {
			float f = Float.valueOf(amountIncVAT);
			int i = (int) (f * 100);

			return (((float) i) / 100) + "%";
		} catch (Exception e) {
		}
		return amountIncVAT;
	}

	public static String convertTotal(GetMyInvoiceItems getMyInvoiceItems) {
		String result = "";
		float f = 0;
		for (int i = 0; i < getMyInvoiceItems.lGetMyOrderItems.size(); i++) {

			ItemGetMyInvoiceItems items = getMyInvoiceItems.lGetMyOrderItems
					.get(i);

			try {
				f += (Float.valueOf(items.DiscountAmount) * Float
						.valueOf(items.AmountIncVAT)) / 100;
			} catch (Exception e) {
			}
		}

		int i = (int) (f * 100);
		result = ((float) i) / 100 + "";
		return MONEY + result;
	}

	public static boolean compareDate(ItemGetMyCurrentInvoices itemOrder,
			int index) {

		try {
			String date = CommonApp.convertDueDate(itemOrder.OrderDate);
			// String dateNow = getTimeYYYMMDD();
			long day = day(date);
			if (index == 0) {
				return day <= 14;
			} else if (index == 1) {
				return day <= 30;
			} else if (index == 2) {
				return day <= 30 * 3;
			} else {
				return day <= 30 * 6;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static long day(String date) {
		String pattern = "yyyy-MM-dd";

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		try {

			Date today = dateFormat.parse(getTimeYYYMMDD());
			Date date2 = dateFormat.parse(date);
			long diff = today.getTime() - date2.getTime();
			long day = diff / (1000 * 60 * 60 * 24);
			return day;
		} catch (ParseException e) {
			return 0;
		}
	}

	public static String getTimeYYYMMDD() {
		String time = "";
		Time currentTime = new Time();
		currentTime.setToNow();
		time += "" + (currentTime.year);
		time += "-" + (currentTime.month < 10 ? "0" : "") + currentTime.month;
		time += "-" + (currentTime.monthDay < 10 ? "0" : "")
				+ currentTime.monthDay;

		return time;
	}
}
