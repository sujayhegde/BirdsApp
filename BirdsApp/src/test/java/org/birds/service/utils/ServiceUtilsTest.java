package org.birds.service.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/*
    Test class  for Service Utils
 */
public class ServiceUtilsTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isListUniqueEmptyList() throws Exception {
        List<String> src = new ArrayList<String>();
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertTrue(isUnique);

    }

    @Test
    public void isListUniqueNullList() throws Exception {
        List<String> src = null;
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertTrue(isUnique);

    }

    @Test
    public void isListUniqueListSingleEmlem() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Europe");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertTrue(isUnique);

    }

    @Test
    public void isListUniqueListTwoEmlem() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Asia");
        src.add("North America");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertTrue(isUnique);

    }

    @Test
    public void isListUniqueListTwoElemSim() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Asia");
        src.add("Europe");
        src.add("Asia");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertFalse(isUnique);

    }

    @Test
    public void isListUniqueListTwoElemSimLow() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Asia");
        src.add("asia");
        src.add("Africa");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertFalse(isUnique);

    }

    @Test
    public void isListUniqueListThreeElemUni() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Asia");
        src.add("Europe");
        src.add("Africa");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertTrue(isUnique);

    }

    @Test
    public void isListUniqueListWhiteSpaceDup() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Asia");
        src.add(" Asia ");
        src.add("Africa");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertFalse(isUnique);

    }

    @Test
    public void isListUniqueListThreeSimDup() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Asia");
        src.add("Asia");
        src.add("Asia");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertFalse(isUnique);

    }

    @Test
    public void isListUniqueListThreeDiffDup() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Asia");
        src.add("    Asia");
        src.add("Asia    ");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertFalse(isUnique);

    }

    @Test
    public void isListUniqueListThreeDiffDup2() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("Asia");
        src.add("    Asia");
        src.add("    asia     ");
        boolean isUnique = ServiceUtils.isListUnique(src);
        assertFalse(isUnique);

    }

    @Test
    public void isListEmpty1() throws Exception {
        List<String> src = new ArrayList<String>();
        boolean isEmpty = ServiceUtils.isListEmpty(src);
        assertTrue(isEmpty);

    }

    @Test
    public void isListEmpt21() throws Exception {
        List<String> src = null;
        boolean isEmpty = ServiceUtils.isListEmpty(src);
        assertTrue(isEmpty);

    }

    @Test
    public void isListEmptOneElem() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("one");
        boolean isEmpty = ServiceUtils.isListEmpty(src);
        assertFalse(isEmpty);

    }

    @Test
    public void isListEmptTwoElem() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("one");
        src.add("two");
        boolean isEmpty = ServiceUtils.isListEmpty(src);
        assertFalse(isEmpty);

    }

    @Test
    public void isListEmptThreeElem() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("one");
        src.add("two");
        src.add("three");
        boolean isEmpty = ServiceUtils.isListEmpty(src);
        assertFalse(isEmpty);

    }

    @Test
    public void isListEmptAllBlank() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("");
        src.add("");
        src.add("");
        boolean isEmpty = ServiceUtils.isListEmpty(src);
        assertTrue(isEmpty);

    }

    @Test
    public void isListEmptAllBlankWhitSpc() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("   ");
        src.add(" ");
        src.add("        ");
        boolean isEmpty = ServiceUtils.isListEmpty(src);
        assertTrue(isEmpty);

    }


    @Test
    public void isListEmptAllBlankWhitSpcWithEllem() throws Exception {
        List<String> src = new ArrayList<String>();
        src.add("   ");
        src.add(" ");
        src.add("        ");
        src.add("    asia ");
        boolean isEmpty = ServiceUtils.isListEmpty(src);
        assertFalse(isEmpty);

    }

}