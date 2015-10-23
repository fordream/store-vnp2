package vnp.mr.mintmedical.s5;

import java.util.ArrayList;
import java.util.List;

import vnp.mr.mintmedical.HeaderView;
import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.view.S6ItemView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.viewpagerindicator.db.DBHome;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S5Activity extends MBaseActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		HeaderView headerView = (HeaderView) findViewById(R.id.activitymain_headerview);
		headerView.showButton(true, false);

		headerView.updateText(R.string.s5mainheader);
		headerView.setOnClickListenerButtonBack(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		//checkMeberShip();
	}

	private void checkMeberShip() {
		try {
			String memberShip = new DBHome(this).getMemberShip();
			memberShip = memberShip.substring(11);
			
			MintUtils.showDialog(this, memberShip.replace("-", ""), new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});
		} catch (Exception exception) {

		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		listView = (ListView) findViewById(R.id.s6listview);
		setupListView(listView);

		List<Object> list = new ArrayList<Object>();
		templete(list);
		listView.setAdapter(new BaseAdapter(this, list, new CommonGenderView() {
			@Override
			public CustomLinearLayoutView getView(Context context, Object data) {
				return new S6ItemView(context);
			}
		}));

		setupListView(listView);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				S5Item item = (S5Item) parent.getItemAtPosition(position);
				if (item.clazz != null) {
					startActivity(new Intent(S5Activity.this, item.clazz));
				}
			}
		});
	}

	private void templete(List<Object> list) {
		list.add(new S5Item(R.drawable.s62, getString(R.string.s5_1), null,
				S5NewCaseActivity.class));

		list.add(new S5Item(R.drawable.s63, getString(R.string.s5_2), null,
				S5CheckRequestAnStatusBActivity.class));

	}

	public class S5Item {
		public int res;
		public String header;
		public String suHeader;
		public Class clazz;

		public S5Item(int i, String string, String string2, Class clazz) {
			res = i;
			header = string;
			suHeader = string2;
			this.clazz = clazz;
		}

	}

	@Override
	public int getLayout() {
		return R.layout.s6activity;
	}
}