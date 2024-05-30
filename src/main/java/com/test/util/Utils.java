package com.test.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static Date calculateDueDate(Date date, Integer dayCount) {
        LocalDate begin = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = begin.plusDays(dayCount);
        return Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static int holidayCount(Date date, Integer dayCount) {
        int holidayCount = 0;
        Calendar begin = Calendar.getInstance();
        begin.setTime(date);

        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.add(Calendar.DATE, dayCount);

        List<Integer> years = new ArrayList<>();
        int beginYear = begin.get(Calendar.YEAR);
        int endYear = end.get(Calendar.YEAR);
        years.add(beginYear);
        while(beginYear < endYear) {
            beginYear++;
            years.add(beginYear);
        }
                //TODO abstract this list to the data layer
        //independence day
        for(Calendar c: calculateJuly4thObserved(years)){
            if(c.compareTo(end) <= 0  && c.after(begin)) {
                holidayCount++;
            }
        }
        //Labor day
        for(Calendar c: calculateLaborDay(years)){
            if(c.compareTo(end) <= 0 && c.after(begin)) {
                holidayCount++;
            }
        }

        return holidayCount;
    }

    public static List<Calendar> calculateJuly4thObserved(List<Integer> years) {
        List<Calendar> july4ths = new ArrayList<>();
        for(Integer year : years) {
            Calendar day = Calendar.getInstance();
            day.set(Calendar.DATE, 4);
            day.set(Calendar.MONTH, Calendar.JULY);
            day.set(Calendar.YEAR, year);
            clearTime(day);
            if (day.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                day.add(Calendar.DATE, -1);
            } else if (day.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                day.add(Calendar.DATE, 1);
            }
            july4ths.add( day );
        }
        return july4ths;
    }

    public static List<Calendar> calculateLaborDay(List<Integer> years) {
        List<Calendar> labordays = new ArrayList<>();
        for(Integer year : years) {
            Calendar day = Calendar.getInstance();
            day.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            day.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            day.set(Calendar.MONTH, Calendar.SEPTEMBER);
            day.set(Calendar.YEAR, year);
            clearTime(day);
            labordays.add(day);
        }
        return labordays;
    }

    public static long numberOfWeekendDays(Date date, Integer dayCount) {
        LocalDate begin = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = begin.plusDays(dayCount);

        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        final long weekendDaysBetween = begin.datesUntil(end)
                .filter(d -> weekend.contains(d.getDayOfWeek()))
                .count();
        return weekendDaysBetween;
    }

    public static long numberOfWeekDays(Date date, Integer dayCount) {
        LocalDate begin = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = begin.plusDays(dayCount);

        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        final long weekendBetween = begin.datesUntil(end)
                .filter(d -> !weekend.contains(d.getDayOfWeek()))
                .count();
        return weekendBetween;
    }

    public static Calendar clearTime(Calendar c) {
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR, 0);
        return c;
    }

}
