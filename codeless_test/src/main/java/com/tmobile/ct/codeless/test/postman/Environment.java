package com.tmobile.ct.codeless.test.postman;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    private String id;
    private String name;
    private List<Value> values = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Value getValue(String valueName) {
        if (values.indexOf(valueName) == -1) {
            return null;
        }
        
        return values.get(values.indexOf(valueName));
    }

    public List<Value> getValues() {
        return values;
    }

    public void addValue(Value value) {
        this.values.add(value);
    }

    public void replaceValue(Value value) {
        if (values.contains(value.getKey())) {
            removeValue(value.getKey());
        }

        addValue(value);
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void removeValue(String valueKey) {
        this.values.remove(valueKey);
    }
}
