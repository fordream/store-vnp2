package org.com.vn.loxleycolour.soap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.com.cnc.common.android.Common;
import org.com.vn.loxleycolour.database.DataStore;
import org.com.vn.loxleycolour.items.Login;
import org.com.vn.loxleycolour.items.order1.GetMyOrderDetails;
import org.com.vn.loxleycolour.items.order1.GetMyOrderItems;
import org.com.vn.loxleycolour.items.order1.OrderProgress;
import org.com.vn.loxleycolour.items.order2.GetMyCurrentInvoices;
import org.com.vn.loxleycolour.items.order2.GetMyInvoiceDetails;
import org.com.vn.loxleycolour.items.order2.GetMyInvoiceItems;

import reset.SendUserAndPass;
import android.util.Log;

public class SoapCommon {
	public static final String TRACK_URL = "http://track2.royalmail.com/portal/rm/track?trackNumber=";
	public static final int TYPE_CATEGORY = 1;
	public static final int TYPE_PRODUCT = 2;
	public static final int TYPE_OFFERS = 3;
	public static final int TYPE_STORE = 4;
	public static final int TYPE_ADDRESS = 5;
	public static final int TYPE_LOCALITY = 6;
	public static final String BLANK = "";

	public static final String URL_SEARCH = "http://api.loxleycolour.com/loxleyservice.asmx";
	public static final String NAMESPACE_SEARCH = "http://LoxleyService";
	public static final String METHOD_NAME_SEARCH = "LoginWebUser";
	public static final String SOAP_ACTION_SEARCH = "" + NAMESPACE_SEARCH + "/"
			+ METHOD_NAME_SEARCH;

	// http://LoxleyService/LoginWebUser

	public static Login login(String user, String pass) {
		Login login = new Login();
		String text = loginToString(user, pass);
		if (!Common.isNullOrBlank(text)) {
			try {
				int index1 = text.indexOf("<LoginWebUserResult>")
						+ "<LoginWebUserResult>".length();
				int index2 = text.indexOf("</LoginWebUserResult>");

				login.setData(text.substring(index1, index2));
			} catch (Exception e) {
			}
		}

		return login;
	}

	private static String loginToString(String user, String pass) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String responseString = null;
		try {

			HttpPost httppost = new HttpPost(
					"http://api.loxleycolour.com/loxleyservice.asmx");
			httppost.setHeader("SOAPAction",
					"http://LoxleyService/LoginWebUser");
			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

			StringBuilder sBEnvelope = new StringBuilder();
			sBEnvelope
					.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:lox=\"http://LoxleyService/\">");
			sBEnvelope.append("<soapenv:Header/>");
			sBEnvelope.append("<soapenv:Body>");
			sBEnvelope.append("<lox:LoginWebUser>");
			sBEnvelope.append("<lox:UserName>").append(user)
					.append("</lox:UserName>");
			sBEnvelope.append("<lox:Password>").append(pass)
					.append("</lox:Password>");
			sBEnvelope.append("</lox:LoginWebUser>");
			sBEnvelope.append("</soapenv:Body>");
			sBEnvelope.append("</soapenv:Envelope>");

			HttpEntity entity = new StringEntity(sBEnvelope.toString());
			httppost.setEntity(entity);
			ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
			responseString = httpClient.execute(httppost, strResponseHandler);
			httpClient.getConnectionManager().shutdown();

			return responseString;

		} catch (Exception objException) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	public static OrderProgress GetMyCurrentOrders(String search) {
		String data = GetMyCurrentOrdersString(search);
		OrderProgress orderProgress = new OrderProgress();
		orderProgress.pare(data);
		return orderProgress;
	}

	private static String GetMyCurrentOrdersString(String search) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String responseString = null;
		try {
			String id = DataStore.getInstance().getId();

			Log.i("sssssssssssssssss", "iddddddddddddd" + id);
			HttpPost httppost = new HttpPost(
					"http://api.loxleycolour.com/loxleyservice.asmx");
			httppost.setHeader("SOAPAction",
					"http://LoxleyService/GetMyCurrentOrders");

			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

			StringBuilder sBEnvelope = new StringBuilder();

			sBEnvelope
					.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:lox=\"http://LoxleyService/\">");
			sBEnvelope.append("<soapenv:Header/>");
			sBEnvelope.append("<soapenv:Body>");
			sBEnvelope.append("<lox:GetMyCurrentOrders>");
			sBEnvelope.append("<lox:AccountID>").append(id)
					.append("</lox:AccountID>");
			sBEnvelope.append("<lox:PageID>0</lox:PageID>");
			sBEnvelope.append("<!--Optional:-->");
			sBEnvelope.append("<lox:SearchString>").append(search)
					.append("</lox:SearchString>");
			sBEnvelope
					.append("<lox:StartDate>1900-01-01T00:00:00.0000</lox:StartDate>");
			sBEnvelope
					.append("<lox:EndDate>1900-01-01T00:00:00.0000</lox:EndDate>");
			sBEnvelope.append("</lox:GetMyCurrentOrders>");
			sBEnvelope.append(" </soapenv:Body>");
			sBEnvelope.append("</soapenv:Envelope>");

			HttpEntity entity = new StringEntity(sBEnvelope.toString());
			httppost.setEntity(entity);
			ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
			responseString = httpClient.execute(httppost, strResponseHandler);
			httpClient.getConnectionManager().shutdown();

			return responseString;

		} catch (Exception objException) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	public static GetMyOrderDetails GetMyOrderDetails(String number) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String responseString = null;
		try {

			HttpPost httppost = new HttpPost(
					"http://api.loxleycolour.com/loxleyservice.asmx");
			httppost.setHeader("SOAPAction",
					"http://LoxleyService/GetMyOrderDetails");

			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

			StringBuilder sBEnvelope = new StringBuilder();

			sBEnvelope
					.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:lox=\"http://LoxleyService/\">");
			sBEnvelope.append("<soap:Header/>");
			sBEnvelope.append("<soap:Body>");
			sBEnvelope.append("<lox:GetMyOrderDetails>");
			sBEnvelope.append("<lox:OrderNumber>").append(number)
					.append("</lox:OrderNumber>");
			sBEnvelope.append("</lox:GetMyOrderDetails>");
			sBEnvelope.append("</soap:Body>");
			sBEnvelope.append("</soap:Envelope>");

			HttpEntity entity = new StringEntity(sBEnvelope.toString());
			httppost.setEntity(entity);
			ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
			responseString = httpClient.execute(httppost, strResponseHandler);
			httpClient.getConnectionManager().shutdown();
		} catch (Exception objException) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		GetMyOrderDetails details = new GetMyOrderDetails();
		details.parse(responseString);
		return details;
	}

	public static GetMyOrderItems GetMyOrderItems(String number) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String responseString = null;
		try {

			HttpPost httppost = new HttpPost(
					"http://api.loxleycolour.com/loxleyservice.asmx");
			httppost.setHeader("SOAPAction",
					"http://LoxleyService/GetMyOrderItems");

			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

			StringBuilder sBEnvelope = new StringBuilder();

			sBEnvelope
					.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:lox=\"http://LoxleyService/\">");
			sBEnvelope.append("<soap:Header/>");
			sBEnvelope.append("<soap:Body>");
			sBEnvelope.append("<lox:GetMyOrderItems>");
			sBEnvelope.append("<lox:OrderNumber>").append(number)
					.append("</lox:OrderNumber>");
			sBEnvelope.append("</lox:GetMyOrderItems>");
			sBEnvelope.append("</soap:Body>");
			sBEnvelope.append("</soap:Envelope>");

			HttpEntity entity = new StringEntity(sBEnvelope.toString());
			httppost.setEntity(entity);
			ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
			responseString = httpClient.execute(httppost, strResponseHandler);
			httpClient.getConnectionManager().shutdown();
		} catch (Exception objException) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		GetMyOrderItems details = new GetMyOrderItems();
		details.parse(responseString);
		return details;
	}

	public static GetMyCurrentInvoices GetMyCurrentInvoices(String search) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String responseString = null;
		try {
			String id = DataStore.getInstance().getId();
			HttpPost httppost = new HttpPost(
					"http://api.loxleycolour.com/loxleyservice.asmx");
			httppost.setHeader("SOAPAction",
					"http://LoxleyService/GetMyCurrentInvoices");

			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

			StringBuilder sBEnvelope = new StringBuilder();

			sBEnvelope
					.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:lox=\"http://LoxleyService/\">");
			sBEnvelope.append("<soap:Header/>");
			sBEnvelope.append("<soap:Body>");
			sBEnvelope.append("<lox:GetMyCurrentInvoices>");
			sBEnvelope.append("<lox:AccountID>").append(id)
					.append("</lox:AccountID>");
			sBEnvelope.append("<lox:PageID>0</lox:PageID>");
			sBEnvelope.append("<lox:SearchString></lox:SearchString>");
			sBEnvelope
					.append("<lox:StartDate>1900-01-01T00:00:00.0000</lox:StartDate>");
			sBEnvelope
					.append("<lox:EndDate>1900-01-01T00:00:00.0000</lox:EndDate>");
			sBEnvelope.append("</lox:GetMyCurrentInvoices>");
			sBEnvelope.append("</soap:Body>");
			sBEnvelope.append("</soap:Envelope>");

			HttpEntity entity = new StringEntity(sBEnvelope.toString());
			httppost.setEntity(entity);
			ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
			responseString = httpClient.execute(httppost, strResponseHandler);
			httpClient.getConnectionManager().shutdown();
		} catch (Exception objException) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		GetMyCurrentInvoices details = new GetMyCurrentInvoices();
		details.parse(responseString);
		return details;
	}

	public static GetMyInvoiceDetails GetMyInvoiceDetails(String InvoiceNumber) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String responseString = null;
		try {

			HttpPost httppost = new HttpPost(
					"http://api.loxleycolour.com/loxleyservice.asmx");
			httppost.setHeader("SOAPAction",
					"http://LoxleyService/GetMyInvoiceDetails");

			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

			StringBuilder sBEnvelope = new StringBuilder();

			sBEnvelope
					.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:lox=\"http://LoxleyService/\">");
			sBEnvelope.append("<soap:Header/>");
			sBEnvelope.append("<soap:Body>");
			sBEnvelope.append("<lox:GetMyInvoiceDetails>");
			sBEnvelope.append("<lox:InvoiceNumber>").append(InvoiceNumber)
					.append("</lox:InvoiceNumber>");
			sBEnvelope.append("</lox:GetMyInvoiceDetails>");
			sBEnvelope.append("</soap:Body>");
			sBEnvelope.append("</soap:Envelope>");

			HttpEntity entity = new StringEntity(sBEnvelope.toString());
			httppost.setEntity(entity);
			ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
			responseString = httpClient.execute(httppost, strResponseHandler);
			httpClient.getConnectionManager().shutdown();
		} catch (Exception objException) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		GetMyInvoiceDetails details = new GetMyInvoiceDetails();
		details.parse(responseString);
		return details;
	}

	public static GetMyInvoiceItems GetMyInvoiceItems(String invoNumber) {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		String responseString = null;
		try {

			HttpPost httppost = new HttpPost(
					"http://api.loxleycolour.com/loxleyservice.asmx");
			httppost.setHeader("SOAPAction",
					"http://LoxleyService/GetMyInvoiceItems");

			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

			StringBuilder sBEnvelope = new StringBuilder();

			sBEnvelope
					.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:lox=\"http://LoxleyService/\">");
			sBEnvelope.append(" <soap:Header/>");
			sBEnvelope.append("  <soap:Body>");
			sBEnvelope.append("     <lox:GetMyInvoiceItems>");
			sBEnvelope.append("<lox:InvoiceNumber>").append(invoNumber)
					.append("</lox:InvoiceNumber>");
			sBEnvelope.append(" </lox:GetMyInvoiceItems>");
			sBEnvelope.append(" </soap:Body>");
			sBEnvelope.append("</soap:Envelope>");

			HttpEntity entity = new StringEntity(sBEnvelope.toString());
			httppost.setEntity(entity);
			ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
			responseString = httpClient.execute(httppost, strResponseHandler);
			httpClient.getConnectionManager().shutdown();
		} catch (Exception objException) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		GetMyInvoiceItems details = new GetMyInvoiceItems();
		details.parse(responseString);
		return details;
	}

	public static SendUserAndPass SendUserAndPass(String email) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String responseString = null;
		try {

			HttpPost httppost = new HttpPost(
					"http://api.loxleycolour.com/loxleyservice.asmx");
			httppost.setHeader("SOAPAction",
					"http://LoxleyService/SendUserAndPassword");

			httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

			StringBuilder sBEnvelope = new StringBuilder();

			sBEnvelope
					.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:lox=\"http://LoxleyService/\">");
			sBEnvelope.append("<soap:Header/>");
			sBEnvelope.append("<soap:Body>");
			sBEnvelope.append("<lox:SendUserAndPassword>");
			sBEnvelope.append("<lox:EmailAddress>").append(email)
					.append("</lox:EmailAddress>");
			sBEnvelope.append(" </lox:SendUserAndPassword>");
			sBEnvelope.append(" </soap:Body>");
			sBEnvelope.append("</soap:Envelope>");

			HttpEntity entity = new StringEntity(sBEnvelope.toString());
			httppost.setEntity(entity);
			ResponseHandler<String> strResponseHandler = new BasicResponseHandler();
			responseString = httpClient.execute(httppost, strResponseHandler);
			httpClient.getConnectionManager().shutdown();
		} catch (Exception objException) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		SendUserAndPass details = new SendUserAndPass();
		details.parse(responseString);
		return details;
	}
}
