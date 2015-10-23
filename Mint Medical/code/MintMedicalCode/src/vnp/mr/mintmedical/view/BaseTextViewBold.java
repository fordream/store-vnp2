package vnp.mr.mintmedical.view;

import com.vnp.core.common.CommonAndroid;

import vnp.mr.mintmedical.base.MintUtils;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
//vnp.mr.mintmedical.view.BaseTextViewBold
public class BaseTextViewBold extends TextView {

	public BaseTextViewBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BaseTextViewBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BaseTextViewBold(Context context) {
		super(context);
		init();
	}

	private void init() {
		try {
			CommonAndroid.FONT.setTypefaceFromAsset(this, "ARIALBD.TTF");
		} catch (Exception exception) {
		}
	}
}
