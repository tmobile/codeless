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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;

/**
 * The TimeStamp class
 *
 */
public class TimeStamp {

    public String current(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public String generate(String format, String interval){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        long offset = 0;
        long end = 0;
        long diff = 0;
        switch (interval){
            case "Y":
                cal.add(Calendar.YEAR,1);
                offset = Timestamp.valueOf(Year.now() +"-01-01 00:00:00").getTime();
                end = Timestamp.valueOf(cal.get(Calendar.YEAR) +"-01-01 00:00:00").getTime();
                diff = end - offset +1;
                break;
            case "M":
                int currentMonth = cal.get(Calendar.MONTH) +1;
                int nextMonth = currentMonth + 1;
                offset = Timestamp.valueOf(Year.now() +"-"+currentMonth +"-01 00:00:00").getTime();
                end = Timestamp.valueOf(Year.now() +"-"+nextMonth +"-01 00:00:00").getTime();
                diff = end - offset +1;
                break;
            case "W":
                cal.set(Calendar.DAY_OF_WEEK,cal.getFirstDayOfWeek());
                cal.set(Calendar.HOUR_OF_DAY,0);
                cal.clear(Calendar.MINUTE);
                cal.clear(Calendar.SECOND);
                cal.clear(Calendar.MILLISECOND);
                offset = cal.getTimeInMillis();
                cal.add(Calendar.DAY_OF_YEAR,7);
                end = cal.getTimeInMillis();
                diff = end - offset + 1;
                break;
            case "D":
                cal.set(Calendar.HOUR_OF_DAY,0);
                cal.clear(Calendar.MINUTE);
                cal.clear(Calendar.SECOND);
                cal.clear(Calendar.MILLISECOND);
                offset = cal.getTimeInMillis();
                cal.add(Calendar.DAY_OF_YEAR,1);
                end = cal.getTimeInMillis();
                diff = end - offset +1;
                break;
        }
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        return sdf.format(rand);
    }

    public String generate(String format,String startBound, String endBound){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        long offset = Timestamp.valueOf(startBound).getTime();
        long end = Timestamp.valueOf(endBound).getTime();
        long diff = end - offset +1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        return sdf.format(rand);
    }

    
}
