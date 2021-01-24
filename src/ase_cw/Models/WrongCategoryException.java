package ase_cw.Models;

/**
 * Exception class. Deals with the manipulation of wrong categories with menus & menu items
 *
 * Date: 10/02/2016
 * Time: 8:18 PM
 *
 * @author Cécile Riquart
 * @version 1.0
 */
public class WrongCategoryException extends Exception {

    public WrongCategoryException(Category wrongCategory, Category expectedCategory){
        super("Wrong category specified when adding a menu item to the menu. Expected: "+ expectedCategory +" but " +
                "found:  "+wrongCategory+" \n");
    }
}
