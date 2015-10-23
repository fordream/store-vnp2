package com.example.org.com.driver;

import java.util.Random;

import android.os.Handler;
import android.os.Message;

import com.vnp.core.activity.BaseApplication;

public class ProClassApplication extends BaseApplication {

	public static final int WHAT_START = 0;
	public static final int WHAT_NORMAL = 1;
	public static final int WHAT_ERROR = 2;
	public static final long TIME = 3000;
	public static final int MAXFRAME = 180;
	public static final String[] ERRORCODE = new String[] { "P0001", "P0002",
			"P0003", "P0004", "P0005", "P0006", "P0007", "P0008", "P0009",
			"P0010"
	};

	@Override
	public void onCreate() {
		super.onCreate();
	}

	private ProHander proHander = new ProHander();

	private class ProHander extends Handler {

		private boolean isEnable = false;

		public void setEnable(boolean b) {
			isEnable = b;
		}

		@Override
		public void dispatchMessage(Message msg) {
			if (isEnable && proLoader != null) {
				if (msg.what == WHAT_START) {
					proLoader.callBackStatus(WHAT_START, new String[] {}, 0);
					nextStep(WHAT_START);
				} else if (msg.what == WHAT_NORMAL) {
					proLoader.callBackStatus(WHAT_NORMAL, new String[] {},
							new Random().nextInt(MAXFRAME) + 1);
					nextStep(WHAT_NORMAL);
				} else if (msg.what == WHAT_ERROR) {

					int count = new Random().nextInt(4) + 1;
					String string[] = new String[count];
					int c = 0;
					for (int i = 0; i < count; i++) {
						c = c + new Random().nextInt(10) + 1;
						string[i] = "P000" + c;
					}

					proLoader.callBackStatus(WHAT_ERROR, string,
							new Random().nextInt(MAXFRAME) + 1);

					nextStep(WHAT_ERROR);
				}
			}
		}

		private void nextStep(int whatNormal) {
			boolean isError = new Random().nextInt(100) < 30;
			if (whatNormal == WHAT_START) {
				proHander.sendEmptyMessageDelayed(WHAT_NORMAL, TIME);
			} else if (whatNormal == WHAT_NORMAL) {
				//if (isError) {
					proHander.sendEmptyMessageDelayed(WHAT_ERROR, TIME);
				//} else {
				//	proHander.sendEmptyMessageDelayed(WHAT_NORMAL, TIME);
				//}
			} else if (whatNormal == WHAT_ERROR) {

			}
		}
	}

	private IProLoader proLoader;

	public void registerProloader(IProLoader proLoader) {
		this.proLoader = proLoader;
	}

	public void unRegisterProloader() {
		this.proLoader = null;
	}

	public void startCheck() {
		proHander.setEnable(true);
		proHander.sendEmptyMessageDelayed(WHAT_START, TIME);
		if (proLoader != null)
			proLoader.callBackStatus(WHAT_START, new String[] {}, 0);
	}

	public void stopCheck() {
		proHander.setEnable(false);
	}

	public interface IProLoader {
		public void callBackStatus(int status, String[] erorrList, int frameSize);
	}
}