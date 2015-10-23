package vnp.mr.mintmedical.s5;

import java.util.List;

import com.viewpagerindicator.db.DBS51;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.item.S51Item;
import vnp.mr.mintmedical.item.S52Item;
import vnp.mr.mintmedical.view.S51ItemView;
import vnp.mr.mintmedical.view.S52BItemView;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class S52 extends MBaseFragment {
	S51Item item;

	public S52(S51Item item) {
		super();
		this.item = item;
	}

	@Override
	public int getLayout() {
		return R.layout.s52;
	}

	@Override
	public void onResume() {
		super.onResume();
		setupListView(findViewById(R.id.listView1));
		resize.resizeSacle(findViewById(R.id.s5_content), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.s9_main_block2),
				MintUtils.WIDTH_ITEM, 80);
		resize.setTextsize(findViewById(R.id.s9texxt3),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.TextView01),
				MintUtils.TEXTSIZE_ITEM1);

		resize.setTextsize(findViewById(R.id.s9texxt4),
				MintUtils.TEXTSIZE_ITEM2);
		updateUI();
	}

	List<Object> list = null;

	private void updateUI() {
		((TextView) findViewById(R.id.s9texxt3)).setText(item.symp_name);
		((TextView) findViewById(R.id.s9texxt4)).setText(item.symp_desc);

		if (list == null) {
			list = (List<Object>) new DBS51(getActivity()).getData(item.id);
		}
		setupListView(findViewById(R.id.listView1));
		if (list != null) {
			ListView listView = (ListView) findViewById(R.id.listView1);
			listView.setAdapter(new BaseAdapter(getActivity(), list,
					new CommonGenderView() {

						@Override
						public CustomLinearLayoutView getView(Context arg0,
								Object arg1) {
							return new S52BItemView(arg0);
						}
					}));
		}
	}

	@Override
	public int getHeaderRes() {
		return R.string.s52header;
	}

	public String getSymptoms() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (list != null) {
			boolean start = true;
			for (int i = 0; i < list.size(); i++) {
				S52Item obs = (S52Item) list.get(i);

				if (obs.isCheced) {
					String data = String.format("\"%s\":\"%s\"", obs.question, obs.code);
					
					if(!start){
						data = String.format(",\"%s\":\"%s\"", obs.question, obs.code);
					}
					builder.append(data);
					start= false;
				}
			}
		}
		builder.append("}");
		return builder.toString();
	}
}