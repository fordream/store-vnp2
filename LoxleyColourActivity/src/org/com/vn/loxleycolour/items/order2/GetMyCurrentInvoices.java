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

public class GetMyCurrentInvoices {
	public List<ItemGetMyCurrentInvoices> lCurrentInvoices = new ArrayList<ItemGetMyCurrentInvoices>();

	public void parse(String responseString) {
		if (!Common.isNullOrBlank(responseString)) {
			try {

				int index1 = responseString.indexOf("<NewDataSet");
				int index2 = responseString.indexOf("</NewDataSet>");

				String sub = responseString.substring(index1, index2) + "</NewDataSet>";

				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(sub));
				Document document = db.parse(is);
				//String name = "MyInvoices";
				//int count = 1;

				NodeList nodes;
				nodes = document.getElementsByTagName("MyInvoices");

				for (int i = 0; i < nodes.getLength(); i++) {
					ItemGetMyCurrentInvoices itemOrder = new ItemGetMyCurrentInvoices();
					Element e = (Element) nodes.item(i);
					
					itemOrder.BalanceDue = getValue(e, "BalanceDue");
					itemOrder.DespatchDate = getValue(e, "DespatchDate");
					itemOrder.DueDate = getValue(e, "DueDate");
					itemOrder.InvoiceID = getValue(e, "InvoiceID");
					itemOrder.InvoiceNumber = getValue(e, "InvoiceNumber");
					itemOrder.OrderDate = getValue(e, "OrderDate");
					itemOrder.OrderNumber = getValue(e, "OrderNumber");
					itemOrder.OrderReference = getValue(e, "OrderReference");
					itemOrder.ShippingMethod = getValue(e, "ShippingMethod");
					itemOrder.ShipToAddress = getValue(e, "ShipToAddress");
					itemOrder.ShipToAddress2 = getValue(e, "ShipToAddress2");
					itemOrder.ShipToCity = getValue(e, "ShipToCity");
					itemOrder.ShipToName = getValue(e, "ShipToName");
					itemOrder.ShipToPostcode = getValue(e, "ShipToPostcode");
					itemOrder.TermsCode = getValue(e, "TermsCode");
					
					itemOrder.TrackingNumber = getValue(e, "TrackingNumber");
					itemOrder.URL = getValue(e, "URL");
					
					lCurrentInvoices.add(itemOrder);

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
