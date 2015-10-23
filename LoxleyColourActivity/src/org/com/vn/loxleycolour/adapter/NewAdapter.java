package org.com.vn.loxleycolour.adapter;

import java.util.ArrayList;
import java.util.List;

import org.com.vn.loxleycolour.items.ItemNew;
import org.com.vn.loxleycolour.views.ItemNewView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class NewAdapter extends ArrayAdapter<ItemNew> {
	private List<ItemNew> lItemNews = new ArrayList<ItemNew>();

	public NewAdapter(Context context, List<ItemNew> objects) {
		super(context, 0, objects);
		lItemNews = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return new ItemNewView(getContext()).setData(lItemNews.get(position));
	}

	public void setData(List<ItemNew> lItemNews2) {
		lItemNews.clear();
		//lItemNews.addAll(lItemNews2);
	}
}
