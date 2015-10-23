/**
 * 
 */
package org.vnp.storeapp.block.gallery;

import java.util.List;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.block.detail.BlockDetailItemActivity;
import org.vnp.storeapp.service.StoreAppService.BlockType;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.vnp.core.base.MBaseAdapter;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.common.ImageLoader;
import com.vnp.core.view.CustomLinearLayoutView;

/**
 * @author teemo
 * 
 */
public class GalleryFagment extends BaseFragment implements OnItemClickListener {
	private ImageLoader imageLoader;

	/**
	 * 
	 */
	public GalleryFagment() {
		super();
		setType(BlockType.BLOCK_GALLERY);
		setCanRunExecute(true);
	}

	@Override
	public int getResourceLayout() {
		return R.layout.galleryfragment;
	}

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);
		imageLoader = new ImageLoader(getActivity());
		getActivity().registerReceiver(broadcastReceiver, new IntentFilter(getCallBackStr()));
		storeAppService.loadData(getType(), getCallBackStr());
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			updateUi(storeAppService.parseResponse(getType()));

			try {
				getView().findViewById(R.id.gallery_loaderview).setVisibility(View.GONE);
			} catch (Exception exception) {

			}
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(broadcastReceiver);
	}

	@Override
	public void updateUi(Object result) {
		super.updateUi(result);
		try {
			@SuppressWarnings("unchecked")
			List<Object> galleryCallBack = (List<Object>) result;
			GridView gallery = (GridView) getView().findViewById(R.id.gallery1);
			gallery.setAdapter(new MBaseAdapter(galleryCallBack) {

				@Override
				public ViewHolder getViewHolder() {
					return new ViewHolder() {
						ImageView imageView;

						@Override
						public void updateView(Object arg0) {
							String url = arg0.toString();
							setImageUrl(imageView, url);
						}

						@Override
						public void init(View view) {
							imageView = (ImageView) view.findViewById(R.id.imageView_about);
							resize(view, 480, 480);
							resize(view.findViewById(R.id.gracenter), 480, 480);
							resize(imageView, 400, 400);
						}
					};
				}

				@Override
				public int resLayout() {
					return R.layout.galleryview;
				}

			});
//			gallery.setAdapter(new BaseAdapter(getActivity(), galleryCallBack, new CommonGenderView() {
//				@Override
//				public CustomLinearLayoutView getView(Context arg0, Object arg1) {
//					return new GalleryView(arg0, imageLoader);
//				}
//			}));

			gallery.setOnItemClickListener(this);
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
		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent intent = new Intent(getActivity(), BlockDetailItemActivity.class);
		intent.putExtra("type", "GALLERY");
		intent.putExtra("pos", pos);
		startActivity(intent);
	}
}