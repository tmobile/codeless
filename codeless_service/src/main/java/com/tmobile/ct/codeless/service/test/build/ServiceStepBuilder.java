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
package com.tmobile.ct.codeless.service.test.build;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tmobile.ct.codeless.functions.CheckFunction;
import com.tmobile.ct.codeless.service.accessor.request.*;
import com.tmobile.ct.codeless.testdata.GetTestData;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.ct.codeless.assertion.AssertJAssertionBuilder;
import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.core.datastructure.SuiteHeaders;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpRequestImpl;
import com.tmobile.ct.codeless.service.accessor.response.BodyStringAccessor;
import com.tmobile.ct.codeless.service.accessor.response.HeaderAccessor;
import com.tmobile.ct.codeless.service.accessor.response.JsonPathAccessor;
import com.tmobile.ct.codeless.service.accessor.response.ResponseTimeAssertion;
import com.tmobile.ct.codeless.service.accessor.response.StaticAccessor;
import com.tmobile.ct.codeless.service.accessor.response.StatusCodeAssertion;
import com.tmobile.ct.codeless.service.accessor.response.XmlPathAccessor;
import com.tmobile.ct.codeless.service.assertion.BaseServiceCallAssertion;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.service.httpclient.Cookie;
import com.tmobile.ct.codeless.service.httpclient.Form;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.httpclient.HttpProtocal;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.service.httpclient.PathParam;
import com.tmobile.ct.codeless.service.httpclient.QueryParam;
import com.tmobile.ct.codeless.service.model.EndPoint;
import com.tmobile.ct.codeless.service.model.Operation;
import com.tmobile.ct.codeless.service.model.Service;
import com.tmobile.ct.codeless.service.model.cache.ServiceCache;
import com.tmobile.ct.codeless.service.model.soap.SoapRequestCache;
import com.tmobile.ct.codeless.service.reference.CallRefByTest;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;
import com.tmobile.ct.codeless.service.restassured.RestAssuredHttpClient;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.testdata.RuntimeTestDataSource;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;
import com.tmobile.ct.codeless.testdata.TestDataHelper;

/**
 * The Class ExcelServiceCallBuilder.
 *
 * @author Rob Graff
 */
public class ServiceStepBuilder {

	/** The mapper. */
	private ObjectMapper mapper = new ObjectMapper();

	/** The test row. */
	private ServiceTestStep testRow;

	/** The data. */
	TestData data = new BasicTestData();

	/** The call. */
	Call call;

	/** The request. */
	HttpRequest<String> request;

	/** Request modifers holder befor building service object. */
	List<RequestModifier> modifers;

	/** The assertions. */
	private List<Assertion> assertions = new ArrayList<>();

	/** The test. */
	private Test test;

	/** The Constant SWAGGER_YAML. */
	private static final String SWAGGER_YAML = "swagger.yaml";

	/** The Constant SOAP_WSDL. */
	private static final String SOAP_WSDL = "wsdlFile.wsdl";

	/** The Constant POSTMAN_COLLECTION_JSON. */
	private static final String POSTMAN_COLLECTION_JSON = "postman_collection.json";

	private ArrayList<String> keys = new ArrayList();
	private ArrayList<String> values = new ArrayList();
	private boolean isPostman = false;

	public void buildServiceStep(String header, String value, ServiceCallInput input, Test test) {
		switch (header.toUpperCase()) {
		case "TARGET":
			String[] parts = value.split("\\.");
			String basePath = getModelDir() + File.separator + parts[0] + File.separator;
			if (ClassPathUtil.exists(basePath+POSTMAN_COLLECTION_JSON)){
				isPostman = true;
			}

			input.add(SuiteHeaders.SERVICE.name(), new MultiValue<>(SuiteHeaders.SERVICE.name(), parts[0]));
			input.add(SuiteHeaders.OPERATION.name(),
					new MultiValue<>(SuiteHeaders.OPERATION.name(), buildOperationString(parts)));
			break;
		case "INPUT":
			input.add(SuiteHeaders.METHOD.name(), new MultiValue<>(SuiteHeaders.METHOD.name(), value));
			break;
		case "STEP":
			input.add(SuiteHeaders.TESTNAME.name(),
					new MultiValue<>(SuiteHeaders.TESTNAME.name(), value));
			break;
		case "DESCRIPTION":
			input.add(SuiteHeaders.DESCRIPTION.name(),
					new MultiValue<>(SuiteHeaders.DESCRIPTION.name(), value));
			break;
		case "ACTION":
			// do nothing
			break;
		default:
			if (StringUtils.isNotBlank(value)) {
				value = parseExport(value, test, input);
				input.add(SuiteHeaders.TESTDATA.name(),
						new MultiValue<>(SuiteHeaders.TESTDATA.name(), value));
			}
		}
	}

	private String buildOperationString(String[] parts) {
		String operation = isPostman ? "" : "/";
		for (int i = 1; i < parts.length; i++) {
			operation += parts[i] + (isPostman ? "." : "/");
		}

		return operation.substring(0, operation.length() - 1);
	}

	/**
	 * Builds the.
	 *
	 * @param test  the test
	 * @param input the input
	 * @return the call
	 */
	public Call build(Test test, ServiceCallInput input) {
		this.test = test;
		testRow = new ServiceTestStep();
		input.stream().forEach(item -> {
			SuiteHeaders header = SuiteHeaders.parse(item.getKey());
			for (String value : item.getValue().getValues()) {
				switch (header) {
				case TESTNAME:
					testRow.testName = value;
					break;
				case SERVICE:
					testRow.service = value;
					break;
				case METHOD:
					testRow.method = value;
					break;
				case OPERATION:
					testRow.operation = value;
					break;
				case BODYTEMPLATE:
					testRow.bodyTemplate = value;
					break;
				case EXPECTEDSTATUS:
					testRow.expectedStatus = value;
					break;
				case DESCRIPTION:
					testRow.description = value;
					break;
				case TESTDATA:
					testRow.testData.add(value);
					break;
				default:
					testRow.testData.add(value);
					break;
				}
			}
		});

		if (testRow.service == null) {
			return null;
		}
		Operation operation = null;
		request = null;

		if (ClassPathUtil.exists(
				CodelessConfiguration.getModelDir() + File.separator + testRow.service + File.separator + SOAP_WSDL)) {

			try {
				request = SoapRequestCache.getRequest(testRow, test);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		    Service service = ServiceCache.getService(testRow.service);
		    if (isPostman) {
				operation = service.getOperation2(testRow.operation);
			} else {
				operation = service.getOperation(HttpMethod.valueOf(testRow.method), testRow.operation);
			}

			if (operation == null) {
				System.err.println("NO OPERATION FOUND FOR INPUT[" + testRow.operation + "]");
				return null;
			}

			try {
				request = mapper.readValue(mapper.writeValueAsBytes(operation.getRequest()), HttpRequestImpl.class);
				if (modifers != null) {
					request.setRequestModifiers(modifers);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotBlank(testRow.bodyTemplate)) {
			setBodyFromTempate(testRow.bodyTemplate);
		}

		call = new Call(new RestAssuredHttpClient(), request, 0);

		testRow.testData.forEach(this::parseTestData);

		if (request.getBody() != null && request.getBody().getBody() != null
				&& request.getBody().getBody().indexOf("{{") > 0 && request.getBody().getBody().indexOf("}}") > 0) {
			setBodyFromEnv(operation);
		}
		setDefaultEndpoint();

		call.setOperation(operation);
		call.setName(testRow.testName);
		call.setDescription(
				StringUtils.isNotBlank(
						testRow.description)
						? testRow.description
						: testRow.testName + "-" + operation.getName());
		call.setAssertions(assertions);
		return call;
	}

	/**
	 * Sets endpoint from default tab of excel sheet
	 */
	private void setDefaultEndpoint() {
		TestData defaultTestData = test.getTestData();
		Host host = new Host();
		boolean isHostInExcel = false;
		boolean isProtocolInExcel = false;
		boolean isPortInExcel = false;
		boolean isPathInExcel = false;

		if (defaultTestData != null) {
			for (String excelData : testRow.testData) {
				String[] parts = excelData.split("::");

				if (parts[0].equalsIgnoreCase("ENDPOINT")) {
					for (String part : parts) {
						switch (part) {
						case "host":
							isHostInExcel = true;
							break;
						case "protocol":
							isProtocolInExcel = true;
							break;
						case "port":
							isPortInExcel = true;
							break;
						case "path":
							isPathInExcel = true;
							break;
						}
					}
				}
			}

			if (defaultTestData.getSourcedValue("host") != null && !isHostInExcel) {
				host.setValue((String) defaultTestData.getSourcedValue("host").getValue().getValue().fullfill());
				request.setHost(host);
			}

			if (defaultTestData.getSourcedValue("protocol") != null && !isProtocolInExcel) {
				String protocol = (String) defaultTestData.getSourcedValue("protocol").getValue().getValue().fullfill();
				if (protocol.equalsIgnoreCase(HttpProtocal.HTTP.toString())) {
					request.setProtocal(HttpProtocal.HTTP);
				} else {
					request.setProtocal(HttpProtocal.HTTPS);
				}
			}

			if (defaultTestData.getSourcedValue("port") != null && !isPortInExcel) {
				request.setPort(Integer
						.valueOf((String) defaultTestData.getSourcedValue("port").getValue().getValue().fullfill()));
			}

			if (defaultTestData.getSourcedValue("path") != null && !isPathInExcel) {
				OperationPath operationPath = new OperationPath();
				TestDataSource path = defaultTestData.getSourcedValue("path").getValue().getValue();
				operationPath.setValue((String) path.fullfill());
				request.setOperationPath(operationPath);
			}
		}
	}

	/**
	 * Parses the endpoint
	 *
	 * @param parts     the parts
	 * @param excelData the excel data
	 */
	private void parseEndpoint(String[] parts, String excelData) {
		EndPoint endpointFromExcel = new EndPoint();

		int counter = 0;
		for (String endPointPart : parts) {
			switch (endPointPart) {
			case "protocol":
				endpointFromExcel.setProtocall(parts[counter + 1]);
				break;
			case "host":
				endpointFromExcel.setHost(parts[counter + 1]);
				break;
			case "port":
				endpointFromExcel.setPort(parts[counter + 1]);
				break;
			case "path":
				endpointFromExcel.setPath(parts[counter + 1]);
				break;
			}
			counter++;
		}

		String endpoint = "";
		String endpointHost = "";

		if (endpointFromExcel.getProtocall() != null) {
			endpointHost = endpointFromExcel.getProtocall();
		}

		Host host = new Host();
		endpointHost = endpointHost + "://";
		if ((endpointFromExcel.getHost() != null)) {
			endpointHost = endpointHost + endpointFromExcel.getHost();
			host.setValue(endpointFromExcel.getHost());
		}

		if (endpointFromExcel.getPort() != null) {
			endpoint = endpointHost + ":" + endpointFromExcel.getPort();
			endpointHost = endpoint;
		} else {
			endpoint = endpointHost;
		}

		String basePath = getModelDir() + File.separator + testRow.service + File.separator;
		if (ClassPathUtil.exists(basePath + SWAGGER_YAML)) {
			request.getHost().setValue(endpointHost);
		} else {
			request.setHost(host);
		}

		OperationPath operationPath = new OperationPath();
		String path = "";
		if (endpointFromExcel.getPath() != null) {
			path = endpointFromExcel.getPath();
			operationPath.setValue(path);
			request.setOperationPath(operationPath);
		}

		endpoint = endpoint + "/" + path;

		HttpProtocal protocall = null;
		if (endpointFromExcel.getProtocall() != null
				&& endpointFromExcel.getProtocall().equalsIgnoreCase(HttpProtocal.HTTPS.toString())) {
			protocall = HttpProtocal.HTTPS;
		} else {
			protocall = HttpProtocal.HTTP;
		}
		request.setProtocal(protocall);

		if (endpointFromExcel.getPort() != null) {
			request.setPort(Integer.valueOf(endpointFromExcel.getPort()));
		}

		if (request.getEndpoint() != null) {
			request.getEndpoint().setValue(endpoint);
		}
	}

	/**
	 * Gets the model dir.
	 *
	 * @return the model dir
	 */
	private static String getModelDir() {
		String modelDir = CodelessConfiguration.getModelDir();

		return modelDir;
	}

	private String findBodyTemplateinTD() {
		for (String testData : testRow.testData) {
			if (testData.toUpperCase().startsWith("BODYTEMPLATE")) {
				String[] tempVals = testData.split("::");
				String requestBody = "";

				for (int counter = 1; counter < tempVals.length; counter += 2) {
					requestBody = requestBody + tempVals[counter] + ":" + tempVals[counter + 1] + ",";
				}

				if (requestBody.endsWith(",")) {
					requestBody = requestBody.substring(0, requestBody.length() - 1);
				}

				return requestBody;
			}
		}
		return null;
	}

	/**
	 * Sets Request body from testData in excelsheet
	 *
	 * @param operation
	 */
	private void setBodyFromEnv(Operation operation) {
		String postmanBody = request.getBody().getBody().toString();
		String bodyTemplateFromTD = findBodyTemplateinTD();
		ObjectMapper mapper = new ObjectMapper();

		if (bodyTemplateFromTD != null) {
			try {
				Map<String, Object> postmanMap = mapper.readValue(postmanBody, Map.class);
				String[] jsonArr = bodyTemplateFromTD.split(",");
				String formattedBody = "{";

				for (String item : jsonArr) {
					for (Map.Entry<String, Object> entry : postmanMap.entrySet()) {
						String key = entry.getKey();
						String value = entry.getValue().toString();
						String tempVal = value.substring(1, value.length() - 1);
						String[] jsonObj = item.split(":");
						boolean notFinalValue = false;
						tempVal = tempVal.substring(1, tempVal.length() - 1);

						if (value.indexOf("{{") >= 0 && value.indexOf("}}") > 0) {
							if (tempVal.equalsIgnoreCase(jsonObj[0])) {
								value = jsonObj[1];
							} else {
								notFinalValue = true;
							}
						}

						if (formattedBody.indexOf(key) < 0 && !notFinalValue) {
							formattedBody = formattedBody + '"' + key + '"' + ":" + '"' + value + '"' + ",";
						}
					}
				}

				if (formattedBody.endsWith(",")) {
					formattedBody = formattedBody.substring(0, formattedBody.length() - 1);
				}
				formattedBody = formattedBody + "}";

				Body<String> newBody = new Body<String>();
				newBody.setBody(formattedBody);
				request.setBody(newBody);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the body from tempate.
	 *
	 * @param bodyTemplate the new body from tempate
	 */
	private void setBodyFromTempate(String bodyTemplate) {
		Optional.ofNullable(parseRequestBody(bodyTemplate)).map(x -> new Body<String>(x, String.class))
				.ifPresent(request::setBody);
	}

	/**
	 * Parses the request body.
	 *
	 * @param bodyTemplate the body template
	 * @return the string
	 */
	private String parseRequestBody(String bodyTemplate) {
		File file = Paths.get(ClassPathUtil.getAbsolutePath(bodyTemplate)).toFile();
		try {
			return FileUtils.readFileToString(file);
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}

	/**
	 * <<<<<<< Updated upstream Parses the test data.
	 *
	 * @param excelData the excel data
	 */
	private void parseTestData(String excelData) {

		if (StringUtils.isBlank(excelData)) {
			return;
		}
		excelData = new CheckFunction().parse(excelData);
		String[] parts = excelData.trim().split("::");

		if (parts.length > 9) {
			System.err.println("ExcelParser: Test Data cell has parts > 9, " + parts);
			return;
		}
		if (parts.length < 2)
			return;

		String type = parts[0];
		String key = parts[1];
		String value = parts[2];

		if (StringUtils.isBlank(type)) {
			return;
		}

		type = type.toUpperCase().trim();

		switch (type) {
		case "QUERY":
			request.getQueryParams().put(key, new QueryParam(key, value));
			break;
		case "HEADER":
			request.getHeaders().put(key, new Header(key, value));
			break;
		case "PATH":
			request.getPathParams().put(key, new PathParam(key, value));
			break;
		case "FORM":
			request.getForms().put(key, new Form(key, value));
			break;
		case "COOKIE":
			request.getCookies().put(key, new Cookie(key, value));
			break;
		case "ASSERT":
			parseAssertion(parts, excelData);
			break;
		case "BODY":
			parseBody(parts, excelData);
			break;
		case "BODYTEMPLATE":
			parseBodyTemplate(parts, excelData);
			break;
		case "BODYFIELD":
			parseBodyField(parts, excelData);
			break;
		case "ENDPOINT":
			parseEndpoint(parts, excelData);
		}
	}

	/**
	 * Parses the body template.
	 *
	 * @param parts     the parts
	 * @param excelData the excel data
	 */
	private void parseBodyTemplate(String[] parts, String excelData) {
		if (StringUtils.isNotBlank(parts[2]) && !parts[2].startsWith("$REF~")) {
			Accessor accessor = new StaticAccessor(parts[2]);
			TestDataSource testdata = new StaticTestDataSource(parts[1], parts[2]);
			RequestModifier modifier = new BodyTemplateModifier(parts[1], testdata);
			if (modifers == null) {
				modifers = new ArrayList<>();
				modifers.add(modifier);
			} else {
				modifers.add(modifier);
			}
		}
	}

	private void parseBodyField(String[] parts, String excelData){
		String body = request.getBody().asString();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> postmanMap;
		String resultbody = "";
		try {
			postmanMap = mapper.readValue(body, Map.class);
			if (postmanMap == null)
				postmanMap = new HashMap<>();
			postmanMap.put(parts[1],parts[2]);
			resultbody = new JSONObject(postmanMap).toJSONString();
			Body newbody = new Body();
			newbody.setBody(resultbody);
			request.setBody(newbody);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Parses the body.
	 *
	 * @param parts     the parts
	 * @param excelData the excel data
	 */
	private void parseBody(String[] parts, String excelData) {

		if (StringUtils.isBlank(parts[1])) {
			return;
		}

		switch (parts[1].trim().toUpperCase()) {
		case "FILE":
			setBodyFromTempate(parts[2]);
			break;
		case "STRING":
			Optional.ofNullable(parts[2]).map(x -> new Body<String>(x, String.class)).ifPresent(request::setBody);
			break;
		}
	}

	/**
	 * Parses the assertion.
	 *
	 * @param parts     the parts
	 * @param excelData the excel data
	 */
	private void parseAssertion(String[] parts, String excelData) {

		// cant have .toUpperCase() applied to method name, breaks reflection call later
		// on
		String[] originalParts = excelData.trim().split("::");

		String type = parts[1]; // statusCode
		String method = originalParts[2]; // isEqualTo
		String expected = originalParts[3]; // 200
		String key = "";
		if (parts.length == 5) {
			key = originalParts[2];
			method = originalParts[3];
			expected = originalParts[4];
		}
		Class<?> typeClass = String.class;
		if (expected != null) {
			if (expected.contains("(!!num)")) {
				expected = expected.replace("(!!num)", "").trim();
				typeClass = Long.class;
			} else if (expected.contains("(!!boolean)")) {
				expected = expected.replace("(!!boolean)", "").trim();
				typeClass = Boolean.class;
			}
		}

		switch (type) {
		case "RESPONSETIME":
			assertions.add(new BaseServiceCallAssertion<>(new ResponseTimeAssertion(), Long.valueOf(expected), method,
					new AssertJAssertionBuilder(), Long.class, call));
			break;
		case "RESPONSECODE":
			assertions.add(new BaseServiceCallAssertion<>(new StatusCodeAssertion(), Integer.valueOf(expected), method,
					new AssertJAssertionBuilder(), Integer.class, call));
			break;
		case "BODYSTRING":
			assertions.add(new BaseServiceCallAssertion<String>(new BodyStringAccessor(), expected, method,
					new AssertJAssertionBuilder(), String.class, call));
			break;
		case "HEADER":
			assertions.add(new BaseServiceCallAssertion<String>(new HeaderAccessor(key), expected, method,
					new AssertJAssertionBuilder(), String.class, call));
			break;
		case "JSONPATH":
			assertions.add(buildJsonPath(key, expected, method, typeClass));
			break;
		case "XMLPATH":
			assertions.add(buildXmlPath(key, expected, method, typeClass));
			break;
		}

	}

	/**
	 * Builds the json path.
	 *
	 * @param key       the key
	 * @param expected  the expected
	 * @param method    the method
	 * @param typeClass the type class
	 * @return the base service call assertion
	 */
	private BaseServiceCallAssertion buildJsonPath(String key, String expected, String method, Class typeClass) {
		if (typeClass == Long.class) {
			return new BaseServiceCallAssertion<Long>(new JsonPathAccessor<Long>(key, Long.class),
					Long.valueOf(expected), method, new AssertJAssertionBuilder(), Long.class, call);
		} else if (typeClass == Boolean.class) {
			return new BaseServiceCallAssertion<Boolean>(new JsonPathAccessor<Boolean>(key, Boolean.class),
					Boolean.parseBoolean(expected), method, new AssertJAssertionBuilder(), Boolean.class, call);
		} else {
			return new BaseServiceCallAssertion<String>(new JsonPathAccessor<String>(key, String.class), expected,
					method, new AssertJAssertionBuilder(), String.class, call);
		}
	}

	/**
	 * Builds the xml path.
	 *
	 * @param key       the key
	 * @param expected  the expected
	 * @param method    the method
	 * @param typeClass the type class
	 * @return the base service call assertion
	 */
	private BaseServiceCallAssertion buildXmlPath(String key, String expected, String method, Class typeClass) {
		if (typeClass == Long.class) {
			return new BaseServiceCallAssertion<Long>(new XmlPathAccessor<Long>(key, Long.class),
					Long.valueOf(expected), method, new AssertJAssertionBuilder(), Long.class, call);
		} else if (typeClass == Boolean.class) {
			return new BaseServiceCallAssertion<Boolean>(new XmlPathAccessor<Boolean>(key, Boolean.class),
					Boolean.parseBoolean(expected), method, new AssertJAssertionBuilder(), Boolean.class, call);
		} else {
			return new BaseServiceCallAssertion<String>(new XmlPathAccessor<String>(key, String.class), expected,
					method, new AssertJAssertionBuilder(), String.class, call);
		}
	}

	public String parseExport(String cellValue, Test test, ServiceCallInput input) {
		String[] values = cellValue.split("::");
        if (cellValue.contains("export")) {
            List<String> stepName = input.get(SuiteHeaders.TESTNAME.name()).getValues();
            if (values.length >= 2) {

            	ServiceCallReference callRef;
        		callRef = new CallRefByTest(test, stepName.get(0));

        		String key = values[1];
        		String accessType = values[2];
        		String accessorValue = values[3];

        		Accessor accessor = null;
        		switch(accessType.trim().toUpperCase()){
        		case "HEADER":
        			accessor = new HeaderAccessor(callRef, key);
        			break;
        		case "BODYSTRING":
        			accessor = new BodyStringAccessor(callRef);
        			break;
        		case "JSONPATH":
        			accessor = new JsonPathAccessor(callRef, accessorValue, String.class);
        			break;
        		case "XMLPATH":
        			accessor = new XmlPathAccessor(callRef, key, String.class);
        			break;
        		}


        		TestDataSource testdata = new RuntimeTestDataSource(accessor);

        		if (test.getTestData() == null) {
                    test.setTestData(new BasicTestData());
                }

        		SourcedValue<TestDataSource> sourceValue = new SourcedValue<TestDataSource>();
                sourceValue.setValue(testdata);
                SourcedDataItem<String, TestDataSource> item = new SourcedDataItem<String, TestDataSource>(key, sourceValue);
                test.getTestData().put(key, item);
            }
        }else {

        	if(values.length < 3 && values[0] != null) {
        		 return values[0];
        	}

        	if (test.getTestData() == null) {
                test.setTestData(new BasicTestData());
            }

        	ArrayList<SourcedDataItem<String, TestDataSource> > sourceValue = new ArrayList<>();

        	String type = values[0];
    		String key = values[1];
    		String value = values[2];
    		String[] dataValue = StringUtils.substringsBetween(value, "{{", "}}");

    		if(dataValue != null && dataValue.length > 0) {
    			for (String source: dataValue){
					sourceValue.add(test.getTestData().getSourcedValue(source));
				}
    		}else {
    			return cellValue;
    		}
    		if(sourceValue != null && sourceValue.size() != 0) {
    			ArrayList<TestDataSource> source = new ArrayList<>();
    			for (SourcedDataItem item : sourceValue){
					if (item != null)
    					source.add((TestDataSource) item.getValue().getValue());
				}

            	RequestModifier modifier = null;

        		switch(type.trim().toUpperCase()){
        		case "QUERY":
        			modifier = new QueryParamsModifier(key,value,source);
        			break;
        		case "HEADER":
        			this.keys.add(key);
        			this.values.add(value);
        			modifier = new HeaderModifier(this.keys,this.values, source);
        			break;
        		case "PATH":
        			modifier = new PathModifier(key,value, source);
        			break;
        		case "FORM":
        			modifier = new FormModifier(key,value, source);
        			break;
        		case "COOKIE":
        			modifier = new CookieModifier(key,value, source);
        			break;
        		case "BODY":
        			modifier = new BodyModifier(value, source);
        			break;
        		case "BODYTEMPLATE":
        			modifier = new BodyTemplateModifier(key, source.get(0));
        			break;
				case "BODYFIELD":
					modifier = new BodyFieldModifier(key,value,source);
        		}

        		if (request == null && modifier != null) {
        			modifers = new ArrayList<>();
        			modifers.add(modifier);
        		}else if (request !=null ) {
        			request.getRequestModifiers().add(modifier);
        		}
    		}
        }
		ArrayList<SourcedDataItem<String, TestDataSource> > sourceValue = new ArrayList<SourcedDataItem<String,TestDataSource>>();
		String[] dataValue = StringUtils.substringsBetween(cellValue, "{{", "}}");
		if(dataValue != null && dataValue.length > 0) {
            String tcds_value = "";
		    if (test.getTcdsData()) {
		        for (int i =0;i<dataValue.length;i++) {
                    TestDataSource testSource = test.getTestData().get(test.getName());
                    tcds_value = TestDataHelper.fullfill(dataValue[i], testSource);
                    if (StringUtils.isNotBlank(tcds_value)) {
                        String replace = "{{" + dataValue[i] + "}}";
                        cellValue = cellValue.replace(replace, tcds_value);
                    }
                }
            }
		    dataValue = StringUtils.substringsBetween(cellValue,"{{","}}"); //after replacing from testdata json
			if (dataValue !=null && dataValue.length > 0) {
				for (int i = 0; i < dataValue.length; i++) {
					if (!(test.getTestData().getSourcedValue(dataValue[i]).getValue().getValue() instanceof RuntimeTestDataSource)) {
						sourceValue.add(test.getTestData().getSourcedValue(dataValue[i]));
					}
				}
			}
		}else {
			return cellValue;
		}
		if(sourceValue != null && sourceValue.size() != 0) {
			ArrayList<TestDataSource> source = new ArrayList<>();

			for(int i=0;i<sourceValue.size();i++){
				if (!(sourceValue.get(i).getValue().getValue() instanceof RuntimeTestDataSource)) {
					String dataVal = (String) sourceValue.get(i).getValue().getValue().fullfill();
					if (dataVal != null && !StringUtils.isBlank(dataVal))
						source.add(sourceValue.get(i).getValue().getValue());
				}
			}

			if (source.size() > 0) {
				GetTestData getTestData = new GetTestData();
				String newVal = getTestData.replaceValueWithTestData(cellValue, source);
				if (StringUtils.isNotBlank(newVal))
					cellValue = newVal;
			}
		}

        return cellValue;

    }

}
