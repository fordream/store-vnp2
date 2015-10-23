package vnp.mr.mintmedical.s5;

import vnp.mr.mintmedical.HeaderView;
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.R.id;
import vnp.mr.mintmedical.R.layout;
import vnp.mr.mintmedical.R.string;
import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.base.MBaseFragment.IMBaseFragmentOnClickListener;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.item.S51Item;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

public class S5NewCaseActivity extends MBaseActivity {

	@Override
	public int getLayout() {
		return R.layout.s5activity;
	}

	private MBaseFragment baseFagment;
	HeaderView headerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		headerView = (HeaderView) findViewById(R.id.activitymain_headerview);
		headerView.showButton(false, true);

		headerView.updateTextButtonRight(R.string.cancel);
		headerView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (baseFagment instanceof S52) {
					gotoS53((S52) baseFagment);
				} else
					finish();
			}
		});
		headerView.setOnClickListenerButtonBack(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		final S51 s51 = new S51();
		s51.setOnClickListener(new IMBaseFragmentOnClickListener() {
			@Override
			public void onClick(Object dialog, int which) {
				S51Item item = (S51Item) dialog;
				gotoS52(item, s51);
			}

		});
		changeFragemtn(R.id.s5_content_main, s51);
	}

	protected void gotoS53(S52 baseFagment2) {
		S53 s52 = new S53();
		s52.setParent(baseFagment2);
		s52.setOnClickListener(new IMBaseFragmentOnClickListener() {
			@Override
			public void onClick(Object dialog, int which) {
			
				String message = "Request sent successfully! Please wait for doctor's confirmation. For any emergency, please seek doctor consult directly.";
				
//				message = "Request sent successfully! Please wait for doctor's confirmation";
				MintUtils.showDialog(S5NewCaseActivity.this, message,
						
						new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						finish();
					}
				});
			}
		});
		changeFragemtn(R.id.s5_content_main, s52);
	}

	private void gotoS52(S51Item item, S51 s51) {
		S52 s52 = new S52(item);
		s52.setParent(s51);
		changeFragemtn(R.id.s5_content_main, s52);
	}

	@Override
	public void changeFragemtn(int r_id_content_frame, Fragment rFragment) {
		super.changeFragemtn(r_id_content_frame, rFragment);
		headerView.updateText(((MBaseFragment) rFragment).getHeaderRes());
		// ((MBaseFragment) rFragment).setParent(baseFagment);
		baseFagment = (MBaseFragment) rFragment;

		initHeader();
	}

	private void initHeader() {
		if (baseFagment instanceof S51) {
			headerView.showButton(true, true);
			headerView.updateTextButtonRight(R.string.cancel);
		} else if (baseFagment instanceof S52) {
			headerView.showButton(true, true);
			headerView.updateTextButtonRight(R.string.next);
		} else if (baseFagment instanceof S54) {
			headerView.showButton(false, true);
			headerView.updateTextButtonRight(R.string.done);
		} else if (baseFagment instanceof S53) {
			headerView.showButton(true, true);
			headerView.updateTextButtonRight(R.string.cancel);
		}

		headerView.updateText(baseFagment.getHeaderRes());
	}

	@Override
	public void onBackPressed() {
		if (baseFagment != null && baseFagment instanceof S54) {
			finish();
		}

		if (baseFagment != null && baseFagment.getParent() != null) {
			changeFragemtn(R.id.s5_content_main, baseFagment.getParent());
			return;
		}

		super.onBackPressed();
	}

}