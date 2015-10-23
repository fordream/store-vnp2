package org.com.vn.loxleycolour.adapter;

import java.util.ArrayList;
import java.util.List;

import org.com.vn.loxleycolour.items.order2.ItemGetMyInvoiceItems;
import org.com.vn.loxleycolour.views.complete.ItemCompleteDetailView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CompleteDetailAdapter extends ArrayAdapter<ItemGetMyInvoiceItems> {
	protected List<ItemGetMyInvoiceItems> lItemNews = new ArrayList<ItemGetMyInvoiceItems>();

	public CompleteDetailAdapter(Context context,
			List<ItemGetMyInvoiceItems> objects) {
		super(context, 0, objects);
		lItemNews = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return new ItemCompleteDetailView(getContext()).setData(lItemNews
				.get(position));
	}

	public void setData(List<ItemGetMyInvoiceItems> lItemNews2) {
		lItemNews.clear();
		lItemNews.addAll(lItemNews2);
	}
}
