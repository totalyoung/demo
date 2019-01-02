package ja.InternationalizationDemo;

import org.junit.jupiter.api.Test;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by totalyoung on 2018/11/10.
 */
public class LocaleDemo {

    public static void main(String[] args) {

    }

    public void formatDateWithLocale(){
        Locale l = Locale.FRANCE;
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.LONG, l);
        System.out.println(dateInstance.format(new Date()));
    }

    @Test
    public void formatDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd-HH-mm-ss");
        System.out.println(simpleDateFormat.format(new Date()));
        Date parse = simpleDateFormat.parse("2018:11:10-23-35-25");
        System.out.println(parse);
    }

    /**
     *
     */
    @Test
    public void printFormat(){
        System.out.printf("test %d",3);
    }

    @Test
    public void stringFormat(){
        String.format("",1);
    }

    @Test
    public void messageFormat(){
        System.out.println(MessageFormat.format("{1},{0}",1,2));
        System.out.println(MessageFormat.format("{1,date,yyyy:MM:dd-HH-mm-ss},{0}",1,new Date()));
        System.out.println(MessageFormat.format("{1,date,long},{0}",1,new Date()));
        System.out.println(MessageFormat.format("{1,date,long},{0,number,currency}",1,new Date()));
    }

    @Test
    public void getBundle(){
        ResourceBundle bundle = ResourceBundle.getBundle("ja/InternationalizationDemo/resource", Locale.SIMPLIFIED_CHINESE);
        System.out.println(bundle.getString("aa.test"));
    }

}
