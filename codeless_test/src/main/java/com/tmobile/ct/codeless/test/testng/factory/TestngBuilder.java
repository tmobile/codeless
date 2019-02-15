package com.tmobile.ct.codeless.test.testng.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestngBuilder {

	TestNG testng = new TestNG();
	List<XmlSuite> suites = new ArrayList<>();
	public TestngBuilder() {
	}
	
	public TestngBuilder addSuite(XmlSuite suite){
		suites.add(suite);
		return this;
	}
	
	public TestngBuilder addSuite(String suiteName, String testName, String suiteId, String testClass, Map<String,String> params){
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName(suiteName);
		XmlTest xmlTest = new XmlTest();
		xmlTest.setName(testName);
		xmlTest.setSuite(xmlSuite);
		List<XmlClass> xmlClasses = new ArrayList<XmlClass>();
		xmlClasses.add(new XmlClass(testClass));
		xmlTest.getClasses().addAll(xmlClasses);
		xmlSuite.addTest(xmlTest);
		//params.put("codeless.suite.id", suiteId);    //not needed? this param is always null.  suiteId is set later in execution through testng
		xmlSuite.setParameters(params);
		System.out.println("check parameters:" +xmlSuite.getParameters());
		System.out.println("check all parameters:" +xmlSuite.getAllParameters());
		
		addSuite(xmlSuite);
		
		return this;
	}
	
	public TestNG build(){
		testng.setXmlSuites(suites);
		return testng;
	}
}
