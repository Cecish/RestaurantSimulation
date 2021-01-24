package ase_cw.Models;

import java.io.File;

/**
 * Represents the manager of the system
 *
 * Date: 31/01/2016
 * Time: 6:43 PM
 *
 * @author Cécile Riquart
 * @version 1.0
 */
public class Manager {

    /**
     * Tuneable
     * WIDTH_BILL : Width of the bill = the maximum number of characters found per row
     * ALINEA_DISHNAME : "Tabulation" before displaying the dish name of menu items
     * DISHNAME_TEXT : Width of the dish name column when displaying the menu or space avaialable for some text
     * PRICE : Width of the price column when displaying the menu  and the unit price in the orders
     * ALINEA_PRICE : "Tabulation" before displaying the unit price of each ordered item
     * CURRENCY_SIZE : Width of the currency whether it's for displaying the menu or details of orders
     * CURRENCY : ISO 4217 format of the currency used
     * QUANTITY : Width of the quantity when displaying the details of the orders made
     * MAX_TOTAL : Width of the maximum size of totals (final or not)
     * MENU: Title of the menu
     * TABLE_SUMMARY : Title of the order summary
     * PATH_LOG_FILE : Location of the log file
     */
    public static final int WIDTH_BILL = 120;
    public static final int ALINEA_DISHNAME = 2;
    public static final int DISHNAME_TEXT = 50;
    public static final int PRICE = 6; // Format : ---,--
    public static final int ALINEA_PRICE = 4;
    public static final int CURRENCY_SIZE = 3;  //ISO 4217
    public static final String CURRENCY = "GBP";    //ISO 4217
    public static final int QUANTITY = 2;
    public static final int MAX_TOTAL = 6;
    public static final String MENU = "MENU";
    public static final String TABLE_SUMMARY = "TABLES SUMMARY";
    public static final String PATH_LOG_FILE = "src" + File.separator + "ase_cw" + File.separator + "log.txt";  //To be changed at convenience

     /**
     * Tuneable
     * reader: allows the input output operations
     * menu: contains the menu
     * orders: contains the orders
     */
    public static TestIO readerWriter =new TestIO();
    private static final Menu menu = new Menu();
    private static CollectionFoodOrders orders = new CollectionFoodOrders(menu);

    /**
     * "Underline" with the "=" symbol a string
     * @param s String to "underline"
     * @return The string which is "underlined"
     */
    public static String underlineString(String s) {

        return (new String(new char[s.length()]).replace("\0", "="));
    }
    
    /**
     * Calculate the percentage of the discount according to the bill amount
     * @param bill The bill on which the discount is to be applied
     * @return the discount as a percentage
     */
    public static int calculateDiscount(double bill){
        int discount=0;
        
        if (bill >= 20 && bill < 50){
            discount = 5;
        }else if(bill >= 50 && bill < 70){
            discount = 10;
        }else if(bill >=70){
            discount = 20;
        }
        
        return discount;
    }

    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException ex) { 
            return false; 
        }
        // only got here if we didn't return false
        return true;
    }
    
    
    /**
     * Checks whether the dimensions provided for the layout of the bill are correct.
     * May lead to an exception if not
     * @return true if the layout of the bill is correct, false otherwise
     */
    public boolean checkDimensionsBill() {

        int menu_format = (ALINEA_DISHNAME + DISHNAME_TEXT + PRICE + CURRENCY_SIZE + 3);

        int orders_format = DISHNAME_TEXT + QUANTITY + ALINEA_PRICE + PRICE + MAX_TOTAL + 7;

        /* NB : No need to check the frequency report and the total summary format because they are "sub objects" of
        menu or table summary layout */

        return ((menu_format <= WIDTH_BILL) || (orders_format <= WIDTH_BILL));
    }
    
    /**
     * Builds a manager
     */
    public Manager() throws WrongDimensionsBillException{
        
        try {
            readerWriter.readMenu(menu);
            orders = new CollectionFoodOrders(menu);
            readerWriter.readOrders(orders);
            if (!checkDimensionsBill()){
                throw (new WrongDimensionsBillException());
            }
        } catch (NoMatchingIDException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    /**
     * Prints the menu, bills and statistics for the restaurant.
     * May lead to an exception if not
     * @return void
     */
    public static void printAll() {
        System.out.println(menu.toString());
        System.out.println(orders.toString());
        System.out.println(orders.showDishCounter());
        System.out.println(orders.showUnorderedDishes());
        System.out.println(orders.showOrdersProfit());
    }
    
    /**
     * Prints the menu.
     * May lead to an exception if not
     * @return void
     */
    public static void printMenu(){
        System.out.println(menu.toString());
    }
    /**
     * Prints the bills.
     * May lead to an exception if not
     * @return void
     */
    public static void printBills(){
        System.out.println(orders.toString());
    }
    /**
     * Prints the number of times each dish has been ordered.
     * May lead to an exception if not
     * @return void
     */
    public static void printDishCounter(){
        System.out.println(orders.showDishCounter());
    }
    /**
     * Prints the unordered dishes.
     * May lead to an exception if not
     * @return void
     */
    public static void printUnorderedDishes(){
        System.out.println(orders.showUnorderedDishes());
    }
    /**
     * Prints the dishes ordered by the profit to the restaurant.
     * May lead to an exception if not
     * @return void
     */
    public static void printProfitDishes(){
        System.out.println(orders.showOrdersProfit());
    }
    /**
     * Gui that allows to insert a table number and see the bill.
     * May lead to an exception if not
     * @return void
     */
    public static void guiTable(){

        boolean billShowed = false;

        while (!billShowed) {
            try {
                orders.showTableBill();
                billShowed = true;

            } catch (NumberFormatException e1) {
                //System.out.println(e1.getMessage());
                break;

            } catch (NoMatchingIDException ex) {
                System.out.println(ex.getMessage());
            }
        }


    }
    
    public static void writeInFile(){
        readerWriter.writeToFile(menu.toString(), orders.toString(), orders.showDishCounter(), orders.showUnorderedDishes(), orders.showOrdersProfit());
    }

    public static CollectionFoodOrders getOrders() {
        return orders;
    }
    
    
}
