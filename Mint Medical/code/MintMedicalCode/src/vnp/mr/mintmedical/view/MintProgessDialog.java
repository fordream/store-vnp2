package vnp.mr.mintmedical.view;

// vnp.mr.mintmedical.view.ListViewHeader
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MintUtils;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.vnp.core.common.VNPResize;

public class MintProgessDialog extends ProgressDialog {

	public MintProgessDialog(Context context) {
		super(context);
		setCancelable(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressloading);
		onAttachedToWindow();
	}

	public void onAttachedToWindow() {
		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(findViewById(R.id.__progressBar1),
				MintUtils.TEXTSIZE_ACCOUNT_HEADER,
				MintUtils.TEXTSIZE_ACCOUNT_HEADER);
		resize.setTextsize(findViewById(R.id.__baseTextView1),
				MintUtils.TEXTSIZE_ITEM1);
	}

}