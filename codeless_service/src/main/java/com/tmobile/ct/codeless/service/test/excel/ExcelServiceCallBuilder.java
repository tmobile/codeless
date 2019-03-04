package com.tmobile.ct.codeless.service.test.excel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.ct.codeless.assertion.AssertJAssertionBuilder;
import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.core.datastructure.SuiteHeaders;
import com.tmobile.ct.codeless.core.util.ResourceLocator;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpRequestImpl;
import com.tmobile.ct.codeless.service.accessor.request.BodyModifier;
import com.tmobile.ct.codeless.service.accessor.request.BodyTemplateModifier;
import com.tmobile.ct.codeless.service.accessor.request.CookieModifier;
import com.tmobile.ct.codeless.service.accessor.request.FormModifier;
import com.tmobile.ct.codeless.service.accessor.request.HeaderModifier;
import com.tmobile.ct.codeless.service.accessor.request.PathModifier;
import com.tmobile.ct.codeless.service.accessor.request.QueryParamsModifier;
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
import com.tmobile.ct.codeless.service.httpclient.Headers;
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
import com.tmobile.ct.codeless.service.reference.CallRefByTest;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;
import com.tmobile.ct.codeless.service.restassured.RestAssuredHttpClient;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.testdata.RuntimeTestDataSource;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;


/**
 * The Class ExcelServiceCallBuilder.
 *
 * @author Rob Graff
 */
public class ExcelServiceCallBuilder {

	/** The formatter. */
	private DataFormatter formatter = new DataFormatter();

	/** The mapper. */
	private ObjectMapper mapper = new ObjectMapper();

	/** The test row. */
	private ExcelTestRow testRow;

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

	/**
	 * Builds the hybrid.
	 *
	 * @param test the test
	 * @param row the row
	 * @return the step
	 */
	public Step buildHybrid(Test test, Row row) {
		ServiceCallInput input = new ServiceCallInput();

		for(Cell cell : row){
			String header = formatter.formatCellValue(cell.getSheet().getRow(0).getCell(cell.getColumnIndex())).trim().toUpperCase();
			String value = formatter.formatCellValue(cell);
			switch(header){
			case "TARGET":
				String[] parts = value.split("\\.");
				input.add(SuiteHeaders.SERVICE.name(), new MultiValue<String,String>(SuiteHeaders.SERVICE.name(), parts[0]));
				String operation = "";
				for(int i =1; i<parts.length; i++){
					operation = operation + parts[i] + "/";
				}
				operation = "/"+operation.substring(0, operation.length()-1);
				input.add(SuiteHeaders.OPERATION.name(),  new MultiValue<String,String>(SuiteHeaders.OPERATION.name(), operation));
				break;
			case "INPUT":
				input.add(SuiteHeaders.METHOD.name(),  new MultiValue<String,String>(SuiteHeaders.METHOD.name(), value));
				break;
			case "STEP":
				input.add(SuiteHeaders.TESTNAME.name(),  new MultiValue<String,String>(SuiteHeaders.TESTNAME.name(), value));
				break;
			case "ACTION":
				// do nothing
				break;
			default:
				if(StringUtils.isNotBlank(value)){
					value = parseExport(value, test, input);
					input.add(SuiteHeaders.TESTDATA.name(),  new MultiValue<String,String>(SuiteHeaders.TESTDATA.name(), value));
				}
			}

		}

		return build(test, input);
	}

	/**
	 * Builds the.
	 *
	 * @param test the test
	 * @param input the input
	 * @return the call
	 */
	public Call build(Test test, ServiceCallInput input){
		this.test = test;
		testRow = new ExcelTestRow();
		input.stream().forEach(item ->{
			SuiteHeaders header = SuiteHeaders.parse(item.getKey());
			for(String value : item.getValue().getValues()){
				switch(header){
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
				case TESTDATA:
					testRow.testData.add(value);
					break;
				default:
					testRow.testData.add(value);
					break;
				}
			}
		});

		if(testRow.service==null){
			return null;
		}

		Operation operation = null;
		request = null;

		if(!ClassPathUtil.exists(CodelessConfiguration.getModelDir() + File.separator + testRow.service + File.separator)) {

			Service service = ServiceCache.getService(testRow.service);
			operation = (service.getOperation(HttpMethod.valueOf(testRow.method), testRow.operation) == null) ? service.getOperation(HttpMethod.valueOf(testRow.method), testRow.operation.substring(1, testRow.operation.length())) :
			service.getOperation(HttpMethod.valueOf(testRow.method), testRow.operation);
			//HttpRequest request = SerializationUtils.clone((HttpRequestImpl) operation.getRequest());

			if(operation == null){
				System.err.println("NO OPERTAION FOUND FOR INPUT["+testRow.operation+"]");
				return null;
			}

			try {
				request = mapper.readValue(mapper.writeValueAsBytes(operation.getRequest()),HttpRequestImpl.class);
				if(modifers != null) {
					request.setRequestModifiers(modifers);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			String[] operations = testRow.operation.split("/");
			String soapAction = operations[1];
			String hostKey = StringUtils.substringBetween(operations[2], "{{", "}}");
			String soapBody = "";

			if(StringUtils.isEmpty(soapAction) || StringUtils.isEmpty(hostKey)) {
				System.err.println("Host or service request action is empty. CHeck Target column value for you test step");
			}

			TestDataSource data = test.getTestData().asMap().get(hostKey);
			String host = data.fullfill();

			if(StringUtils.isEmpty(host)) {
				System.err.println("Please provide host in you test data sheet for host key " + hostKey);
			}

			// check for request body presence
			String requestBody = testRow.testData.get(0) + ".txt";
			if(StringUtils.isEmpty(requestBody)) {
				System.err.println("Please provide request body for you service call step in Overrides column");
			}

			request = new HttpRequestImpl<>();
			Headers headers = new Headers();
			Header header = new Header("Content-Type", "text/xml");
			Header header2 = new Header("SOAPAction", "\"" + soapAction + "\"");
			headers.put("Content-Type", header);
			headers.put("SOAPAction", header2);
			request.setHeaders(headers);

			//String name = "activateRequest.txt";
			String bathPath = CodelessConfiguration.getModelDir() + File.separator + "dsg" + File.separator + requestBody;
			String path = path(bathPath);
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

		}


		if(StringUtils.isNotBlank(testRow.bodyTemplate)){
			setBodyFromTempate(testRow.bodyTemplate);
		}

		call = new Call(new RestAssuredHttpClient(), request, 0);

		testRow.testData.forEach(this::parseTestData);

		if(request.getBody() != null && request.getBody().getBody() != null && request.getBody().getBody().indexOf("{{") > 0 && request.getBody().getBody().indexOf("}}") > 0 ){
			setBodyFromEnv(operation);
		}
		setDefaultEndpoint();

		call.setOperation(operation);
		call.setName(testRow.testName);
		call.setAssertions(assertions);
		return call;
	}

	/**
	 * Sets endpoint from default tab of excel sheet
	 */
	private void setDefaultEndpoint(){
		TestData defaultTestData = test.getTestData();
		Host host = new Host();
		boolean isHostInExcel = false;
		boolean isProtocolInExcel = false;
		boolean isPortInExcel = false;
		boolean isPathInExcel = false;

		if(defaultTestData != null){
		for(String excelData : testRow.testData){
			String[] parts = excelData.split("::");

			if(parts[0].equalsIgnoreCase("ENDPOINT")){
				for(String part : parts){
					switch(part){
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

		if(defaultTestData.getSourcedValue("host") != null && !isHostInExcel){
			host.setValue(defaultTestData.getSourcedValue("host").getValue().getValue().fullfill());
			request.setHost(host);
		}

		if(defaultTestData.getSourcedValue("protocol") != null && !isProtocolInExcel){
			if(defaultTestData.getSourcedValue("protocol").getValue().getValue().fullfill().equalsIgnoreCase(HttpProtocal.HTTP.toString())){
				request.setProtocal(HttpProtocal.HTTP);
			}else{
				request.setProtocal(HttpProtocal.HTTPS);
			}
		}

		if(defaultTestData.getSourcedValue("port") != null && !isPortInExcel){
			request.setPort(Integer.valueOf(defaultTestData.getSourcedValue("port").getValue().getValue().fullfill()));
		}

		if(defaultTestData.getSourcedValue("path") != null && !isPathInExcel){
			OperationPath operationPath = new OperationPath();
			TestDataSource path =defaultTestData.getSourcedValue("path").getValue().getValue();
			operationPath.setValue(path.fullfill());
			request.setOperationPath(operationPath);
		}
		}
	}

	/**
	 * Parses the endpoint
	 *
	 * @param parts the parts
	 * @param excelData the excel data
	 */
	private void parseEndpoint(String[] parts, String excelData) {
		EndPoint endpointFromExcel = new EndPoint();

		int counter = 0;
		for(String endPointPart : parts ){
			switch(endPointPart){
				case "protocol":
					endpointFromExcel.setProtocall(parts[counter+1]);
					break;
				case "host":
					endpointFromExcel.setHost(parts[counter+1]);
					break;
				case "port":
					endpointFromExcel.setPort(parts[counter+1]);
					break;
				case "path":
					endpointFromExcel.setPath(parts[counter+1]);
					break;
			}
			counter++;
		}

		String endpoint = "";
		String endpointHost = "";


		if(endpointFromExcel.getProtocall() != null ){
			endpointHost = endpointFromExcel.getProtocall();
		}

		Host host = new Host();
		endpointHost = endpointHost + "://" ;
		if((endpointFromExcel.getHost() != null)){
			endpointHost = endpointHost + endpointFromExcel.getHost();
			host.setValue(endpointFromExcel.getHost());
		}

		if(endpointFromExcel.getPort() != null){
			endpoint = endpointHost + ":" + endpointFromExcel.getPort();
			endpointHost = endpoint;
		}else{
			endpoint = endpointHost;
		}

		String basePath = getModelDir() + File.separator + testRow.service + File.separator;
		if(ClassPathUtil.exists(basePath+SWAGGER_YAML)){
			request.getHost().setValue(endpointHost);
		}
		else{
			request.setHost(host);
		}

		OperationPath operationPath = new OperationPath();
		String path = "";
		if(endpointFromExcel.getPath() != null){
			path = endpointFromExcel.getPath();
			operationPath.setValue(path);
			request.setOperationPath(operationPath);
		}


		endpoint = endpoint + "/" + path;

		HttpProtocal protocall = null;
		if(endpointFromExcel.getProtocall() != null && endpointFromExcel.getProtocall().equalsIgnoreCase(HttpProtocal.HTTPS.toString())){
			protocall = HttpProtocal.HTTPS;
		}else{
			protocall = HttpProtocal.HTTP;
		}
		request.setProtocal(protocall);

		if(endpointFromExcel.getPort() != null){
			request.setPort(Integer.valueOf(endpointFromExcel.getPort()));
		}

		if(request.getEndpoint() != null){
			request.getEndpoint().setValue(endpoint);
		}
	}

	/**
	 * Gets the model dir.
	 *
	 * @return the model dir
	 */
	private static String getModelDir(){
		String modelDir = CodelessConfiguration.getModelDir();

		return modelDir;
	}


	private String findBodyTemplateinTD(){
		for(String testData : testRow.testData){
			if(testData.toUpperCase().startsWith("BODYTEMPLATE")){
				String[] tempVals = testData.split("::");
				String requestBody = "";

				for(int counter = 1; counter < tempVals.length; counter += 2){
					requestBody = requestBody + tempVals[counter] +  ":" + tempVals[counter+1] + ",";
				}

				if(requestBody.endsWith(",")){
					requestBody= requestBody.substring(0, requestBody.length()-1);
				}

				return requestBody;
			}
		}
		return null;
	}

	/**
	 * Sets Request body from testData in excelsheet
	 * @param operation
	 */
	private void setBodyFromEnv(Operation operation){
		String postmanBody = request.getBody().getBody().toString();
		String bodyTemplateFromTD = findBodyTemplateinTD();
		ObjectMapper mapper = new ObjectMapper();

		if(bodyTemplateFromTD != null){
			try {
				Map<String,Object> postmanMap = mapper.readValue(postmanBody, Map.class);
				String[] jsonArr = bodyTemplateFromTD.split(",");
				String formattedBody = "{";

				for(String item : jsonArr){
					for (Map.Entry<String, Object> entry : postmanMap.entrySet()) {
					    String key = entry.getKey();
					    String value = entry.getValue().toString();
					    String tempVal = value.substring(1, value.length()-1);
						String[] jsonObj = item.split(":");
						boolean notFinalValue = false;
						tempVal = tempVal.substring(1, tempVal.length()-1);

						if(value.indexOf("{{") >= 0 && value.indexOf("}}") > 0){
							if(tempVal.equalsIgnoreCase(jsonObj[0])){
								value = jsonObj[1];
							}else{
								notFinalValue = true;
							}
						}

						if(formattedBody.indexOf(key) < 0 && !notFinalValue){
							formattedBody = formattedBody +'"'+ key +'"' +":" + '"' + value +'"' +",";
						}
					}
				}

				if(formattedBody.endsWith(","))
				{
					formattedBody = formattedBody.substring(0,formattedBody.length() - 1);
				}
				formattedBody = formattedBody +"}";

				Body<String> newBody = new Body<String>();
				newBody.setBody(formattedBody);
				request.setBody(newBody);
			} catch (IOException  e) {
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
		Optional.ofNullable(parseRequestBody(bodyTemplate))
				.map(x -> new Body<String>(x, String.class))
				.ifPresent(request::setBody);
	}

	/**
	 * Parses the request body.
	 *
	 * @param bodyTemplate the body template
	 * @return the string
	 */
	private String parseRequestBody(String bodyTemplate){
		File file = Paths.get(ClassPathUtil.getAbsolutePath(bodyTemplate)).toFile();
		try {
			return FileUtils.readFileToString(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

//	private TestData captureTestData(String value){
//		SourcedValue<ExcelTestData, String> value = new SourcedValue<>();
//		value.setSource(ExcelTestData.class.getName());
//		value.setSourceClass(ExcelTestData.class);
//		value.setValue(formatter.formatCellValue(cell));
//
//		String key = formatter.formatCellValue(row.getCell(0));
//		SourcedDataItem<String> item = new SourcedDataItem<>(key, value);
//		data.put(key, item);
//	}

	/**
 * Parses the test data.
 *
 * @param excelData the excel data
 */
private void parseTestData(String excelData){

		if(StringUtils.isBlank(excelData)){
			return;
		}

		String[] parts = excelData.trim().split("::");

		if(parts.length > 9){
			System.err.println("ExcelParser: Test Data cell has parts > 9, "+parts);
			return;
		}
		if(parts.length < 2) return;

		String type = parts[0];
		String key = parts[1];
		String value = parts[2];

		//value = parseRefValue(excelData);

		if(StringUtils.isBlank(type)){
			return;
		}

		type = type.toUpperCase().trim();

		switch(type){
		case "QUERY":
			request.getQueryParams().put(key,new QueryParam(key, value));
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
			request.getCookies().put(key, new Cookie(key,value));
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
		case "ENDPOINT":
			parseEndpoint(parts, excelData);
		}
	}

	/**
	 * Parses the body template.
	 *
	 * @param parts the parts
	 * @param excelData the excel data
	 */
	private void parseBodyTemplate(String[] parts, String excelData) {
		if(StringUtils.isNotBlank(parts[2]) && !parts[2].startsWith("$REF~")){
			Accessor accessor = new StaticAccessor(parts[2]);
			TestDataSource testdata = new StaticTestDataSource(parts[1], parts[2]);
			RequestModifier modifier = new BodyTemplateModifier(parts[1], testdata);
			//request.getRequestModifiers().add(modifier);
			if(modifers == null) {
				modifers = new ArrayList<>();
				modifers.add(modifier);
			}else {
				modifers.add(modifier);
			}
		}
	}

	/**
	 * Parses the body.
	 *
	 * @param parts the parts
	 * @param excelData the excel data
	 */
	private void parseBody(String[] parts, String excelData) {

		if(StringUtils.isBlank(parts[1])){
			return;
		}

		switch(parts[1].trim().toUpperCase()){
		case "FILE":
			setBodyFromTempate(parts[2]);
			break;
		case "STRING":
			Optional.ofNullable(parts[2])
			.map(x -> new Body<String>(x, String.class))
			.ifPresent(request::setBody);
			break;
		}
	}

	/**
	 * Parses the assertion.
	 *
	 * @param parts the parts
	 * @param excelData the excel data
	 */
	private void parseAssertion(String[] parts, String excelData) {

		//cant have .toUpperCase() applied to method name, breaks reflection call later on
		String[] originalParts = excelData.trim().split("::");

		String type = parts[1]; //statusCode
		String method = originalParts[2]; // isEqualTo
		String expected = originalParts[3]; // 200
		String key = "";
		if(parts.length == 5){
			key = originalParts[2];
			method = originalParts[3];
			expected = originalParts[4];
		}
		Class<?> typeClass = String.class;
		if(expected != null ){
			if (expected.contains("(!!num)")){
				expected = expected.replace("(!!num)", "").trim();
				typeClass = Long.class;
			}else if(expected.contains("(!!boolean)")){
				expected = expected.replace("(!!boolean)", "").trim();
				typeClass = Boolean.class;
			}
		}

		switch (type){
		case "RESPONSETIME":
			assertions.add(new BaseServiceCallAssertion<>(
					new ResponseTimeAssertion(),Long.valueOf(expected), method,
					new AssertJAssertionBuilder(), Long.class, call));
			break;
		case "RESPONSECODE":
			assertions.add(new BaseServiceCallAssertion<>(
					new StatusCodeAssertion(),Integer.valueOf(expected), method,
					new AssertJAssertionBuilder(), Integer.class, call));
			break;
		case "BODYSTRING":
			assertions.add(new BaseServiceCallAssertion<String>(
					new BodyStringAccessor(),expected, method,
					new AssertJAssertionBuilder(), String.class, call));
			break;
		case "HEADER":
			assertions.add(new BaseServiceCallAssertion<String>(
					new HeaderAccessor(key), expected, method,
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
	 * @param key the key
	 * @param expected the expected
	 * @param method the method
	 * @param typeClass the type class
	 * @return the base service call assertion
	 */
	private BaseServiceCallAssertion buildJsonPath(String key, String expected, String method, Class typeClass){
		if(typeClass == Long.class){
				return new BaseServiceCallAssertion<Long>(
					new JsonPathAccessor<Long>(key, Long.class), Long.valueOf(expected), method,
					new AssertJAssertionBuilder(), Long.class, call);
		} else if(typeClass == Boolean.class){
				return new BaseServiceCallAssertion<Boolean>(
					new JsonPathAccessor<Boolean>(key, Boolean.class), Boolean.parseBoolean(expected), method,
					new AssertJAssertionBuilder(), Boolean.class, call);
		} else {
				return new BaseServiceCallAssertion<String>(
					new JsonPathAccessor<String>(key, String.class), expected, method,
					new AssertJAssertionBuilder(), String.class, call);
		}
	}

	/**
	 * Builds the xml path.
	 *
	 * @param key the key
	 * @param expected the expected
	 * @param method the method
	 * @param typeClass the type class
	 * @return the base service call assertion
	 */
	private BaseServiceCallAssertion buildXmlPath(String key, String expected, String method, Class typeClass){
		if(typeClass == Long.class){
				return new BaseServiceCallAssertion<Long>(
					new XmlPathAccessor<Long>(key, Long.class), Long.valueOf(expected), method,
					new AssertJAssertionBuilder(), Long.class, call);
		} else if(typeClass == Boolean.class){
				return new BaseServiceCallAssertion<Boolean>(
					new XmlPathAccessor<Boolean>(key, Boolean.class), Boolean.parseBoolean(expected), method,
					new AssertJAssertionBuilder(), Boolean.class, call);
		} else {
				return new BaseServiceCallAssertion<String>(
					new XmlPathAccessor<String>(key, String.class), expected, method,
					new AssertJAssertionBuilder(), String.class, call);
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

        	SourcedDataItem<String, TestDataSource> sourceValue = null;

        	String type = values[0];
    		String key = values[1];
    		String value = values[2];
    		String[] dataValue = StringUtils.substringsBetween(value, "{{", "}}");

    		if(dataValue != null && dataValue.length > 0) {
    			sourceValue = test.getTestData().getSourcedValue(dataValue[0]);
    		}else {
    			return cellValue;
    		}
    		if(sourceValue != null) {
    			TestDataSource source = sourceValue.getValue().getValue();

            	RequestModifier modifier = null;

        		switch(type.trim().toUpperCase()){
        		case "QUERY":
        			modifier = new QueryParamsModifier(key, source);
        			break;
        		case "HEADER":
        			modifier = new HeaderModifier(key, source);
        			break;
        		case "PATH":
        			modifier = new PathModifier(key, source);
        			break;
        		case "FORM":
        			modifier = new FormModifier(key, source);
        			break;
        		case "COOKIE":
        			modifier = new CookieModifier(key, source);
        			break;
        		case "BODY":
        			modifier = new BodyModifier(source);
        			break;
        		case "BODYTEMPLATE":
        			modifier = new BodyTemplateModifier(key, source);
        		}

        		if (request == null && modifier != null) {
        			modifers = new ArrayList<>();
        			modifers.add(modifier);
        		}else if (request !=null ) {
        			request.getRequestModifiers().add(modifier);
        		}
    		}
        }

        Pattern p = Pattern.compile("\\{\\{(.*)\\}\\}");
        String[] parts = cellValue.split("::");
        for (int i = 0; i < parts.length; i++) {
            Matcher m = p.matcher(parts[i]);
            while (m.find()) {
                SourcedDataItem<String, TestDataSource> value = test.getTestData().getSourcedValue(m.group(1));
                if (value != null) {
                	TestDataSource source = value.getValue().getValue();
                	String key = value.getKey();
                	if(source instanceof StaticTestDataSource) {
                		String Value = source.fullfill();
                        cellValue = cellValue.replace(parts[i], Value);
                	}/*else {
                		cellValue = cellValue.replace(parts[i], key);
                	}*/

                }
            }
        }
        return cellValue;

    }

	private String path(String file){
		return ClassPathUtil.getAbsolutePath(file);
	}

}
