package dateAndTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {

    public static String currentDateAndTime;

    public static String getDateTime() {
        DateFormat currDateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");   //   2020/03/24 16:59:41
        Date date = new Date();
        String cdate = currDateTimeFormat.format(date);
        currentDateAndTime = cdate.replaceAll("/" + "|:" + "| ", "-");
        return currentDateAndTime;


    }
}
