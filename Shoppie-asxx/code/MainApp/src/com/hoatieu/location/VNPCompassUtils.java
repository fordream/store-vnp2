package com.hoatieu.location;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class VNPCompassUtils implements SensorEventListener {
	private static VNPCompassUtils instance = new VNPCompassUtils();
	private SensorManager mSensorManager;

	private VNPCompassUtils() {

	}

	public static VNPCompassUtils getInstance() {
		return instance == null ? (instance = new VNPCompassUtils()) : instance;
	}

	private Context mContext;

	public void init(Context context) {
		mContext = context;
		mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
	}

	public void onResume() {
		currentDegree = 0;
		gravity[0] = 0;
		gravity[1] = 0;
		gravity[2] = 0;

		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);

		// if (Build.VERSION.SDK_INT <= 10) {
		// mSensorManager.registerListener(this,
		// mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
		// SensorManager.SENSOR_DELAY_GAME);
		// } else {
		//
		// mSensorManager.registerListener(this,
		// mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
		// SensorManager.SENSOR_DELAY_UI);
		// }

	}

	public void onPause() {
		mSensorManager.unregisterListener(this);
	}

	public float currentDegree = 0f;
	public float degree;
	double[] gravity = new double[3];
	private float[] mMagneticValues;
	private float[] mAccelerometerValues;

	// private float mAzimuth;
	// private float mPitch;
	// private float mRoll;

	@Override
	public void onSensorChanged(SensorEvent event) {
		// if (Build.VERSION.SDK_INT <= 10) {
		// angle between the magnetic north direction
		// 0=North, 90=East, 180=South, 270=West
		degree = Math.round(event.values[0]);

		// } else {
		// // Log.e("ABC", event.values.length + "");
		// // degree = Math.round(event.values[0]);
		// // switch (event.sensor.getType()) {
		// // case Sensor.TYPE_MAGNETIC_FIELD:
		// // mMagneticValues = event.values.clone();
		// // break;
		// // case Sensor.TYPE_ACCELEROMETER:
		// // mAccelerometerValues = event.values.clone();
		// // break;
		// // }
		// //
		// // if (mMagneticValues != null && mAccelerometerValues != null) {
		// // float[] R = new float[3];
		// // SensorManager.getRotationMatrix(R, null, mAccelerometerValues,
		// // null);
		// // float[] orientation = new float[3];
		// // SensorManager.getOrientation(R, orientation);
		// // mAzimuth = orientation[0];
		// // mPitch = orientation[1];
		// // mRoll = orientation[2];
		// // }
		// //
		// // degree = mAzimuth;
		//
		// }
		// RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
		// Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		// ra.setDuration(210);
		// ra.setFillAfter(true);

		if (sensorEventListener != null) {
			sensorEventListener.onSensorChanged(event);
		}
		currentDegree = -degree;

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	SensorEventListener sensorEventListener;

	public void addSensorEventListener(SensorEventListener sensorEventListener) {
		this.sensorEventListener = sensorEventListener;
	}

	public boolean isSupported() {
		return mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null;
	}

}
