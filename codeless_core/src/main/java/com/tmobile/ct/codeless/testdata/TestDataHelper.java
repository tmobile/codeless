package com.tmobile.ct.codeless.testdata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.core.TestDataSource;

public class TestDataHelper {

	public static String fullfill(String key, TestDataSource sourceData) {
		return processDataSource(key, sourceData);
	}

	public static String fullfill(TestDataSource sourceData) {
		return (String) sourceData.fullfill();
	}

	private static String processDataSource(String key, TestDataSource sourceData) {
		String value = "";
		JSONTestData tdata = null;
		if(!StringUtils.isEmpty(key) && sourceData != null) {
			String keyMethod = "get" + key;
			if (sourceData instanceof TcdsTestDataSource) {
				tdata = (JSONTestData) sourceData.fullfill();
				Class<?> c = tdata.getClass();
				for (Method accessor : c.getDeclaredMethods()) {
					if (keyMethod.equalsIgnoreCase(accessor.getName())) {
						try {
							value = (String) accessor.invoke(tdata, null);
							return value;
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				// if key is not located on root level of JSONTestData, go look to JSONFieldData object
				if(StringUtils.isEmpty(value)) {
					String[] keyPath = key.split("\\.");
					if(keyPath.length < 2) {
						return value;
					}
					Map<String, JSONFieldData> fdata= tdata.getDataSet();
					for(String index : fdata.keySet()) {
						if(keyPath[0].equalsIgnoreCase(index)) {
							Map<String,Object> dataSet = fdata.get(index).getFieldData();
							for(String data_key : dataSet.keySet()) {
								if(keyPath[1].equalsIgnoreCase(data_key)) {
									value = (String) dataSet.get(data_key);
									return value;
								}
							}
						}
					}
				}
			}
		}
		return value;
	}

}
