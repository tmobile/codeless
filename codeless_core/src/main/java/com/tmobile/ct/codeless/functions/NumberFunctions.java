package com.tmobile.ct.codeless.functions;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class NumberFunctions {

    private String val1;
    private String val2;
    private int roundDigits = 0;

    public NumberFunctions(String val1){
        this.val1 = val1;
    }

    public NumberFunctions(String val1, String val2){
        this.val1 = val1;
        this.val2 = val2;
    }

    public NumberFunctions(String val1, String val2, String roundDigits){
        this(val1,val2);
        this.roundDigits = Integer.valueOf(roundDigits);
    }

    public String add(){
        BigDecimal num1 = new BigDecimal(this.val1);
        BigDecimal num2 = new BigDecimal(this.val2);
        if (roundDigits != 0)
            return num1.add(num2,new MathContext(this.roundDigits)).toPlainString();
        return num1.add(num2).toString();

    }

    public String subtract(){
        BigDecimal num1 = new BigDecimal(this.val1);
        BigDecimal num2 = new BigDecimal(this.val2);
        if (roundDigits != 0)
            return num1.subtract(num2,new MathContext(this.roundDigits)).toPlainString();
        return num1.subtract(num2).toString();
    }

    public String multiply(){
        BigDecimal num1 = new BigDecimal(this.val1);
        BigDecimal num2 = new BigDecimal(this.val2);
        if (roundDigits != 0)
            return num1.multiply(num2,new MathContext(this.roundDigits)).toPlainString();
        return num1.multiply(num2).toString();
    }

    public String divide(){
        BigDecimal num1 = new BigDecimal(this.val1);
        BigDecimal num2 = new BigDecimal(this.val2);
        if (roundDigits != 0)
            return num1.divide(num2,new MathContext(this.roundDigits)).toPlainString();
        return num1.divide(num2).toPlainString();
    }

    public String mod(){
        BigInteger num1 = new BigInteger(this.val1);
        BigInteger num2 = new BigInteger(this.val2);
        return num1.mod(num2).toString();
    }

    public String roundUp(){
        BigDecimal num1 = new BigDecimal(this.val1);
        return num1.setScale(0,RoundingMode.UP).toPlainString();
    }

    public String roundDown(){
        BigDecimal num1 = new BigDecimal(this.val1);
        return num1.setScale(0,RoundingMode.DOWN).toPlainString();
    }

    public String roundClosest(){
        BigDecimal num1 = new BigDecimal(this.val1);
        if (this.val1.contains(".") && this.val1.contains("-"))
            return num1.round(new MathContext(val1.length()-3,RoundingMode.HALF_UP)).toPlainString();
        else if (this.val1.contains(".") || this.val1.contains(("-")))
            return num1.round(new MathContext(val1.length()-2,RoundingMode.HALF_UP)).toPlainString();
        else
            return num1.round(new MathContext(val1.length()-1,RoundingMode.HALF_UP)).toPlainString();
    }

    public Method getNumberMethod(String value) throws NoSuchMethodException {
        return this.getClass().getDeclaredMethod(value,null);
    }




}
