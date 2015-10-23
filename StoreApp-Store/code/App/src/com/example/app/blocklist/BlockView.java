/**
 * 
 */
package com.example.app.blocklist;

import org.core.store.app.StoreAppUtils;
import org.core.store.app.StoreBaseView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.app.R;
import com.example.app.utils.MemoryUtils;

/**
 * @author teemo
 * 
 */
public class BlockView extends StoreBaseView {
	MemoryUtils memoryUtils;

	public BlockView(Context context) {
		super(context);
		init(R.layout.blockview);
	}

	public BlockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.blockview);
	}

	@Override
	public void init(int res) {
		super.init(res);
		memoryUtils = new MemoryUtils(getContext());
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	protected void onAttachedToWindowx2() {
		rezise(findViewById(R.id.imageView_about), BlockListUtils.WIDTH * 2
				+ BlockListUtils.BLOCK_SPACE, BlockListUtils.HEIGHT * 2
				+ BlockListUtils.BLOCK_SPACE);
		rezise(findViewById(R.id.content_gid), BlockListUtils.WIDTH * 2
				+ BlockListUtils.BLOCK_SPACE, BlockListUtils.HEIGHT * 2
				+ BlockListUtils.BLOCK_SPACE);
		rezise(this, BlockListUtils.WIDTH * 2 + BlockListUtils.BLOCK_SPACE,
				BlockListUtils.HEIGHT * 2 + BlockListUtils.BLOCK_SPACE);
		StoreAppUtils.setTextSize(findViewById(R.id.textView1), 38);
		rezise(findViewById(R.id.textView1), BlockListUtils.WIDTH * 2
				+ BlockListUtils.BLOCK_SPACE, 50 * 2);
		rezise(findViewById(R.id.imageView1), 70 * 2, 70 * 2);

		findViewById(R.id.textView2).setVisibility(View.VISIBLE);
		rezise(findViewById(R.id.textView2), BlockListUtils.WIDTH * 2
				+ BlockListUtils.BLOCK_SPACE, 30 * 2);
		StoreAppUtils.setTextSize(findViewById(R.id.textView2), 25);

	}

	protected void onAttachedToWindowx1() {
		rezise(findViewById(R.id.imageView_about), BlockListUtils.WIDTH,
				BlockListUtils.HEIGHT);
		rezise(this, BlockListUtils.WIDTH, BlockListUtils.HEIGHT);
		StoreAppUtils.setTextSize(findViewById(R.id.textView1), 38);
		rezise(findViewById(R.id.textView1), BlockListUtils.WIDTH, 50 * 2);
		rezise(findViewById(R.id.imageView1), 40 * 2, 40 * 2);
		findViewById(R.id.imageView1).setVisibility(View.GONE);

	}

	@Override
	public void setGender() {
		ImageView img = getView(R.id.imageView_about);
		Block bin = (Block) getData();

		String pathFolder = String.format("%s%s", memoryUtils.getPathContent(),
				bin.getFolder());
		setImageUrl(img, String.format("%s/icon.png", pathFolder));
		setImageUrl((ImageView) getView(R.id.imageView1), String.format("%s/sub-icon.png", pathFolder));

		setText(R.id.textView1, bin.getHeader());
		setText(R.id.textView2, bin.getSub_header());

		if (bin.isBig()) {
			onAttachedToWindowx2();
		} else {
			onAttachedToWindowx1();
		}

	}

	public void hidden() {
		setVisibility(INVISIBLE);
	}
}