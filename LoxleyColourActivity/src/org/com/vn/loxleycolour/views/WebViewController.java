package org.com.vn.loxleycolour.views;

import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.IView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewController extends WebView implements IView {
	public boolean isTimer = false;

	public WebViewController(Context context, AttributeSet attrs) {
		super(context, attrs);
		config();
	}

	public WebViewController(Context context) {
		super(context);
		config();

	}

	private void config() {
		getSettings().setJavaScriptEnabled(true);
		// getSettings().setBuiltInZoomControls(true);
		getSettings().setSupportZoom(true);
		setInitialScale(1);
		setWebViewClient(new HelloWebViewClient());
		setWebChromeClient(webChromeClient);
		getSettings().setJavaScriptEnabled(true);

		reset();
	}

	private ProgressDialog dialog;

	private void _showDialog(String strView) {
		Resources resources = getResources();
		strView = resources.getString(R.string.loadding);
		// }

		dialog = ProgressDialog.show(getContext(), null, strView, true);
	}

	private void hiddenDialog() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	private class HelloWebViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			hiddenDialog();
		}

	}

	private WebChromeClient webChromeClient = new WebChromeClient() {
		public void onProgressChanged(WebView view, int progress) {

			if (dialog == null) {
				_showDialog("Loading ...");
				if (!isTimer) {
					Timer timer = new Timer(30000, 100);
					timer.start();
					isTimer = true;
				}
			}

			if (progress == 100) {
				hiddenDialog();
			}

		}
	};

	public void reset() {
		// ((IheaderLabel) getContext()).setTextHeader("Follow");
	}

	public class Timer extends CountDownTimer {

		public Timer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);

		}

		@Override
		public void onFinish() {
			hiddenDialog();
		}

		@Override
		public void onTick(long millisUntilFinished) {

		}
	}

}