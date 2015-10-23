package com.xing.joy.play;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.game.file.Parse;
import com.example.game.file.ReadFile;
import com.xing.joy.play.util.CommonImageFrame;
import com.xing.joy.play.util.MemoryUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class Content {
	private String pathLRC = "content/A8_eng_indian_uta.lrc";
	private String pathPlist = Environment.getExternalStorageDirectory()
			+ "/A8_eng_indian_20.plist";
	private String pathPng = "content/A8_eng_indian_20.png";

	private String pathVideo = "";
	private Context context;

	private MPlist plist;
	private LRC lrc;

	private Bitmap bitmapStore;

	public Content(Context context) {
		this.context = context;
		pathLRC = MemoryUtils.getFolderContent("A8_eng_indian_uta.lrc");
		pathLRC = MemoryUtils.getFolderContent("vol3gonbesan_movie.lrc");
		pathPlist = MemoryUtils.getFolderContent("vol3gonbesan_20-hd.plist");
		pathPng = MemoryUtils.getFolderContent("vol3gonbesan_20-hd.png");
		pathVideo = MemoryUtils
				.getFolderContent("vol3gonbesan_movie.mp4.nomedia");

		bitmapStore = getBitmap();

		loadContent();
	}

	public void loadContent() {
		ReadFile readFile = new ReadFile();
		readFile.setFilePath(pathLRC);
		readFile.readFle();
		lrc = new LRC(readFile.getContent());
		plist = new MPlist(pathPlist);

	}

	public MPlist getPlist() {
		return plist;
	}

	public LRC getLrc() {
		return lrc;
	}

	public String getPathLRC() {
		return pathLRC;
	}

	public void setPathLRC(String pathLRC) {
		this.pathLRC = pathLRC;
	}

	public String getPathPlist() {
		return pathPlist;
	}

	public void setPathPlist(String pathPlist) {
		this.pathPlist = pathPlist;
	}

	public String getPathPng() {
		return pathPng;
	}

	public void setPathPng(String pathPng) {
		this.pathPng = pathPng;
	}

	public String getPathVideo() {
		return pathVideo;
	}

	public void setPathVideo(String pathVideo) {
		this.pathVideo = pathVideo;
	}

	private Bitmap getBitmap() {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(
					getPathPng())));
		} catch (Exception e) {
		}
		return bitmap;
	}

	public Bitmap getFrameBitmap(int index) {

		MPlist mPlist = getPlist();
		Frame frame = mPlist.getFrame(index);
		if (frame != null) {
			int widthBitmap = mPlist.getWidth();
			int startFrameX = frame.startFrameX();
			int startFrameY = frame.startFrameY();
			int widthFrame = frame.startWidthFrame();
			int heightFrame = frame.startHeightFrame();
			boolean isRontate = frame.isRontate();
			return CommonImageFrame.getFrame(bitmapStore, widthBitmap,
					startFrameX, startFrameY, widthFrame, heightFrame,
					isRontate);
		}

		return null;
	}
}