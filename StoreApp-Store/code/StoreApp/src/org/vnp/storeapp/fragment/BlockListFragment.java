/**
 * 
 */
package org.vnp.storeapp.fragment;

import java.util.List;

import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.R;
import org.vnp.storeapp.activity.BlockActivity;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.blocklist.Block;
import org.vnp.storeapp.blocklist.BlockView;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Intent;
import android.view.View;

import com.vnp.core.common.VNPResize;

/**
 * @author teemo
 * 
 */
public class BlockListFragment extends BaseFragment {

	public BlockListFragment() {
		super();
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
		// updateUi(v, R.id.block6, 5);
	}

	@Override
	public void onResume() {
		super.onResume();

		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(getView().findViewById(R.id.block_content), 
				260 * StoreAppUtils.BASE_SCALE, 
				300 * StoreAppUtils.BASE_SCALE);
	}

	@SuppressWarnings("unchecked")
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
							// if (!storeAppService.haveBlock(block.getType()))
							// {
							// // showDialogMarket(R.string.update_new_version);
							// // return;
							// }
							Intent intent = new Intent(getActivity(),
									BlockActivity.class);
							intent.putExtra("bin", block.getType());
							startActivity(intent);
						}
					}
				});
			} else {
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