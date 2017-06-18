package org.birds.service.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by 30198676 on 18-06-2017.
 */
public class DaoUtilsTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPojo() throws Exception {
    }



    @Test
    public void isDateProperCorrectDateFormat() throws Exception {
        String date = "2017-06-17";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertTrue(isProper);
    }

    @Test
    public void isDateProperCorrectDateFormat2() throws Exception {
        String date = "2017-11-12";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertTrue(isProper);
    }

    @Test
    public void isDateProperCorrectDateFormat3() throws Exception {
        String date = "4000-12-12";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertTrue(isProper);
    }

    @Test
    public void isDateProperInCorrectDateFormat1() throws Exception {
        String date = "40000-12-12";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }

    @Test
    public void isDateProperCorrectDateFormat5() throws Exception {
        String date = "400-12-12";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }

    @Test
    public void isDateProperCorrectDateFormat6() throws Exception {
        String date = "2017-12-12";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertTrue(isProper);
    }

    @Test
    public void isDateProperCorrectDateFormat7() throws Exception {
        String date = "2017-1-12";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertTrue(isProper);
    }

    @Test
    public void isDateProperInCorrectDateFormatMonthOverflow() throws Exception {
        String date = "2017-13-12";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }

    @Test
    public void isDateProperInCorrectDateFormatMonthunderflow() throws Exception {
        String date = "2017-13-12";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }



    @Test
    public void isDateProperInCorrectDateFormatDayOverflow() throws Exception {
        String date = "2017-3-112";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }

    @Test
    public void isDateProperInCorrectDateFormatDayUnderflow() throws Exception {
        String date = "2017-3-0";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }

    @Test
    public void isDateProperInCorrectDateFormatDayMonthUnderflow() throws Exception {
        String date = "2017-0-0";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }


    @Test
    public void isDatePropeInrCorrectDateFormatYYMmDD() throws Exception {
        String date = "17-10-10";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }


    @Test
    public void isDateProperinCorrectDateFormatDDMMYYYY() throws Exception {
        String date = "10-10-17";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }

    @Test
    public void isDateProperinCorrectDateFormatWrongDate() throws Exception {
        String date = "2017-10-10-17";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }

    @Test
    public void isDateProperinCorrectDateFormatWrongDelimiters() throws Exception {
        String date = "2017:10:10";
        String format = "yyyy-MM-dd";
        boolean isProper = DaoUtils.isDateProper(date, format);
        assertFalse(isProper);
    }


}