package com.example.app.utils;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.example.app.blocklist.Block;
import com.vnp.core.common.VnpMemoryUtils;

public class MemoryUtils extends VnpMemoryUtils {

	public MemoryUtils(Context context) {
		super(context);
	}

	public String getPathContent() {
		if (Utils.ISDEBUG) {
			return getPathFileExternalMemory();
		}
		return getPathFileInternalMemory();

	}

	public boolean haveBlockContent() {
		String pathBlock = String.format("%s%s", getPathContent(), "block.txt");
		return new File(pathBlock).isFile();
	}

}
