package org.vnp.flyingtiger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import jp.flyingtiger.arcamera.R;

import org.vnp.flyingtiger.base.MbaseAdialog;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnp.core.base.callback.CallBack;
import com.vnp.core.base.callback.ExeCallBack;
import com.vnp.core.base.common.CommonAndroid;

public class SaveFileDialog extends MbaseAdialog implements android.view.View.OnClickListener {
	private Bitmap bitmap;
	Bitmap bitmap2;

	public SaveFileDialog(Context context, Bitmap bitmap, Bitmap bitmap2) {
		super(context);
		this.bitmap = bitmap;
		this.bitmap2 = bitmap2;
	}

	private static final int[] resShare = new int[] { R.id.share_back, R.id.share_twitter, R.id.share_header, };
	private TextView share_status;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		for (int res : resShare) {
			if (findViewById(res) != null) {
				findViewById(res).setVisibility(View.GONE);
			}
		}

		share_status = (TextView) findViewById(R.id.share_status);

		((ImageView) findViewById(R.id.imageView1)).setImageBitmap(bitmap2);
		((ImageView) findViewById(R.id.imageView2)).setImageBitmap(bitmap);
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);

		findViewById(R.id.button1).setVisibility(View.GONE);
		findViewById(R.id.button2).setVisibility(View.GONE);

		if (findViewById(R.id.share_twitter) != null) {
			findViewById(R.id.share_twitter).setOnClickListener(this);
		}

		if (findViewById(R.id.share_back) != null) {
			findViewById(R.id.share_back).setOnClickListener(this);
		}

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				startSaveImage();
			}

		}, 500);
	}

	private Handler handler = new Handler();
	private String pathToSave;

	private void startSaveImage() {
		share_status.setText("Saving to file ...");
		CallBack callBack = new CallBack() {

			@Override
			public void onCallBack(Object object) {
				if ("1".equals(object)) {
					for (int res : resShare) {
						if (findViewById(res) != null) {
							findViewById(res).setVisibility(View.VISIBLE);
						}
					}

					String message = String.format(getContext().getString(R.string.img_save_success), pathToSave);
					share_status.setText(message);
					share_status.setVisibility(View.GONE);
					CommonAndroid.toast(getContext(), message);

				} else {
					String message = String.format(getContext().getString(R.string.img_save_fail), pathToSave);
					share_status.setText(message);
					if (findViewById(R.id.share_back) != null) {
						findViewById(R.id.share_back).setVisibility(View.VISIBLE);
					}
				}
			}

			@Override
			public Object execute() {
				//FtcCamera
				//Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
				File fPath = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/FtcCamera");

				if (!fPath.exists()) {
					fPath.mkdirs();
				}
				// File f = new
				// File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile()
				// + "/ANDRO100", System.currentTimeMillis() + ".jpg");
				File f = new File(fPath, System.currentTimeMillis() + ".jpg");

				pathToSave = f.getAbsolutePath();
				try {
					Bitmap bitmap = TutorialHelloWorld.getDrawingCache(findViewById(R.id.main));
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
					f.createNewFile();
					FileOutputStream fo = new FileOutputStream(f);
					fo.write(bytes.toByteArray());
					fo.close();

					try {
						CommonAndroid.DEVICEID.rescanSdcard(getContext());
					} catch (Exception exception) {

					}
					

					try {
						//MediaStore.Images.Media.insertImage(getContext().getContentResolver(), f.getAbsolutePath(), f.getName(), f.getName());
						getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
					} catch (Exception e) {

					}

					return "1";
				} catch (Exception exception) {

				}
				return "0";
			}
		};

		new ExeCallBack().executeAsynCallBack(callBack);
	}

	@Override
	public int getLayout() {
		return R.layout.img;
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.share_twitter) {
			// save();
			final String packageSkype = "com.twitter.android";

			if (utils.CommonAndroid.havePackage(getContext(), packageSkype)) {
				callShare();
			} else {
				Builder builder = new Builder(getContext());
				builder.setTitle(R.string.app_name);
				builder.setMessage(R.string.skype_install);
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						CommonAndroid.showMarketProductBuyPackage(getContext(), packageSkype);
					}
				});

				builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SaveFileDialog.this.dismiss();
					}
				});
				builder.show();
			}

		} else if (v.getId() == R.id.share_back) {
			dismiss();
		} else if (v.getId() == R.id.button2) {
			// saveFile();
		} else {
			dismiss();
		}
	}

	// private void saveFile() {
	// File f = new
	// File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile(),
	// System.currentTimeMillis() + ".jpg");
	// String message = "The image can not save file : " + f.getAbsolutePath();
	// try {
	// Bitmap bitmap =
	// TutorialHelloWorld.getDrawingCache(findViewById(R.id.main));
	// ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	// bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
	// f.createNewFile();
	// FileOutputStream fo = new FileOutputStream(f);
	// fo.write(bytes.toByteArray());
	// fo.close();
	//
	// message = "The image save to file :" + f.getAbsolutePath();
	//
	// final String packageSkype = "com.skype.raider";
	// if (utils.CommonAndroid.havePackage(getContext(), packageSkype)) {
	// // call to share
	// if (findViewById(R.id.share_controler) != null) {
	// findViewById(R.id.share_controler).setVisibility(View.VISIBLE);
	// }
	//
	// } else {
	// Builder builder = new Builder(getContext());
	// builder.setTitle(R.string.app_name);
	// builder.setMessage(R.string.skype_install);
	// builder.setCancelable(false);
	// builder.setPositiveButton(R.string.ok, new
	// DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// CommonAndroid.showMarketProductBuyPackage(getContext(), packageSkype);
	// }
	// });
	//
	// builder.setNegativeButton(R.string.cancel, new
	// DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// SaveFileDialog.this.dismiss();
	// }
	// });
	// builder.show();
	// }
	// } catch (Exception e) {
	// // builder.setMessage("The image can not save file : " +
	// // f.getAbsolutePath());
	// dismiss();
	// }
	//
	// try {
	// CommonAndroid.DEVICEID.rescanSdcard(getContext());
	// } catch (Exception exception) {
	//
	// }
	//
	//
	// CommonAndroid.toast(getContext(), message);
	// }

	public void callShare() {
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		Uri screenshotUri = Uri.parse(pathToSave);
		sharingIntent.setType("image/*");

		sharingIntent.putExtra(Intent.EXTRA_TEXT, "#flyingtiger http://ftc-camera.net");
		sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);

		PackageManager packageManager = getContext().getPackageManager();
		List<ResolveInfo> lract = packageManager.queryIntentActivities(sharingIntent, PackageManager.MATCH_DEFAULT_ONLY);

		boolean resolved = false;

		for (ResolveInfo ri : lract) {
			if (ri.activityInfo.name.contains("com.twitter.android")) {
				sharingIntent.setClassName(ri.activityInfo.packageName, ri.activityInfo.name);
				resolved = true;
				break;
			}
		}

		if (resolved) {
			dismiss();
			getContext().startActivity(sharingIntent);
		}
	}

}
