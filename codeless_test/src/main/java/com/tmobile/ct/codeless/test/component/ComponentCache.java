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
package com.tmobile.ct.codeless.test.component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tmobile.ct.codeless.core.Component;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.files.FileDotIdentifier;
import com.tmobile.ct.codeless.files.FileMapper;
import com.tmobile.ct.codeless.test.csv.CsvTestBuilder;
import com.tmobile.ct.codeless.test.excel.ExcelFileReader;
import com.tmobile.ct.codeless.test.excel.ExcelTestBuilder;
import com.tmobile.ct.codeless.test.suite.SuiteImpl;
import com.tmobile.ct.codeless.test.suite.TestImpl;
/**
 * The Class ComponentCache.
 *
 * @author Rob Graff, Fikreselam Elala
 */

public class ComponentCache {

	private static ConcurrentHashMap<String, List<Step>> cache = new ConcurrentHashMap<>();
	private static ConcurrentHashMap<String, FileDotIdentifier> files;

	private TestImpl test;

	private ComponentCache() {}

	private static void init() {
		String path = ClassPathUtil.getAbsolutePath(".."+File.separator+"components");
		FileMapper mapper = new FileMapper(Paths.get(path), "component");
		files = mapper.map();
	}

	public static List<Step> getComponent(String name,Test test){

		if(files == null) {
			init();
		}

		if(StringUtils.isBlank(name)) {
			return null;
		}

		if(!cache.containsKey(name)) {
			buildComponentSteps(name,test);
		}

		return cache.get(name);
	}

	private static void buildComponentSteps(String name, Test test) {
		FileDotIdentifier fdId = files.get(name);

		if(fdId == null) {
			return;
		}

		if(fdId.getExtension().equalsIgnoreCase("csv")) {
			cache.put(name, buildFromCsv(fdId.getPath(), fdId.getName(), test));
		}else {
			cache.put(name, buildFromExcel(fdId.getPath(), fdId.getName(),test));
		}
	}

	private static List<Step> buildFromCsv(String path, String name,Test test) {
		Iterable<CSVRecord> records;
		try {
			records = CSVFormat.DEFAULT.parse(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		CsvTestBuilder builder = new CsvTestBuilder();
		builder.build(new SuiteImpl(""), records.iterator(), "", null,test);
		Component component = new ComponentImpl();
		component.setName(name);
		test.getSteps().forEach(step -> step.setComponent(component));

		return test.getSteps();

	}

	private static List<Step> buildFromExcel(String path, String name,Test test){

		Workbook workbook = ExcelFileReader.readExcelFile(path,false);
		Stream<Sheet> sheets = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED), false);

		List<Step> steps = new ArrayList<>();


		sheets.forEach(x -> {
			if(!x.getSheetName().startsWith("c-")) {
				steps.addAll(new ExcelTestBuilder().build(x,test));
			}
		});

		return steps;

	}
}