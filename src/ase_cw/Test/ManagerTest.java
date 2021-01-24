package ase_cw.Test;

import ase_cw.Models.Category;
import ase_cw.Models.Manager;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test on the Manager class representing the manager of the system
 *
 * Date: 10/02/2016
 * Time: 6:31 PM
 *
 * @author Cécile Riquart
 * @version 1.0
 */
public class ManagerTest {
    private String underlinedString;
    private double bill1, bill2, bill3, bill4;
    private String string_int, string_int2;

    @Before
    public void setUp() {
        underlinedString = "====";
        bill1 = 10;
        bill2 = 25.9;
        bill3 = 57.1;
        bill4 = 70;
        string_int = "not_ant_int";
        string_int2 = "2";
    }

    @After
    public void tearDown() {
        System.out.println(" ############# The end ###########");
    }

    @Test
    public void testUnderlineString() {
        assertEquals("Test on underlineString() failed", underlinedString, Manager.underlineString("MENU"));
    }

    @Test
    public void testCalculateDiscount1() {
        assertEquals("Test on calculateDiscount() failed", 0, Manager.calculateDiscount(bill1));
    }

    @Test
    public void testCalculateDiscount2() {
        assertEquals("Test on calculateDiscount() failed", 5, Manager.calculateDiscount(bill2));
    }

    @Test
    public void testCalculateDiscount3() {
        assertEquals("Test on calculateDiscount() failed", 10, Manager.calculateDiscount(bill3));
    }

    @Test
    public void testCalculateDiscount4() {
        assertEquals("Test on calculateDiscount() failed", 20, Manager.calculateDiscount(bill4));
    }

    @Test
    public void testIsInteger1() {
        assertFalse("Test on isInteger() failed", Manager.isInteger(string_int));
    }

    @Test
    public void testIsInteger2() {
        assertTrue("Test on isInteger() failed", Manager.isInteger(string_int2));
    }
}
