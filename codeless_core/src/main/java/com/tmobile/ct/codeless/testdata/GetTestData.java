package com.tmobile.ct.codeless.testdata;

import com.tmobile.ct.codeless.core.TestDataSource;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * Class to get test data.
 * This allows for multiple dynamic variables to be in one cell, as well as having both dynamic and static values.
 * @author Julio Zevallos
 */
public class GetTestData {

    /**
     *
     * @param original The original cellvalue
     * @param dataSource The list of dataSources
     * @return the new cellValue, after dynamic variables have been replaced.
     */
    public String replaceValueWithTestData(String original, ArrayList<TestDataSource> dataSource){
        String[] dataValue = StringUtils.substringsBetween(original, "{{", "}}");
        for (int i =0;i<dataValue.length;i++)
        {
            String replace = "{{" + dataValue[i] + "}}";
            original = original.replace(replace,dataSource.get(i).fullfill());
        }
        return original;
    }
}
