package vnp.mr.mintmedical.view;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.s6.S6BItem;
import vnp.mr.mintmedical.s6.S6BMedicationConfig;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class S6BItemView extends CustomLinearLayoutView implements
		OnClickListener {

	public S6BItemView(Context context) {
		super(context);
		init(R.layout.s6bitemview);
	}

	public S6BItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.s6bitemview);
	}

	@Override
	public void init(int res) {
		super.init(res);
		final CheckBox box = (CheckBox) findViewById(R.id.s6bcheckbok);
		findViewById(R.id.s6bitemtext).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						box.setChecked(!box.isChecked());
					}
				});
		box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (getData() instanceof S6BItem)
					((S6BItem) getData()).isChecked = box.isChecked();
			}
		});

		findViewById(R.id.s6bbutton).setOnClickListener(this);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		try {
			VNPResize resize = VNPResize.getInstance();
			resize.resizeSacle(findViewById(R.id.id001_s6item),
					MintUtils.WIDTH_ITEM,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.s6bitem_content1),
					MintUtils.WIDTH_ITEM, 35);

			resize.resizeSacle(this,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

			resize.setTextsize(findViewById(R.id.s6bitem__textView1),
					MintUtils.TEXTSIZE_ITEM1);
			resize.setTextsize(findViewById(R.id.textView1),
					MintUtils.TEXTSIZE_ITEM1);

			resize.resizeSacle(findViewById(R.id.s6bcheckbok), 20, 20);
			resize.setTextsize(findViewById(R.id.s6bitemtext),
					MintUtils.TEXTSIZE_ITEM1);

			resize.resizeSacle(findViewById(R.id.s6bitem_content1_1),
					MintUtils.WIDTH_ITEM, 35);
			resize.setTextsize(findViewById(R.id.s6bitemtext_1),
					MintUtils.TEXTSIZE_ITEM1);

			resize.resizeSacle(findViewById(R.id.s6bactivity__textView1),
					MintUtils.WIDTH_ITEM, LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.s6bbutton),
					MintUtils.WIDTH_ITEM, 45);
			resize.resizeSacle(findViewById(R.id.s6btextview),
					MintUtils.WIDTH_ITEM, 100);
			resize.setTextsize(findViewById(R.id.s6btextview),
					MintUtils.TEXTSIZE_ITEM1);
			resize.setTextsize(findViewById(R.id.s6bactivity__textView1),
					MintUtils.TEXTSIZE_ITEM1);
		} catch (Exception exception) {

		}
	}

	@Override
	public void setGender() {
		findViewById(R.id.s6bcontent).setVisibility(View.GONE);
		final CheckBox box = (CheckBox) findViewById(R.id.s6bcheckbok);
		if (getData() instanceof S6BItem) {
			box.setChecked(((S6BItem) getData()).isChecked);
			update(((S6BItem) getData()).getString("name"));
		} else if (getData() instanceof S6BMedicationConfig) {
			if (((S6BMedicationConfig) getData()).type == S6BMedicationConfig.SENDREQUEST) {
				findViewById(R.id.s6bcontent).setVisibility(View.VISIBLE);
				findViewById(R.id.s6bitem_content1).setVisibility(View.GONE);
			} else {

			}
		}
	}

	public void update(String string) {
		((TextView) findViewById(R.id.s6bitemtext)).setText(string);
	}

	public void update(int s6b1) {
		((TextView) findViewById(R.id.s6bitemtext)).setText(s6b1);
	}

	// public void showHeader(boolean showHeader, int resHeader) {
	// if (showHeader) {
	// findViewById(R.id.id001_s6item).setVisibility(VISIBLE);
	// } else {
	// findViewById(R.id.id001_s6item).setVisibility(GONE);
	// }
	// ((TextView) findViewById(R.id.s6bitem__textView1)).setText("");
	// if (resHeader != 0) {
	// ((TextView) findViewById(R.id.s6bitem__textView1))
	// .setText(resHeader);
	// }
	// }

	// public void showHeader(boolean showHeader, boolean isLastRequest) {
	// if (showHeader) {
	// findViewById(R.id.id001_s6item).setVisibility(VISIBLE);
	// } else {
	// findViewById(R.id.id001_s6item).setVisibility(GONE);
	// }
	//
	// ((TextView) findViewById(R.id.s6bitem__textView1))
	// .setText("Medication list");
	// if (isLastRequest) {
	// ((TextView) findViewById(R.id.s6bitem__textView1))
	// .setText("Pass medications");
	// }
	// }

	@Override
	public void showHeader(boolean arg0) {

	}

	public void showHeader(boolean showHeader, Object item) {

		findViewById(R.id.s6bitem_content1_1).setVisibility(GONE);
		
		if (item instanceof S6BMedicationConfig) {
			if (((S6BMedicationConfig) item).type == S6BMedicationConfig.SENDREQUEST) {
				findViewById(R.id.s6bitem_content1).setVisibility(GONE);
				findViewById(R.id.id001_s6item).setVisibility(GONE);
				
				return;
			}
			findViewById(R.id.id001_s6item).setVisibility(VISIBLE);
			findViewById(R.id.s6bitem_content1).setVisibility(GONE);
			S6BMedicationConfig config = (S6BMedicationConfig) item;

			((TextView) findViewById(R.id.s6bitem__textView1)).setText("Past medications");

			if (config.size == 0) {
				findViewById(R.id.s6bitem_content1_1).setVisibility(VISIBLE);
				((TextView) findViewById(R.id.s6bitemtext_1))
						.setText(config.text);
			}

			if (config.type == S6BMedicationConfig.MEDICATIONLIST) {
				((TextView) findViewById(R.id.s6bitem__textView1))
						.setText("Medication list");
			}
		} else {
			findViewById(R.id.s6bitem_content1).setVisibility(VISIBLE);
			findViewById(R.id.id001_s6item).setVisibility(GONE);
		}
	}

	@Override
	public void onClick(View v) {
		if (callBack != null) {
			callBack.onCallBack(((TextView) findViewById(R.id.s6btextview))
					.getText().toString());
		}
	}

	public void setCallBack(ICallBack callBack) {
		this.callBack = callBack;
	}

	private ICallBack callBack;

	public interface ICallBack {
		public void onCallBack(String str);
	}
}
