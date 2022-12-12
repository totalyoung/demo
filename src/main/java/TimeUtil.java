import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2019/8/7.
 */
public class TimeUtil {

    public static long getTime(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return parse.getTime();
    }

    public static long getDayOfStartTime(String date)  {
        return getTime(date+" 00:00:00");
    }

    public static long getDayOfEndTime(String date)  {
        return getTime(date+" 23:59:59");
    }

}
