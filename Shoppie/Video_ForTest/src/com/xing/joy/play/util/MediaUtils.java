package com.xing.joy.play.util;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;

import jp.naver.KDTCUE.test.R;

import com.example.game.file.ReadFile;
import com.xing.joy.play.Content;
import com.xing.joy.play.Frame;
import com.xing.joy.play.LRC;
import com.xing.joy.play.MPlist;
import com.xing.joy.play.PlayMovieActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;

public class MediaUtils
// implements Runnable
{
	private MediaPlayer mediaPlayer;
	private IMedialayer iPlay;
	private String filePath;

	public void setiPlay(IMedialayer iPlay) {
		this.iPlay = iPlay;
	}

	public MediaUtils(Context context) {
		// new Thread(this).start();
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void startVideoPlayback() {
		if (mediaPlayer == null) {
			return;
		}
		if (mediaPlayer.isPlaying()) {
			return;
		}
		mediaPlayer.start();
	}

	public void initializationMediaPlayer(String pathMovie, String songName,
			SurfaceHolder surfaceHolder) {
		try {
			File file = new File(filePath);
			byte[] decodeHeader = file.getName().replace(".nomedia", "")
					.getBytes();

			FileDescriptor fileDescriptor = getFileDecriptor(filePath);

			if (mediaPlayer != null) {
				mediaPlayer.stop();
				mediaPlayer.release();

				mediaPlayer = null;
			}
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDisplay(surfaceHolder);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

			mediaPlayer.setDataSource(fileDescriptor, decodeHeader.length,
					file.length() - decodeHeader.length);
			if (iPlay != null) {
				mediaPlayer.setOnCompletionListener(iPlay);
				mediaPlayer.setOnErrorListener(iPlay);
				mediaPlayer.setOnInfoListener(iPlay);
				mediaPlayer.setOnPreparedListener(iPlay);
				mediaPlayer.setOnSeekCompleteListener(iPlay);
				mediaPlayer.setOnVideoSizeChangedListener(iPlay);
			}
			mediaPlayer.prepareAsync();
		} catch (Exception e) {
		}
	}

	public void initializationMediaPlayer(String filePath,
			SurfaceHolder surfaceHolder) {
		try {
			File file = new File(filePath);
			byte[] decodeHeader = file.getName().replace(".nomedia", "")
					.getBytes();

			FileDescriptor fileDescriptor = getFileDecriptor(filePath);

			if (mediaPlayer != null) {
				mediaPlayer.stop();
				mediaPlayer.release();

				mediaPlayer = null;
			}
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDisplay(surfaceHolder);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

			mediaPlayer.setDataSource(fileDescriptor, decodeHeader.length,
					file.length() - decodeHeader.length);
			if (iPlay != null) {
				mediaPlayer.setOnCompletionListener(iPlay);
				mediaPlayer.setOnErrorListener(iPlay);
				mediaPlayer.setOnInfoListener(iPlay);
				mediaPlayer.setOnPreparedListener(iPlay);
				mediaPlayer.setOnSeekCompleteListener(iPlay);
				mediaPlayer.setOnVideoSizeChangedListener(iPlay);
			}
			mediaPlayer.prepareAsync();
		} catch (Exception e) {
		}
	}

	public void destroy() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public FileDescriptor getFileDecriptor(String videoResName) {
		try {

			File tempFile = new File(videoResName);
			FileInputStream fileInputStream = new FileInputStream(tempFile);
			FileDescriptor descriptor = fileInputStream.getFD();
			descriptor.sync();

			return descriptor;
		} catch (Exception e) {
			return null;
		}
	}


	public void setPath(String pathVideo) {
		filePath = pathVideo;
	}

}