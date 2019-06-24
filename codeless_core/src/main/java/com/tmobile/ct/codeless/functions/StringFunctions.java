package com.tmobile.ct.codeless.functions;

import io.swagger.models.auth.In;

import java.lang.reflect.Method;

public class StringFunctions {

    private String theString;

    public StringFunctions(String value){
        this.theString = value;
    }

    public String lowerCase(){
        return this.theString.toLowerCase();
    }

    public String upperCase(){
        return this.theString.toUpperCase();
    }

    public String charAt(String param){
        return "" +this.theString.charAt(Integer.valueOf(param));
    }

    public String contain(String param){
        return "" +this.theString.contains(param);
    }

    public String compare(String param){
        int result = this.theString.compareTo(param);
        if (result == 0)
            return "Equal";
        else
            return "Not Equal";
    }

    public String substring(String index1,String index2){
        return this.theString.substring(Integer.valueOf(index1), Integer.valueOf(index2));
    }

    public Method getMethod(String method) throws NoSuchMethodException {
        return this.getClass().getDeclaredMethod(method,null);
    }

    public Method getMethod(String method, Class<?>... param) throws NoSuchMethodException {
        return this.getClass().getDeclaredMethod(method,param);
    }



}
