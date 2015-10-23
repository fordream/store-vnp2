package vnp.mr.mintmedical.view;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.S2Activity.S2Item;
import vnp.mr.mintmedical.base.MintUtils;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.viewpagerindicator.db.DBHome;
import com.viewpagerindicator.db.DBUserLogin;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class S2ItemView extends CustomLinearLayoutView {
	DBUserLogin dbUserLogin;
	DBHome dbHome;

	public S2ItemView(Context context) {
		super(context);
		init(R.layout.s2itemview);
	}

	public S2ItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.s2itemview);
	}

	@Override
	public void init(int res) {
		super.init(res);
		dbUserLogin = new DBUserLogin(getContext());
		dbHome = new DBHome(getContext());
		setWillNotDraw(false);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (getData() instanceof S2Item) {
			S2Item item = (S2Item) getData();
			TextView textView = ((TextView) findViewById(R.id.mainactivityitemview_text2));
			if (item.res == R.drawable.main_img_1 && dbHome != null) {
				textView.setText(dbHome.getAppointments());
			} else if (R.drawable.main_img_3 == item.res) {
				textView.setText(dbHome.getPrescription());
			} else if (R.drawable.main_img_8 == item.res) {
				textView.setText(dbHome.getNewAndAlerts());
			} else if (R.drawable.main_img_6 == item.res) {
				// String message = "Membership status";
				// if (!MintUtils.isMeberShip(getContext())) {
				// message = "Not yet membership";
				// /} else {
				//
				// }
				if (!MintUtils.isLogin(getContext())) {
					textView.setText("Membership status");
				} else {

					if (MintUtils.isMeberShip(getContext())) {
						textView.setText(dbHome.getMemberShip());
					} else {
						textView.setText("Not yet membership");
					}

					String memberShip = dbHome.getMemberShip();
					if (memberShip != null
							& memberShip
									.equalsIgnoreCase("Active membership required")) {
						textView.setText(memberShip);
					}
				}

			}
		}
		invalidate();
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(findViewById(R.id.s11itemview_m1_main),
				MintUtils.WIDTH_ITEM, 60);
		resize.resizeSacle(findViewById(R.id.s11itemview_m1),
				MintUtils.WIDTH_ITEM, LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.mainactivityitemview_img1),
				MintUtils.IMGITEM_WIDTH, MintUtils.IMGITEM_WIDTH);
		resize.resizeSacle(findViewById(R.id.mainactivityitemview_img2),
				MintUtils.IMGITEM_WIDTH2, MintUtils.IMGITEM_WIDTH2);
		resize.resizeSacle(findViewById(R.id.mainactivityitemview_text1), 220,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.mainactivityitemview_text2), 220,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		resize.setTextsize(findViewById(R.id.mainactivityitemview_text1),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.mainactivityitemview_text2),
				MintUtils.TEXTSIZE_ITEM2);
	}

	@Override
	public void setGender() {
		if (getData() instanceof S2Item) {
			S2Item item = (S2Item) getData();
			((TextView) findViewById(R.id.mainactivityitemview_text1))
					.setText(item.header);
			((TextView) findViewById(R.id.mainactivityitemview_text2))
					.setText(item.suHeader);
			findViewById(R.id.mainactivityitemview_img1).setBackgroundResource(
					item.res);

			if (item.suHeader == null) {
				findViewById(R.id.mainactivityitemview_text2).setVisibility(
						View.GONE);
			} else {
				findViewById(R.id.mainactivityitemview_text2).setVisibility(
						View.VISIBLE);
			}
		}
	}

	@Override
	public void showHeader(boolean arg0) {
		// TODO Auto-generated method stub

	}
}
