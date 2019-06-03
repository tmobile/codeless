/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.ct.codeless.service.model.soap;

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
import com.tmobile.ct.codeless.service.test.build.ServiceTestStep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ServiceCache.
 *
 * @author Fikreselam Elala
 */
public class SoapRequestCache {

	private static Logger log = LoggerFactory.getLogger(SoapRequestCache.class);

	/* The cache */
	private static ConcurrentHashMap<String,ConcurrentHashMap<String,HttpRequest<String>>> cache = new ConcurrentHashMap<>();

	public static HttpRequest getRequest(ServiceTestStep testRow, Test test) throws Exception {
		if(cache.containsKey(testRow.service)) {
			String[] operations = testRow.operation.split("/");
			String soapAction = operations[1];

			if(cache.get(testRow.service).containsKey(soapAction)) {
				return cache.get(testRow.service).get(soapAction);
			}
		}

		return buildRequest(testRow,test);
	}

	public static HttpRequest buildRequest(ServiceTestStep testRow, Test test) throws Exception {

		HttpRequest<String> request;

		String[] operations = testRow.operation.split("/");
		String soapAction = operations[1];
		String hostKey = StringUtils.substringBetween(operations[2], "{{", "}}");
		String soapBody = "";

		if (StringUtils.isEmpty(soapAction) || StringUtils.isEmpty(hostKey)) {
			log.error("Host or service request action is empty. CHeck Target column value for you test step");
			return null;
		}

		TestDataSource data = (TestDataSource) test.getTestData().asMap().get(hostKey);
		String host = (data != null) ? (String) data.fullfill() : null;

		if (StringUtils.isEmpty(host)) {
			log.error("Please provide host in you test data sheet for host key " + hostKey);
			return null;
		}

		// check for request body presence
		String requestBody = testRow.testData.get(0) + ".txt";
		if (StringUtils.isEmpty(requestBody)) {
			log.error("Please provide request body for you service call step in Overrides column");
			return null;
		}

		request = new HttpRequestImpl<>();
		Headers headers = new Headers();
		Header header = new Header("Content-Type", "text/xml");
		Header header2 = new Header("SOAPAction", "/" + soapAction);
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
