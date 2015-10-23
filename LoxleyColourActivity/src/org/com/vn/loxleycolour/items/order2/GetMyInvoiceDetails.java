package org.com.vn.loxleycolour.items.order2;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.com.cnc.common.android.Common;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GetMyInvoiceDetails {
	public ItemGetMyInvoiceDetails details = new ItemGetMyInvoiceDetails();

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
				nodes = document.getElementsByTagName("MyInvoiceDetails");

				for (int i = 0; i < nodes.getLength(); i++) {
					Element e = (Element) nodes.item(i);
					details.AccountNo = getValue(e, "AccountNo");
					details.BalanceDue = getValue(e, "BalanceDue");
					details.BillToAddress1 = getValue(e, "BillToAddress1");
					details.BillToAddress2 = getValue(e, "BillToAddress2");
					details.BillToCity = getValue(e, "BillToCity");
					details.BillToPostcode = getValue(e, "BillToPostcode");
					details.CustomerReference = getValue(e, "CustomerReference");
					details.DueDate = getValue(e, "DueDate");
					details.InvoiceTo = getValue(e, "InvoiceTo");
					details.PurchaseOrderNo = getValue(e, "PurchaseOrderNo");
					details.ReworkOrderNo = getValue(e, "ReworkOrderNo");
					details.ShipmentMethod = getValue(e, "ShipmentMethod");
					details.ShipToAddress1 = getValue(e, "ShipToAddress1");
					details.ShipToAddress2 = getValue(e, "ShipToAddress2");
					details.ShipToCity = getValue(e, "ShipToCity");
					details.ShipToName = getValue(e, "ShipToName");
					details.ShipToPostcode = getValue(e, "ShipToPostcode");
					
					details.TotalExVat = getValue(e, "TotalExVat");
					details.TotalIncVAT = getValue(e, "TotalIncVAT");
					
					details.OrderNumber = getValue(e, "OrderNumber");
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
