package com.test;

import com.test.util.Utils;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {

    List<Integer> years = new ArrayList<>(){{
        add(2024);
        add(2025);
        add(2026);
        add(2027);
    }};


    @Test
    public void testLaborDays(){
        List<Calendar> laborDays = Utils.calculateLaborDay(years);
        List<Calendar> knownLaborDays = new ArrayList<>();

        Calendar l1 = Calendar.getInstance();
        l1.set(Calendar.YEAR, 2024);
        l1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        l1.set(Calendar.DAY_OF_MONTH, 2);
        Utils.clearTime(l1);
        knownLaborDays.add(l1);

        Calendar l2 = Calendar.getInstance();
        l2.set(Calendar.YEAR, 2025);
        l2.set(Calendar.MONTH, Calendar.SEPTEMBER);
        l2.set(Calendar.DAY_OF_MONTH, 1);
        Utils.clearTime(l2);
        knownLaborDays.add(l2);

        Calendar l3 = Calendar.getInstance();
        l3.set(Calendar.YEAR, 2026);
        l3.set(Calendar.MONTH, Calendar.SEPTEMBER);
        l3.set(Calendar.DAY_OF_MONTH, 7);
        Utils.clearTime(l3);
        knownLaborDays.add(l3);

        Calendar l4 = Calendar.getInstance();
        l4.set(Calendar.YEAR, 2027);
        l4.set(Calendar.MONTH, Calendar.SEPTEMBER);
        l4.set(Calendar.DAY_OF_MONTH, 6);
        Utils.clearTime(l4);
        knownLaborDays.add(l4);

        assertTrue(CollectionUtils.isEqualCollection(laborDays, knownLaborDays));
    }

    @Test
    public void testJulyDays(){
        List<Calendar> days = Utils.calculateJuly4thObserved(years);
        List<Calendar> knownDays = new ArrayList<>();

        Calendar d1 = Calendar.getInstance();
        d1.set(Calendar.YEAR, 2024);
        d1.set(Calendar.MONTH, Calendar.JULY);
        d1.set(Calendar.DATE, 4);
        Utils.clearTime(d1);
        knownDays.add(d1);

        Calendar d2 = Calendar.getInstance();
        d2.set(Calendar.YEAR, 2025);
        d2.set(Calendar.MONTH, Calendar.JULY);
        d2.set(Calendar.DAY_OF_MONTH, 4);
        Utils.clearTime(d2);
        knownDays.add(d2);

        Calendar d3 = Calendar.getInstance();
        d3.set(Calendar.YEAR, 2026);
        d3.set(Calendar.MONTH, Calendar.JULY);
        d3.set(Calendar.DAY_OF_MONTH, 3);
        Utils.clearTime(d3);
        knownDays.add(d3);

        Calendar d4 = Calendar.getInstance();
        d4.set(Calendar.YEAR, 2027);
        d4.set(Calendar.MONTH, Calendar.JULY);
        d4.set(Calendar.DAY_OF_MONTH, 5);
        Utils.clearTime(d4);
        knownDays.add(d4);

        assertTrue(CollectionUtils.isEqualCollection(days, knownDays));
    }

    @Test
    public void testHolidays(){
        List<Calendar> days = Utils.calculateJuly4thObserved(years);
        List<Calendar> knownDays = new ArrayList<>();

        Calendar d1 = Calendar.getInstance();
        d1.set(Calendar.YEAR, 2024);
        d1.set(Calendar.MONTH, Calendar.JULY);
        d1.set(Calendar.DATE, 4);
        Utils.clearTime(d1);
        knownDays.add(d1);

        Calendar d2 = Calendar.getInstance();
        d2.set(Calendar.YEAR, 2025);
        d2.set(Calendar.MONTH, Calendar.JULY);
        d2.set(Calendar.DAY_OF_MONTH, 4);
        Utils.clearTime(d2);
        knownDays.add(d2);

        Calendar d3 = Calendar.getInstance();
        d3.set(Calendar.YEAR, 2026);
        d3.set(Calendar.MONTH, Calendar.JULY);
        d3.set(Calendar.DAY_OF_MONTH, 3);
        Utils.clearTime(d3);
        knownDays.add(d3);

        Calendar d4 = Calendar.getInstance();
        d4.set(Calendar.YEAR, 2027);
        d4.set(Calendar.MONTH, Calendar.JULY);
        d4.set(Calendar.DAY_OF_MONTH, 5);
        Utils.clearTime(d4);
        knownDays.add(d4);

        days.addAll(Utils.calculateLaborDay(years));


        Calendar l1 = Calendar.getInstance();
        l1.set(Calendar.YEAR, 2024);
        l1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        l1.set(Calendar.DAY_OF_MONTH, 2);
        Utils.clearTime(l1);
        knownDays.add(l1);

        Calendar l2 = Calendar.getInstance();
        l2.set(Calendar.YEAR, 2025);
        l2.set(Calendar.MONTH, Calendar.SEPTEMBER);
        l2.set(Calendar.DAY_OF_MONTH, 1);
        Utils.clearTime(l2);
        knownDays.add(l2);

        Calendar l3 = Calendar.getInstance();
        l3.set(Calendar.YEAR, 2026);
        l3.set(Calendar.MONTH, Calendar.SEPTEMBER);
        l3.set(Calendar.DAY_OF_MONTH, 7);
        Utils.clearTime(l3);
        knownDays.add(l3);

        Calendar l4 = Calendar.getInstance();
        l4.set(Calendar.YEAR, 2027);
        l4.set(Calendar.MONTH, Calendar.SEPTEMBER);
        l4.set(Calendar.DAY_OF_MONTH, 6);
        Utils.clearTime(l4);
        knownDays.add(l4);

        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.YEAR, 2024);
        begin.set(Calendar.MONTH, Calendar.MAY);
        begin.set(Calendar.DAY_OF_MONTH, 30);
        Utils.clearTime(begin);

        int count = Utils.holidayCount(begin.getTime(), 365*4);
        assertTrue(count == knownDays.size());
    }

    @Test
    public void testWeekDays(){
        LocalDate localDate = LocalDate.parse("2024-05-30");
        long count = Utils.numberOfWeekDays(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), 16);
        assertTrue(count == 12L);
    }

    @Test
    public void testWeekendDays(){
        LocalDate localDate = LocalDate.parse("2024-05-30");
        long count = Utils.numberOfWeekendDays(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), 16);
        assertTrue(count == 4L);
    }


}
