package ase_cw.Models;

/**
 * Enumeration of the menu item's categories that can be used
 * NB: The restaurant wishing to enhance this category list can add/remove categories here
 *
 * Date: 31/01/2016
 * Time: 1:50 PM
 *
 * @author Cécile Riquart
 * @version 1.0
 */
public enum  Category {

    STARTER,
    MAIN,
    DESSERT,
    DRINKS;

    /**
     * Private constructor in order to prevent instantiation outside of the object
     * NB: Check with the singleton pattern in the second stage of this coursework
     */
    Category() {}
}