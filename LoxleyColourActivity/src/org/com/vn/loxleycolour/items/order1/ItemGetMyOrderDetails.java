package org.com.vn.loxleycolour.items.order1;

import android.util.Log;

public class ItemGetMyOrderDetails {
	public String AccountNo;
	public String InvoiceTo;
	public String BillToAddress1;
	public String BillToAddress2;
	public String BillToCity;
	public String BillToPostcode;
	public String OrderDate;
	public String DueDate;
	public String ShipToName;
	public String shipToAddress1String;
	public String ShipToAddress2;
	public String ShipToCity;
	public String ShipToPostcode;
	public String ShipmentMethod;
	public String CustomerReference;
	public String ReworkOrderNo;
	public String BalanceDue;
	public String PurchaseOrderNo;
	
	@Override
	public String toString() {
		
		Log.i("AAAAAAAAAAAAAAA", OrderDate + "--------------------------");
		return super.toString();
	}
}
