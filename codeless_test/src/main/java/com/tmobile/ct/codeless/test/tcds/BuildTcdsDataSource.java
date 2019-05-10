package com.tmobile.ct.codeless.test.tcds;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.testdata.JSONTestData;
import com.tmobile.ct.codeless.testdata.TcdsTestDataSource;

public class BuildTcdsDataSource {

	private TestData testData;
	private String test_name;

	public BuildTcdsDataSource(TestData testData) {
		this.testData = testData;
	}

	public TestData parseTcdsTestData(String testName) throws IOException {

		test_name = testName;
		String path = ".." + File.separator + "testdata" + File.separator + "tcds";

		File folder = new File(ClassPathUtil.getAbsolutePath(path));

		Arrays.asList(folder.list()).stream().map(x -> folder.getPath() + '\\' + x).forEach(resource -> {
			String filePath[] = resource.split("\\\\");
			if (filePath.length > 1) {
				try {
					if (filePath[filePath.length - 1].equalsIgnoreCase(testName.concat(".json"))) {
						parseJsonTestData(resource);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return testData;
	}

	private void parseJsonTestData(String jsonFile) throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Map<String, String>> values = new HashMap<>();
		JSONTestData jsonTestData = null;
		ObjectMapper mapper = new ObjectMapper();
		jsonTestData = mapper.readValue(new File(jsonFile), JSONTestData.class);

		TcdsTestDataSource tcdsSource = new TcdsTestDataSource(jsonTestData);
		SourcedValue<TestDataSource> sourcedValue = new SourcedValue<TestDataSource>();
		sourcedValue.setValue(tcdsSource);
		sourcedValue.setSource("TCDS");
		SourcedDataItem<String, TestDataSource> item = new SourcedDataItem<>(test_name, sourcedValue);
		testData.put(test_name, item);

		// TestDataHelper.fullfill("login.user",testData.get(filename));

	}

}
