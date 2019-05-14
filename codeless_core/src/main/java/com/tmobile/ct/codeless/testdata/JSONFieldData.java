package com.tmobile.ct.codeless.testdata;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class JSONFieldData {

	public JSONFieldData() {}

    Map<String, Object> fieldDatas= new LinkedHashMap<>();

    @JsonAnySetter
    public void setFieldData(String key, Object value) {
        fieldDatas.put(key, value);
    }

    @JsonAnyGetter(enabled = true)
    public Map<String, Object> getFieldData() {
        return fieldDatas;
    }

    @Override
    public String toString() {
        return "JSONFieldData{" +
                "fieldDatas=" + fieldDatas +
                '}';
    }

}


