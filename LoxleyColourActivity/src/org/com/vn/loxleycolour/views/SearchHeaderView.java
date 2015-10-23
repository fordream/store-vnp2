package org.com.vn.loxleycolour.views;

import org.com.vn.loxleycolour.v1.R;
import org.com.vn.loxleycolour._interface.Const;
import org.com.vn.loxleycolour._interface.IView;
import org.com.vn.loxleycolour._interface.Isearch;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class SearchHeaderView extends RelativeLayout implements
		OnClickListener, Const, IView {
	private EditText etSearch;
	private EditText etCheck;
	private Isearch isearch;

	public SearchHeaderView(Context context) {
		super(context);
		init();
	}

	public SearchHeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	private void init() {
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.search_header, this);
		findViewById(R.id.rrSearchContent).setLayoutParams(
				new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT));

		etSearch = (EditText) findViewById(R.id.editText1);
		etCheck = (EditText) findViewById(R.id.editText2);
		etSearch.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				isearch.search(etSearch.getText().toString());
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void afterTextChanged(Editable s) {

			}
		});
		reset();
	}

	public void onClick(View view) {
	}

	public void reset() {
	}

	public void setFocus() {
		etCheck.requestFocus();
	}

	public void setSearch(Isearch orderInProgressView) {
		isearch = orderInProgressView;
	}

	public void setHint(String string) {
		etSearch.setHint(string);
	}
}
