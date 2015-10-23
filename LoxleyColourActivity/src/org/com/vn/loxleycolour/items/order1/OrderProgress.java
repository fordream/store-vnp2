package org.com.vn.loxleycolour.items.order1;

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

public class OrderProgress {
	String data;
	public List<ItemOrder> lData = new ArrayList<ItemOrder>();

	public void pare(String data) {
		this.data = data;

		if (!Common.isNullOrBlank(data)) {
			try {

				int index1 = data.indexOf("<NewDataSet");
				int index2 = data.indexOf("</NewDataSet>");

				String sub = data.substring(index1, index2) + "</NewDataSet>";

				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(sub));
				Document document = db.parse(is);
				//String name = "MyOrders";
				//int count = 1;

				NodeList nodes;
				nodes = document.getElementsByTagName("MyOrders");

				for (int i = 0; i < nodes.getLength(); i++) {
					ItemOrder itemOrder = new ItemOrder();
					Element e = (Element) nodes.item(i);
					itemOrder.AmountPaid = getValue(e, "AmountPaid");
					itemOrder.BalanceDue = getValue(e, "BalanceDue");
					itemOrder.DueDate = getValue(e, "DueDate");
					itemOrder.OrderDate = getValue(e, "OrderDate");
					itemOrder.OrderID = getValue(e, "OrderID");
					itemOrder.OrderNumber = getValue(e, "OrderNumber");
					itemOrder.OrderReference = getValue(e, "OrderReference");
					itemOrder.OrderStatus = getValue(e, "OrderStatus");
					itemOrder.ShippingMethod = getValue(e, "ShippingMethod");
					itemOrder.ShipToAddress = getValue(e, "ShipToAddress");

					itemOrder.ShipToAddress2 = getValue(e, "ShipToAddress2");
					itemOrder.ShipToCity = getValue(e, "ShipToCity");
					itemOrder.ShipToName = getValue(e, "ShipToName");
					itemOrder.ShipToPostCode = getValue(e, "ShipToPostCode");
					itemOrder.TermsCode = getValue(e, "TermsCode");
					itemOrder.TotalPrice = getValue(e, "TotalPrice");
					lData.add(itemOrder);

					//count++;
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
