package ja.LocalDateDemo;

import org.junit.Test;

import java.time.*;

public class LocalDateDemo {

    @Test
    public void newLocalDate(){
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = LocalDate.now();
        date = LocalDate.parse("2014-03-18");
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println(date);
    }

    @Test
    public void newLocalTime(){
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        time = LocalTime.parse("13:45:20");
        System.out.println(time);
    }

    @Test
    public void newLocalDateTime(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
    }

    @Test
    public void newDuration(){
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration duration = threeMinutes.withSeconds(5);
        System.out.println();
    }

    @Test
    public void newPeriod(){
        Period period = Period.ofDays(12);
        Period duration = period.plusDays(5);
        System.out.println();
    }
}
