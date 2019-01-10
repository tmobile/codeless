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
import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.core.datastructure.SuiteHeaders;
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
import com.tmobile.ct.codeless.service.accessor.request.RequestModifier;
import com.tmobile.ct.codeless.service.accessor.response.BodyStringAccessor;
import com.tmobile.ct.codeless.service.accessor.response.HeaderAccessor;
import com.tmobile.ct.codeless.service.accessor.response.JsonPathAccessor;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;
import com.tmobile.ct.codeless.service.accessor.response.ResponseTimeAssertion;
import com.tmobile.ct.codeless.service.accessor.response.StaticAccessor;
import com.tmobile.ct.codeless.service.accessor.response.StatusCodeAssertion;
import com.tmobile.ct.codeless.service.accessor.response.XmlPathAccessor;
import com.tmobile.ct.codeless.service.assertion.BaseServiceCallAssertion;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.service.httpclient.Cookie;
import com.tmobile.ct.codeless.service.httpclient.Form;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.httpclient.PathParam;
import com.tmobile.ct.codeless.service.httpclient.QueryParam;
import com.tmobile.ct.codeless.service.model.Operation;
import com.tmobile.ct.codeless.service.model.Service;
import com.tmobile.ct.codeless.service.model.cache.ServiceCache;
import com.tmobile.ct.codeless.service.reference.CallRefBySuite;
import com.tmobile.ct.codeless.service.reference.CallRefByTest;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;
import com.tmobile.ct.codeless.service.restassured.RestAssuredHttpClient;

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

	/** The assertions. */
	private List<Assertion> assertions = new ArrayList<>();

	/** The test. */
	private Test test;

	/** The Override host. */
	private static String OVERRIDE_HOST="$HOST";

	/** The Override operation. */
	private static String OVERRIDE_OPERATION="$OPERATION";

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
			case "TARGET_OVERRIDES":
				if(null != test.getTestData()) {
					String[] overrides = value.split(",");
					for(String override : overrides) {
						String keyValue[] = override.split("\\=") ;
						if(OVERRIDE_HOST.toUpperCase().equalsIgnoreCase(keyValue[0]) && test.getTestData() != null && keyValue.length > 1) {
							if(test.getTestData().asMap().containsKey(keyValue[1])) {
								if(System.getProperty(keyValue[1]) != null) {
									String sys_value = System.getProperty(keyValue[1]);
									input.add(SuiteHeaders.CUSTOMHOST.name(), new MultiValue<String,String>(SuiteHeaders.CUSTOMHOST.name(), sys_value));
									break;
								}

								if(System.getenv(keyValue[1]) != null) {
									String env_value = System.getenv(keyValue[1]);
									input.add(SuiteHeaders.CUSTOMHOST.name(), new MultiValue<String,String>(SuiteHeaders.CUSTOMHOST.name(), env_value));
									break;
								}

								String custom_host = test.getTestData().get(keyValue[1]);
								input.add(SuiteHeaders.CUSTOMHOST.name(), new MultiValue<String,String>(SuiteHeaders.CUSTOMHOST.name(), custom_host));
							}
						}else if(OVERRIDE_OPERATION.toUpperCase().equalsIgnoreCase(keyValue[0]) && test.getTestData() != null && keyValue.length > 1) {
							if(test.getTestData().asMap().containsKey(keyValue[1])) {
								String custom_operation = test.getTestData().get(keyValue[1]);
								input.add(SuiteHeaders.CUSTOMOPERATION.name(), new MultiValue<String,String>(SuiteHeaders.CUSTOMOPERATION.name(), custom_operation));
							}
						}
					}
			}
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

		Service service = ServiceCache.getService(testRow.service);
		Operation operation = service.getOperation(HttpMethod.valueOf(testRow.method), testRow.operation);
//		HttpRequest request = SerializationUtils.clone((HttpRequestImpl) operation.getRequest());

		if(operation == null){
			System.err.println("NO OPERTAION FOUND FOR INPUT["+testRow.operation+"]");
			return null;
		}

		request = null;
		try {
			request = mapper.readValue(mapper.writeValueAsBytes(operation.getRequest()),HttpRequestImpl.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(StringUtils.isNotBlank(testRow.bodyTemplate)){
			setBodyFromTempate(testRow.bodyTemplate);
		}

		call = new Call(new RestAssuredHttpClient(), request, 0);

		testRow.testData.forEach(this::parseTestData);


		if(request != null && request.getBody()!=null && request.getBody().getBody().indexOf("{{") > 0 && request.getBody().getBody().indexOf("}}") > 0 ){
			setBodyFromEnv(operation);
		}
		call.setOperation(operation);
		call.setName(testRow.testName);
		call.setAssertions(assertions);
		return call;
	}

	private void setBodyFromEnv(Operation operation){
		String body = request.getBody().getBody();
		String path = operation.getPath();
		String customPath = "";

		if(path.indexOf(".") > 0){
			String[] arr = path.split(".");
			for(String item : arr){
				customPath = customPath + "/" +item;
			}
		}else{
			customPath = path;
		}
		System.out.println("check request body before postman injection "+request.getBody().getBody() );

		System.out.println("custom path::"+ customPath);

		Service serviceFromPostman = ServiceCache.getService(testRow.service);

		Operation oper = serviceFromPostman.getOperation( HttpMethod.POST, testRow.operation);
		String postmanBody = oper.getRequest().getBody().getBody().toString();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String,Object> postmanMap = mapper.readValue(postmanBody, Map.class);
			String[] jsonArr = body.substring(1, body.length()-1).split(",");

			String finalString = "";
			Map<String,Object> newMap = null;
			for(String item : jsonArr){
				if(item.indexOf("{{") > 0 && item.indexOf("}}") > 0){
					String[] jsonObj = item.split(":");
					String val = jsonObj[1].substring(1, jsonObj[1].length()-1);
					val = val.substring(1, val.length()-1);

					if(val.indexOf("{") == 0 && val.indexOf("}") > 0 ){
						val =val.substring(1, val.length()-1);
					}

					String key = jsonObj[0].substring(1, jsonObj[0].length()-1);

					postmanMap.remove(key);

					postmanMap.put(key, val );
					finalString = "{";
					for (Map.Entry<String, Object> entry : postmanMap.entrySet()) {
					    String finalKey = entry.getKey().toString();
					    Object value = entry.getValue().toString();
					    finalString = finalString +'"'+ finalKey +'"' +":" + '"' + value +'"' +",";
					}

					if(finalString.endsWith(","))
					{
						finalString = finalString.substring(0,finalString.length() - 1);
					}
					finalString = finalString +"}";
				}
			}

			Body<String> newBody = new Body<String>();

			System.out.println("postman map ************************************* " + postmanMap.toString());
			newBody.setBody(finalString);
			request.setBody(newBody);

			System.out.println("check request body after postman injection "+request.getBody().getBody() );
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		if(parts.length > 5){
			System.err.println("ExcelParser: Test Data cell has parts > 5, "+parts);
			return;
		}

		String type = parts[0];
		String key = parts[1];
		String value = parts[2];

		value = parseRefValue(excelData);

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
			ResponseAccessor accessor = new StaticAccessor(parts[2]);
			RequestModifier modifier = new BodyTemplateModifier(parts[1], accessor);
			request.getRequestModifiers().add(modifier);
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
	 * Parses the ref value.
	 *
	 * @param excelData the excel data
	 * @return the string
	 */
	private String parseRefValue(String excelData) {

		String[] excelparts = excelData.trim().split("::");

		String type = excelparts[0];
		String key = excelparts[1];
		String value = excelparts[2];

		//assert::query-response::account_status::isEqualTo::$REF~SELF~get balance~jsonpath~account_status

		if(StringUtils.isBlank(value)){
			return value;
		}

		if(!value.startsWith("$REF~")){
			return value;
		}

		String[] parts = value.trim().split("~");
		String locator = parts[1].toUpperCase();
		String callName = parts[2].toUpperCase();
		String responsePart = parts[3];
		String responseKey = parts[4];

		ServiceCallReference callRef;
		if(locator.equalsIgnoreCase("SELF")){
			callRef = new CallRefByTest(test, callName);
		}else{
			callRef = new CallRefBySuite(test.getSuite(),locator, callName);
		}

		ResponseAccessor accessor = null;
		switch(responsePart.trim().toUpperCase()){
		case "HEADER":
			accessor = new HeaderAccessor(callRef, responseKey);
			break;
		case "BODYSTRING":
			accessor = new BodyStringAccessor(callRef);
			break;
		case "JSONPATH":
			accessor = new JsonPathAccessor(callRef, responseKey, String.class);
			break;
		case "XMLPATH":
			accessor = new XmlPathAccessor(callRef, responseKey, String.class);
			break;
		}


		RequestModifier modifier = null;
		switch(type.trim().toUpperCase()){
		case "QUERY":
			modifier = new QueryParamsModifier(key, accessor);
			break;
		case "HEADER":
			modifier = new HeaderModifier(key, accessor);
			break;
		case "PATH":
			modifier = new PathModifier(key, accessor);
			break;
		case "FORM":
			modifier = new FormModifier(key, accessor);
			break;
		case "COOKIE":
			modifier = new CookieModifier(key, accessor);
			break;
		case "BODY":
			modifier = new BodyModifier(accessor);
			break;
		case "BODYTEMPLATE":
			modifier = new BodyTemplateModifier(key, accessor);
		}

		request.getRequestModifiers().add(modifier);
		return value;
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

		String type = parts[1];
		String method = originalParts[2];
		String expected = originalParts[3];
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
        if (cellValue.contains("export")) {
            String[] values = cellValue.split("::");
            List<String> stepName = input.get(SuiteHeaders.TESTNAME.name()).getValues();
            if (values.length >= 2) {
                String REF = "$REF";
                REF += "~" + test.getName() + "~" + stepName.get(0) + "~";
                String key = values[1];
                for (int i = 2; i < values.length - 1; i++) {
                    REF += values[i] + "~";
                }
                REF += values[values.length - 1];
                SourcedValue<String> sourceValue = new SourcedValue<String>();
                sourceValue.setValue(REF);
                SourcedDataItem<String, String> item = new SourcedDataItem<String, String>(key, sourceValue);
                if (test.getTestData() == null) {
                    test.setTestData(new BasicTestData());
                }
                test.getTestData().put(key, item);
            }
        }
        Pattern p = Pattern.compile("\\{\\{(.*)\\}\\}");
        Matcher m = p.matcher(cellValue);
        while (m.find()) {
            SourcedDataItem<String, String> value = test.getTestData().getSourcedValue(m.group(1));
            if (value != null) {
                String Value = value.getValue().getValue();
                String key = value.getKey();
                cellValue = cellValue.replace("{{" + key + "}}", Value);
            }
        }
        return cellValue;

    }

}
