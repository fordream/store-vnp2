package org.vnp.storeapp.block.map;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseMapFagment;
import org.vnp.storeapp.service.StoreAppService.BlockType;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

//com.google.android.gms.maps.MapFragment
public class MapFagment extends BaseMapFagment {
	private GoogleMap mMap;
	// private Marker customMarker;
	private LatLng markerLatLng;

	public MapFagment() {
		super();
		setType(BlockType.BLOCK_MAP);
		setCanRunExecute(false);
	}

	@Override
	public void onStart() {
		super.onStart();
		mMap = getMap();

		Map itemMap = (Map) new MapCallBack(getActivity()).parseResponse();
		markerLatLng = new LatLng(itemMap.getLat(), itemMap.getLng());
		View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
		TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
		numTxt.setText(itemMap.numTxt);

		// customMarker =
//		mMap.addMarker(new MarkerOptions().position(markerLatLng).title(itemMap.title).snippet(itemMap.description)
//				.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), marker))));
	}

	// Convert a view to bitmap
	public static Bitmap createDrawableFromView(Context context, View view) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
		view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
		view.buildDrawingCache();
		Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmap);
		view.draw(canvas);

		return bitmap;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}