package com.tmobile.ct.codeless.testdata;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class JSONTestData {

    private String templateURL;
    private String almId;
    private String almName;

    Map<String, JSONFieldData> dataSets = new LinkedHashMap<>();

    @JsonAnySetter
    public void setDataSet(String key, JSONFieldData value) {
        dataSets.put(key, value);
    }

    @JsonAnyGetter(enabled = true)
    public Map<String, JSONFieldData> getDataSet() {
        return dataSets;
    }

    public String getTemplateURL() {
        return templateURL;
    }

    public void setTemplateURL(String templateURL) {
        this.templateURL = templateURL;
    }

    public String getAlmId() {
        return almId;
    }

    public void setAlmId(String almId) {
        this.almId = almId;
    }

    public String getAlmName() {
        return almName;
    }

    public void setAlmName(String almName) {
        this.almName = almName;
    }

    @Override
    public String toString() {
        return "JSONTestData{" +
                "templateURL='" + templateURL + '\'' +
                ", almId='" + almId + '\'' +
                ", almName='" + almName + '\'' +
                ", dataSets=" + dataSets +
                '}';
    }
}
