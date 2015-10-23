package org.com.vn.loxleycolour.adapter;

import java.util.ArrayList;
import java.util.List;

import org.com.vn.loxleycolour.items.order1.ItemOrder;
import org.com.vn.loxleycolour.views.orderinprogress.ItemProgressView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ProgressAdapter extends ArrayAdapter<ItemOrder> {
	protected List<ItemOrder> lItemNews = new ArrayList<ItemOrder>();

	public ProgressAdapter(Context context, List<ItemOrder> objects) {
		super(context, 0, objects);
		lItemNews = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return new ItemProgressView(getContext()).setData(lItemNews.get(position));
	}

	public void setData(List<ItemOrder> lItemNews2) {
		lItemNews.clear();
		lItemNews.addAll(lItemNews2);
	}
}
