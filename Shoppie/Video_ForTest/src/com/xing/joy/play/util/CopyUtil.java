package com.xing.joy.play.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class CopyUtil {
	public void copyAssets(Context context, String folderAsset,
			String pathFolderStore) {
		AssetManager assetManager = context.getAssets();
		String[] files = null;
		try {
			files = assetManager.list(folderAsset);
			File file = new File(pathFolderStore);
			if (!file.exists()) {
				file.mkdirs();
			}
			for (String filename : files) {
				String temp = folderAsset + File.separator + filename;
				Log.e("FILE NAME", temp);
				InputStream in = null;
				OutputStream out = null;
				try {
					in = assetManager.open(temp);
					out = new FileOutputStream(pathFolderStore + File.separator
							+ filename);
					copyFile(in, out);
					in.close();
					in = null;
					out.flush();
					out.close();
					out = null;
				} catch (IOException e) {
					Log.e("tag", "Failed to copy asset file: " + filename, e);
				}
			}
		} catch (IOException e) {
		}
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}
}