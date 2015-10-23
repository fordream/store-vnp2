package com.example.org.com.driver.view;

import com.vnp.core.datastore.DataStore;

import android.content.Context;

public class ProDB {

	public ProDB(Context context) {
		DataStore.getInstance().init(context);
	}

	public String getFullName() {
		return DataStore.getInstance().get("getFullName", "");
	}

	public void saveFullName(String str) {
		DataStore.getInstance().save("getFullName", str);
	}

	public String getEmail() {
		return DataStore.getInstance().get("getEmail", "");
	}

	public void saveEmail(String str) {
		DataStore.getInstance().save("getEmail", str);
	}

	public String getPhone() {
		return DataStore.getInstance().get("getPhone", "");
	}

	public void savePhone(String str) {
		DataStore.getInstance().save("getPhone", str);
	}

	public String getRepairShop() {
		return DataStore.getInstance().get("getRepairShop", "");
	}

	public void saveRepairShop(String string) {
		DataStore.getInstance().save("getRepairShop", string);
	}

	public void savePhoneShop(String string) {
		DataStore.getInstance().save("savePhoneShop", string);
	}

	public String getPhoneShop() {
		return DataStore.getInstance().get("savePhoneShop", "(###) ###-####");
	}

	public void savePasswrod(String password) {
		DataStore.getInstance().save("savePasswrod", password);
	}

	public String getPassword() {
		return DataStore.getInstance().get("savePasswrod", "");
	}

	public void saveEndWall(boolean b) {
		DataStore.getInstance().save("saveEndWall", b);
	}

	public boolean isEndWall() {
		return DataStore.getInstance().get("saveEndWall", false);
	}

	public void clear() {
		saveEmail("");
		saveEndWall(false);
		saveFullName("");
		savePasswrod("");
		savePhone("");
		saveRepairShop("");
		savePhoneShop("");
	}

}