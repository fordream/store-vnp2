package vnp.mr.mintmedical.view;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.S2Activity.S2Item;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.item.S51Item;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.viewpagerindicator.db.DBHome;
import com.viewpagerindicator.db.DBUserLogin;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class S51ItemView extends CustomLinearLayoutView {
	DBUserLogin dbUserLogin;
	DBHome dbHome;

	public S51ItemView(Context context) {
		super(context);
		init(R.layout.s51itemview);
	}

	public S51ItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.s51itemview);
	}

	@Override
	public void init(int res) {
		super.init(res);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(findViewById(R.id.s11itemview_m1_main),
				MintUtils.WIDTH_ITEM, 60);
		resize.resizeSacle(findViewById(R.id.s11itemview_m1),
				MintUtils.WIDTH_ITEM, LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.mainactivityitemview_img2),
				MintUtils.IMGITEM_WIDTH2, MintUtils.IMGITEM_WIDTH2);
		resize.resizeSacle(findViewById(R.id.mainactivityitemview_text1),
				MintUtils.WIDTH_ITEM - MintUtils.IMGITEM_WIDTH2,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.mainactivityitemview_text2),
				MintUtils.WIDTH_ITEM - MintUtils.IMGITEM_WIDTH2,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		resize.setTextsize(findViewById(R.id.mainactivityitemview_text1),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.mainactivityitemview_text2),
				MintUtils.TEXTSIZE_ITEM2);
	}

	@Override
	public void setGender() {
		if (getData() instanceof S51Item) {
			S51Item item = (S51Item) getData();
			((TextView) findViewById(R.id.mainactivityitemview_text1))
					.setText(item.symp_name);
			((TextView) findViewById(R.id.mainactivityitemview_text2))
					.setText(item.symp_desc);
		}
	}

	@Override
	public void showHeader(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
