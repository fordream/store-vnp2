package vnp.mr.mintmedical;

import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.callback.S9Callback;
import vnp.mr.mintmedical.config.DBConfig;
import vnp.mr.mintmedical.item.S9Item;
import vnp.mr.mintmedical.item.UserLogin;
import vnp.mr.mintmedical.view.AvatarView;
import vnp.mr.mintmedical.view.MintProgessDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.viewpagerindicator.db.DBS9Item;
import com.viewpagerindicator.db.DBUserLogin;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.ImageLoaderUtils;

public class S9Activity extends MBaseActivity {

	public int getLayout() {
		return R.layout.s9activity;
	}

	AvatarView avatarView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		HeaderView headerView = (HeaderView) findViewById(R.id.activitymain_headerview);
		headerView.updateText(R.string.account);
		headerView.showButton(true, false);
		headerView.setOnClickListenerButtonBack(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		avatarView = getView(R.id.s9avatarView1);
		avatarView.setType(AvatarView.S9);
		findViewById(R.id.s9button).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// update_link
						try {
							CommonAndroid.callWeb(S9Activity.this,
									new DBConfig(S9Activity.this)
											.getUpdateUrl());
						} catch (Exception exception) {
						}
					}
				});

	}

	@Override
	protected void onResume() {
		super.onResume();
		resize.resizeSacle(findViewById(R.id.s9content_main),
				MintUtils.WIDTH_ITEM, LayoutParams.MATCH_PARENT);
		resize.resizeSacle(findViewById(R.id.s9main_name),
				MintUtils.WIDTH_ITEM, 105);
		resize.resizeSacle(findViewById(R.id.s9_main_block2),
				MintUtils.WIDTH_ITEM, 60);
		resize.resizeSacle(findViewById(R.id.s9_main_block3),
				MintUtils.WIDTH_ITEM, 60);
		resize.resizeSacle(findViewById(R.id.s9button), MintUtils.WIDTH_ITEM,
				45);
		resize.setTextsize(findViewById(R.id.s9texxt1),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s9texxt2),
				MintUtils.TEXTSIZE_ITEM2);
		resize.setTextsize(findViewById(R.id.s9texxt3),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s9texxt4),
				MintUtils.TEXTSIZE_ITEM2);
		resize.setTextsize(findViewById(R.id.s9texxt5),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s9texxt6),
				MintUtils.TEXTSIZE_ITEM2);

		updateUi();

		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.setExeCallBackOption(new ExeCallBackOption(this, true,
				R.string.loadding, new MintProgessDialog(this)));
		exeCallBack.executeAsynCallBack(new S9Callback(this) {
			@Override
			public void loadSucess(String response) {
				super.loadSucess(response);
				updateUi();
			}
		});
	}

	private void updateUi() {
		S9Item s9Item = (S9Item) new DBS9Item(this).getData();
		if (s9Item != null) {
			setText(R.id.s9texxt1, s9Item.fullname);

			String date = s9Item.getString("member_expire_date");

			// MintUtils.showDialog(this, date);
			if (date != null && date.contains("0000")) {
				date = "Not yet membership";
				date ="Non-member";
			} else {
				if (date != null)
					date = "Expire on " + date;
			}

			setText(R.id.s9texxt2, date);

			String doctor = s9Item.getString("doctor_name");

			if (MintUtils.isNullOrBlank(doctor)) {
				doctor = "Choose your doctor. Click Update My Account";
			}

			setText(R.id.s9texxt4, doctor);

			setText(R.id.s9texxt6, config(s9Item.getString("doctor_email")));
			avatarView.loadAvartar(s9Item.getString("avatar"),
					R.drawable.avatar);
			// avatarView.loadAvartar("http://google.com",R.drawable.avatar);

		}
	}

	private String config(String string) {
		if (string == null)
			return "";

		if ("null".toLowerCase().equals(string.toLowerCase()))
			return "";
		return string;
	}

	private void setText(int res, String custName) {
		((TextView) findViewById(res)).setText(custName);
	}
}