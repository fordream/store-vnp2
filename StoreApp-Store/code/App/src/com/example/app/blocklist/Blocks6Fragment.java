/**
 * 
 */
package com.example.app.blocklist;

import java.util.List;

import org.core.store.app.StoreBaseFragment;
import org.vnp.storeapp.block.BlockActivity;

import android.content.Intent;
import android.view.View;

import com.example.app.R;

/**
 * @author teemo
 * 
 */
public class Blocks6Fragment extends StoreBaseFragment {

	public Blocks6Fragment() {
		super();
		setCanRunExecute(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vnp.storeapp.fragment.BaseFragment#getResourceLayout()
	 */
	@Override
	public int getResourceLayout() {
		return R.layout.block6;
	}

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);

		updateUi(v, R.id.block1, 0);
		updateUi(v, R.id.block2, 1);
		updateUi(v, R.id.block3, 2);
		updateUi(v, R.id.block4, 3);
		updateUi(v, R.id.block5, 4);
		updateUi(v, R.id.block6, 5);

		resize(v.findViewById(R.id.id_block6_space1),
				BlockListUtils.BLOCK_SPACE, BlockListUtils.HEIGHT * 2);
		resize(v.findViewById(R.id.id_block6_space3), BlockListUtils.WIDTH,
				BlockListUtils.BLOCK_SPACE);
		resize(v.findViewById(R.id.id_block6_space_2), BlockListUtils.WIDTH,
				BlockListUtils.BLOCK_SPACE);
		resize(v.findViewById(R.id.id_block6_space4),
				BlockListUtils.BLOCK_SPACE, BlockListUtils.HEIGHT);
		resize(v.findViewById(R.id.id_block6_space5),
				BlockListUtils.BLOCK_SPACE, BlockListUtils.HEIGHT);
	}

	private void updateUi(View v, int block1, int i) {
		try {
			BlockView view = (BlockView) v.findViewById(block1);

			if (i < ((List<Object>) getData()).size()) {
				final Block block = (Block) ((List<Object>) getData()).get(i);
				view.setData(block);

				view.setGender();
				view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						{
							Intent intent = new Intent(getActivity(),
									BlockActivity.class);
							startActivity(intent);
						}
					}
				});
			} else {
				view.onAttachedToWindowx1();
				view.hidden();
			}
		} catch (Exception exception) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vnp.storeapp.fragment.BaseFragment#getTitleBar()
	 */
	@Override
	public String getTitleBar() {
		return "login";
	}

}