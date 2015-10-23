package com.vnp.mlook.acticities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.vnp.mlook.LoginReference;
import com.vnp.mlook.LoginReference.LoginLogoutCallBack;
import com.vnp.mlook.R;
import com.vnp.mlook.fragment.MLookAction;
import com.vnp.mlook.fragment.account.ForgotPasswordFragment;
import com.vnp.mlook.fragment.account.LoginFragment;
import com.vnp.mlook.fragment.account.SupportFragment;
import com.vnp.mlook.fragment.game.MainFragment;
import com.vnp.mlook.views.HeaderView.HeaderCallBaclItem;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
		com.vnp.mlook.views.HeaderView.CallBack {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((com.vnp.mlook.views.HeaderView) findViewById(R.id.headerView1))
				.setCallBack(this);
		callAction(MLookAction.MAIN);

	}

	@Override
	public void CallBackheadera(HeaderCallBaclItem index) {
		if (index == HeaderCallBaclItem.HEADER_MENU) {
			AlphaAnimation animation = new AlphaAnimation(0.5f, 1f);
			animation.setDuration(500);
			findViewById(R.id.menuView1).startAnimation(animation);
			findViewById(R.id.menuView1).setVisibility(View.VISIBLE);
		} else if (index == HeaderCallBaclItem.HEADER_1) {
			AlphaAnimation animation = new AlphaAnimation(0.5f, 1f);
			animation.setDuration(500);
			findViewById(R.id.menu1View1).startAnimation(animation);
			findViewById(R.id.menu1View1).setVisibility(View.VISIBLE);
		} else if (index == HeaderCallBaclItem.HEADER_2) {
			AlphaAnimation animation = new AlphaAnimation(0.5f, 1f);
			animation.setDuration(500);
			findViewById(R.id.menu2View1).startAnimation(animation);
			findViewById(R.id.menu2View1).setVisibility(View.VISIBLE);
		} else if (index == HeaderCallBaclItem.HEADER_3) {
			AlphaAnimation animation = new AlphaAnimation(0.5f, 1f);
			animation.setDuration(500);
			findViewById(R.id.menu3View1).startAnimation(animation);
			findViewById(R.id.menu3View1).setVisibility(View.VISIBLE);
		}
	}

	public void putViewFragment(Fragment fragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		// fragmentTransaction.add(R.id.idMain, fragment);

		fragmentTransaction.replace(R.id.idMain, fragment);
		// if (!(fragment instanceof LoginFragment)) {
		fragmentTransaction.addToBackStack(null);
		// }

		fragmentTransaction.commit();
	}

	@Override
	public void onBackPressed() {
		if (findViewById(R.id.menu3View1).getVisibility() == View.VISIBLE) {
			findViewById(R.id.menu3View1).setVisibility(View.GONE);
		} else if (findViewById(R.id.menu1View1).getVisibility() == View.VISIBLE) {
			findViewById(R.id.menu1View1).setVisibility(View.GONE);
		} else if (findViewById(R.id.menu2View1).getVisibility() == View.VISIBLE) {
			findViewById(R.id.menu2View1).setVisibility(View.GONE);
		}
		if (findViewById(R.id.menuView1).getVisibility() == View.VISIBLE) {
			findViewById(R.id.menuView1).setVisibility(View.GONE);
		} else {
			FragmentManager fragmentManager = getSupportFragmentManager();
			super.onBackPressed();
			if (fragmentManager.findFragmentById(R.id.idMain) == null) {
				finish();
			}
		}
	}

	public void callAction(MLookAction mLookAction) {
		if (mLookAction == MLookAction.FORGOTPASSWORK) {
			putViewFragment(new ForgotPasswordFragment());
		} else if (mLookAction == MLookAction.LOGIN) {
			putViewFragment(new LoginFragment());
		} else if (mLookAction == MLookAction.MENU_SUPPORT) {
			putViewFragment(new SupportFragment());
		} else if (mLookAction == MLookAction.MENU_LOGIN) {
			putViewFragment(new LoginFragment());
		} else if (mLookAction == MLookAction.MAIN) {
			putViewFragment(new MainFragment());
		} else if (mLookAction == MLookAction.MENU_HOME) {
			putViewFragment(new MainFragment());
		} else if (mLookAction == MLookAction.MENU_PERSION) {
		} else if (mLookAction == MLookAction.MENU_LOGOUT) {
			LoginReference.getInStance().init(this);
			LoginReference.getInStance().logout(new LoginLogoutCallBack() {
				@Override
				public void onComplete(String arg0) {

				}

				@Override
				public void onCancel() {

				}

				@Override
				public void error(String arg0) {

				}
			});
		}
	}
}
