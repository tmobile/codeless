package com.tmobile.ct.codeless.testdata;

import com.tmobile.ct.codeless.core.Test;
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
            original = original.replace(replace,(String) dataSource.get(i).fullfill());
        }
        return original;
    }

    public String replaceValueWithTestData(String original, ArrayList<TestDataSource> dataSource, Test test){
        String[] dataValue = StringUtils.substringsBetween(original, "{{", "}}");
        if (test.getTcdsData() && test.getTestData().asMap().containsKey(test.getName())) {
            for (int i =0;i<dataValue.length;i++) {
                String tcds_value = "";
                TestDataSource testSource = test.getTestData().get(test.getName());
                tcds_value = TestDataHelper.fullfill(dataValue[i], testSource);
                if (StringUtils.isNotBlank(tcds_value)) {
                    String replace = "{{" + dataValue[i] + "}}";
                    original = original.replace(replace, tcds_value);
                }
            }
        }
        dataValue = StringUtils.substringsBetween(original, "{{", "}}");
        if (dataValue !=null && dataValue.length > 0) {
            for (int i = 0; i < dataValue.length; i++) {
                String replace = "{{" + dataValue[i] + "}}";
                original = original.replace(replace, (String) dataSource.get(i).fullfill());
            }
        }
        return original;
    }
}
