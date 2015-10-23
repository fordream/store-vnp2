package com.example.game.file;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parse {
	public static List<String> parseStringToArrayByLine(String input) {
		List<String> ldata = new ArrayList<String>();
		StringTokenizer stringTokenizer = new StringTokenizer(input, "\n");
		while (stringTokenizer.hasMoreElements()) {
			String object = (String) stringTokenizer.nextElement();
			ldata.add(object);
		}
		return ldata;
	}
}