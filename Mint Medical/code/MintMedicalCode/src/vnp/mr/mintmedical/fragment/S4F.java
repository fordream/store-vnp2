package vnp.mr.mintmedical.fragment;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MBaseFragment;
import android.view.View;
import android.view.View.OnClickListener;

public class S4F extends MBaseFragment implements OnClickListener {

	@Override
	public void onResume() {
		super.onResume();
		resize.resizeSacle(findViewById(R.id.s4fcontent), 574 / 2, 560 / 2);
	}

	public int getTextContentRes() {
		return R.string.s4f2;
	}

	@Override
	public int getLayout() {
		return R.layout.s4f;
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public int getHeaderRes() {
		return R.string.s4f_header;
	}

}