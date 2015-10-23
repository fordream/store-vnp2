package com.xing.joy.play.util;

import java.io.File;

import android.os.Environment;

public class MemoryUtils {
	public static String getFolderContent() {
		return Environment.getExternalStorageDirectory() + File.separator
				+ "content";
	}

	public static String getFolderContent(String fileName) {
		return getFolderContent() + File.separator + fileName;
	}
}
