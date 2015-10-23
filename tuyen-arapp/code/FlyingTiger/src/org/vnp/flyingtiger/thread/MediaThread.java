package org.vnp.flyingtiger.thread;

import jp.flyingtiger.arcamera.R;
import android.content.Context;
import android.media.MediaPlayer;

public class MediaThread extends Thread {
	private Context mContext;

	public MediaThread(Context context) {
		mContext = context;
	}

	@Override
	public void run() {
		super.run();
		try {
			MediaPlayer mediaPlayer = MediaPlayer.create(mContext, R.raw.camera);
			mediaPlayer.start();
		} catch (Exception exception) {

		}
	}
}
