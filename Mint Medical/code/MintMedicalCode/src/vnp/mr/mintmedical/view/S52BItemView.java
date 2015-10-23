package vnp.mr.mintmedical.view;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.item.S52Item;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class S52BItemView extends CustomLinearLayoutView {

	public S52BItemView(Context context) {
		super(context);
		init(R.layout.s52itemview);
	}

	public S52BItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.s52itemview);
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
						((S52Item) getData()).setChecked(box.isChecked());
					}
				});
		box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				((S52Item) getData()).setChecked(box.isChecked());
			}
		});
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		try {
			VNPResize resize = VNPResize.getInstance();
			resize.resizeSacle(findViewById(R.id.s52main),
					MintUtils.WIDTH_ITEM, 60);
			resize.resizeSacle(findViewById(R.id.s52main1),
					MintUtils.WIDTH_ITEM,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.s6bcheckbok), 20, 20);
			resize.setTextsize(findViewById(R.id.s6bitemtext),
					MintUtils.TEXTSIZE_ITEM1);
		} catch (Exception exception) {

		}
	}

	@Override
	public void setGender() {
		S52Item item = ((S52Item) getData());
		CheckBox box = (CheckBox) findViewById(R.id.s6bcheckbok);
		box.setChecked(item.isCheced);
		((TextView) findViewById(R.id.s6bitemtext)).setText(item.question);
	}

	@Override
	public void showHeader(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}