package org.vnp.storeapp.block.detail;

import java.util.List;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseView;
import org.vnp.storeapp.base.MBaseActivity;
import org.vnp.storeapp.block.HeaderDetailView;
import org.vnp.storeapp.block.HeaderDetailView.IClickPage;
import org.vnp.storeapp.block.gallery.GalleryDetailView;
import org.vnp.storeapp.blocklist.Block;
import org.vnp.storeapp.blocklist.BlockCallBack;
import org.vnp.storeapp.service.StoreAppService;
import org.vnp.storeapp.service.StoreAppService.BlockType;

import android.os.Bundle;
import android.widget.LinearLayout;

public class BlockDetailItemActivity extends MBaseActivity implements IClickPage {
	org.vnp.storeapp.base.BasePagerAdapter mPagerAdapter;
	private HeaderDetailView headerDetailView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blockdetailitemactivity);
		headerDetailView = (HeaderDetailView) findViewById(R.id.bindInDetailView2);
		headerDetailView.setIClickPage(this);
	}

	@Override
	public void updateService(StoreAppService mService) {
		super.updateService(mService);
		
		List<Block> lBins = new BlockCallBack(this).getBinListRemoveAdd(this);
		for (Block block : lBins) {
			if (!mService.haveBlock(block.getType())) {
				lBins.remove(block);
			}
		}

		headerDetailView.setData(lBins);
		BaseView baseView = null; 
		String type = getIntent().getStringExtra("type");
		int position = getIntent().getIntExtra("pos", 0);
		if ("GALLERY".equals(type)) {
			baseView = new GalleryDetailView(this);
			baseView.setData(mService.parseResponse(BlockType.BLOCK_GALLERY));
		} else if ("PROFOLIO".equals(type)) {
//			baseView = new ProfolioDetailView(this);
//			baseView.setData(mService.parseResponse(BlockType.BLOCK_PROFOLIO));
		} else if ("NEW".equals(type)) {
//			baseView = new NewDetailView(this);
//			baseView.setData(mService.parseResponse(BlockType.BLOCK_NEW));
		}

		if(baseView!= null){
			((LinearLayout)findViewById(R.id.id_content)).addView(baseView);
			
			baseView.setGender();
			baseView.updatePosition(position);
		}
	}

	@Override
	public void onPageSelected(int position) {
	}

	@Override
	public void onClickpage(int pos) {
	}

}