package vnp.mr.mintmedical;

import java.util.List;

import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.callback.S11Callback;
import vnp.mr.mintmedical.item.S11;
import vnp.mr.mintmedical.view.MintProgessDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.viewpagerindicator.db.DBS11;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S11Activity extends MBaseActivity implements OnItemClickListener {
	private HeaderView headerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		headerView = getView(R.id.s11headerview);
		headerView.updateText(R.string.s11header);
		headerView.showButton(true, true);
		headerView.updateTextButtonRight(R.string.cancel);
		headerView.setOnClickListenerButtonBack(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		headerView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		ListView listView = getView(R.id.s11listview);
		listView.setOnItemClickListener(this);

		setupListView(listView);
		updateUI();

		ExeCallBack callBack = new ExeCallBack();
		callBack.setExeCallBackOption(new ExeCallBackOption(this, true,
				R.string.loadding, new MintProgessDialog(this)));
		callBack.executeAsynCallBack(new S11Callback(this) {
			@Override
			public void loadSucess(String response) {
				super.loadSucess(response);
				updateUI();
			}
		});

	}

	private void updateUI() {
		ListView listView = getView(R.id.s11listview);
		List<Object> list = (List<Object>) new DBS11(this).getData();
		listView.setAdapter(new BaseAdapter(this, list, new CommonGenderView() {
			@Override
			public CustomLinearLayoutView getView(Context context, Object data) {
				return new S11ItemView(context);
			}
		}));
	}

	@Override
	public int getLayout() {
		return R.layout.s11activity;
	}

	private class S11ItemView extends CustomLinearLayoutView {

		public S11ItemView(Context context) {
			super(context);
			init(R.layout.s11itemview);
		}

		public S11ItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(R.layout.s11itemview);
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();

			resize.resizeSacle(findViewById(R.id.s11itemimg),
					MintUtils.IMGITEM_WIDTH2, MintUtils.IMGITEM_WIDTH2);
			resize.resizeSacle(findViewById(R.id.s11itemview_m1),
					MintUtils.WIDTH_ITEM,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.s11itemview_m1_main),
					MintUtils.WIDTH_ITEM, 80);

			resize.resizeSacle(findViewById(R.id.s11itemcontent),
					MintUtils.WIDTH_ITEM - 13,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.setTextsize(findViewById(R.id.s11itemview_text1),
					MintUtils.TEXTSIZE_ITEM1);
			resize.setTextsize(findViewById(R.id.s11itemview_text2),
					MintUtils.TEXTSIZE_ITEM1 - 1);
			resize.setTextsize(findViewById(R.id.s11itemview_text3),
					MintUtils.TEXTSIZE_ITEM1 - 1);
		}

		@Override
		public void setGender() {
			if (getData() instanceof S11) {
				S11 item = (S11) getData();
				((TextView) findViewById(R.id.s11itemview_text1))
						.setText(item.sender);
				((TextView) findViewById(R.id.s11itemview_text2))
						.setText(MintUtils.cutDate(item.info_datetime));
				((TextView) findViewById(R.id.s11itemview_text3))
						.setText(item.info);
			}
		}

		@Override
		public void showHeader(boolean arg0) {

		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		S11 item = (S11) parent.getItemAtPosition(position);
		S11Detailt s11Detailt = new S11Detailt(this, item);
		s11Detailt.show();
	}
}