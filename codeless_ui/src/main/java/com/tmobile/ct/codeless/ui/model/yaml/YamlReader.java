package com.tmobile.ct.codeless.ui.model.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.ui.model.ControlElement;

/**
 * The Class YamlReader.
 *
 * @author Rob Graff
 */
public class YamlReader {

	/** The Controls. */
	private static Map<String, ControlElement> Controls;

	/**
	 * Instantiates a new yaml reader.
	 */
	private YamlReader() {};

	/**
	 * Read control.
	 *
	 * @param modelName the model name
	 * @param targetName the target name
	 * @return the map
	 */
	public static Map<String, ControlElement> ReadControl(String modelName, String targetName) {


		return	initializeYaml(modelName,targetName);

	}

	/**
	 * Initialize yaml.
	 *
	 * @param modelName the model name
	 * @param targetName the target name
	 * @return the map
	 */
	private static Map<String, ControlElement> initializeYaml(String modelName, String targetName) {
		Yaml yaml = new Yaml();
		Map<String, Map<String, String>> mapyaml = null;
		Map<String, ControlElement> yamldata = new HashMap<String, ControlElement>();
		ControlElement ctrlElem = null;
		try {
			File path = new File(ClassPathUtil.getAbsolutePath(File.separator+"models/" + modelName + "/" + targetName +".yaml"));
			if (path != null) {
				InputStream ios = new FileInputStream(path);
				mapyaml = (Map<String, Map<String, String>>) yaml.load(ios);
				if (mapyaml != null) {

					for (Map.Entry<String, Map<String, String>> itEntry : mapyaml.entrySet()) {
						Map<String, String> ctrlData = itEntry.getValue();
						if (ctrlData != null) {
							ctrlElem = new ControlElement();
							if (ctrlElem != null) {
								ctrlElem.setBy(ctrlData.get("by"));
								ctrlElem.setLocator(ctrlData.get("locator"));
							}
							yamldata.put(itEntry.getKey(), ctrlElem);
						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Controls = yamldata;
		return Controls;
	}

}
