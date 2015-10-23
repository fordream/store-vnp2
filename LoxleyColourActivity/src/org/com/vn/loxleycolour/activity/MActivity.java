package org.com.vn.loxleycolour.activity;

import java.util.ArrayList;
import java.util.List;

import org.com.cnc.common.android.Common;
import org.com.cnc.common.android.CommonView;
import org.com.cnc.common.android._interface.IHeaderView;
import org.com.vn.loxleycolour.CustomTabsActivity;
import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IActivity;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.IheaderLabel;
import org.com.vn.loxleycolour.database.DataStore;
import org.com.vn.loxleycolour.items.order1.ItemOrder;
import org.com.vn.loxleycolour.items.order2.ItemGetMyCurrentInvoices;
import org.com.vn.loxleycolour.soap.SoapCommon;
import org.com.vn.loxleycolour.views.ForgotPassView;
import org.com.vn.loxleycolour.views.HeaderView;
import org.com.vn.loxleycolour.views.LoginView;
import org.com.vn.loxleycolour.views.MyWebView;
import org.com.vn.loxleycolour.views.NewHomeVIew;
import org.com.vn.loxleycolour.views.OrderMenuView;
import org.com.vn.loxleycolour.views.complete.OrderComplateView;
import org.com.vn.loxleycolour.views.complete.OrderCompleteDetailView;
import org.com.vn.loxleycolour.views.orderinprogress.OrderInProgressDetailView;
import org.com.vn.loxleycolour.views.orderinprogress.OrderInProgressView;
import org.com.vn.loxleycolour.views.wiget.CustomDialog;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class MActivity extends Activity implements IHeaderView, Const,
		IheaderLabel, IActivity {
	public LinearLayout llContent;
	public HeaderView headerView;
	public List<View> lViews = new ArrayList<View>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		llContent = (LinearLayout) findViewById(R.id.llContent);
		headerView = (HeaderView) findViewById(R.id.headerView1);

		hiddenButtonback(true);
	}

	public void onClickBackHeader(Object arg0) {
		if (BACK.equals(arg0)) {
			// back
			onBack();
		} else {
			// logout
			DataStore.getInstance().saveConfig(LOGIN, false);
			Handler handler = CustomTabsActivity.HandlerTab;

			if (this instanceof OrdersActivity) {
				gotoLogin();
				return;
			}
			if (handler != null) {
				handler.sendEmptyMessage(0);
			}
		}
	}

	public void setTextHeader(String text) {
		headerView.setTextHeader(text);
	}

	public void addView(View view) {
		llContent.removeAllViews();
		int width = LayoutParams.FILL_PARENT;
		int height = LayoutParams.FILL_PARENT;
		LayoutParams layoutParams = new LayoutParams(width, height);
		llContent.addView(view, layoutParams);

		// llContent.addView(view);

		lViews.add(view);

		hiddenButtonback(lViews.size() <= 1);
	}

	public void setTextHeader(int res) {
		headerView.setTextHeader(res);
	}

	protected void onResume() {
		super.onResume();
		if (DataStore.getInstance().getConfig(LOGIN, false)) {
			headerView.setHiddenBtnLogout(false);
		} else {
			headerView.setHiddenBtnLogout(true);
		}
	}

	public void gotoFace() {
		// llContent.removeAllViews();
		// setTextHeader("Facebook");
		// MyWebView myWebView = new MyWebView(this);
		// addView(myWebView);
		// myWebView.loadUrl(URL_FACE);

		CommonView.callWeb(this, URL_FACE);
	}

	public void gotoWeb(String url) {

		if (Common.isNullOrBlank(url)) {
			CustomDialog customDialog = new CustomDialog(this);
			customDialog.setTitle("Check URL");
			customDialog.setMessage("Can't load!");
			customDialog.setTextButton("OK");
			customDialog.show();
			return;
		}
		setTextHeader("Detail");
		MyWebView myWebView = new MyWebView(this);
		addView(myWebView);
		myWebView.loadUrl(url);
	}

	public void gotoOrderMenu() {
		llContent.removeAllViews();
		lViews.clear();
		// WebViewController controller = new WebViewController(this);
		OrderMenuView myWebView = new OrderMenuView(this);
		addView(myWebView);

		hiddenButtonback(true);
		headerView.setHiddenBtnLogout(false);
		// llContent.addView(controller);
	}

	public void gotoNewHome() {
		//NewHomeVIew myWebView = new NewHomeVIew(this);
		//addView(myWebView);
		headerView.setTextHeader("News");
		MyWebView view = new MyWebView(this);
		view.loadUrl(Const.URL_NEW);
		addView(view);
	}

	public void gotoLogin() {
		llContent.removeAllViews();
		lViews.clear();
		// WebViewController controller = new WebViewController(this);
		LoginView myWebView = new LoginView(this);
		addView(myWebView);

		hiddenButtonback(true);
		headerView.setHiddenBtnLogout(true);
		// llContent.addView(controller);
	}

	public boolean gotoForgotPass() {
		CommonView.callWeb(this, URL_FORGOT);
		//llContent.removeAllViews();
		// setTextHeader("Forget password");
//		ForgotPassView myWebView = new ForgotPassView(this);
//		addView(myWebView);
		
		//MyWebView myWebView = new MyWebView(this);
		//addView(myWebView);
		
		//myWebView.loadUrl(URL_FORGOT);
		return false;
	}

	public void gotoOrderInProgress() {
		llContent.removeAllViews();
		OrderInProgressView myWebView = new OrderInProgressView(this);
		addView(myWebView);
	}

	public void gotoDetailOrderInProgess(ItemOrder orderNumber) {

		llContent.removeAllViews();

		OrderInProgressDetailView inProgressView = new OrderInProgressDetailView(
				this);
		inProgressView.setOrderNumber(orderNumber);
		addView(inProgressView);
	}

	public void gotoDetailOrderComplete(ItemGetMyCurrentInvoices InvoiceNumber) {

		llContent.removeAllViews();

		OrderCompleteDetailView inProgressView = new OrderCompleteDetailView(
				this);
		inProgressView.setOrderNumber(InvoiceNumber);
		addView(inProgressView);
	}

	public void gotoTrack(ItemGetMyCurrentInvoices itemOrder) {
		String url = SoapCommon.TRACK_URL + itemOrder.TrackingNumber;
		// TODO

		if (Common.isNullOrBlank(url)) {
			CustomDialog customDialog = new CustomDialog(this);
			customDialog.setTitle("Check URL");
			customDialog.setMessage("Can't load!");
			customDialog.setTextButton("OK");
			customDialog.show();
			return;
		}
		llContent.removeAllViews();
		setTextHeader("TRACK");
		MyWebView myWebView = new MyWebView(this);
		addView(myWebView);
		myWebView.loadUrl(url);
	}

	public void gotoCompleteOrder() {
		llContent.removeAllViews();
		OrderComplateView myWebView = new OrderComplateView(this);
		addView(myWebView);

	}

	public void gotoTWi() {
		// llContent.removeAllViews();
		// setTextHeader("TWister");
		// MyWebView myWebView = new MyWebView(this);
		// addView(myWebView);
		// myWebView.loadUrl(URL_TWI);
		CommonView.callWeb(this, URL_TWI);
	}

	public void hiddenButtonback(boolean b) {
		headerView.hiddenButtonback(b);
	}

	public boolean onBack() {
		if (lViews.size() <= 1) {
			finish();
		} else {
			llContent.removeAllViews();
			lViews.remove(lViews.size() - 1);

			int width = LayoutParams.FILL_PARENT;
			int height = LayoutParams.FILL_PARENT;
			LayoutParams layoutParams = new LayoutParams(width, height);
			llContent.addView(lViews.get(lViews.size() - 1), layoutParams);
			hiddenButtonback(lViews.size() <= 1);

			((IView) lViews.get(lViews.size() - 1)).reset();
		}
		return false;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

}
