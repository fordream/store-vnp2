package com.xing.joy.play;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.game.file.Parse;

public class LRC {
	private List<String> listLRC = new ArrayList<String>();

	public LRC(String input) {
		listLRC.clear();
		listLRC.addAll(Parse.parseStringToArrayByLine(input));

		Log.e("LRC SIZE", size() + "");
	}

	public int size() {
		return listLRC.size();
	}

	public int check(int position) {
		int sec = position / 1000;
		int secsec = position - sec * 1000;
		int minus = sec / 60;
		sec = sec % 60;
		if (secsec >= 100) {
			secsec = secsec / 10;
		}

		String time = ((minus < 10) ? "0" : "") + minus + ":";
		time += ((sec < 10) ? "0" : "") + sec + ":";
		time += ((secsec < 10) ? "0" : "") + secsec;

		time = time.replace(":", "");

		for (int i = 0; i < size(); i++) {
			if (i > 3) {
				try {
					String data = listLRC.get(i);
					String start = data.substring(1, 9).replace(":", "");
					String end = data.substring(data.length() - 9,
							data.length() - 1).replace(":", "");
					String content = data.substring(11, data.length() - 11);

					int _start = Integer.parseInt(start);
					int _end = Integer.parseInt(end);
					int _time = Integer.parseInt(time);
					if (_start <= _time && _time <= _end) {
						Log.e("START ", start + "   " + time + "   " + end);
						return i - 4;
					}
					// if (time.compareTo(start) >= 0 && time.compareTo(end) <=
					// 0) {
					//
					// return i - 4;
					// }
				} catch (Exception e) {
				}
			}
		}

		return -1;
	}

}