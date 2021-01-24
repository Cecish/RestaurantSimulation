package ase_cw.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ase_cw.Models.FoodOrder;
import ase_cw.Models.InvalidQuantityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giannis
 */
public class FoodOrderTest {
    private FoodOrder o1;
    private FoodOrder o2;
    private FoodOrder o_test;
    
    public FoodOrderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            o1 = new FoodOrder(3, "Beef Enchiladas", 2);
            o2 = new FoodOrder(1, "Beef Enchiladas", 1);
            o_test = new FoodOrder(1, "Greek Lamb Gyros with Tzatziki Sauce", 10);
        } catch (InvalidQuantityException ex) {
            Logger.getLogger(FoodOrderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        System.out.println(" ############# The end ###########");
    }

    /**
     * Test of setDish method, of class FoodOrder.
     */
    @Test
    public void testSetDish() {
        o1.setDish("Beef Enchiladas");
        assertEquals("setDish failed", "Beef Enchiladas" , o1.getDishName());
    }

    /**
     * Test of setTableId method, of class FoodOrder.
     */
    @Test
    public void testSetTableId() {
        o1.setTableId(3);
        assertEquals("setTableID failed", 3 , o1.getTableId());
    }

    /**
     * Test of setQuantity method, of class FoodOrder.
     */
    @Test
    public void testSetQuantity() {
        o1.setQuantity(2);
        assertEquals("setQuantity failed", 2, o1.getQuantity());
    }

    /**
     * Test of getTableId method, of class FoodOrder.
     */
    @Test
    public void testGetTableId() {
        assertEquals("getTableID failed", 3, o1.getTableId());
    }

    /**
     * Test of getDishName method, of class FoodOrder.
     */
    @Test
    public void testGetDishName() {
        assertEquals("getDishName failed", "Beef Enchiladas", o1.getDishName());
    }

    /**
     * Test of getQuantity method, of class FoodOrder.
     */
    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity failed", 2, o1.getQuantity());
    }

    /**
     * Test of compareTo method, of class FoodOrder.
     */
    @Test
    public void testCompareToLess() {
        assertTrue(o1.compareTo(o_test) < 0);
    }
    
    @Test
    public void testCompareToGreater() {
        assertTrue(o_test.compareTo(o1) > 0);
    }
    
    @Test
    public void testCompareToEqual() {
        assertTrue(o1.compareTo(o2) == 0);
    }
    
    @Test 
    public void testInvalidQuantityException() throws InvalidQuantityException {
        try{
            FoodOrder oNeg = new FoodOrder(1, "negative", -1);
        }catch (InvalidQuantityException e) {
            assertTrue(e.getMessage().equals("The quantity in the order negative is invalid.\n"));
        }
        
    }
    
}
