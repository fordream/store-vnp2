package org.vnp.flyingtiger.thread;

import android.app.Activity;
import android.os.Handler;

public class ChoooserFrameThread extends Thread {
	private Activity activity;
	Handler handler2;

	public ChoooserFrameThread(Activity activity, Handler handler2) {
		this.activity = activity;
		this.handler2 = handler2;
	}

	private boolean enableNext = true;

	public void setEnableNext(boolean enableNext) {
		this.enableNext = enableNext;
	}

	private long time = 1000;

	public void reload() {
		time = 1000;
	}

	@Override
	public void run() {
		super.run();

		while (!activity.isFinishing()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			time = time - 1000;

			if (time <= 0) {
				reload();

				if (enableNext) {
					handler2.sendEmptyMessage(0);
				}
			}
		}
	}

}
