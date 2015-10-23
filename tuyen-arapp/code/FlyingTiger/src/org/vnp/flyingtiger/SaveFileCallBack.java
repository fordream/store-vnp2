package org.vnp.flyingtiger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import jp.flyingtiger.arcamera.R;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

import com.vnp.core.base.callback.CallBack;
import com.vnp.core.base.common.CommonAndroid;

public class SaveFileCallBack extends CallBack {
	private View view;
	private String path;

	public String getPath() {
		return path;
	}

	public SaveFileCallBack(View view) {
		this.view = view;
	}

	@Override
	public Object execute() {
		File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile(), System.currentTimeMillis() + ".jpg");
		path = f.getAbsolutePath();
		try {
			Bitmap bitmap = TutorialHelloWorld.getDrawingCache(view);
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
			f.createNewFile();
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(bytes.toByteArray());
			fo.close();

			try {
				CommonAndroid.DEVICEID.rescanSdcard(view.getContext());
			} catch (Exception exception) {

			}

			return "1";
		} catch (Exception exception) {

		}
		return "0";
	}

	@Override
	public void onCallBack(Object object) {

	}

}
