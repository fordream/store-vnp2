package vnp.mr.mintmedical.base;

import vnp.mr.mintmedical.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

import com.vnp.core.common.VNPResize;

public abstract class MbaseAdialog extends Dialog {
	public DialogInterface.OnClickListener clickListener;
	protected VNPResize resize = VNPResize.getInstance();

	public MbaseAdialog(Context context,
			DialogInterface.OnClickListener clickListener) {
		super(context, R.style.AppTheme);
		this.clickListener = clickListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCancelable(false);
		setContentView(getLayout());
	}

	public abstract int getLayout();



}