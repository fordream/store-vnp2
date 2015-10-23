/**
 * 
 */
package org.vnp.storeapp.block;

import org.core.store.app.StoreBaseView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.app.R;
import com.example.app.blocklist.Block;
import com.example.app.utils.MemoryUtils;

/**
 * @author teemo
 * 
 */
public class BlockDetailView extends StoreBaseView {

	public BlockDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.blockdetailview);
		resize(R.id.imageView_about, BlockUtils.WIDTH, BlockUtils.HEIGHT);
		resize(R.id.content_gid, BlockUtils.WIDTH, BlockUtils.HEIGHT);
	}

	public BlockDetailView(Context context) {
		super(context);
		init(R.layout.blockdetailview);
		rezise(findViewById(R.id.imageView_about), BlockUtils.WIDTH,
				BlockUtils.HEIGHT);
		rezise(findViewById(R.id.content_gid), BlockUtils.WIDTH,
				BlockUtils.HEIGHT);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		rezise(this, BlockUtils.WIDTH, BlockUtils.HEIGHT);
	}

	@Override
	public void setGender() {
		Block bin = (Block) getData();

		if (bin.getFolder() == null) {
			String pathFolder = String.format("%sicon.png", new MemoryUtils(
					getContext()).getPathContent());
			setImageUrl(R.id.imageView_about, pathFolder);
			return;
		}
		String pathFolder = String
				.format("%s%s", new MemoryUtils(getContext()).getPathContent(),
						bin.getFolder());
		setImageUrl(R.id.imageView_about,
				String.format("%s/icon.png", pathFolder));
	}

	public void updateCurentab(Object object) {
	}

	public void unChecked() {
	}
}