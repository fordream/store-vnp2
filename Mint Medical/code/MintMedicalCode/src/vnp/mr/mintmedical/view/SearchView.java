package vnp.mr.mintmedical.view;

// vnp.mr.mintmedical.view.ListViewHeader
import vnp.mr.mintmedical.R;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class SearchView extends CustomLinearLayoutView {

	private EditText editText;

	public SearchView(Context context) {
		super(context);
		init(R.layout.searchview);
	}

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.searchview);
	}

	@Override
	public void init(int res) {
		super.init(res);
		editText = getView(R.id.searchview_edt);
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (watcher != null)
					watcher.onTextChanged(s, start, before, count);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				if (watcher != null)
					watcher.beforeTextChanged(s, start, count, after);

			}

			@Override
			public void afterTextChanged(Editable s) {
				String text = s.toString();
				if (text.equals("")) {
					editText.setBackgroundResource(R.drawable.s4h_edtseartch_none);
				} else {
					editText.setBackgroundResource(R.drawable.s4h_edtseartch);
				}

				if (watcher != null)
					watcher.afterTextChanged(s);
			}
		});
	}

	TextWatcher watcher;

	public void addTextChangedListener(TextWatcher watcher) {
		this.watcher = watcher;
	}

	// s4h_edtseartch_none

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(this,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, 50);
		resize.resizeSacle(findViewById(R.id.searchview_main),
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, 50);
		resize.resizeSacle(editText, 300, 30);
		resize.setTextsize(editText, 15);
	}

	@Override
	public void setGender() {

	}

	public void onHiddenKeyBoard() {
		CommonAndroid.hiddenKeyBoard((Activity) getContext());
	}

	@Override
	public void showHeader(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}