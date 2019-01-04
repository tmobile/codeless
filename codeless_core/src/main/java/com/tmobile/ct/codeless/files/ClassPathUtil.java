package com.tmobile.ct.codeless.files;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * The Class ClassPathUtil.
 *
 * @author Rob Graff
 */
public class ClassPathUtil {

	/**
	 * Gets the absolute path.
	 *
	 * @param file the file
	 * @return the absolute path
	 */
	public static String getAbsolutePath(String file){
		
		if(Boolean.parseBoolean(System.getProperty("EXEC.JAR"))){
            return System.getProperty("EXEC.ABSOLUTE.DIR") + File.separator + file;
		}
		
		try {
			file = file.replace("\\", "/");
			return Paths.get(ClassPathUtil.class.getResource(file).toURI()).toAbsolutePath().toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Exists.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	public static boolean exists(String file){
		
		if(Boolean.parseBoolean(System.getProperty("EXEC.JAR"))){       
            return Paths.get(System.getProperty("EXEC.ABSOLUTE.DIR") + File.separator + file).toAbsolutePath().toFile().exists();
		}
		
		try {
			file = file.replace("\\", "/");
			return Paths.get(ClassPathUtil.class.getResource(file).toURI()).toAbsolutePath().toFile().exists();
		} catch (Exception e) {
			return false;
		}
	}
}
