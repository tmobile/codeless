/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 *****************************************************************************/
package com.tmobile.ct.codeless.functions;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * The Class NumberGenerator
 * @author Julio Zevallos
 */
public class NumberGenerator {
    public BigInteger generate(BigInteger min, BigInteger max){
        if (min.compareTo(max) == 1)
        {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        BigInteger range = max.subtract(min);
        BigInteger num = new BigInteger(range.bitLength(), new SecureRandom());
        if (num.compareTo(range) == 1)      //if outside range
        {
            num = num.mod(range);
        }
        num = num.add(min);
        return num;
    }

    /**
     * Lets user put range with Strings
     * @param min
     * @param max
     * @return random BigInteger in given range
     */
    public BigInteger generate(String min, String max){
        return generate(new BigInteger(min),new BigInteger(max));
    }

    /**
     * Generates random BigInteger with certain amount of digits
     * @param digits
     * @return random BigInteger with certain amount of digits
     */
    public BigInteger generate(int digits){
        BigInteger bigmin, bigmax;
        String max = "9";
        String min = "1";
        for (int i =1;i<digits;i++)     //get maximum and minimum values for # of digits
        {
            max += "9";
            min += "0";
        }
        bigmin = new BigInteger(min);
        bigmax = new BigInteger(max);
        return generate(bigmin,bigmax);
    }
}
