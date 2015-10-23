package vnp.mr.mintmedical.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.doctor.DBDoctor;
import vnp.mr.mintmedical.doctor.Doctor;
import vnp.mr.mintmedical.item.Clinic;
import vnp.mr.mintmedical.view.AvatarView;
import vnp.mr.mintmedical.view.SearchView;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.viewpagerindicator.db.DBClinic;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class S4H extends MBaseFragment implements OnClickListener {
	private SearchView searchView;

	@Override
	public void onHiddenKeyBoard() {
		super.onHiddenKeyBoard();
		if (searchView != null)
			searchView.onHiddenKeyBoard();
	}

	// Appointment
	private ListView listView;

	@Override
	public void onResume() {
		super.onResume();

		listView = (ListView) findViewById(R.id.s4dlistView);
		setupListView(listView);
		searchView = (SearchView) findViewById(R.id.s4hsearchView1);

		searchView.addTextChangedListener(watcher);
		updateUi();
	}

	private TextWatcher watcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			String text = s.toString();
			if (text.equals("")) {
				updateUi(listShow);
			} else {
				List<Object> lShow = new ArrayList<Object>();

				for (Object object : listShow) {
					S4HItemShow show = (S4HItemShow) object;

					if (show.item.name.toLowerCase().contains(
							text.toLowerCase())) {
						lShow.add(show);
					}
				}
				updateUi(lShow);
			}
		}
	};
	List<Object> listShow = new ArrayList<Object>();

	private void updateUi() {
		listShow.clear();

		List<Object> list = (List<Object>) new DBDoctor(getActivity())
				.getData();

		List<Object> clincsList = (List<Object>) new DBClinic(getActivity())
				.getData();
		for (Object doctor : list) {
			S4HItemShow itemShow = new S4HItemShow();
			itemShow.item = (Doctor) doctor;

			for (Object clincs : clincsList) {
				if (itemShow.item.clinic_id.equals(((Clinic) clincs).id)) {
					itemShow.clinic = (Clinic) clincs;
					break;
				}
			}

			listShow.add(itemShow);
		}

		Comparator<? super Object> a = new Comparator<Object>() {

			@Override
			public int compare(Object lhs, Object rhs) {

				if (lhs instanceof S4HItemShow & rhs instanceof S4HItemShow) {
					S4HItemShow s4h1 = (S4HItemShow) lhs;
					S4HItemShow s4h2 = (S4HItemShow) rhs;
					return s4h1.item.name.toLowerCase().compareTo(
							s4h2.item.name.toLowerCase());
				}
				return 0;
			}
		};
		Collections.sort(listShow, a);

		updateUi(listShow);
	}

	private void updateUi(List<Object> listShow) {
		listView.setAdapter(new S4HAdater(getActivity(), listShow,
				new CommonGenderView() {
					@Override
					public CustomLinearLayoutView getView(Context arg0,
							Object arg1) {
						return new S4HItemView(arg0);
					}
				}));
		setupListView(listView);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getOnClickListener()
						.onClick(
								((S4HItemShow) parent
										.getItemAtPosition(position)).item, 0);
			}
		});
		listView.setFastScrollEnabled(true);
	}

	@Override
	public int getLayout() {
		return R.layout.s4h;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.id1) {

		} else if (v.getId() == R.id.id2) {

		} else if (v.getId() == R.id.id3) {

		}
	}

	private class S4HItemView extends CustomLinearLayoutView {

		public S4HItemView(Context context) {
			super(context);
			init(R.layout.s4hitemview);
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();

			resize.resizeSacle(this, LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.s4h1), MintUtils.WIDTH_ITEM,
					LayoutParams.WRAP_CONTENT);

			resize.setTextsize(findViewById(R.id.s4hitemview_textview1),
					MintUtils.TEXTSIZE_ITEM1);
			resize.setTextsize(findViewById(R.id.s4hitemview_textview2),
					MintUtils.TEXTSIZE_ITEM2);
			resize.setTextsize(findViewById(R.id.s4hitemview_textview3),
					MintUtils.TEXTSIZE_ITEM2);

			resize.resizeSacle(findViewById(R.id.s4hitemview_content_main),
					MintUtils.WIDTH_ITEM, 80);

			resize.resizeSacle(findViewById(R.id.s4hitemview_content),
					MintUtils.WIDTH_ITEM - MintUtils.IMGITEM_WIDTH,
					LayoutParams.WRAP_CONTENT);
			AvatarView avatarView = (AvatarView) findViewById(R.id.s4hitemviewavatarView1);

			avatarView.setType(AvatarView.S4H);
		}

		@Override
		public void setGender() {
			Doctor item = ((S4HItemShow) getData()).item;
			Clinic clinic = ((S4HItemShow) getData()).clinic;

			if (item == null)
				item = new Doctor();

			if (clinic == null)
				clinic = new Clinic();

			setText(R.id.s4hitemview_textview1, String.format("%s", item.name));
			String professional = (item.professional == null ? "" : item.professional);
			if(professional.equals("null")){
				professional = "";
			}
			setText(R.id.s4hitemview_textview3, String.format("%s \n%s", item.level, professional));

			AvatarView avatarView = (AvatarView) findViewById(R.id.s4hitemviewavatarView1);
			avatarView.loadAvartar(item.avatar);
			setText(R.id.s4hitemview_textview2, clinic.address);
		}

		private void setText(int s4ditemviewTextheader, String day) {
			((TextView) findViewById(s4ditemviewTextheader)).setText(day);
		}

		@Override
		public void showHeader(boolean arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	public int getHeaderRes() {
		return R.string.s4h_header;
	}

	public static class S4HItemShow {
		Doctor item;
		Clinic clinic;
	}
}
