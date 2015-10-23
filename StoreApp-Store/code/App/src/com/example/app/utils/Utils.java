package com.example.app.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.app.blocklist.Block;

import android.content.Context;

public class Utils {

	public static final String BLOCKLIST = "https://app-server.googlecode.com/svn/trunk/storeapp/blocklist.txt";
	public static final boolean ISDEBUG = true;

	public static List<Block> getListBlocks(Context context) {
		List<Block> obs = new ArrayList<Block>();
		try {
			MemoryUtils memoryUtils = new MemoryUtils(context);

			String pathBlock = String.format("%s%s",
					memoryUtils.getPathContent(), "block.txt");
			BufferedReader reader = new BufferedReader(
					new FileReader(pathBlock));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			// String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}

			String data = stringBuilder.toString();

			JSONArray array = new JSONObject(data).getJSONArray("blocks");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				Block block = new Block(context, jsonObject);
				obs.add(block);
			}

		} catch (Exception exception) {

		}

		return obs;
	}
}
