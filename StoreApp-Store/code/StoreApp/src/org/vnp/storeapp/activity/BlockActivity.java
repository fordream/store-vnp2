package org.vnp.storeapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.R;
import org.vnp.storeapp.base.MBaseActivity;
import org.vnp.storeapp.blocklist.Block;
import org.vnp.storeapp.blocklist.BlockCallBack;
import org.vnp.storeapp.fragment.AboutFagment;
import org.vnp.storeapp.fragment.ContactFragment;
import org.vnp.storeapp.fragment.GalleryFagment;
import org.vnp.storeapp.fragment.NewFagment;
import org.vnp.storeapp.fragment.NewVersionFragment;
import org.vnp.storeapp.fragment.ProfolioFagment;
import org.vnp.storeapp.utils.StoreAppUtils;
import org.vnp.storeapp.utils.StoreAppUtils.SIZE_BLOCKACTIVITY;
import org.vnp.storeapp.views.MainHeaderView;
import org.vnp.storeapp.views.MenuHeaderView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class BlockActivity extends MBaseActivity implements OnClickListener {
	org.vnp.storeapp.base.BasePagerAdapter mPagerAdapter;
	private BlockType type;
	private MainHeaderView headerView;
	private List<Object> list = new ArrayList<Object>();

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.blockactivity_new);
		headerView = (MainHeaderView) findViewById(R.id.mainHeaderView1);
		headerView.setOnClickListener(this);
		headerView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					menuConfig(false);
				}

				return false;
			}
		});

		findViewById(R.id.blockactivity_control_cancel_tocuch)
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						menuConfig(false);
					}
				});
		list = (List<Object>) new BlockCallBack(this).parseResponse();
		Block _block = new Block(BlockType.HOME);

		list.add(0, _block);
		LinearLayout scrollView = (LinearLayout) findViewById(R.id.blockactivity_new_control);
		for (Object object : list) {
			final Block block = (Block) object;
			MenuHeaderView headerView = new MenuHeaderView(this);
			headerView.setData(block);
			scrollView.addView(headerView);
			headerView.setGender();
			headerView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Block mBlock = block;
					if (mBlock.getType() == BlockType.HOME) {
						finish();
					} else {
						menuConfig(false);
						updateUI(block.getType());
					}
				}
			});
		}
		type = (BlockType) getIntent().getSerializableExtra("bin");
		updateUI(type);
	}

	@Override
	protected void onResume() {
		super.onResume();
		menuConfig(true);
		if (list.size() > 5) {
			resize.resizeSacle(findViewById(R.id.scrollView1),
					LayoutParams.MATCH_PARENT,
					SIZE_BLOCKACTIVITY.HEIGHT_MENU_CONTROLLER * 5);
		}
	}

	private void updateUI(BlockType type) {

		LinearLayout scrollView = (LinearLayout) findViewById(R.id.blockactivity_new_control);

		for (int i = 0; i < scrollView.getChildCount(); i++) {
			if (scrollView.getChildAt(i) instanceof MenuHeaderView) {
				MenuHeaderView menu = (MenuHeaderView) scrollView.getChildAt(i);
				if (menu.getData() instanceof Block) {
					Block block = (Block) menu.getData();
					menu.setChooser(block.getType() == type);
				}
			}
		}

		this.type = type;

		for (Object block : list) {
			if (type == ((Block) block).getType()) {
				Block block2 = (Block) block;
				String url = block2.getImg();

				if (StoreAppUtils.isBlank(url)) {
					url = block2.getSub_icon();
				}

				if (StoreAppUtils.isBlank(url)) {
					url = block2.getBackgroundImg();
				}

				if (StoreAppUtils.isBlank(url)) {
					url = block2.getBackgroundImg();
				}

				headerView.update(block2.getHeader(),
						block2.getBackgoundColor(), type, url);
				break;
			}
		}

		if (type == BlockType.PORTFOLIO) {
			changeFragemtn(R.id.blockactivity_new_block_content,
					new ProfolioFagment());
		} else if (type == BlockType.LATESTNEWS) {
			changeFragemtn(R.id.blockactivity_new_block_content,
					new NewFagment());
		} else if (type == BlockType.CONTACT) {
			changeFragemtn(R.id.blockactivity_new_block_content,
					new ContactFragment());
		} else if (type == BlockType.GALLERY) {
			changeFragemtn(R.id.blockactivity_new_block_content,
					new GalleryFagment());
		} else if (type == BlockType.ABOUT) {
			changeFragemtn(R.id.blockactivity_new_block_content,
					new AboutFagment());

		} else {
			changeFragemtn(R.id.blockactivity_new_block_content,
					new NewVersionFragment());
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == headerView.getId()) {
		}
	}

	private void menuConfig(boolean isResme) {
		View scrollView = findViewById(R.id.scrollView1);

		if (isResme) {
			headerView.showIcon(true);
			scrollView.setVisibility(View.GONE);
			findViewById(R.id.blockactivity_control_cancel_tocuch)
					.setVisibility(View.GONE);
		} else {
			if (scrollView.getVisibility() == View.VISIBLE) {
				headerView.showIcon(true);
				scrollView.setVisibility(View.GONE);
				findViewById(R.id.blockactivity_control_cancel_tocuch)
						.setVisibility(View.GONE);
			} else {
				scrollView.setVisibility(View.VISIBLE);
				headerView.showIcon(false);
				findViewById(R.id.blockactivity_control_cancel_tocuch)
						.setVisibility(View.VISIBLE);
			}
		}

	}
}