package org.vnp.storeapp.block;

import java.util.ArrayList;
import java.util.List;

import org.core.store.app.StoreBaseView;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.app.R;
import com.example.app.blocklist.Block;

public class HeaderDetailView extends StoreBaseView implements
		android.view.View.OnClickListener {
	public interface IClickPage {

		public void onClickpage(int index);

	}

	private IClickPage clickPage;

	public void setIClickPage(IClickPage clickPage) {
		this.clickPage = clickPage;
	}

	public HeaderDetailView(Context context) {
		super(context);
		init(R.layout.headerdetailview);
	}

	private List<BlockDetailView> lBindInDetailView = new ArrayList<BlockDetailView>();

	public HeaderDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.headerdetailview);
	}

	@Override
	public void init(int res) {
		super.init(res);
		findViewById(R.id.mcontrol).setOnClickListener(this);
		rezise(findViewById(R.id.mcontrol), 960, BlockUtils.HEIGHT * 3 / 4);
		rezise(findViewById(R.id.bindInDetailView1), BlockUtils.WIDTH,
				BlockUtils.HEIGHT);
		hiddenEnimation(findViewById(R.id.horizontalScrollView1));
		findViewById(R.id.horizontalScrollView1).setOnClickListener(this);

		BlockDetailView bindInDetailView1 = getView(R.id.bindInDetailView1);
		bindInDetailView1.setData(new Block(getContext()));
		bindInDetailView1.setGender();
		bindInDetailView1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity) v.getContext()).finish();
			}
		});

		HorizontalScrollView _orizontalScrollView = getView(R.id._orizontalScrollView);
		_orizontalScrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				resetTimeAnimation();
				return false;
			}
		});
	}

	@Override
	public void setData(Object data) {
		super.setData(data);
		List<Object> objects = (List<Object>) getData();

		for (int i = 0; i < objects.size(); i++) {
			BlockDetailView bindInDetailView = new BlockDetailView(getContext());
			bindInDetailView.setData(objects.get(i));
			bindInDetailView.setGender();

			((LinearLayout) findViewById(R.id.mContentA))
					.addView(bindInDetailView);
			bindInDetailView.setOnClickListener(this);
			lBindInDetailView.add(bindInDetailView);
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		rezise(this, 960, BlockUtils.HEIGHT);
	}

	@Override
	public void setGender() {
	}

	@Override
	public void onClick(View v) {
		resetTimeAnimation();
		if (v.getId() == R.id.mcontrol) {
			exeEnimation(findViewById(R.id.horizontalScrollView1));
		} else if (v instanceof BlockDetailView) {
			int tab = lBindInDetailView.indexOf(v);
			clickPage.onClickpage(tab);
			setCurrentTab(tab);
		}
	}

	public void setCurrentTab(int index) {
		BlockDetailView view = lBindInDetailView.get(index);
		for (BlockDetailView v : lBindInDetailView) {
			if (view != v)
				v.unChecked();
		}

		view.updateCurentab(null);
	}
}