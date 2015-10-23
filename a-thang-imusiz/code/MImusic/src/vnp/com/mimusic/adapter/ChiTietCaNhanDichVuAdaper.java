package vnp.com.mimusic.adapter;

import vnp.com.mimusic.R;
import vnp.com.mimusic.view.BangXepHangItemView;
import vnp.com.mimusic.view.ChiTietCaNhanBangXepHangItemView;
import vnp.com.mimusic.view.ChiTietCaNhanDichVuItemView;
import vnp.com.mimusic.view.MenuLeftFooterView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChiTietCaNhanDichVuAdaper extends ArrayAdapter<String> {

	public ChiTietCaNhanDichVuAdaper(Context context, String[] objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = new ChiTietCaNhanDichVuItemView(parent.getContext());
		}

		return convertView;
	}
}
