package org.vnp.storeapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.VNPResize;

public class StoreAppUtils {
	public static final boolean ENALBLE = true;
	public static final int BASE_SCALE = 3;
	public static final int BASE_HEIGHT = 480 * BASE_SCALE;
	public static final int BASE_WIDTH = 320 * BASE_SCALE;

	public static class TEXTSIZE {
		public static final int HEADER = 25 * BASE_SCALE;
		public static final int BLOCKMAIN = 16 * BASE_SCALE;
		public static final int BLOCKLIST1 = 22 * BASE_SCALE;
		public static final int BLOCKLIST2 = 18 * BASE_SCALE;
		public static final int MENU = 15 * BASE_SCALE;
		public static final int HEADER_ITEM = 22 * BASE_SCALE;
		public static final int HEADER_ITEM_SUB = 16 * BASE_SCALE;
	}

	public static class SIZE_BLOCKACTIVITY {
		public static final int HEIGHT_MENU_CONTROLLER = 50 * BASE_SCALE;
	}

	/* PROFOLIO */
	public static final String URL_BASE = "http://vnpmanager.esy.es/api-storeapp/%s";

	public static final String URLBINLIST = String
			.format(URL_BASE, "block.php");
	public static final String URLNEWS = String.format(URL_BASE, "news.php");
	public static final String URLPORTFOLIO = String.format(URL_BASE,
			"portfolio.php");
	public static final String URLGALLERY = String.format(URL_BASE,
			"gallery.php");
	public static final String URLCONTACT = String.format(URL_BASE,
			"contact.php");
	public static final String URLSTATUS = String.format(URL_BASE,
			"checkstatus.php");
	public static final String URLABOUT = String.format(URL_BASE, "abount.php");
	

	public static void resizeW960(View view, int width, int height) {
		try {
			CommonAndroid.RESIZE._20130408_resizeW960(view, width, height);
		} catch (Exception exception) {

		}
	}

	public static boolean isBlank(String img) {
		return img == null || img != null && img.trim().equals("");
	}

	public static void setTextSize(View v, int size) {
		VNPResize resize = VNPResize.getInstance();
		resize.setTextsize(v, size);
	}

	public static String getStringFromAsset(Context context) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(context
					.getAssets().open("bin.txt")));

			// do reading, usually loop until end of file reading
			String mLine = reader.readLine();
			while (mLine != null) {
				// process line
				builder.append(mLine == null ? "" : mLine);
				mLine = reader.readLine();
			}
		} catch (IOException e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}

	/**
	 * 
	 * @param htmlText
	 */
	public String encode(String htmlText) {
		try {
			return URLEncoder.encode(htmlText, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return htmlText;
		}
	}

	/**
	 * 
	 * @param htmlEncode
	 */
	public String decode(String htmlEncode) {
		return URLDecoder.decode(htmlEncode);
	}

	public static String decodeHtml(String htmlEncode) {
		try {
			return openFileToString(Base64.decode(htmlEncode, Base64.DEFAULT));
		} catch (Exception exception) {
			return htmlEncode;
		}
		// return htmlEncode;
		// return StringUtils.newStringUtf8(Base64.decodeBase64(htmlEncode));
	}

	public static String openFileToString(byte[] _bytes) {
		String file_string = "";

		for (int i = 0; i < _bytes.length; i++) {
			file_string += (char) _bytes[i];
		}

		return file_string;
	}

	public static void setTextType(View view) {
		if (view != null & view instanceof TextView) {
			CommonAndroid.FONT.setTypefaceFromAsset((TextView) view,
					"arial.ttf");
		}
	}

	public static void setTextTypeBold(View view) {
		if (view != null & view instanceof TextView) {
			CommonAndroid.FONT.setTypefaceFromAsset((TextView) view,
					"ARIALBD.TTF");
		}
	}
}