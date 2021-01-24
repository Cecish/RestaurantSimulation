package ase_cw.Test;

import ase_cw.Models.Category;
import ase_cw.Models.Manager;
import ase_cw.Models.MenuItem;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests methods from the class representing a food item figuring on the menu of the restaurant
 *
 * Date: 09/02/2016
 * Time: 8:16 PM
 *
 * @author Cécile Riquart
 * @version 1.0
 */
public class MenuItemTest {

    private MenuItem menuItem;
    private MenuItem otherMenuItem1;
    private MenuItem otherMenuItem2;
    private MenuItem otherMenuItem3;
    private MenuItem menuItemSetter;
    private String new_dishname;
    private double new_price;
    private String dishnameToString1;
    private MenuItem longMenuItem;
    private String dishnameToString2;
    private String res;
    private MenuItem longMenuItem2;
    private String dishnameToString3;

    @Before
    public void setUp() {
        menuItem = new MenuItem(Category.DESSERT, "Crème Brûlée", 4.50);
        otherMenuItem1 = new MenuItem(Category.MAIN, "Crème Brûlée", 12.7);
        otherMenuItem2 = new MenuItem(Category.MAIN, "Boeuf bourguignon", 1.2);
        otherMenuItem3 = new MenuItem(Category.MAIN, "Quiche lorraine", 4);
        menuItemSetter = new MenuItem(Category.MAIN, "Toast", 1.1);
        new_dishname = "French tartine";
        new_price = 1;
        res = "%-" + Manager.ALINEA_DISHNAME + "s %-" +
                Manager.DISHNAME_TEXT + "s %-"   +
                Manager.PRICE + "s %-"           +
                Manager.CURRENCY_SIZE +"s";

        dishnameToString1 = String.format(res, "", menuItem.getName().toUpperCase(), menuItem.getPrice(), Manager.CURRENCY) + "\n";

        longMenuItem = new MenuItem(Category.DESSERT, "azertyuiopqsdfghjklmwxcvbnazertyuiopqsdfgazertyuiopqsdfghjklm", 12);

        dishnameToString2 = String.format(res, "", longMenuItem.getName().substring(0,
                Manager.DISHNAME_TEXT-1).toUpperCase(), "", "") + "\n"
            + String.format(res, "", longMenuItem.getName().substring(Manager.DISHNAME_TEXT-1,
                longMenuItem.getName().length()).toUpperCase(), longMenuItem.getPrice(), Manager.CURRENCY) + "\n";

        longMenuItem2 = new MenuItem(Category.DESSERT, "azertyuiopqsdfghjklmwcvbnazertyuiopqsdfghjklmwxcvbnazertyuiopp" +
                "qsdfghjklmwxcvbnazertyuiopqsdfghjklmwxcvbnazert", 13.7);

        dishnameToString3 = String.format(res, "", longMenuItem2.getName().substring(0, Manager.DISHNAME_TEXT-1).toUpperCase(), "", "") + "\n" +
            String.format(res, "", longMenuItem2.getName().substring(Manager.DISHNAME_TEXT-1, 2*(Manager.DISHNAME_TEXT-1)).toUpperCase(), "", "") +"\n" +
            String.format(res, "", longMenuItem2.getName().substring(2*(Manager.DISHNAME_TEXT-1), longMenuItem2.getName().length()).toUpperCase(), longMenuItem2.getPrice(), Manager.CURRENCY) +"\n";
    }

    @After
    public void tearDown() {
        System.out.println(" ############# End MenuItem Testing ###########");
    }

    @Test
    public void testConstructor() {
        assertTrue("Test on constructor() failed", ((menuItem.getName().equals("Crème Brûlée") &&
                (menuItem.getPrice() == 4.50)) && (menuItem.getCategory().equals(Category.DESSERT))));
    }

    @Test
    public void testGetCategory() {
        assertEquals("Test on getName() failed", Category.DESSERT, menuItem.getCategory());
    }

    @Test
    public void testGetName() {
        assertEquals("Test on getCategory() failed", "Crème Brûlée", menuItem.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals("Test on getPrice() failed", 4.50, menuItem.getPrice(), 0.0);
    }

    @Test
    public void testSetCategory() {
        menuItemSetter.setCategory(Category.STARTER);
        assertEquals("Test on setCategory() failed", Category.STARTER, menuItemSetter.getCategory());
    }

    @Test
    public void testSetName() {
        menuItemSetter.setName(new_dishname);
        assertEquals("Test on setName() failed", new_dishname, menuItemSetter.getName());
    }

    @Test
    public void testSetPrice() {
        menuItemSetter.setPrice(new_price);
        assertEquals("Test on setPrice() failed", new_price, menuItemSetter.getPrice(), 0.0);
    }

    @Test
    public void testEqual() {
        int result = menuItem.compareTo(otherMenuItem1);
        assertTrue("Is expected to be equal", result == 0);
    }

    @Test
    public void testGreaterThan() {
        int result = menuItem.compareTo(otherMenuItem2);
        assertTrue("Is expected to be greater than", result >= 1);
    }

    @Test
    public void testLessThan() {
        int result = menuItem.compareTo(otherMenuItem3);
        assertTrue("Is expected to be less than", result <= -1);
    }

    @Test
    public void testToString1() {
        assertEquals("Test on toString() with a short dish name failed", dishnameToString1, menuItem.toString());
    }

    @Test
    public void testToString2() {
        assertEquals("Test on toString() with a rather long a dish name failed", dishnameToString2, longMenuItem.toString());
    }

    @Test
    public void testToString3() {
        assertEquals("Test on toString() with a long dish name failed", dishnameToString3, longMenuItem2.toString());
    }

}
