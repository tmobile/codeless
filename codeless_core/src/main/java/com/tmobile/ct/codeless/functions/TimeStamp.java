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

    /**
     * Returns the current time in a given format
     * @param format the format
     * @return the current time
     */
    public String current(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    /**
     * Generates a random timestamp in a given interval. Year, Month, Week, or Day
     * @param format the format
     * @param interval the interval
     * @return the timestamp
     */
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
            default:
                return sdf.format(modifyTime(interval).getTime());
        }
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        return sdf.format(rand);
    }

    /**
     * Generates a random timestamp between a start and end time
     * @param format the format
     * @param startBound the start time
     * @param endBound the end time
     * @return the timestamp
     */
    public String generate(String format,String startBound, String endBound){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        long offset = Timestamp.valueOf(startBound).getTime();
        long end = Timestamp.valueOf(endBound).getTime();
        long diff = end - offset +1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        return sdf.format(rand);
    }

    /**
     * Returns the current timestamp modified to the past or future by a set amount, with +/- , the integer value, and the interval.
     * ie. -2W would give the timestamp of 2 weeks ago
     * @param interval the interval
     * @return the timestamp
     */
    private Calendar modifyTime(String interval){
        String value = interval.replaceAll("\\D+","");
        Calendar cal = Calendar.getInstance();
        int diff;
        if (interval.contains("-"))
            diff = -Integer.parseInt(value);
        else
            diff = Integer.parseInt(value);

        if (interval.contains("Y") || interval.contains("y"))
            cal.add(Calendar.YEAR,diff);
        else if (interval.contains("M"))
            cal.add(Calendar.MONTH,diff);
        else if (interval.contains("W") || interval.contains("w"))
            cal.add(Calendar.WEEK_OF_YEAR,diff);
        else if (interval.contains("d"))
            cal.add(Calendar.DAY_OF_YEAR,diff);
        else if (interval.contains("H") || interval.contains("h"))
            cal.add(Calendar.HOUR_OF_DAY,diff);
        else if (interval.contains("m"))
            cal.add(Calendar.MINUTE,diff);
        else if (interval.contains("s"))
            cal.add(Calendar.SECOND,diff);
        else if (interval.contains("S"))
            cal.add(Calendar.MILLISECOND,diff);
        return cal;
    }

    
}
