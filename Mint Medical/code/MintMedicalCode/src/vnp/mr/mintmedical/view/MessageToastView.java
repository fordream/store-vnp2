package vnp.mr.mintmedical.view;

// vnp.mr.mintmedical.view.ListViewHeader
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MintUtils;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class MessageToastView extends CustomLinearLayoutView {

	public MessageToastView(Context context) {
		super(context);
		init(R.layout.messagepopup);
	}

	public MessageToastView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.messagepopup);
	}

	@Override
	public void init(int res) {
		super.init(res);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		VNPResize resize = VNPResize.getInstance();
		resize.setTextsize(findViewById(R.id._baseTextView1),
				MintUtils.TEXTSIZE_ITEM1);
	}

	@Override
	public void setGender() {

	}

	public void setText(String message) {
		((TextView) findViewById(R.id._baseTextView1)).setText(message);
	}

	@Override
	public void showHeader(boolean arg0) {
		// TODO Auto-generated method stub

	}
}