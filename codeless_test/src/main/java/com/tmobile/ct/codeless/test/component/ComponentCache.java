package com.tmobile.ct.codeless.test.component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.core.Component;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.files.FileDotIdentifier;
import com.tmobile.ct.codeless.files.FileMapper;
import com.tmobile.ct.codeless.test.csv.CsvTestBuilder;
import com.tmobile.ct.codeless.test.suite.SuiteImpl;

public class ComponentCache {

	private static ConcurrentHashMap<String, List<Step>> cache = new ConcurrentHashMap<>();
	private static ConcurrentHashMap<String, FileDotIdentifier> files;
	
	private ComponentCache() {}
	
	private static void init() {
		String path = ClassPathUtil.getAbsolutePath(File.separator+"components");
		FileMapper mapper = new FileMapper(Paths.get(path), "component");
		files = mapper.map();
	}
	
	public static List<Step> getComponent(String name){
		
		if(files == null) {
			init();
		}
		
		if(StringUtils.isBlank(name)) {
			return null;
		}
		
		if(!cache.containsKey(name)) {
			buildComponentSteps(name);
		}
		
		return cache.get(name);
	}

	private static void buildComponentSteps(String name) {
		FileDotIdentifier fdId = files.get(name);
		
		if(fdId == null) {
			return;
		}
		
		if(fdId.getExtension().equalsIgnoreCase("csv")) {
			cache.put(name, buildFromCsv(fdId.getPath(), fdId.getName()));
		}else {
			// parse xslx, xls
		}
	}

	private static List<Step> buildFromCsv(String path, String name) {
		Iterable<CSVRecord> records;
		try {
			records = CSVFormat.DEFAULT.parse(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		CsvTestBuilder builder = new CsvTestBuilder();
		Test test = builder.build(new SuiteImpl(""), records.iterator(), "", null);
		Component component = new ComponentImpl();
		component.setName(name);
		test.getSteps().forEach(step -> step.setComponent(component));
		
		return test.getSteps();
		
	}
}