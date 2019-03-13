package com.tmobile.ct.codeless.service.model.wsdl;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.util.ResourceLocator;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpRequestImpl;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.service.httpclient.Headers;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.test.excel.ExcelTestRow;

/**
 * The Class ServiceCache.
 *
 * @author Fikreselam Elala
 */
public class soapRequestCache {

	/* The cache */
	private static ConcurrentHashMap<String,ConcurrentHashMap<String,HttpRequest<String>>> cache = new ConcurrentHashMap<>();

	public static HttpRequest getRequest(ExcelTestRow testRow, Test test) throws Exception {
		if(cache.containsKey(testRow.service)) {
			String[] operations = testRow.operation.split("/");
			String soapAction = operations[1];

			if(cache.get(testRow.service).containsKey(soapAction)) {
				return cache.get(testRow.service).get(soapAction);
			}
		}
		return buildRequest(testRow,test);
	}

	public static HttpRequest buildRequest(ExcelTestRow testRow, Test test) throws Exception {

		HttpRequest<String> request;

		String[] operations = testRow.operation.split("/");
		String soapAction = operations[1];
		String hostKey = StringUtils.substringBetween(operations[2], "{{", "}}");
		String soapBody = "";

		if (StringUtils.isEmpty(soapAction) || StringUtils.isEmpty(hostKey)) {
			System.err.println("Host or service request action is empty. CHeck Target column value for you test step");
			return null;
		}

		TestDataSource data = test.getTestData().asMap().get(hostKey);
		String host = data.fullfill();

		if (StringUtils.isEmpty(host)) {
			System.err.println("Please provide host in you test data sheet for host key " + hostKey);
			return null;
		}

		// check for request body presence
		String requestBody = testRow.testData.get(0) + ".txt";
		if (StringUtils.isEmpty(requestBody)) {
			System.err.println("Please provide request body for you service call step in Overrides column");
			return null;
		}

		request = new HttpRequestImpl<>();
		Headers headers = new Headers();
		Header header = new Header("Content-Type", "text/xml");
		Header header2 = new Header("SOAPAction", "\"" + soapAction + "\"");
		headers.put("Content-Type", header);
		headers.put("SOAPAction", header2);
		request.setHeaders(headers);

		// String name = "activateRequest.txt";
		String bathPath = CodelessConfiguration.getModelDir() + File.separator + testRow.service + File.separator
				+ requestBody;
		String path = ClassPathUtil.path(bathPath);
		String requestFile = null;
		try {
			requestFile = ResourceLocator.getRequestFromFile(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		soapBody = requestFile;
		Body<String> newBody = new Body<String>();
		newBody.setBody(soapBody);
		request.setBody(newBody);
		request.setHost(new Host(host));
		request.setHttpMethod(HttpMethod.POST);

		ConcurrentHashMap<String, HttpRequest<String>> requestMap = new ConcurrentHashMap<String,HttpRequest<String>>();
		requestMap.put(soapAction, request);
		cache.put(testRow.service,requestMap);

		return request;
	}
}
