package org.com.vn.loxleycolour.adapter;

import java.util.ArrayList;
import java.util.List;

import org.com.vn.loxleycolour.items.order1.ItemGetMyOrderItems;
import org.com.vn.loxleycolour.views.orderinprogress.ItemProgressDetailView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ProgressDetailAdapter extends ArrayAdapter<ItemGetMyOrderItems> {
	protected List<ItemGetMyOrderItems> lItemNews = new ArrayList<ItemGetMyOrderItems>();

	public ProgressDetailAdapter(Context context, List<ItemGetMyOrderItems> objects) {
		super(context, 0, objects);
		lItemNews = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return new ItemProgressDetailView(getContext()).setData(lItemNews.get(position));
	}

	public void setData(List<ItemGetMyOrderItems> lItemNews2) {
		lItemNews.clear();
		lItemNews.addAll(lItemNews2);
	}
}
