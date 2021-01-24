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
public class InvalidQuantityException extends Exception {

    public InvalidQuantityException(String order){
        super("The quantity in the order " + order + " is invalid.\n");
    }
}
