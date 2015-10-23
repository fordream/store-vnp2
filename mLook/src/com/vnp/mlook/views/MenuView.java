package com.vnp.mlook.views;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ict.library.adapter.CommonBaseAdapter;
import com.ict.library.adapter.CommonGenderView;
import com.ict.library.view.CustomLinearLayoutView;
import com.vnp.mlook.LoginReference;
import com.vnp.mlook.R;
import com.vnp.mlook.acticities.MainActivity;
import com.vnp.mlook.fragment.MLookAction;
import com.vnp.mlook.models.ItemMenu;
import com.vnp.mlook.utils.DBAdapter;

public class MenuView extends CustomLinearLayoutView {

	public MenuView(Context context) {
		super(context);
		init(R.layout.menuview);
	}

	public MenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.menuview);
	}

	@Override
	public void init(int res) {
		super.init(res);
		setVisibility(View.GONE);
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setEnabled(false);
				AlphaAnimation animation = new AlphaAnimation(1f, 0f);
				animation.setDuration(500);
				animation.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						setVisibility(View.GONE);
						setEnabled(true);
					}
				});
				startAnimation(animation);
			}
		});

	}

	ListView listView;

	@Override
	public void setVisibility(int visibility) {
		super.setVisibility(visibility);
		listView = (ListView) findViewById(R.id.lview);
		LoginReference.getInStance().init((Activity) getContext());
		if (visibility == View.VISIBLE) {
			List<Object> lMenus = new ArrayList<Object>();
			if (LoginReference.getInStance().isLogin()) {
				lMenus.add(new ItemMenu("Trang chu", "", MLookAction.MENU_HOME));
				lMenus.add(new ItemMenu("Luong tin", "",
						MLookAction.MENU_LUONGTIN));
				lMenus.add(new ItemMenu("Tin nhan", "",
						MLookAction.MENU_MESSAGE));
				lMenus.add(new ItemMenu("Thong bao", "",
						MLookAction.MENU_THONGBAO));
				lMenus.add(new ItemMenu("Ban be", "", MLookAction.MENU_FRIEND));
				lMenus.add(new ItemMenu("Thong tin ca nhan", "",
						MLookAction.MENU_PERSION));
				lMenus.add(new ItemMenu("Ho tro", "", MLookAction.MENU_SUPPORT));
				lMenus.add(new ItemMenu("Dang xuat", "",
						MLookAction.MENU_LOGOUT));
			} else {
				lMenus.add(new ItemMenu("Trang chu", "", MLookAction.MENU_HOME));
				lMenus.add(new ItemMenu("Ho tro", "", MLookAction.MENU_SUPPORT));
				lMenus.add(new ItemMenu("LOGIN", "", MLookAction.MENU_LOGIN));
			}

			listView.setAdapter(new CommonBaseAdapter(getContext(), lMenus,
					new CommonGenderView() {
						@Override
						public CustomLinearLayoutView getView(Context arg0,
								Object arg1) {
							return new ItemView(arg0);
						}
					}));
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					setVisibility(View.GONE);
					((MainActivity) getContext()).callAction(((ItemMenu) arg0
							.getItemAtPosition(arg2)).tagAction);
				}
			});
		}
	}

	@Override
	public void setGender() {

	}

	public class ItemView extends CustomLinearLayoutView {

		public ItemView(Context context) {
			super(context);
			init(R.layout.persion_menu_infor_item);
		}

		@Override
		public void setGender() {
			TextView textView = (TextView) findViewById(R.id.textView1);
			textView.setText(((ItemMenu) getData()).name);
		}
	}
}