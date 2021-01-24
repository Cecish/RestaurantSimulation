/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw.Models;


import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.JOptionPane;

/**
 * The collection that holds all the Order objects.
 * It is using a Map with the tableId as a key (Integer) and a TreeSet of OrderItems as the value
 * @author giannis
 */
public class CollectionFoodOrders {
    private final Map<Integer, ? super Set<FoodOrder>> orderCol;
    private final Menu menu;

    //statistic variables
     
    
    //Get all the prices of all the dishes in pairs from the Menu class. As we are using a map, traversing the Map to get the price is O(1) complexity so its optimal.
    private Map<String, Double> NamePricePair = new TreeMap<>();

    /**
     * Creates a new collection of orders
     * @param menu The Menu collection is passed in order to gain access to the prices later on
     */
    public CollectionFoodOrders(Menu menu){
        orderCol = new TreeMap<>();
        this.menu = menu;
        NamePricePair = menu.getValues();
    }

    /**
     * Returns the orders of the specified table
     * @param tableId The table from which the orders are requested
     * @return The orders made by the provided table in a TreeSet
     */
    public TreeSet<FoodOrder> getValue (Integer tableId){
        return (TreeSet<FoodOrder>) orderCol.get(tableId);
    }

    /**
     * Returns all the keys (TableId) from the collection of orders
     * @return the keys of the collection as a Set
     */
    public Set<Integer> getKeys(){
        return orderCol.keySet();
    }
    
    /**
     * Adds a collection of OrderItems to the collection, under the specified tableId
     * @param tableId The table under which the sub-set of orders will be placed
     * @param oi A collection (TreeSet) of OrderItems
     */
    public void addValues(Integer tableId, TreeSet<FoodOrder> oi){
        orderCol.put(tableId, oi);
    }
    
    /**
     * Adds an FoodOrder to the CollectionFoodOrders (Map). 
     * As the items are grouped by the tableId, if this is the first time the table is inserted, a <tableId, TreeSet of OrderItems> pair will be created first
     * @param tableId The key (the id of the table)
     * @param oi the Value (an FoodOrder object)
     */
    public void addValue(Integer tableId, FoodOrder oi){
        if (orderCol.containsKey(tableId)){
            this.getValue(tableId).add(oi);
        }else{
            TreeSet<FoodOrder> ts = new TreeSet<>();
            ts.add(oi);
            orderCol.put(tableId, ts);
        }
    }
    
    /**
     * Overridden method to print the bill. Only tables with placed orders will be printed.
     * @return All the orders of the collection as a String, in tabular format.
     */
    @Override
    public String toString(){
        String result= "";
                
        result += "TABLES SUMMARY\n";
        
        //Put all the tables in an (ordered) set
        Set<Integer> tables = this.getKeys();
        Set<FoodOrder> orders;
        
        //For each table available
        for (Integer t : tables){   
            orders = getValue(t);
            if (!orders.isEmpty()) {result += getBill(t) + "\n";}
        }
        
        return result;
    }
    
    /**
     * Returns a string representation of the bill for a given table in tabular format.
     * @param tableId The table we need the bill for
     * @return  A table with the details of the tables bill.
     */
    public String getBill(int tableId){
        double bill=0;
        double dishTotal;
        int discountPerc;    
        String temp;
        int nb_new_lines, i;
        
        String result="Table " + tableId + " Summary\n";
        
        //Create the string formater to be applied to String.format
        String formater = "%-" 
                + Manager.ALINEA_DISHNAME + "s %-" 
                + Manager.DISHNAME_TEXT + "s %-" 
                + Manager.QUANTITY + "s %-1s %-" 
                + Manager.PRICE + "s %-1s %-" 
                + Manager.MAX_TOTAL + "s %-" 
                + Manager.CURRENCY_SIZE + "s";
        
        Set<FoodOrder> orders = getValue(tableId);
            for (FoodOrder order : orders){
                try{
                    dishTotal = NamePricePair.get(order.getDishName()) * order.getQuantity();
                    if (order.getDishName().length() > Manager.DISHNAME_TEXT) {

                    //Number of lines necessary to write the dish's name
                    nb_new_lines = order.getDishName().length() / Manager.DISHNAME_TEXT;

                    //For each line
                    for (i = 0; i< nb_new_lines; i++) {

                        //If it is not the line finishing printing the dish's name: format the line without the price
                        //If it is the line finishing printing the dish's name: format the line with the price
                        if (i < nb_new_lines-1 | i == nb_new_lines-1) {
                            temp = order.getDishName().substring(i*(Manager.DISHNAME_TEXT-1), (i+1)*(Manager.DISHNAME_TEXT-1));
                            result += String.format(formater, "", temp, "", "","","","","") + "\n";
                        }
                    }

                    temp = order.getDishName().substring((nb_new_lines)*(Manager.DISHNAME_TEXT-1), order.getDishName().length());
                    result += String.format(formater, "", temp, order.getQuantity(), "*", NamePricePair.get(order.getDishName()),"=" ,dishTotal, Manager.CURRENCY) + "\n";

                    } else {
                        result += String.format(formater, "", order.getDishName(), order.getQuantity(),"*", NamePricePair.get(order.getDishName()),"=" ,dishTotal, Manager.CURRENCY) + "\n";
                    }

                    bill += dishTotal;
                }catch (NullPointerException ex){
                    System.out.println("The orther: " + order.getDishName() + ", does not match any of the dishes in the menu. Please revise the file.\nEnding program...");
                    System.exit(1);
                    
                }
               
                 
               
            }
        discountPerc = Manager.calculateDiscount(bill);
        double discount = (discountPerc * bill) / 100;
        
        result += "\n";
        
        //Also print the summed total of the table, included the discounted price (if applicable - configured in the manager class)
        result += String.format(formater,"","Total for this table:","","","","",bill, Manager.CURRENCY) +"\n";
        result += String.format(formater,"","Discount: " + discountPerc + "%","","","","", discount, Manager.CURRENCY) +"\n";
        result += String.format(formater,"","Discounted total:","","","","",bill - discount, Manager.CURRENCY) +"\n\n";
        
        //Update the tables bill with the discount (if any)
        bill -= discount;

        return result;
    }
    
    /**
     * Shows a dialog, prompting the user to enter a table ID and show a window with the 
     * bill for the provided table
     * @throws NoMatchingIDException
     */
    public void showTableBill() throws NoMatchingIDException {
        String id = JOptionPane.showInputDialog(null, "Enter table id");
        //JTextArea textArea = new JTextArea(getBill(Integer.valueOf(id)),5, 50);

        if (id != null){
            if (!Manager.isInteger(id)) {
                JOptionPane.showMessageDialog(null, "Wrong table ID", "Attention", JOptionPane.ERROR_MESSAGE);
                throw new NoMatchingIDException(id);
            }
        } else {
            throw new NumberFormatException();
        }
  
        int t_id = Integer.valueOf(id); //convert it to Integer
        if (t_id < 0){
            JOptionPane.showMessageDialog(null, "Table ID can not be a negative number", "Attention", JOptionPane.ERROR_MESSAGE);
            throw new NoMatchingIDException(id);
        }
        if (!this.getKeys().contains(t_id)){
            JOptionPane.showMessageDialog(null, "Table ID not existant.", "Attention", JOptionPane.ERROR_MESSAGE);
            throw new NoMatchingIDException(id);

        } else{
            JOptionPane.showMessageDialog(null, getBill(Integer.valueOf(id)));
        }
    }
    
    /**
     * Creates a frequency report of how many times each dish has been order in total within
     * the day. 
     * @return A String table showing how many times each dish has been ordered.
     */
    public String showDishCounter(){
        String result = String.format("%-60s %-3s", "Dish name", "Times ordered") + "\n";
        result += Manager.underlineString(result) + "\n";
        Map<String, Integer> quantityOrders = new TreeMap<>();
        
        //First build a Map of all distinct food names and set the counter to 0
        for (Map.Entry<Integer, ? super Set<FoodOrder>> entry : orderCol.entrySet()){
            for (FoodOrder o : (TreeSet<FoodOrder>) entry.getValue()){
                quantityOrders.put(o.getDishName(), 0);    
            }
        }
        
        //Then for each of these foods, search the order collection again and for everytime found, add its quantity to the counter (as a value of the map) 
        for (Map.Entry<String, Integer> foodList : quantityOrders.entrySet()){
            for (Map.Entry<Integer, ? super Set<FoodOrder>> entry : orderCol.entrySet()){
                for (FoodOrder o : (TreeSet<FoodOrder>) entry.getValue()){
                    if (o.getDishName().equals(foodList.getKey())){
                        foodList.setValue(foodList.getValue() + o.getQuantity());
                    }   
                }
            }
        }
        
        //Iterate through the produced Map to build the table for output.
        for (Map.Entry<String, Integer> foodList : quantityOrders.entrySet()){
            result += String.format("%-60s %-3s", foodList.getKey(), foodList.getValue()) + "\n"; 
        }
        
        return result;
    }
    
    /**
     * Creates a report showing the dishes that have not been ordered
     * @return a list formatted string representation of the unordered dishes.
     */
    public String showUnorderedDishes(){
        String result="List of unoredered dishes:\n" + Manager.underlineString("List of unoredered dishes:")+"\n";
        Set<String> orderList = new TreeSet<>();
        
        //First build a Map of all distinct food names and set the counter to 0
        for (Map.Entry<Integer, ? super Set<FoodOrder>> entry : orderCol.entrySet()){
            for (FoodOrder o : (TreeSet<FoodOrder>) entry.getValue()){
                orderList.add(o.getDishName());    
            }
        }
      
        //Then add all dishes that do not exist in the orders
        for (Map.Entry<String, Double> entry : NamePricePair.entrySet()){
            if (!orderList.contains(entry.getKey())){
                result += entry.getKey() +"\n";
            }
        }
        
        return result +"\n";
        
    }
    
    /**
     * Creates a report showing the dishes in order of profit
     * @return a list formated string representation of the dishes ordered by profit.
     */
    public String showOrdersProfit(){
        String result = String.format("%-60s %-6s", "Dish name", "Profit") + "\n";
        result += Manager.underlineString(result) + "\n";
        Map<String, Double> quantityOrders = new TreeMap<>();
        ArrayList <String> names = new ArrayList <>();
        ArrayList <Double> prices = new ArrayList <>();
        
        //First build a Map of all distinct food names and set the counter of profit to 0
        for (Map.Entry<Integer, ? super Set<FoodOrder>> entry : orderCol.entrySet()){
            for (FoodOrder o : (TreeSet<FoodOrder>) entry.getValue()){
                quantityOrders.put(o.getDishName(), 0.0);    
            }
        }
        
        //Then for each of these foods, search the order collection again and for everytime found, 
        //add its quantity multiplied by the price to the pricecounter (as a value of the map) 
        for (Map.Entry<String, Double> foodList : quantityOrders.entrySet()){
            for (Map.Entry<Integer, ? super Set<FoodOrder>> entry : orderCol.entrySet()){
                for (FoodOrder o : (TreeSet<FoodOrder>) entry.getValue()){
                    if (o.getDishName().equals(foodList.getKey())){
                        foodList.setValue(foodList.getValue() + (o.getQuantity()*NamePricePair.get(o.getDishName())));
                    }   
                }
            }
        }
        //Order the dishes by profit
        for (Map.Entry<String, Double> foodList : quantityOrders.entrySet()){
            names.add(foodList.getKey());
            prices.add(foodList.getValue());
            
        }
        bubble_srt(names, prices);
        //Iterate through the produced Map to build the table for output.
        for (int i = 0; i < prices.size(); i++) {
            result += String.format("%-60s %-6s %-3s", names.get(i), prices.get(i), Manager.CURRENCY) + "\n"; 
        }        
        return result;
        
    }
    
     /**
     * Sorts.
     * @return
     */
    public static void bubble_srt(ArrayList<String> names, ArrayList<Double> prices) {
        int n = prices.size();
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (prices.get(i) > prices.get(k)) {
                    swap(i, k, prices);
                    swap(i, k, names);
                }
            }
        }
    }
  
    private static void swap(int i, int j, ArrayList price) {
  
        Object temp;
        temp = price.get(i);
        price.set(i, price.get(j));
        price.set(j, temp);
    }

    public Menu getMenu() {
        return menu;
    }
    
}
    
