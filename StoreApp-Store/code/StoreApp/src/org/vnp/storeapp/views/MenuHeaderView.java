package org.vnp.storeapp.views;

import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseView;
import org.vnp.storeapp.blocklist.Block;
import org.vnp.storeapp.utils.StoreAppUtils;
import org.vnp.storeapp.utils.StoreAppUtils.SIZE_BLOCKACTIVITY;
import org.vnp.storeapp.utils.StoreAppUtils.TEXTSIZE;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.vnp.core.common.VNPResize;

public class MenuHeaderView extends BaseView {

	public MenuHeaderView(Context context) {
		super(context);
		init(R.layout.menuheader);
	}

	public MenuHeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.menuheader);
	}

	@Override
	public void init(int res) {
		super.init(res);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		VNPResize.getInstance().resizeSacle(this,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		VNPResize.getInstance().resizeSacle(
				findViewById(R.id.menuheader__content), 240 * 3,
				SIZE_BLOCKACTIVITY.HEIGHT_MENU_CONTROLLER);
		VNPResize.getInstance().resizeSacle(findViewById(R.id.menuheader_icon),
				45 * 3, 45 * 3);
		VNPResize.getInstance().resizeSacle(findViewById(R.id.menuheader_txt),
				(240 - 45 - 30) * 3, 50 * 3);
		VNPResize.getInstance().resizeSacle(
				findViewById(R.id.menuheader_status), 30 * 3, 30 * 3);
		VNPResize.getInstance().setTextsize(findViewById(R.id.menuheader_txt),
				TEXTSIZE.MENU);
	}

	@Override
	public void setGender() {
		Block bin = (Block) getData();
		ImageView img = (ImageView) findViewById(R.id.menuheader_icon);
		if (bin.getType() != BlockType.NONE) {
			if (bin.getType() == BlockType.HOME) {
				img.setBackgroundResource(R.drawable.home);
				setText(R.id.menuheader_txt,
						getResources().getString(R.string.home));
			} else {
				String url = bin.getImg();
				if (url == null || (url != null && url.trim().equals("")))
					url = bin.getSub_icon();
				if (StoreAppUtils.isBlank(url)) {
					url = bin.getBackgroundImg();
				}
				setImageUrl(img, url);
				setText(R.id.menuheader_txt, bin.getHeader());
			}
		}
	}

	public void clear() {

	}

	public void setChooser(boolean b) {
		setBackgroundResource(R.id.menuheader_status, b ? R.drawable.nenu_open
				: R.drawable.nenu_close);
	}
}