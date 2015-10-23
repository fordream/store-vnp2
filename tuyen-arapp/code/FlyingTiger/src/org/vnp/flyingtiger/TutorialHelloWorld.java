// Copyright 2007-2014 metaio GmbH. All rights reserved.
package org.vnp.flyingtiger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jp.flyingtiger.arcamera.R;

import org.vnp.flyingtiger.thread.ChoooserFrameThread;
import org.vnp.flyingtiger.thread.MediaThread;

import utils.CommonAndroid;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.Camera;
import com.metaio.sdk.jni.CameraVector;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.ImageStruct;
import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.tools.io.AssetsManager;

public class TutorialHelloWorld extends ARViewActivity {
	private Map<String, String[]> mData = new HashMap<String, String[]>();
	private int index = 0;
	private String mPath = null;
	private ChoooserFrameThread tutorialThread;
	private Handler handler2 = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			super.dispatchMessage(msg);
			showFrame(false);
		};
	};

	@Override
	protected int getGUILayout() {
		return R.layout.tutorial_hello_world;
	}

	private View content, content_land;

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.a_nothing, R.anim.a_top_to_bot);
	}

	private ProgressDialog dialog = null;

	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		// refresh();
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		// this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_HOME) {
			// The Code Want to Perform.

			CommonAndroid.toast(this, "click to home");
		}
		return super.onKeyDown(keyCode, event);
	};

	// @Override
	// public void onUserInteraction() {
	// super.onUserInteraction();
	// }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		super.onCreate(savedInstanceState);

		/**
		 * init data
		 */

		// item 1
		mData.put("1", new String[] { "Halloween_frameH_03" });
		mData.put("2", new String[] { "Halloween_frameH_03" });
		mData.put("3", new String[] { "Halloween_frameH_03" });
		mData.put("4", new String[] { "Halloween_frameH_03" });
		mData.put("5", new String[] { "Halloween_frameH_03" });
		mData.put("6", new String[] { "Halloween_frameH_03" });
		mData.put("7", new String[] { "Halloween_frameH_03" });
		mData.put("8", new String[] { "Halloween_frameH_03" });
		mData.put("9", new String[] { "Halloween_frameH_03" });

		// item 2
		// , "Halloween_frameH_03"
		mData.put("10", new String[] { "Halloween_frameW_03" });
		mData.put("11", new String[] { "Halloween_frameW_03" });
		mData.put("12", new String[] { "Halloween_frameW_03" });
		mData.put("13", new String[] { "Halloween_frameW_03" });
		// item 3
		// hearts
		mData.put("14", new String[] { "hearts_frame_1", "hearts_frame_2", "hearts_frame_3", "hearts_frame_4" });
		mData.put("17", new String[] { "hearts_frame_1", "hearts_frame_2", "hearts_frame_3", "hearts_frame_4" });

		// raindeer
		mData.put("15", new String[] { "raindeer_frame_1", "raindeer_frame_2", "raindeer_frame_3", "raindeer_frame_4" });
		mData.put("18", new String[] { "raindeer_frame_1", "raindeer_frame_2", "raindeer_frame_3", "raindeer_frame_4" });
		// santa
		mData.put("16", new String[] { "santa_frame_1", "santa_frame_2", "santa_frame_3", "santa_frame_4" });
		mData.put("19", new String[] { "santa_frame_1", "santa_frame_2", "santa_frame_3", "santa_frame_4" });
		/**
		 * 
		 */
		dialog = ProgressDialog.show(this, null, getString(R.string.loader));
		overridePendingTransition(R.anim.a_bot_to_top, R.anim.a_nothing);
		if (mGUIView != null) {
			content = mGUIView.findViewById(R.id.content);
			content_land = mGUIView.findViewById(R.id.content_land);
			m1m0 = mGUIView.findViewById(R.id.m1m0);
			m1m1 = mGUIView.findViewById(R.id.m1m1);

			mGUIView.findViewById(R.id.button2).setVisibility(View.GONE);
			mGUIView.findViewById(R.id.button1).setVisibility(View.GONE);
			mGUIView.findViewById(R.id.button3).setVisibility(View.GONE);
		}

		// start chooser thread
		tutorialThread = new ChoooserFrameThread(this, handler2);
		tutorialThread.start();
	}

	private int id = -1;
	private View m1m0, m1m1;
	private boolean isCallShare = false;

	@Override
	protected void onResume() {
		super.onResume();

		if (!isCallShare) {
			refresh();
		}

		isCallShare = false;

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (tutorialThread != null) {
					tutorialThread.setEnableNext(true);
				}
			}
		}, 20);
	}

	private void refresh() {
		id = -1;
		handler.post(new Runnable() {

			@Override
			public void run() {
				if (content != null)
					content.setBackgroundDrawable(null);

				if (content_land != null)
					content_land.setBackgroundDrawable(null);
			}
		});
	}

	@Override
	public void onDrawFrame() {
		super.onDrawFrame();
		if (metaioSDK != null) {
			TrackingValuesVector poses = metaioSDK.getTrackingValues();
			if (poses.size() != 0) {
				if (id != poses.get(0).getCoordinateSystemID()) {
					id = poses.get(0).getCoordinateSystemID();
					updateUI();
				}
			}
		}
	}

	private void updateUI() {
		if (id >= 0) {
			content.post(new Runnable() {
				public void run() {

					// String folder = CommonAndroid
					// .getScreenSize(TutorialHelloWorld.this);
					// String nameLand =
					// "frame/android128_75/Halloween_frameW_03land.png";
					// String name =
					// "frame/android128_75/Halloween_frameW_03.png";
					//
					// String idstr = ",1,2,3,4,5,6,7,8,9,";
					// if (!idstr.contains("," + id + ",")) {
					// } else {
					// nameLand =
					// "frame/android128_75/Halloween_frameH_03land.png";
					// name = "frame/android128_75/Halloween_frameH_03.png";
					// }
					//
					// nameLand = nameLand.replace("128_75", folder);
					// name = name.replace("128_75", folder);
					//
					// content.setBackgroundDrawable(new BitmapDrawable(
					// getResources(), getBitmapFromAsset(name)));
					//
					// content_land.setBackgroundDrawable(new BitmapDrawable(
					// getResources(), getBitmapFromAsset(nameLand)));

					String key = id + "";

					if (mData.containsKey(key)) {
						post(key);
					}
				}

			});
		}
	}

	private String currentKey = null;

	private void post(String key) {
		if (currentKey == null) {
			currentKey = key;
			showFrame(true);
		} else if (!currentKey.equals(key)) {
			currentKey = key;
			showFrame(true);
		}
	}

	@SuppressWarnings("deprecation")
	private void showFrame(boolean isNew) {
		if (id == -1) {
			return;
		}
		if (currentKey != null) {
			String data[] = mData.get(currentKey);
			if (isNew) {
				tutorialThread.reload();
				index = 0;
			} else {
				if (data.length == 1) {
					return;
				}

				if (index < data.length - 1) {
					index++;
				} else if (index == data.length - 1) {
					index = 0;
				}
			}
			String nameNormal = String.format("frame/android128_75/%s.png", data[index]);
			String nameNormalLand = String.format("frame/android128_75/%sland.png", data[index]);

			content.setBackgroundDrawable(new BitmapDrawable(getResources(), getBitmapFromAsset(nameNormal)));
			content_land.setBackgroundDrawable(new BitmapDrawable(getResources(), getBitmapFromAsset(nameNormalLand)));
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			visivibily(content_land, true);
			visivibily(content, false);
			visivibily(m1m1, true);
			visivibily(m1m0, false);
		} else {
			visivibily(content_land, false);
			visivibily(content, true);

			visivibily(m1m1, false);
			visivibily(m1m0, true);
		}
	}

	private void visivibily(View content2, boolean b) {
		if (content2 != null) {
			content2.setVisibility(b ? View.VISIBLE : View.GONE);
		}
	}

	private Bitmap getBitmapFromAsset(String strName) {
		AssetManager assetManager = getAssets();
		InputStream istr = null;
		try {
			istr = assetManager.open(strName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		return bitmap;
	}

	private int start = Camera.FACE_FRONT;

	public void onButtonClickCamera(View v) {
		final CameraVector cameras = metaioSDK.getCameraList();
		if (cameras.size() > 0) {
			com.metaio.sdk.jni.Camera camera = cameras.get(0);
			for (int i = 0; i < cameras.size(); i++) {

				if (cameras.get(i).getFacing() == start) {
					camera = cameras.get(i);
					break;
				} else {
					camera = cameras.get(i);
				}
			}

			if (start == Camera.FACE_FRONT) {
				start = Camera.FACE_BACK;
			} else {
				start = Camera.FACE_FRONT;
			}

			metaioSDK.startCamera(camera);
		}

		updateAutoFocus();
	}

	private void updateAutoFocus() {
		try {
			android.hardware.Camera camera = IMetaioSDKAndroid.getCamera(this);
			android.hardware.Camera.Parameters params = camera.getParameters();

			params.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_AUTO);
			params.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
			params.setSceneMode(android.hardware.Camera.Parameters.SCENE_MODE_AUTO);
			params.setWhiteBalance(android.hardware.Camera.Parameters.WHITE_BALANCE_AUTO);
			params.setExposureCompensation(0);
			params.setPictureFormat(ImageFormat.JPEG);
			// params.setJpegQuality(100);
			params.setRotation(90);
			camera.setParameters(params);
		} catch (Exception exception) {

		}
	}

	public void onButtonClick(View v) {
		finish();
	}

	private Handler handler = new Handler();

	public void onButtonSave(View v) {
		final View view = findViewById(R.id.button3);
		final View view2 = findViewById(R.id.Button03);

		view.setVisibility(View.GONE);
		view2.setVisibility(View.GONE);

		new MediaThread(this).start();
		final String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile().getAbsolutePath() + "/tem.png";
		mPath = path;

		if (metaioSDK != null) {
			if (tutorialThread != null) {
				tutorialThread.setEnableNext(false);
			}
			metaioSDK.registerCallback(new IMetaioSDKCallback() {
				@Override
				public void onScreenshotSaved(String filepath) {
					super.onScreenshotSaved(filepath);
					handler.post(new Runnable() {
						@Override
						public void run() {
							save(null, path);

							handler.postDelayed(new VisibleView(view), VisibleView.TIME);
							handler.postDelayed(new VisibleView(view2), VisibleView.TIME);
						}
					});
				}
			});
		}
		metaioSDK.requestScreenshot(path);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (tutorialThread != null) {
			tutorialThread.setEnableNext(false);
		}
	}

	private void save(ImageStruct image, String path) {
		Bitmap bitmap = null;
		if (content != null && content.getVisibility() == View.VISIBLE) {
			bitmap = getDrawingCache(content);
		} else {
			bitmap = getDrawingCache(content_land);
		}

		new SaveFileDialog(this, bitmap, BitmapFactory.decodeFile(path)) {
			public void dismiss() {
				super.dismiss();
				if (tutorialThread != null) {
					tutorialThread.setEnableNext(true);
				}
			};

			public void callShare() {
				isCallShare = true;
				super.callShare();
			}
		}.show();

		/**
		 * delete file path
		 */
		try {
			new File(path).delete();
		} catch (Exception exception) {

		}
	}

	public static Bitmap getDrawingCache(View child) {
		try {

			child.setDrawingCacheEnabled(true);
			child.buildDrawingCache(true);

			child.buildDrawingCache(true);
			Bitmap bm = Bitmap.createBitmap(child.getDrawingCache());

			child.setDrawingCacheEnabled(false);

			return bm;

		} catch (Exception exception) {
			return null;
		}
	}

	@Override
	protected void startCamera() {
		super.startCamera();
		if (mGUIView != null) {
			mGUIView.findViewById(R.id.button2).setVisibility(View.VISIBLE);
			mGUIView.findViewById(R.id.button1).setVisibility(View.VISIBLE);
			mGUIView.findViewById(R.id.button3).setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void loadContents() {

		new AsyncTask<String, String, String>() {
			@Override
			protected String doInBackground(String... params) {
				try {
					String trackingConfigFile = AssetsManager.getAssetPath(getApplicationContext(), "TutorialHelloWorld/Assets/TrackingData_MarkerlessFast.xml");
					boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile);
					MetaioDebug.log("Tracking data loaded: " + result);
				} catch (Exception e) {
					MetaioDebug.printStackTrace(Log.ERROR, e);
				}
				return null;
			}

			protected void onPostExecute(String result) {
				super.onPostExecute(result);

				if (dialog != null) {
					dialog.dismiss();
				}
			};
		}.execute("");
		// new Thread() {
		// public void run() {
		// try {
		// String trackingConfigFile = AssetsManager
		// .getAssetPath(getApplicationContext(),
		// "TutorialHelloWorld/Assets/TrackingData_MarkerlessFast.xml");
		// boolean result = metaioSDK
		// .setTrackingConfiguration(trackingConfigFile);
		// MetaioDebug.log("Tracking data loaded: " + result);
		// } catch (Exception e) {
		// MetaioDebug.printStackTrace(Log.ERROR, e);
		// }
		// };
		// }.start();

		updateAutoFocus();
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// Not used in this tutorial
	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		return null;
	}

	// private class TutorialThread extends Thread {
	// private long time = 5000;
	//
	// public void reload() {
	// time = 5000;
	// }
	//
	// @Override
	// public void run() {
	// super.run();
	//
	// while (!isFinishing()) {
	// try {
	// Thread.sleep(1000);
	// } catch (InterruptedException e) {
	// }
	//
	// time = time - 1000;
	//
	// if (time <= 0) {
	// reload();
	// handler2.sendEmptyMessage(0);
	// }
	// }
	// }
	// }
}
