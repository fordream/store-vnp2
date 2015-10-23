package com.xing.joy.play.util;

import android.graphics.Bitmap;
import android.media.MediaPlayer;

public abstract class IMedialayer implements
		MediaPlayer.OnVideoSizeChangedListener,
		MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnCompletionListener,
		MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener,
		MediaPlayer.OnPreparedListener {
	@Override
	public void onPrepared(MediaPlayer mp) {
		if (mp == null) {
			return;
		}
		if (mp.isPlaying()) {
			return;
		}
		mp.start();
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		return false;
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		return false;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// mp.stop();
		// mp.release();
	}

	@Override
	public void onSeekComplete(MediaPlayer mp) {

	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

	}

	public abstract void onPositionChangle(int position, int duration);

}