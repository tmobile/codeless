package com.tmobile.ct.codeless.functions;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class NumberFunctionsTest {

    @Test
    public void testNumberFunctions() {
        String val1 = "100";
        String val2 = "50";
        NumberFunctions numberFunctions = new NumberFunctions(val1,val2);
        Assert.assertNotNull(numberFunctions.add());
        Assert.assertNotNull(numberFunctions.subtract());
        Assert.assertNotNull(numberFunctions.divide());
        Assert.assertNotNull(numberFunctions.mod());
        Assert.assertNotNull(numberFunctions.multiply());
        Assert.assertNotNull(numberFunctions.roundClosest());
        Assert.assertNotNull(numberFunctions.roundDown());
        Assert.assertNotNull(numberFunctions.roundUp());
    }
}
