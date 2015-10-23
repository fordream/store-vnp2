package org.com.vn.loxleycolour.items.order2;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.com.cnc.common.android.Common;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GetMyInvoiceItems {
	public List<ItemGetMyInvoiceItems> lGetMyOrderItems = new ArrayList<ItemGetMyInvoiceItems>();

	public void parse(String responseString) {
		if (!Common.isNullOrBlank(responseString)) {
			try {

				int index1 = responseString.indexOf("<NewDataSet");
				int index2 = responseString.indexOf("</NewDataSet>");

				String sub = responseString.substring(index1, index2)
						+ "</NewDataSet>";

				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(sub));
				Document document = db.parse(is);

				NodeList nodes;
				nodes = document.getElementsByTagName("MyInvoiceItems");

				for (int i = 0; i < nodes.getLength(); i++) {
					ItemGetMyInvoiceItems itemOrder = new ItemGetMyInvoiceItems();
					Element e = (Element) nodes.item(i);

					itemOrder.Amount = getValue(e, "Amount");
					itemOrder.AmountIncVAT = getValue(e, "AmountIncVAT");
					itemOrder.DiscountAmount = getValue(e, "DiscountAmount");
					itemOrder.ItemCode = getValue(e, "ItemCode");
					itemOrder.ItemDescription = getValue(e, "ItemDescription");
					itemOrder.Quantity = getValue(e, "Quantity");
					itemOrder.UnitCost = getValue(e, "UnitCost");
					itemOrder.VatRate = getValue(e, "VatRate");
					itemOrder.DiscountRate = getValue(e, "DiscountRate");
					lGetMyOrderItems.add(itemOrder);

				}

			} catch (Exception e) {
			}
		}
	}

	public static String getValue(Element item, String str) {
		try {
			NodeList n = item.getElementsByTagName(str);
			return getElementValue(n.item(0));
		} catch (Exception e) {
			return "";
		}
	}

	public final static String getElementValue(Node elem) {
		Node kid;
		if (elem != null) {
			if (elem.hasChildNodes()) {
				for (kid = elem.getFirstChild(); kid != null; kid = kid
						.getNextSibling()) {
					if (kid.getNodeType() == Node.TEXT_NODE) {
						return kid.getNodeValue();
					}
				}
			}
		}
		return "";
	}
}
