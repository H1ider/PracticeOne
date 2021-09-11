package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static String returnNextMonth(){
        //create a date object
        Date dNow = new Date();

        //create a Gregorian Calendar object
        Calendar calendar = new GregorianCalendar();

        //set calendar date to current date
        calendar.setTime(dNow);

        //format the date
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-YYYY");
        calendar.add(Calendar.MONTH, 1);

        return sdf.format(calendar.getTime());

    }
}
