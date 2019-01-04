package com.tmobile.selenium.sam.report;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * The Class ExecutionReport.
 *
 * @author Rob Graff
 */
public class ExecutionReport {

	/** The start time. */
	private static Long startTime;
	
	/** The end time. */
	private static Long endTime;

	/** The build num. */
	private static String buildNum;
	
	/** The environment. */
	private static String environment;
	
	/** The Constant execId. */
	private static final String execId = UUID.randomUUID().toString();;
	
	/** The jwt. */
	private static String jwt;
	
	/** The domain. */
	private static String domain;
	
	/** The project. */
	private static String project;
	
	/** The test set id. */
	private static String testSetId;
	
	/** The use alm proxy. */
	private static boolean useAlmProxy;
	
	/** The tep location. */
	private static String tepLocation;
	
	/** The result list. */
	private static List<TestResult> resultList = new LinkedList<TestResult>();

	/**
	 * Instantiates a new execution report.
	 */
	private ExecutionReport() {
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public static Long getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public static void setStartTime(Long startTime) {
		ExecutionReport.startTime = startTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endtime the new end time
	 */
	public static void setEndTime(Long endtime) {
		ExecutionReport.endTime = endtime;
	}

	/**
	 * Gets the builds the num.
	 *
	 * @return the builds the num
	 */
	public static String getBuildNum() {
		return buildNum;
	}

	/**
	 * Sets the builds the num.
	 *
	 * @param buildNum the new builds the num
	 */
	public static void setBuildNum(String buildNum) {
		ExecutionReport.buildNum = buildNum;
	}

	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	public static String getEnvironment() {
		return environment;
	}

	/**
	 * Sets the environment.
	 *
	 * @param environment the new environment
	 */
	public static void setEnvironment(String environment) {
		ExecutionReport.environment = environment;
	}

	/**
	 * Gets the result list.
	 *
	 * @return the result list
	 */
	public static List<TestResult> getResultList() {
		return resultList;
	}

	/**
	 * Sets the result list.
	 *
	 * @param resultList the new result list
	 */
	public static void setResultList(List<TestResult> resultList) {
		ExecutionReport.resultList = resultList;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public static Long getEndTime() {
		return endTime;
	}
	
	/**
	 * Adds the result.
	 *
	 * @param result the result
	 */
	public static void addResult(TestResult result){
		resultList.add(result);
	}


	/**
	 * Gets the exec id.
	 *
	 * @return the exec id
	 */
	public static String getExecId() {
		return execId;
	}

	/**
	 * Gets the jwt.
	 *
	 * @return the jwt
	 */
	public static String getJwt() {
		return jwt;
	}

	/**
	 * Sets the jwt.
	 *
	 * @param jwt the new jwt
	 */
	public static void setJwt(String jwt) {
		ExecutionReport.jwt = jwt;
	}

	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public static String getDomain() {
		return domain;
	}

	/**
	 * Sets the domain.
	 *
	 * @param domain the new domain
	 */
	public static void setDomain(String domain) {
		ExecutionReport.domain = domain;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public static String getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public static void setProject(String project) {
		ExecutionReport.project = project;
	}

	/**
	 * Gets the test set id.
	 *
	 * @return the test set id
	 */
	public static String getTestSetId() {
		return testSetId;
	}

	/**
	 * Sets the test set id.
	 *
	 * @param testSetId the new test set id
	 */
	public static void setTestSetId(String testSetId) {
		ExecutionReport.testSetId = testSetId;
	}

	/**
	 * Checks if is use alm proxy.
	 *
	 * @return true, if is use alm proxy
	 */
	public static boolean isUseAlmProxy() {
		return useAlmProxy;
	}

	/**
	 * Sets the use alm proxy.
	 *
	 * @param useAlmProxy the new use alm proxy
	 */
	public static void setUseAlmProxy(boolean useAlmProxy) {
		ExecutionReport.useAlmProxy = useAlmProxy;
	}

	/**
	 * Gets the tep location.
	 *
	 * @return the tep location
	 */
	public static String getTepLocation() {
		return tepLocation;
	}

	/**
	 * Sets the tep location.
	 *
	 * @param tepLocation the new tep location
	 */
	public static void setTepLocation(String tepLocation) {
		ExecutionReport.tepLocation = tepLocation;
	}
	
	
	

}
