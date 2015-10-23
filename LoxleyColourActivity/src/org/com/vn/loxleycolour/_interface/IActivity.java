package org.com.vn.loxleycolour._interface;

import org.com.vn.loxleycolour.items.order1.ItemOrder;
import org.com.vn.loxleycolour.items.order2.ItemGetMyCurrentInvoices;

public interface IActivity {
	public void gotoFace();

	public void gotoTWi();

	public boolean onBack();

	public boolean gotoForgotPass();

	public void gotoLogin();

	public void gotoOrderMenu();

	public void gotoNewHome();

	public void gotoWeb(String url);

	public void gotoOrderInProgress();

	public void gotoCompleteOrder();

	public void gotoDetailOrderInProgess(ItemOrder orderNumber);

	public void gotoDetailOrderComplete(ItemGetMyCurrentInvoices InvoiceNumber);

	public void gotoTrack(ItemGetMyCurrentInvoices itemOrder);
}
