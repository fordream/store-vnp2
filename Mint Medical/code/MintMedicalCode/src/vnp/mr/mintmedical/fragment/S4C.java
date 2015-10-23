package vnp.mr.mintmedical.fragment;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.base.MintUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;

import com.vnp.core.common.CommonAndroid;

public class S4C extends MBaseFragment implements OnClickListener {

	@Override
	public void onHiddenKeyBoard() {
		super.onHiddenKeyBoard();
		CommonAndroid.hiddenKeyBoard(getActivity());
	}
	
	public void onResume() {
		super.onResume();
		resize.resizeSacle(findViewById(R.id.s4cmaincontent), MintUtils.WIDTH_ITEM, LayoutParams.MATCH_PARENT);
		resize.resizeSacle(findViewById(R.id.textView1), MintUtils.WIDTH_ITEM, 60);
		resize.resizeSacle(findViewById(R.id.s4cet1), MintUtils.WIDTH_ITEM, 100);
		
		resize.resizeSacle(findViewById(R.id.s4cbtn), MintUtils.WIDTH_ITEM,45);
		resize.setTextsize(findViewById(R.id.textView1), MintUtils.TEXTSIZE_ITEM1);
		
		resize.setTextsize(findViewById(R.id.s4cet1), MintUtils.TEXTSIZE_EDITTEXT);
		findViewById(R.id.s4cbtn).setOnClickListener(this);
	}

	public int getLayout() {
		return R.layout.s4c;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.s4cbtn) {
			getOnClickListener().onClick(((EditText)findViewById(R.id.s4cet1)).getText().toString(), 1);
		}
	}

	@Override
	public int getHeaderRes() {
		return R.string.s4c_header;
	}
}