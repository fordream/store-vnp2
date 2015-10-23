package org.com.vn.loxleycolour;

import android.app.Application;
import android.util.Log;
import org.com.vn.loxleycolour.v1.R;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.push.CustomPushNotificationBuilder;
import com.urbanairship.push.PushManager;

public class MyApplication extends Application {

	@Override
	public void onCreate() {

		super.onCreate();

		AirshipConfigOptions options = AirshipConfigOptions
				.loadDefaultOptions(this);

		// Optionally, customize your config at runtime:
		//
		// options.inProduction = false;
		// options.developmentAppKey = "Your Development App Key";
		// options.developmentAppSecret "Your Development App Secret";

		
		//options.developmentAppKey = "Your development app key";
		//options.productionAppKey = "Your production app key";
		options.inProduction = false; //determines which app key to use
		 
		
		
		UAirship.takeOff(this, options);

		PushManager.enablePush();
		Logger.logLevel = Log.VERBOSE;

		// use CustomPushNotificationBuilder to specify a custom layout
		CustomPushNotificationBuilder nb = new CustomPushNotificationBuilder();

		nb.statusBarIconDrawableId = R.drawable.icon;// custom status bar
														// icon

		nb.layout = R.layout.notification;
		nb.layoutIconDrawableId = R.drawable.icon;
		nb.layoutIconId = R.id.icon;
		nb.layoutSubjectId = R.id.subject;
		nb.layoutMessageId = R.id.message;

		// customize the sound played when a push is received
		// nb.soundUri =
		// Uri.parse("android.resource://"+this.getPackageName()+"/"
		// +R.raw.cat);

		PushManager.shared().setNotificationBuilder(nb);
		PushManager.shared().setIntentReceiver(IntentReceiver.class);

	}
}
