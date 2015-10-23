package org.com.vn.loxleycolour.items;

import org.com.cnc.common.android.Common;

public class Login {
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isSuccess() {
		if (!Common.isNullOrBlank(data)) {
			if (data.contains(",")) {
				return true;
			}
		}
		return false;
	}
}
