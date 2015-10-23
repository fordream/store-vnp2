package vnp.mr.mintmedical.s5;

import java.util.List;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.callback.S51Callback;
import vnp.mr.mintmedical.item.S51Item;
import vnp.mr.mintmedical.view.MintProgessDialog;
import vnp.mr.mintmedical.view.S51ItemView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.viewpagerindicator.db.DBS51;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S51 extends MBaseFragment {
	@Override
	public int getLayout() {
		return R.layout.s51;
	}

	@Override
	public void onResume() {
		super.onResume();
		setupListView(findViewById(R.id.listView1));
		resize.resizeSacle(findViewById(R.id.s5_content), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.textView1), MintUtils.WIDTH_ITEM,
				60);
		resize.setTextsize(findViewById(R.id.textView1), MintUtils.TEXTSIZE_ITEM1);

		updateUI();
		ExeCallBack callBack = new ExeCallBack();
		callBack.setExeCallBackOption(new ExeCallBackOption(getActivity(),
				true, R.string.loadding,new MintProgessDialog(getActivity())));
		callBack.executeAsynCallBack(new S51Callback(getActivity()) {
			@Override
			public void loadSucess(String response) {
				super.loadSucess(response);
				updateUI();
			}
		});
	}

	private void updateUI() {
		List<Object> list = (List<Object>) new DBS51(getActivity()).getData();
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(new BaseAdapter(getActivity(), list,
				new CommonGenderView() {

					@Override
					public CustomLinearLayoutView getView(Context arg0,
							Object arg1) {
						return new S51ItemView(arg0);
					}
				}));

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				S51Item item = (S51Item) parent.getItemAtPosition(position);

				if (getOnClickListener() != null)
					getOnClickListener().onClick(item, 0);
			}
		});

	}

	@Override
	public int getHeaderRes() {
		return R.string.s5header;
	}
}