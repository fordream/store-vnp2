package com.xing.joy.play.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.Log;

public class CommonImageFrame {
	public static Bitmap getFrameVwrtiacal(int frame, int totalFrame,
			Bitmap bitmap) {

		Bitmap btm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight()
				/ totalFrame, Config.ARGB_8888);

		Paint p = new Paint();
		p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		Canvas c = new Canvas(btm);
		c.drawBitmap(bitmap, 0, -frame * bitmap.getHeight() / totalFrame, null);

		return btm;
	}

	public static Bitmap getFrameHori(int frame, int totalFrame, Bitmap bitmap) {

		Bitmap btm = Bitmap.createBitmap(bitmap.getWidth() / totalFrame,
				bitmap.getHeight(), Config.ARGB_8888);

		Paint p = new Paint();
		p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		Canvas c = new Canvas(btm);
		c.drawBitmap(bitmap, -frame * bitmap.getWidth() / totalFrame, 0, null);

		return btm;
	}

	public static Bitmap getFrame(Point sizeBitmap, Bitmap bitmap, Point from,
			Point to) {
		float scale = ((float) bitmap.getWidth()) / ((float) sizeBitmap.x);
		Bitmap btm = Bitmap.createBitmap(
				((int) (Math.abs(to.x - from.x) * scale)),
				((int) (Math.abs(to.y - from.y) * scale)), Config.ARGB_8888);

		Paint p = new Paint();
		p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		Canvas c = new Canvas(btm);
		c.drawBitmap(bitmap, -from.x * scale, -from.y * scale, null);

		return btm;
	}

	public static Bitmap getFrame(Bitmap bitmap, int widthBitmap,
			int startFrameX, int startFrameY, int widthFrame, int heightFrame,
			boolean isRontate) {

		if (bitmap == null) {
			return null;
		}
		float scale = ((float) bitmap.getWidth()) / ((float) widthBitmap);

		Bitmap btm = Bitmap.createBitmap(((int) (widthFrame * scale)),
				((int) (heightFrame * scale)), Config.ARGB_8888);

		if (isRontate) {
			btm = Bitmap.createBitmap(((int) (heightFrame * scale)),
					((int) (widthFrame * scale)), Config.ARGB_8888);
		}

		Paint p = new Paint();
		p.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		Canvas c = new Canvas(btm);
		c.drawBitmap(bitmap, -startFrameX * scale, -startFrameY * scale, null);

		if (isRontate) {
			Matrix matrix = new Matrix();
			matrix.postRotate(-90);
			btm = Bitmap.createBitmap(btm, 0, 0, btm.getWidth(),
					btm.getHeight(), matrix, true);
		}
		return btm;
	}
}