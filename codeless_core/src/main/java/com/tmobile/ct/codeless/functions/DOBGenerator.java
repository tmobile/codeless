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
 ******************************************************************************/
package com.tmobile.ct.codeless.functions;


import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Class that generates a Date of Birth(DOB)
 * @author Julio Zevallos
 */
public class DOBGenerator {

    /**
     * Generates a DOB with a format, with a specific age.
     * @param format the format
     * @param age the age
     * @return the DOB
     */
    public String generate(String format, int age){
        if (age < 0)
            throw new IllegalArgumentException("Age must be a non-negative value");
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        Random random = new SecureRandom();
        cal.add(Calendar.YEAR, -age);
        cal.add(Calendar.DAY_OF_YEAR, -random.nextInt(365));
        return sdf.format(cal.getTime());
    }

    /**
     * Generates a DOB with a format, and between min(inclusive) and max(exclusive) age.
     * @param format the format
     * @param min the minimum age
     * @param max the max age
     * @return the DOB
     */
    public String generate(String format, int min, int max){
        if (min < 0 || max < 0)
            throw new IllegalArgumentException("Minimum age and maximum age must be non-negative values.");
        if (!(min < max))
            throw new IllegalArgumentException("Minimum age must be greater than maximum age.");
        Random random = new SecureRandom();
        return generate(format,min+random.nextInt(max-min));
    }

    /**
     * Generates a DOB between min(inclusive) and max(exclusive) age.
     * @param min the minimum age
     * @param max the maximum age
     * @return the DOB
     */
    public String generate(int min, int max){
        return generate("MM/dd/YYYY",min,max);
    }

    /**
     * Generates a DOB, with a specific age.
     * @param age the age
     * @return the DOB
     */
    public String generate(int age){
        return generate("MM/dd/YYYY",age);
    }

    /**
     * Generates a random DOB with age range 0-100, default format.
     * @return the DOB
     */
    public String generate(){
        return generate(0,100);
    }

    /**
     * GEnerates a random DOB with given format, and between 0-100 age.
     * @param format the format
     * @return the DOB
     */
    public String generate(String format){
        return generate(format,0,100);
    }
}
