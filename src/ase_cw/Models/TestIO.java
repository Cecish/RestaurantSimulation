/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw.Models;

import static ase_cw.Models.Category.DESSERT;
import static ase_cw.Models.Category.DRINKS;
import static ase_cw.Models.Category.MAIN;
import static ase_cw.Models.Category.STARTER;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omiranda93
 */
public class TestIO {
    //Global variables
    //Depending on the OS the paths might need to change
    private static final String inputFileMenu = "src/ase_cw/menu.txt";//"src/ase_cw/menu.txt""menu.txt"
    private static final String inputFileOrder = "src/ase_cw/orders.txt";//"src/ase_cw/orders.txt""orders.txt"
    private static final String outputFile = "ase_cw/output.txt";//"src/ase_cw/output.txt"
    
    public void readMenu(Menu menu){
        BufferedReader readBuffer;
        String strRead;
        String[] splitArray;
        TreeSet <MenuItem> starters = new TreeSet<>();
        TreeSet <MenuItem> mains = new TreeSet<>();
        TreeSet <MenuItem> desserts = new TreeSet<>();
        TreeSet <MenuItem> drinks = new TreeSet<>();
        try {
            //To execute this from the id comment the following name and change the direction, up in the declaration and in the new filereader
            //String path = System.getProperty("user.dir");
            //readBuffer = new BufferedReader(new FileReader(path + "/"+inputFileMenu));
            readBuffer = new BufferedReader(new FileReader(inputFileMenu));

            while ((strRead = readBuffer.readLine()) != null) {
                splitArray = strRead.split("/ ");
                switch(splitArray[2]){
                    case "Starter": starters.add(new MenuItem(STARTER, splitArray[0], Double.parseDouble(splitArray[1])));
                        break;
                    case "Main": mains.add(new MenuItem(MAIN, splitArray[0], Double.parseDouble(splitArray[1])));
                        break;
                    case "Dessert": desserts.add(new MenuItem(DESSERT, splitArray[0], Double.parseDouble(splitArray[1])));
                        break;
                    case "Drinks": drinks.add(new MenuItem(DRINKS, splitArray[0], Double.parseDouble(splitArray[1])));
                        break;      
                }
            }
            readBuffer.close();
            menu.addValues(STARTER, starters);
            menu.addValues(MAIN, mains);
            menu.addValues(DESSERT, desserts);
            menu.addValues(DRINKS, drinks);
            System.out.println(menu.getValues().size()+" #############");
            
        }catch (IOException e1){
            System.out.println("There was an error loading the menu from file.\nEnding program...");
            System.exit(1);
        } catch (WrongCategoryException e2) {
            System.out.println(e2.getMessage()+"\nEnding program...");
            System.exit(1);
        } catch (NumberFormatException e3){
            System.out.println("One or more prices in the text file are not a price or are wrongly introduced.\nEnding program...");
            System.exit(1);
        }
    }
    
    
    
    public void readOrders(CollectionFoodOrders orders) throws NoMatchingIDException{
        BufferedReader readbuffer;
        String strRead;
        String[] splitarray;
        TreeSet <FoodOrder> table1 = new TreeSet<>();
        TreeSet <FoodOrder> table2 = new TreeSet<>();
        TreeSet <FoodOrder> table3 = new TreeSet<>();
        TreeSet <FoodOrder> table4 = new TreeSet<>();
        TreeSet <FoodOrder> table5 = new TreeSet<>();
        TreeSet <FoodOrder> table6 = new TreeSet<>();
        TreeSet <FoodOrder> table7 = new TreeSet<>();
        TreeSet <FoodOrder> table8 = new TreeSet<>();
        TreeSet <FoodOrder> table9 = new TreeSet<>();
        TreeSet <FoodOrder> table10 = new TreeSet<>();
        TreeSet <FoodOrder> table11 = new TreeSet<>();
        TreeSet <FoodOrder> table12 = new TreeSet<>();
        TreeSet <FoodOrder> table13 = new TreeSet<>();
        TreeSet <FoodOrder> table14 = new TreeSet<>();
        TreeSet <FoodOrder> table15 = new TreeSet<>();

        try {
            //To execute this from the id comment the following name and change the direction, up in the declaration and in the new filereader
            //String path = System.getProperty("user.dir");
            //readbuffer = new BufferedReader(new FileReader(path+"/"+inputFileOrder));
            readbuffer = new BufferedReader(new FileReader(inputFileOrder));
            while ((strRead = readbuffer.readLine()) != null) {
                splitarray = strRead.split("/ ");
                switch(splitarray[0]){
                    case "1": table1.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;
                    case "2": table2.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;
                    case "3": table3.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "4": table4.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "5": table5.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "6": table6.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "7": table7.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "8": table8.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "9": table9.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "10": table10.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "11": table11.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "12": table12.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "13": table13.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "14": table14.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;      
                    case "15": table15.add(new FoodOrder(Integer.parseInt(splitarray[0]), splitarray[1], Integer.parseInt(splitarray[2])));
                        break;   
                    default: throw new NoMatchingIDException(splitarray[0]);
                }
            }
            readbuffer.close();
            orders.addValues(1, table1);
            orders.addValues(2, table2);
            orders.addValues(3, table3);
            orders.addValues(4, table4);
            orders.addValues(5, table5);
            orders.addValues(6, table6);
            orders.addValues(7, table7);
            orders.addValues(8, table8);
            orders.addValues(9, table9);
            orders.addValues(10, table10);
            orders.addValues(11, table11);
            orders.addValues(12, table12);
            orders.addValues(13, table13);
            orders.addValues(14, table14);
            orders.addValues(15, table15);
        }catch (InvalidQuantityException e1){
            System.out.println(e1.getMessage()+"\nEnding program...");
            System.exit(1);
        }catch (IOException e2){
            System.out.println("There was an error loading the orders from file.\nEnding program...");
            System.exit(1);
        } catch (NumberFormatException e3){
            System.out.println("One of the quantities introduced is not a number. The file could not be loaded.\nEnding program...");
            System.exit(1);
        }
    }
    
    public void writeToFile (String menu, String orders, String numberDish, String unorderedDishes, String profits){
        BufferedWriter writer;
        try {
            //To execute this from the id comment the following name and change the direction, up in the declaration and in the new filereader
            String path = System.getProperty("user.dir");
            writer = new BufferedWriter(new FileWriter(path+"/"+outputFile));
            writer.write(menu);
            writer.write("-----------------------------------------------------------------------------------\n");
            writer.write(orders);
            writer.write("-----------------------------------------------------------------------------------\n");
            writer.write(numberDish);
            writer.write("-----------------------------------------------------------------------------------\n");
            writer.write(unorderedDishes);
            writer.write("-----------------------------------------------------------------------------------\n");            
            writer.write(profits);
            writer.write("-----------------------------------------------------------------------------------\n");            
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TestIO.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
}
