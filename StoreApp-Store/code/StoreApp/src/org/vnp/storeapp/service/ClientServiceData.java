package org.vnp.storeapp.service;

import android.os.Parcel;
import android.os.Parcelable;

public class ClientServiceData implements Parcelable {
	private String keyCreate = System.currentTimeMillis() + "";
	private String api = "";
	private int messageCode = 0;
	private String response = "";

	public ClientServiceData(Parcel source) {
		// this.mSName = source.readString();
		// this.mSAge = source.readInt();
		// this.mSAddress = source.readString();
		// this.mSCourse = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// dest.writeString(mSName);
		// dest.writeInt(mSAge);
		// dest.writeString(mSAddress);
		// dest.writeString(mSCourse);
	}

	public static final Parcelable.Creator<ClientServiceData> CREATOR = new Parcelable.Creator<ClientServiceData>() {

		@Override
		public ClientServiceData createFromParcel(Parcel source) {
			return new ClientServiceData(source);
		}

		@Override
		public ClientServiceData[] newArray(int size) {
			return new ClientServiceData[size];
		}
	};
}
