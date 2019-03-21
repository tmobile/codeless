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
package com.tmobile.ct.codeless.service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.head;
import static com.github.tomakehurst.wiremock.client.WireMock.options;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import org.junit.Before;
import org.junit.Rule;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpRequestImpl;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;

public class BaseWiremockTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Before
	public void beforeTest() {
		stubFor(get(urlPathMatching("/do/test"))
				  .willReturn(aResponse()
				  .withStatus(200)
				  .withHeader("Content-Type", "application/json")
				  .withBody("\"This is a GET test\": \"WireMock\"")));

		stubFor(post(urlPathMatching("/do/test"))
				  .willReturn(aResponse()
				  .withStatus(200)
				  .withHeader("Content-Type", "application/json")
				  .withBody("\"This is a POST test\": \"WireMock\"")));

		stubFor(put(urlPathMatching("/do/test"))
				  .willReturn(aResponse()
				  .withStatus(200)
				  .withHeader("Content-Type", "application/json")
				  .withBody("\"This is a PUT test\": \"WireMock\"")));

		stubFor(patch(urlPathMatching("/do/test"))
				  .willReturn(aResponse()
				  .withStatus(200)
				  .withHeader("Content-Type", "application/json")
				  .withBody("\"This is a PATCH test\": \"WireMock\"")));

		stubFor(delete(urlPathMatching("/do/test"))
				  .willReturn(aResponse()
				  .withStatus(200)
				  .withHeader("Content-Type", "application/json")
				  .withBody("\"This is a DELETE test\": \"WireMock\"")));

		stubFor(options(urlPathMatching("/do/test"))
				  .willReturn(aResponse()
				  .withStatus(200)
				  .withHeader("Content-Type", "application/json")
				  .withBody("\"This is a OPTIONS test\": \"WireMock\"")));

		stubFor(head(urlPathMatching("/do/test"))
				  .willReturn(aResponse()
				  .withStatus(200)
				  .withHeader("Content-Type", "application/json")
				  .withBody("\"This is a HEAD test\": \"WireMock\"")));
	}
	
	/*
	 * Stubs out a default HTTP request to be used by all tests
	 */
	protected HttpRequest buildDefaultRequest() {
		HttpRequest req = new HttpRequestImpl();
		req.setOperationPath(new OperationPath("/test"));
		req.setServicePath(new ServicePath("/do"));
		req.setPort(8080);
		req.setHost(new Host("http://localhost"));
		
		return req;
	}
}
