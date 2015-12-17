package Sandbox.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jonathanevans on 02/12/2015.
 */
public class DateTimeStuff {
    public static void main(String[] args) throws IllegalAccessException {
        java.util.Date lastExecutionStarted = new java.util.Date();

        Date date = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.HOUR, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        boolean bob = isItSafeToExecute(simpleDateFormat.format(calender.getTime()),
                lastExecutionStarted);
    }



    public static boolean isItSafeToExecute(final String timeLimit, final Date dateExecutionStarted) {
        Calendar nowCalender = Calendar.getInstance();
        nowCalender.setTime(new Date());

        Calendar timeLimitCalender = Calendar.getInstance();
        timeLimitCalender.setTime(dateExecutionStarted);

        String[] time = timeLimit.split(":");

        timeLimitCalender.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        timeLimitCalender.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        timeLimitCalender.set(Calendar.SECOND, Integer.parseInt(time[2]));

        Date tl = timeLimitCalender.getTime();
        Date no = nowCalender.getTime();

        System.out.println(tl.toString());
        System.out.println(no.toString());

        return timeLimitCalender.after(nowCalender);
    }
}
