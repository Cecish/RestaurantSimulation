/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw.Test;

import java.util.Set;
import java.util.TreeSet;

import ase_cw.Models.*;
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
 * @author omiranda93
 */
public class TestIOTest {
    
    private Menu menu,menu2;
    private MenuItem m1, m2, m3, m4,m5, m6, m7,m8, m9, m10,m11, m12, m13,m14, m15,
    m16,m17, m18, m19,m20, m21, m22,m23, m24, m25,m26, m27, m28,m29, m30,
    m31,m32, m33, m34,m35, m36, m37,m38, m39, m40;
    private CollectionFoodOrders orders,ordersCol;

    //Bug examples
    MenuItem mi9 = new MenuItem(Category.MAIN, "Way_Too_Long_Name_That_Wont_Fit_On_The_Bill_Blablablablablablabla", 13);
    MenuItem mi10 = new MenuItem(Category.DRINKS, "Thing", 7777777777.7777777);

    private TestIO instance;
    
    @Before
    public void setUp() {

        m1 = new MenuItem(Category.STARTER, "Smoked Salmon", 6.00);
        m2 = new MenuItem(Category.MAIN, "Chicken Balerno", 8.00);
        m3 = new MenuItem(Category.MAIN, "Roast Beef", 10.00);
        m40 = new MenuItem(Category.DESSERT, "Chocolate Cake", 4.50);
        m4 = new MenuItem(Category.DRINKS, "Coffee and Mints", 2.5);
        m5 = new MenuItem(Category.STARTER, "Carrot Soup", 3.5);
        m6 = new MenuItem(Category.DESSERT, "Pudding", 4.00);
        m7 = new MenuItem(Category.DRINKS, "Coke", 2.00);
        m8 = new MenuItem(Category.STARTER, "DIY British beef crostini", 5.00);
        m9 = new MenuItem(Category.STARTER, "Cheesy sprout fondue", 5.50);
        m10 = new MenuItem(Category.STARTER, "Lobster, green bean & radicchio salad", 5.50);
        m11 = new MenuItem(Category.STARTER, "Classic potted shrimps", 3.00);
        m12 = new MenuItem(Category.STARTER, "Watercress & celeriac soup with goat's cheese croutons", 6.00);
        m13 = new MenuItem(Category.STARTER, "Creamy cucumber with gravadlax", 3.50);
        m14 = new MenuItem(Category.STARTER, "Chicken liver & pineau pate", 7.00);
        m15 = new MenuItem(Category.STARTER, "Seared scallops with leeks & lemon chilli butter", 5.00);
        m16 = new MenuItem(Category.MAIN, "Beef Enchiladas", 9.00);
        m17 = new MenuItem(Category.MAIN, "Beer and Brown Sugar Kielbasa & Sauerkraut", 8.50);
        m18 = new MenuItem(Category.DRINKS, "Cider", 3.00);
        m19 = new MenuItem(Category.DRINKS, "Guinness", 4.00);
        m20 = new MenuItem(Category.DESSERT, "Dairy-free avocado chocolate mousse", 4.00);
        m21 = new MenuItem(Category.MAIN, "Coconut Shrimp with Mango Dipping Sauce", 7.00);
        m22 = new MenuItem(Category.MAIN, "General Tso’s Chicken", 11.00);
        m23 = new MenuItem(Category.DRINKS, "Dr Pepper", 2.00);
        m24 = new MenuItem(Category.DRINKS, "Beer", 2.50);
        m25 = new MenuItem(Category.DESSERT, "Chocolate & mint cheesecake", 3.50);
        m26 = new MenuItem(Category.MAIN, "Crab Cakes with Remoulade Sauce", 9.50);
        m27 = new MenuItem(Category.MAIN, "Frito Pie", 9.00);
        m28 = new MenuItem(Category.DRINKS, "Fanta lemon", 2.00);
        m29 = new MenuItem(Category.MAIN, "Favorite Meatloaf", 8.50);
        m30 = new MenuItem(Category.DESSERT, "Chocolate mousse", 2.5);
        m31 = new MenuItem(Category.DESSERT, "Choc a block", 3.00);
        m32 = new MenuItem(Category.MAIN, "Greek Lamb Gyros with Tzatziki Sauce", 13.00);
        m33 = new MenuItem(Category.DRINKS, "Fanta orange", 2.00);
        m34 = new MenuItem(Category.DESSERT, "Chocolate-Mint Bars", 5.00);
        m35 = new MenuItem(Category.DESSERT, "Oatmeal Pecan Pie", 4.00);
        m36 = new MenuItem(Category.DESSERT, "Chocolate banoffee pie", 5.00);
        m37 = new MenuItem(Category.DESSERT, "Sticky date pudding", 6.00);
        m38 = new MenuItem(Category.DRINKS, "Red wine", 3.00);
        m39 = new MenuItem(Category.MAIN, "Crab Cakes with Remoulade Sauce", 9.5);
        m40 = new MenuItem(Category.DESSERT, "Chocolate Cake", 4.50);

        instance = new TestIO();
        menu = new Menu();
        menu2 = new Menu();
        

        try {
            menu2.addValue(Category.STARTER, m1);
            menu2.addValue(Category.MAIN, m2);
            menu2.addValue(Category.MAIN, m3);
            menu2.addValue(Category.DRINKS, m4);
            menu2.addValue(Category.STARTER, m5);
            menu2.addValue(Category.DESSERT, m6);
            menu2.addValue(Category.DRINKS, m7);
            menu2.addValue(Category.STARTER, m8);
            menu2.addValue(Category.STARTER, m9);
            menu2.addValue(Category.STARTER, m10);
            menu2.addValue(Category.STARTER, m11);
            menu2.addValue(Category.STARTER, m12);
            menu2.addValue(Category.STARTER, m13);
            menu2.addValue(Category.STARTER, m14);
            menu2.addValue(Category.STARTER, m15);
            menu2.addValue(Category.MAIN, m16);
            menu2.addValue(Category.MAIN, m17);
            menu2.addValue(Category.DRINKS, m18);
            menu2.addValue(Category.DRINKS, m19);
            menu2.addValue(Category.DESSERT, m20);
            menu2.addValue(Category.MAIN, m21);
            menu2.addValue(Category.MAIN, m22);
            menu2.addValue(Category.DRINKS, m23);
            menu2.addValue(Category.DRINKS, m24);
            menu2.addValue(Category.DESSERT, m25);
            menu2.addValue(Category.MAIN, m26);
            menu2.addValue(Category.MAIN, m27);
            menu2.addValue(Category.DRINKS, m28);
            menu2.addValue(Category.MAIN, m29);
            menu2.addValue(Category.DESSERT, m30);
            menu2.addValue(Category.DESSERT, m31);
            menu2.addValue(Category.MAIN, m32);
            menu2.addValue(Category.DRINKS, m33);
            menu2.addValue(Category.DESSERT, m34);
            menu2.addValue(Category.DESSERT, m35);
            menu2.addValue(Category.DESSERT, m36);
            menu2.addValue(Category.DESSERT, m37);
            menu2.addValue(Category.DRINKS, m38);
            menu2.addValue(Category.MAIN, m39);
            menu2.addValue(Category.DESSERT, m40);
        } catch (WrongCategoryException ex) {
            Logger.getLogger(TestIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        orders = new CollectionFoodOrders(menu2);
        ordersCol = new CollectionFoodOrders(menu2);
        try {
            FoodOrder o1 = new FoodOrder(3, "Roast Beef", 2);
            FoodOrder o2 = new FoodOrder(1, "Smoked Salmon", 1);
            FoodOrder o3 = new FoodOrder(1, "Carrot Soup", 1);
            FoodOrder o4 = new FoodOrder(1, "Roast Beef", 2);
            FoodOrder o5 = new FoodOrder(3, "Chocolate Cake", 2);
            FoodOrder o6 = new FoodOrder(2, "Carrot Soup", 2);
            FoodOrder o7 = new FoodOrder(2, "Roast Beef", 2);
            FoodOrder o8 = new FoodOrder(3, "Coffee and Mints", 2);
            FoodOrder o9 = new FoodOrder(4, "Coconut Shrimp with Mango Dipping Sauce", 1);
            FoodOrder o10 = new FoodOrder(4, "Frito Pie", 2);
            FoodOrder o11 = new FoodOrder(5, "DIY British beef crostini", 3);
            FoodOrder o12 = new FoodOrder(5, "Guinness", 3);
            FoodOrder o13 = new FoodOrder(5, "Greek Lamb Gyros with Tzatziki Sauce", 1);
            FoodOrder o14 = new FoodOrder(6, "Chocolate & mint cheesecake", 2);
            FoodOrder o15 = new FoodOrder(6, "Coconut Shrimp with Mango Dipping Sauce", 1);
            FoodOrder o16 = new FoodOrder(6, "Beef Enchiladas", 1);
            FoodOrder o17 = new FoodOrder(7, "Dr Pepper", 4);
            FoodOrder o18 = new FoodOrder(7, "Beer and Brown Sugar Kielbasa & Sauerkraut", 4);
            FoodOrder o19 = new FoodOrder(8, "Coconut Shrimp with Mango Dipping Sauce", 2);
            FoodOrder o20 = new FoodOrder(8, "Guinness", 2);
            FoodOrder o21 = new FoodOrder(8, "Roast Beef", 2);
            FoodOrder o22 = new FoodOrder(9, "DIY British beef crostini", 1);
            FoodOrder o23 = new FoodOrder(9, "Crab Cakes with Remoulade Sauce", 1);
            FoodOrder o24 = new FoodOrder(9, "Chocolate mousse", 1);
            FoodOrder o25 = new FoodOrder(9, "Red wine", 1);
            FoodOrder o26 = new FoodOrder(10, "Beef Enchiladas", 5);
            FoodOrder o27 = new FoodOrder(10, "Fanta lemon", 1);
            FoodOrder o28 = new FoodOrder(10, "Dr Pepper", 1);
            FoodOrder o29 = new FoodOrder(10, "Beer", 3);
            FoodOrder o30 = new FoodOrder(11, "Dairy-free avocado chocolate mousse", 2);
            FoodOrder o31 = new FoodOrder(11, "Creamy cucumber with gravadlax", 1);
            FoodOrder o32 = new FoodOrder(11, "Watercress & celeriac soup with goat's cheese croutons", 1);
            FoodOrder o33 = new FoodOrder(11, "Guinness", 2);
            TreeSet <FoodOrder> table12 = new TreeSet<FoodOrder>();
            TreeSet <FoodOrder> table13 = new TreeSet<FoodOrder>();
            TreeSet <FoodOrder> table14 = new TreeSet<FoodOrder>();
            TreeSet <FoodOrder> table15 = new TreeSet<FoodOrder>();
            
            
            ordersCol.addValue(o1.getTableId(), o1);
            ordersCol.addValue(o2.getTableId(), o2);
            ordersCol.addValue(o3.getTableId(), o3);
            ordersCol.addValue(o4.getTableId(), o4);
            ordersCol.addValue(o5.getTableId(), o5);
            ordersCol.addValue(o6.getTableId(), o6);
            ordersCol.addValue(o7.getTableId(), o7);
            ordersCol.addValue(o8.getTableId(), o8);
            ordersCol.addValue(o9.getTableId(), o9);
            ordersCol.addValue(o10.getTableId(), o10);
            ordersCol.addValue(o11.getTableId(), o11);
            ordersCol.addValue(o12.getTableId(), o12);
            ordersCol.addValue(o13.getTableId(), o13);
            ordersCol.addValue(o14.getTableId(), o14);
            ordersCol.addValue(o15.getTableId(), o15);
            ordersCol.addValue(o16.getTableId(), o16);
            ordersCol.addValue(o17.getTableId(), o17);
            ordersCol.addValue(o18.getTableId(), o18);
            ordersCol.addValue(o19.getTableId(), o19);
            ordersCol.addValue(o20.getTableId(), o20);
            ordersCol.addValue(o21.getTableId(), o21);
            ordersCol.addValue(o22.getTableId(), o22);
            ordersCol.addValue(o23.getTableId(), o23);
            ordersCol.addValue(o24.getTableId(), o24);
            ordersCol.addValue(o25.getTableId(), o25);
            ordersCol.addValue(o26.getTableId(), o26);
            ordersCol.addValue(o27.getTableId(), o27);
            ordersCol.addValue(o28.getTableId(), o28);
            ordersCol.addValue(o29.getTableId(), o29);
            ordersCol.addValue(o30.getTableId(), o30);
            ordersCol.addValue(o31.getTableId(), o31);
            ordersCol.addValue(o32.getTableId(), o32);
            ordersCol.addValue(o33.getTableId(), o33);
            ordersCol.addValues(15, table15);
            ordersCol.addValues(14, table14);
            ordersCol.addValues(13, table13);
            ordersCol.addValues(12, table12);
        
        } catch (InvalidQuantityException ex) {
            Logger.getLogger(TestIOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

    }
    
    @After
    public void tearDown() {
        System.out.println(" ############# The end ###########");
    }

    /**
     * Test of readMenu method, of class TestIO.
     */
    @Test
    public void testReadMenu() {
        System.out.println("readMenu");
        //TestIO instance = new TestIO();
        instance.readMenu(menu);
        assertEquals(menu2.toString(), menu.toString());
    }

    /**
     * Test of readOrders method, of class TestIO.
     */
    @Test
    public void testReadOrders() {
        System.out.println("readOrders");
        //TestIO instance = new TestIO();
        try {
            instance.readOrders(orders);
        } catch (NoMatchingIDException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(ordersCol.toString(), orders.toString());
    }
    
}

