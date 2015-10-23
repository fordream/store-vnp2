package vnp.mr.mintmedical.view;

// vnp.mr.mintmedical.view.ListViewHeader
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MintUtils;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.ImageLoaderUtils;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class ProgessDialogView extends CustomLinearLayoutView {

	public ProgessDialogView(Context context) {
		super(context);
		init(R.layout.progressloading);
	}

	public ProgessDialogView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.progressloading);
	}

	@Override
	public void init(int res) {
		super.init(res);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(findViewById(R.id.__progressBar1), MintUtils.TEXTSIZE_ACCOUNT_HEADER, MintUtils.TEXTSIZE_ACCOUNT_HEADER);
		resize.setTextsize(findViewById(R.id.__baseTextView1),
				MintUtils.TEXTSIZE_ITEM1);
	}

	@Override
	public void setGender() {

	}


	@Override
	public void showHeader(boolean arg0) {
		// TODO Auto-generated method stub

	}
}