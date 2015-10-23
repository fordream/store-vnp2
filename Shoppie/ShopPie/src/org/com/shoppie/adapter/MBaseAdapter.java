package org.com.shoppie.adapter;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MBaseAdapter extends ArrayAdapter<Object> {
	List<Object> objects;
	public MBaseAdapter(Context context, List<Object> objects) {
		super(context, 0, objects);
		this.objects = objects;
	}
	
	public void setData(List<Object> oblects){
		this.objects.clear();
		this.objects.addAll(oblects);
	}
}
