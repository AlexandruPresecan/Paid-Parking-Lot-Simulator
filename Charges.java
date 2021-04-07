package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class Charges {

    private static float[] charges = initCharges();

    private static float[] initCharges() {

        float[] charges = new float[24];

        charges[0] = 2f;
        charges[1] = 2f;
        charges[2] = 2f;
        charges[3] = 2f;
        charges[4] = 2f;
        charges[5] = 2f;
        charges[6] = 2f;
        charges[7] = 2f;
        charges[8] = 2f;
        charges[9] = 2f;
        charges[10] = 2f;
        charges[11] = 2f;
        charges[12] = 3f;
        charges[13] = 3f;
        charges[14] = 3f;
        charges[15] = 3f;
        charges[16] = 3f;
        charges[17] = 3f;
        charges[18] = 3f;
        charges[19] = 3f;
        charges[20] = 3f;
        charges[21] = 3f;
        charges[22] = 3f;
        charges[23] = 3f;

        return charges;
    }

    /** Calculates and returns the charge from a given ticket
     * @param ticket Ticket to get the charge from
     * @return Charge calculated from the specified ticket
     */
    public static float getCharge(Ticket ticket) {

        float charge = 0;

        Calendar enter = Calendar.getInstance();
        Calendar exit = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            enter.setTime(format.parse(ticket.getEnterTime()));
            exit.setTime(format.parse(ticket.getExitTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar nextHour = Calendar.getInstance();
        nextHour.setTime(enter.getTime());

        nextHour.set(Calendar.SECOND, 0);
        nextHour.set(Calendar.MINUTE, 0);
        nextHour.add(Calendar.HOUR, 1);

        if (nextHour.getTime().compareTo(exit.getTime()) <= 0)
            charge += (60 - enter.get(Calendar.MINUTE)) * charges[enter.get(Calendar.HOUR_OF_DAY)] / 60;
        else
            return (exit.get(Calendar.MINUTE) - enter.get(Calendar.MINUTE)) * charges[enter.get(Calendar.HOUR_OF_DAY)] / 60;

        enter.setTime(nextHour.getTime());
        nextHour.add(Calendar.HOUR, 1);

        while (nextHour.before(exit)) {
            charge += charges[enter.get(Calendar.HOUR_OF_DAY)];
            nextHour.add(Calendar.HOUR_OF_DAY, 1);
            enter.add(Calendar.HOUR_OF_DAY, 1);
        }

        charge += exit.get(Calendar.MINUTE) * charges[exit.get(Calendar.HOUR_OF_DAY)] / 60;

        return charge;
    }

}
