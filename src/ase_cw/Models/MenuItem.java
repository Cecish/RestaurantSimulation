/*

*Cecile's part
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw.Models;

/**
 * Represents a food item figuring on the menu of the restaurant
 *
 * Date: 31/01/2016
 * Time: 1:54 PM
 *
 * @author Cécile Riquart
 * @version 1.0
 */
public class MenuItem implements Comparable<MenuItem> {

    /**
     * A menu item is characterised by:
     * - a category (starter, main, dessert, drinks),
     * - a name: the name of the dish
     * - a price (in pound sterling, £)
     */
    private Category category;
    private String dishName;
    private double price;

    /**
     * Constructor of a menu item
     * @param category Category of the menu item
     * @param dishName Name of the menu item
     * @param price Price of the menu item (expressed in pound sterling £)
     */
    public MenuItem(Category category, String dishName, double price) {
        this.category = category;
        this.dishName = dishName;
        this.price = price;
    }

    /**
     * Gets the category of this menu item
     * @return this menu item's category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the dish name of this menu item
     * @return this menu item's dish name
     */
    public String getName() {
        return dishName;
    }

    /**
     * Gets the price of this menu item
     * @return this menu item's price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     *  Sets the category of this menu item
     * @param category New category of this menu item
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Sets the name of this menu ite
     * @param dishName New name of this menu item
     */
    public void setName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Sets the price of this menu item
     * @param price New price of this menu item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Compares this menu item with another one.
     * The comparison is based on the alphabetical order of the dish names
     * @param o The other menu item with which this menu item is compared to
     * @return a value less than 0 if the dish name's argument is after this menu item's dish name according to the
     *         alphabet order
     *         0 if the dish name's argument equals to this menu item's dish name,
     *         a value greater than 0 if the dish name's argument is before this menu item's dish name according to the
     *         alphabet order
     */
    @Override
    public int compareTo(MenuItem o) {
        return dishName.compareTo(o.getName());
    }

    /**
     * Represents this menu item as a String
     * NB: Continues printing the menu item's name on another line if too long, with respect to the bill format
     * @return the string that represents this menu item
     */
    @Override
    public String toString() {
        String temp;
        int nb_new_lines, i;
        String result = "";

        String res = "%-" + Manager.ALINEA_DISHNAME + "s %-" +
                Manager.DISHNAME_TEXT + "s %-"   +
                Manager.PRICE + "s %-"           +
                Manager.CURRENCY_SIZE +"s";

        if (getName().length() > Manager.DISHNAME_TEXT) {

            //Number of lines necessary to write the dish's name
            nb_new_lines = dishName.length() / Manager.DISHNAME_TEXT;

            //For each line
            for (i = 0; i< nb_new_lines; i++) {

                //If it is not the line finishing printing the dish's name: format the line without the price
                //If it is the line finishing printing the dish's name: format the line with the price
                if (i < nb_new_lines-1 | i == nb_new_lines-1) {
                    temp = dishName.substring(i*(Manager.DISHNAME_TEXT-1), (i+1)*(Manager.DISHNAME_TEXT-1));
                    result += String.format(res, "", temp.toUpperCase(), "", "") + "\n";
                }
            }

            temp = dishName.substring((nb_new_lines)*(Manager.DISHNAME_TEXT-1), dishName.length());
            result += String.format(res, "", temp.toUpperCase(), price, Manager.CURRENCY) + "\n";

        } else {
            result += String.format(res, "", dishName.toUpperCase(), price, Manager.CURRENCY) + "\n";
        }

        return result;
    }
}
