package org.com.vn.loxleycolour.adapter;

import java.util.ArrayList;
import java.util.List;

import org.com.vn.loxleycolour.items.order2.ItemGetMyCurrentInvoices;
import org.com.vn.loxleycolour.views.complete.ItemProgressCompleView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CompleteProgressAdapter extends ArrayAdapter<ItemGetMyCurrentInvoices>{

	protected List<ItemGetMyCurrentInvoices> lItemNews = new ArrayList<ItemGetMyCurrentInvoices>();

	public CompleteProgressAdapter(Context context, List<ItemGetMyCurrentInvoices> objects) {
		super(context, 0, objects);
		lItemNews = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return new ItemProgressCompleView(getContext()).setData(lItemNews.get(position));
	}

	public void setData(List<ItemGetMyCurrentInvoices> lItemNews2) {
		lItemNews.clear();
		lItemNews.addAll(lItemNews2);
	}
}
