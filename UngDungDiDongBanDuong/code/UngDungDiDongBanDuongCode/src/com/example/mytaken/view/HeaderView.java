/**
 * 
 */
package com.example.mytaken.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.mytaken.AccountManagerActiity;
import com.example.mytaken.BookOnlineActiity;
import com.example.mytaken.LoginActivity;
import com.example.mytaken.R;
import com.example.mytaken.RegisterActivity;
import com.example.mytaken.util.BanDuongUtils;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

/**
 * @author teemo
 * 
 */
public class HeaderView extends CustomLinearLayoutView implements Runnable, OnClickListener {
	public interface IHeaderViewOnClickListener {
		public void onback();

		public void onCancel();
	}

	private IHeaderViewOnClickListener headerViewOnClickListener = new IHeaderViewOnClickListener() {

		@Override
		public void onback() {

		}

		@Override
		public void onCancel() {

		}
	};

	public void setHeaderViewOnClickListener(IHeaderViewOnClickListener headerViewOnClickListener) {
		this.headerViewOnClickListener = headerViewOnClickListener;
	}

	/**
	 * @param context
	 */
	public HeaderView(Context context) {
		super(context);
		init(R.layout.headerview);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.headerview);
	}

	@Override
	public void init(int res) {
		super.init(res);

		findViewById(R.id.customButton1_header).setOnClickListener(this);
		findViewById(R.id.customButton2_header).setOnClickListener(this);
		post(this);

		setText();
	}

	private void setText() {
		int res = R.string.register;

		if (getContext() instanceof RegisterActivity) {
		} else if (getContext() instanceof LoginActivity) {
			res = R.string.login;
		} else if (getContext() instanceof BookOnlineActiity) {
			res = R.string.bookonline;
		} else if (getContext() instanceof AccountManagerActiity) {
			res = R.string.accountmanager;
		}
		((TextView) findViewById(R.id.customTextViewBold1_header)).setText(res);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnp.core.view.CustomLinearLayoutView#setGender()
	 */
	@Override
	public void setGender() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnp.core.view.CustomLinearLayoutView#showHeader(boolean)
	 */
	@Override
	public void showHeader(boolean arg0) {

	}

	@Override
	public void run() {
		VNPResize resize = VNPResize.getInstance();
		try {
			resize.resizeSacle(this, android.view.ViewGroup.LayoutParams.MATCH_PARENT, BanDuongUtils.SIZE.HEIGHT_HEADER);
			resize.resizeSacle(findViewById(R.id.idheadermain), android.view.ViewGroup.LayoutParams.MATCH_PARENT, BanDuongUtils.SIZE.HEIGHT_HEADER);
			resize.resizeSacle(findViewById(R.id.customButton1_header), BanDuongUtils.SIZE.WIDTHBUTTONHEADER, BanDuongUtils.SIZE.HEIGHTBUTTONHEADER);
			resize.resizeSacle(findViewById(R.id.customButton2_header), BanDuongUtils.SIZE.WIDTHBUTTONHEADER, BanDuongUtils.SIZE.HEIGHTBUTTONHEADER);
			resize.setTextsize(findViewById(R.id.customButton1_header), BanDuongUtils.SIZE.TEXTSIZEBUTTONHEADER);
			resize.setTextsize(findViewById(R.id.customButton2_header), BanDuongUtils.SIZE.TEXTSIZEBUTTONHEADER);
			resize.setTextsize(findViewById(R.id.customTextViewBold1_header), BanDuongUtils.SIZE.TEXTSIZEBUTTONHEADER);
		} catch (Exception exception) {
		} catch (Error error) {
		}

		if (getContext() instanceof RegisterActivity) {
		} else if (getContext() instanceof LoginActivity) {
		} else if (getContext() instanceof BookOnlineActiity) {
		} else if (getContext() instanceof AccountManagerActiity) {
		}
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.customButton1_header) {
			headerViewOnClickListener.onback();
			if (getContext() instanceof RegisterActivity || getContext() instanceof LoginActivity) {
				((Activity) getContext()).finish();
			} else if (getContext() instanceof BookOnlineActiity) {
			} else if (getContext() instanceof AccountManagerActiity) {
			}
		} else if (v.getId() == R.id.customButton2_header) {
			headerViewOnClickListener.onCancel();
			if (getContext() instanceof RegisterActivity || getContext() instanceof LoginActivity) {
				((Activity) getContext()).finish();
			} else if (getContext() instanceof BookOnlineActiity) {
			} else if (getContext() instanceof AccountManagerActiity) {
			}
		}
	}
}