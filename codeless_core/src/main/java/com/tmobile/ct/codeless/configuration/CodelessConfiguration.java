package com.tmobile.ct.codeless.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class CodelessConfiguration.
 *
 * @author Rob Graff
 */
public final class CodelessConfiguration {
	
	/** The model dir. */
	private static String modelDir = File.separator + "models";
	
	/** The suite dir. */
	private static String suiteDir = File.separator + "suites";
	
	private static Properties properties;

	/**
	 * Load.
	 */
	public static void load() {
		 properties = new Properties();

		try (InputStream configStream = new FileInputStream("." + File.separator + "codeless.config.properties")){
			properties.load(configStream);
			modelDir = properties.getProperty("model.dir", File.separator + "model");
			suiteDir = properties.getProperty("suites.dir", File.separator + "suites");
		} catch (Exception e) {
			// No config file present, default
			System.out.println("************************************************************************");
			System.out.println("No valid codeless.config.properties file found, defaulting configuration");
			System.out.println("************************************************************************");
		}
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		CodelessConfiguration.properties = properties;
	}

	/**
	 * Gets the model dir.
	 *
	 * @return the model dir
	 */
	public static String getModelDir() {
		return modelDir;
	}

	/**
	 * Gets the suite dir.
	 *
	 * @return the suite dir
	 */
	public static String getSuiteDir() {
		return suiteDir;
	}
}
