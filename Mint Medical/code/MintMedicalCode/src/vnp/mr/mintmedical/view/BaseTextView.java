package vnp.mr.mintmedical.view;

import vnp.mr.mintmedical.base.MintUtils;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
//vnp.mr.mintmedical.view.BaseTextView
public class BaseTextView extends TextView {

	public BaseTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BaseTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BaseTextView(Context context) {
		super(context);
		init();
	}

	private void init() {
		try {
			MintUtils.setTypeface(this);
		} catch (Exception exception) {
		}
	}
}
