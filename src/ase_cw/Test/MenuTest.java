package ase_cw.Test;

import ase_cw.Models.*;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests methods from the class representing a menu composed of food items proposed by the restaurant
 *
 * Date: 10/02/2016
 * Time: 2:38 PM
 *
 * @author Cécile Riquart
 * @version 1.0
 */
public class MenuTest {

    private Menu menu;
    private MenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
    private Set<Category> setKeys;
    private Set<MenuItem> menuItems1, menuItems2;
    private Map<String, Double> mapValues;
    private String stringMenu;

    @Before
    public void setUp() {
        menuItem1 = new MenuItem(Category.STARTER, "Toast", 1.8);
        menuItem2 = new MenuItem(Category.MAIN, "Roast Beef", 8.8);
        menuItem3 = new MenuItem(Category.DESSERT, "Ice cream", 3.50);
        menuItem4 = new MenuItem(Category.DRINKS, "Ricard", 10);
        menuItem5 = new MenuItem(Category.DRINKS, "Coca-Cola", 0.8);

        menu = new Menu();

        setKeys = new HashSet<Category>() {{
            add(Category.DESSERT); add(Category.MAIN); add(Category.STARTER); add(Category.DRINKS);
        }};

        menuItems1 = new TreeSet<MenuItem>() {{
            add(menuItem4); add(menuItem5);
        }};

        menuItems2 = new TreeSet<MenuItem>() {{
            add(menuItem2);
        }};

        mapValues = new TreeMap<>();
        mapValues.put("Roast Beef", 8.8);
        mapValues.put("Ricard", 10.0);
        mapValues.put("Coca-Cola", 0.80);

        stringMenu = "\n" + Manager.MENU + "\n" + Manager.underlineString(Manager.MENU) + "\n\n" + "STARTER\n\nMAIN\n" +
                "\nDESSERT\n\nDRINKS\n" + menuItem5.toString() + menuItem4.toString();
    }

    @After
    public void tearDown() {
        System.out.println(" ############# The end ###########");
    }

    @Test
    public void testGetKeys() {
        assertEquals("Test on getKeys() failed", setKeys, menu.getKeys());
    }

    @Test
    public void testGetValue() {
        try {
            menu.addValues(Category.DRINKS, new TreeSet<MenuItem>() {{add(menuItem4); add(menuItem5);}});
        } catch (WrongCategoryException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("Test on getValue() failed", menuItems1, menu.getValue(Category.DRINKS));
    }

    @Test
    public void testAddValues() throws WrongCategoryException {
        menu.addValues(Category.MAIN, menuItems2);
        assertEquals("Test on addValues() failed", menuItems2, menu.getValue(Category.MAIN));
    }

    @Test(expected = WrongCategoryException.class)
    public void testAddValues2() throws WrongCategoryException {
        menuItems2.add(menuItem3);
        menu.addValues(Category.MAIN, menuItems2);
    }

    @Test
    public void testGetValues() {
        try {
            menu.addValues(Category.DRINKS, menuItems1);
            menu.addValues(Category.MAIN, menuItems2);
        } catch (WrongCategoryException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("Test on getValues() failed", mapValues, menu.getValues());
    }

    @Test
    public void testAddValue() throws WrongCategoryException {
        menu.addValue(Category.STARTER, menuItem1);
        assertTrue("Test on addValue() failed", menu.getValue(Category.STARTER).contains(menuItem1));
    }

    @Test(expected = WrongCategoryException.class)
    public void testAddValue2() throws WrongCategoryException {
        menu.addValue(Category.MAIN, menuItem3);
    }

    @Test
    public void testToString() {
        try {
            menu.addValues(Category.DRINKS, menuItems1);
        } catch (WrongCategoryException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(stringMenu, menu.toString());
    }

}
