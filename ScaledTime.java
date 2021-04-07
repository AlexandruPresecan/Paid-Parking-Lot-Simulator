package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScaledTime {

    private static Date time =  Calendar.getInstance().getTime();
    public static Event onTimeChanged = new Event();

    /** Increments the time by 5 minutes
     */
    public static void increment() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MINUTE, 5);

        time =  calendar.getTime();

        onTimeChanged.callListeners();
    }

    /** Returns the current time
     * @return The current time is converted into a string in the format "yyyy-MM-dd HH:mm:ss"
     */
    public static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    /** Sets the current time
     * @param timeString A string in the format "yyyy-MM-dd HH:mm:ss"
     */
    public static void setTime(String timeString) {

        try {
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeString);
            onTimeChanged.callListeners();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
