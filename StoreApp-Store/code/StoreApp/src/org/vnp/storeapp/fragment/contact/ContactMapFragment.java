package org.vnp.storeapp.fragment.contact;

import org.vnp.storeapp.fragment.MapFagment;

public class ContactMapFragment extends MapFagment {
	public ContactMapFragment() {
		super();
	}

	@Override
	public void onResume() {
		super.onResume();
		addMaker(0.1, 0.3, null);
	}
}
