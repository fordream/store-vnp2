package org.core.store.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vnp.core.common.CommonAndroid;

public class StoreAppUtils {
	public static final int WIDTH = 100 * 3;
	public static final int HEIGHT = 150 * 3;

	/* PROFOLIO */
	public static final int WIDTH_PROFOLIO = 500;
	public static final int HEIGHT_PROFOLIO = 500;
	public static final int WIDTH_IMG_PROFOLIO = 450;
	public static final int WIDTH_SPACE_PROFOLIO = 30;
	public static final int HEIGHT_TEXT_VIEW_PROFOLIO = 100;
	public static final int TEXT_SIZE_PROFOLIO = 38;
	/**
	 * Gallery
	 */
	public static final int WIDTH_BUTTON_NEXT_GALLERY = 150;
	public static final int WIDTH_MAX = 960;
	public static final int GALLERY_DETAIL_HEIGHT_GALLERY = 200;

	public static final int WIDTH_BUTTON_NEXT = 150;

	public static String URL_BASE = "https://app-server.googlecode.com/svn/trunk/storeapp/%s";

	public static final String URLICON = String.format(URL_BASE, "logo.png");
	public static final String URLBINLIST = String.format(URL_BASE, "bin.txt");
	public static final String URLGALLERY = String.format(URL_BASE, "gallery.txt");
	public static final String URLNEWS = String.format(URL_BASE, "news.txt");
	public static final String URLPORTFOLIO = String.format(URL_BASE, "portfolio.txt");
	public static final String URLCONTACT = URLBINLIST;
	public static final String URLABOUT = URLBINLIST;
	public static final String URLAMAP = URLBINLIST;
	public static final String URLSTATUSPACKAGE = URLBINLIST;

	public static final String URLCONTACTFORME = String.format(URL_BASE, "contactforme.txt");

	public static void resizeW960(View view, int width, int height) {
		try {
			CommonAndroid.RESIZE._20130408_resizeW960(view, width, height);
		} catch (Exception exception) {

		}
	}

	public static boolean isBlank(String img) {
		return img != null && !img.trim().equals("");
	}

	public static void setTextSize(View v, int size) {
		try {
			int _size = CommonAndroid.RESIZE._20130408_getSizeByScreenW960(v.getContext(), size);
			if (v instanceof TextView) {
				((TextView) v).setTextSize(_size);
			} else if (v instanceof EditText) {
				((EditText) v).setTextSize(_size);
			}
		} catch (Exception exception) {

		}
	}

	public static String getStringFromAsset(Context context) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(context.getAssets().open("bin.txt")));

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

}