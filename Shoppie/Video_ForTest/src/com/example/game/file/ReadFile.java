package com.example.game.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ReadFile {
	private String filePath;
	private StringBuilder sbContent;

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Bitmap getBitmapFromAssets(String file, Context context) {
		try {
			InputStream is = context.getAssets().open(file);
			return BitmapFactory.decodeStream(is);
		} catch (Exception exception) {
			return null;
		}
	}

	public boolean readFle() {
		sbContent = new StringBuilder();
		File file = new File(filePath);
		Log.e("file ton tai", file.exists() + "");
		try {
			FileInputStream fstream = new FileInputStream(filePath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (!sbContent.toString().equals("")) {
					sbContent.append("\n");
				}

				sbContent.append(strLine);
			}
			in.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getContent() {
		return (sbContent != null) ? sbContent.toString() : "";
	}

	public boolean readFromAssest(Context context) {
		sbContent = new StringBuilder();
		try {
			InputStream is = context.getAssets().open(filePath);
			DataInputStream in = new DataInputStream(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (!sbContent.toString().equals("")) {
					sbContent.append("\n");
				}

				sbContent.append(strLine);
			}
			in.close();

			return true;
		} catch (Exception e) {
			Log.e("SSSSSSSSSS", "s", e);
			return false;
		}
	}
}