package vnp.mr.mintmedical.fragment;

import java.util.List;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.view.OfficeItemView;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.viewpagerindicator.db.DBClinic;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S4G extends MBaseFragment implements OnClickListener {
	// Appointment
	private ListView listView;

	@Override
	public void onResume() {
		super.onResume();
		listView = (ListView) findViewById(R.id.s4dlistView);
		updateUI();
	}

	private void updateUI() {
		setupListView(listView);
		listView.setAdapter(new BaseAdapter(getActivity(),
				(List<Object>) new DBClinic(getActivity()).getData(),
				new CommonGenderView() {
					@Override
					public CustomLinearLayoutView getView(Context arg0,
							Object arg1) {
						return new OfficeItemView(arg0);
					}
				}));

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getOnClickListener().onClick(
						parent.getItemAtPosition(position), 0);
			}
		});
	}

	@Override
	public int getLayout() {
		return R.layout.s4g;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.id1) {

		} else if (v.getId() == R.id.id2) {

		} else if (v.getId() == R.id.id3) {

		}
	}

	@Override
	public int getHeaderRes() {
		return R.string.s4g_header;
	}

	
}