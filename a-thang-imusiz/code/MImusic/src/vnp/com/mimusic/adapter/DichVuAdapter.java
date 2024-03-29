package vnp.com.mimusic.adapter;

import vnp.com.db.DichVu;
import vnp.com.mimusic.R;
import vnp.com.mimusic.base.diablog.DangKyDialog;
import vnp.com.mimusic.view.DichVuItemView;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class DichVuAdapter extends CursorAdapter {

	public DichVuAdapter(Context context, Cursor c) {
		super(context, c, true);
	}

	@Override
	public void bindView(View convertView, Context context, Cursor cursor) {
		if (convertView == null) {
			convertView = new DichVuItemView(context);
		}

		((DichVuItemView) convertView).setData(cursor);

		ImageView home_item_img_icon = (ImageView) convertView.findViewById(R.id.home_item_img_icon);
		ImageView home_item_right_control_1 = (ImageView) convertView.findViewById(R.id.home_item_right_control_1);
		ImageView home_item_right_control_2 = (ImageView) convertView.findViewById(R.id.home_item_right_control_2);

		TextView home_item_tv_name = (TextView) convertView.findViewById(R.id.home_item_tv_name);
		TextView home_item_tv_link = (TextView) convertView.findViewById(R.id.home_item_tv_link);
		TextView home_item_tv_content = (TextView) convertView.findViewById(R.id.home_item_tv_content);

		home_item_img_icon.setBackgroundResource(R.drawable.icon_imusiz);

		home_item_tv_name.setText(cursor.getString(cursor.getColumnIndex(DichVu.NAME)));
		home_item_tv_link.setText(cursor.getString(cursor.getColumnIndex(DichVu.URL)));
		home_item_tv_content.setText(cursor.getString(cursor.getColumnIndex(DichVu.SHORTCONTENT)));

		final ContentValues values = new ContentValues();
		values.put("name", cursor.getString(cursor.getColumnIndex(DichVu.NAME)));
		values.put("content", cursor.getString(cursor.getColumnIndex(DichVu.SHORTCONTENT)));
		home_item_right_control_1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new DangKyDialog(v.getContext(), values).show();
			}
		});

		home_item_right_control_2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});
		convertView.findViewById(R.id.home_item_right_control_2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				moiDVChoNhieuNguoi();
			}
		});

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return new DichVuItemView(context);
	}

	public abstract void moiDVChoNhieuNguoi();
}
