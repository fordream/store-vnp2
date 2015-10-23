package vnp.mr.mintmedical.view;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.item.Clinic;
import vnp.mr.mintmedical.item.VisitType;
import vnp.mr.mintmedical.service.VNPLocationUtils;
import android.content.Context;
import android.location.Location;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class OfficeItemView extends CustomLinearLayoutView {

	public OfficeItemView(Context context) {
		super(context);
		init(R.layout.s4gitemview);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		VNPResize resize = VNPResize.getInstance();

		resize.resizeSacle(findViewById(R.id.s4gitem_main), MintUtils.WIDTH_ITEM,LayoutParams.WRAP_CONTENT);
		resize.setTextsize(findViewById(R.id.s4gitemview_textview1),MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s4gitemview_textview2),MintUtils.TEXTSIZE_ITEM2);
		resize.setTextsize(findViewById(R.id.s4gitemview_textview3),MintUtils.TEXTSIZE_ITEM2);
		resize.resizeSacle(findViewById(R.id.s4gitemview_content_main),  MintUtils.WIDTH_ITEM,60);
		resize.resizeSacle(findViewById(R.id.s4gitemview_content1),  MintUtils.WIDTH_ITEM - MintUtils.IMGITEM_WIDTH2,LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.s4gitemview_img2),MintUtils.IMGITEM_WIDTH2, MintUtils.IMGITEM_WIDTH2);
	}

	@Override
	public void setGender() {
		setText(R.id.s4gitemview_textview3, "");

		if (getData() instanceof VisitType) {
			VisitType visitType = (VisitType) getData();
			setText(R.id.s4gitemview_textview1, visitType.value);
			setText(R.id.s4gitemview_textview2,visitType.desc);
			return;
		} else if (getData() instanceof Clinic) {
			Clinic item = (Clinic) getData();
			setText(R.id.s4gitemview_textview1, item.name);
			setText(R.id.s4gitemview_textview2, item.address);

			try {
				Location last = VNPLocationUtils.getInstance().lastKnownLocation;
				LatLng latLng1 = new LatLng(last.getLatitude(),
						last.getLongitude());
				LatLng latLng2 = new LatLng(Double.parseDouble(item.latitude),
						Double.parseDouble(item.longitude));
				setText(R.id.s4gitemview_textview3,
						String.format("%s km",
								VNPLocationUtils.getDistance(latLng1, latLng2)));
			} catch (Exception exception) {
			}
		}
	}

	private void setText(int res, String day) {
		((TextView) findViewById(res)).setText(day);
	}

	@Override
	public void showHeader(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
