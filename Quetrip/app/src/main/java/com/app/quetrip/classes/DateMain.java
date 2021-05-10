package com.app.quetrip.classes;

import android.content.Context;

import com.app.quetrip.models.TimeObj;

import java.util.Calendar;
import java.util.Date;

public class DateMain{

    public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String getDateStr(long timestamp){
        String[] monthes={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        String dateStr = "";
        Calendar c = Calendar.getInstance();
        //Set time in milliseconds
        c.setTimeInMillis(timestamp);
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMin = c.get(Calendar.MINUTE);

        String datetimeStr = "";
        if(mHour > 12){
            mHour = mHour - 12;
            if(mHour < 10) {
                if(mMin < 10){
                    datetimeStr = " 0" + mHour + ":0" + mMin + " PM";
                }else {
                    datetimeStr = " 0" + mHour + ":" + mMin + " PM";
                }
            }
            else if(mMin < 10){
                datetimeStr = " " + mHour + ":0" + mMin + " PM";
            }
            else if(mHour < 10 && mMin < 10){
                datetimeStr = " 0" + mHour + ":0" + mMin + " PM";
            }
            else {
                if(mMin < 10){
                    datetimeStr = " " + mHour + ":0" + mMin + " PM";
                }else {
                    datetimeStr = " " + mHour + ":" + mMin + " PM";
                }
            }
        }else {
            if(mHour < 10) {
                if(mMin < 10){
                    datetimeStr = " 0" + mHour + ":0" + mMin + " AM";
                }else {
                    datetimeStr = " 0" + mHour + ":" + mMin + " AM";
                }
            }
            else if(mMin < 10){
                datetimeStr = " " + mHour + ":0" + mMin + " AM";
            }
            else if(mHour < 10 && mMin < 10){
                datetimeStr = " 0" + mHour + ":0" + mMin + " AM";
            }
            else {
                if(mMin < 10){
                    datetimeStr = " " + mHour + ":0" + mMin + " AM";
                }
                else {
                    datetimeStr = " " + mHour + ":" + mMin + " AM";
                }
            }
        }

        if(mDay<10)
            dateStr = monthes[mMonth] + " 0" + mDay + ", " + mYear;
        else
            dateStr = monthes[mMonth] + " " + mDay + ", " + mYear;

        return dateStr;
    }

    public static String getDateTimeStr(long timestamp){
        String[] monthes={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        String dateStr = "";
        Calendar c = Calendar.getInstance();
        //Set time in milliseconds
        c.setTimeInMillis(timestamp);
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMin = c.get(Calendar.MINUTE);

        String datetimeStr = "";
        if(mHour > 12){
            mHour = mHour - 12;
            if(mHour < 10) {
                if(mMin < 10){
                    datetimeStr = " 0" + mHour + ":0" + mMin + " PM";
                }else {
                    datetimeStr = " 0" + mHour + ":" + mMin + " PM";
                }
            }
            else if(mMin < 10){
                datetimeStr = " " + mHour + ":0" + mMin + " PM";
            }
            else if(mHour < 10 && mMin < 10){
                datetimeStr = " 0" + mHour + ":0" + mMin + " PM";
            }
            else {
                if(mMin < 10){
                    datetimeStr = " " + mHour + ":0" + mMin + " PM";
                }else {
                    datetimeStr = " " + mHour + ":" + mMin + " PM";
                }
            }
        }else {
            if(mHour < 10) {
                if(mMin < 10){
                    datetimeStr = " 0" + mHour + ":0" + mMin + " AM";
                }else {
                    datetimeStr = " 0" + mHour + ":" + mMin + " AM";
                }
            }
            else if(mMin < 10){
                datetimeStr = " " + mHour + ":0" + mMin + " AM";
            }
            else if(mHour < 10 && mMin < 10){
                datetimeStr = " 0" + mHour + ":0" + mMin + " AM";
            }
            else {
                if(mMin < 10){
                    datetimeStr = " " + mHour + ":0" + mMin + " AM";
                }
                else {
                    datetimeStr = " " + mHour + ":" + mMin + " AM";
                }
            }
        }

        if(mDay<10)
            dateStr = monthes[mMonth] + " 0" + mDay + ", " + mYear + datetimeStr;
        else
            dateStr = monthes[mMonth] + " " + mDay + ", " + mYear + datetimeStr;

        return dateStr;
    }

    public static TimeObj getTime(long timestamp){
        TimeObj timeObj = new TimeObj();
        Calendar c = Calendar.getInstance();
        //Set time in milliseconds
        c.setTimeInMillis(timestamp);
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMin = c.get(Calendar.MINUTE);
        timeObj.setYear(mYear);
        timeObj.setMonth(mMonth);
        timeObj.setDay(mDay);
        timeObj.setHour(mHour);
        timeObj.setMinute(mMin);
        return timeObj;
    }

}

























