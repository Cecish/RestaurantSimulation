/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw.Models;

import static ase_cw.ASE_CW.sleep_time;
import ase_cw.Views.MainPanel;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MonitorProdCons {
    private final ArrayList<FoodOrder> notedOrders;
    private final ArrayList<FoodOrder> readyOrders;
    private final ArrayList<FoodOrder> servedOrders;
    CollectionFoodOrders orders;
    CollectionFoodOrders ordersProcessed;
    
    //Stop variables
    boolean outOfOrders = false;
    
    //Reference to panel for updates
    MainPanel panel;

    private final ReentrantLock Cerrojo = new ReentrantLock();
    private final Condition Eat = Cerrojo.newCondition();

    //For the table simulation
    private final static int NumCustomers = 3;
    private final static int NumChairs = 3;

    private static Semaphore semaforoEntrar;
    private static Semaphore[] semaforoTenedor;
    



    public MonitorProdCons(CollectionFoodOrders orders) {
        notedOrders = new ArrayList<>();
        readyOrders = new ArrayList<>();
        servedOrders = new ArrayList<>();
        this.orders = orders;
        this.ordersProcessed = new CollectionFoodOrders(orders.getMenu());
    }

    public void setPanel(MainPanel panel) {
        this.panel = panel;
    }

    public CollectionFoodOrders getOrders() {
        return orders;
    }
    
    public CollectionFoodOrders getOrdersProcessed(){
        return ordersProcessed;
    }
    
    public void closeKitchen(){
        outOfOrders = true;
    }

    public synchronized void kitchen(int id) {
        if (outOfOrders && notedOrders.isEmpty()){
            panel.updateOutOfOrders();
            Thread.currentThread().stop();
            return;
        }
        FoodOrder dish;
        while (this.notedOrders.size() == 0) {
            LogSingletonObs.getInstance().update("The kitchen is not working. It is waiting for orders");
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        dish = notedOrders.get(0);
        LogSingletonObs.getInstance().update("In the kitchen. Cooks have completed the dish "+ dish.getDishName()+" for " +
                "table n°" +notedOrders.remove(0).getTableId()+ ". Ready to be served");
        panel.updateNoted(notedOrders);
        try {
            Thread.sleep(2*sleep_time);
        } catch (InterruptedException ignored) {

        }
        this.readyOrders.add(dish);
        panel.updateReady(readyOrders);
        try {
            Thread.sleep(2*sleep_time);
        } catch (InterruptedException ignored) {

        }
        this.notifyAll();
    }

    public synchronized void waiter(int id) {
        
        //Stop condition
        if (outOfOrders && readyOrders.isEmpty()){
            panel.printWaiterClosed();
            Thread.currentThread().stop();
            return;
        }
        
        FoodOrder dish;
        while (this.readyOrders.size() == 0) {
            LogSingletonObs.getInstance().update("Waiter n° " + id + " is ready to serve customers ... but nothing has " +
                    "been ordered yet!");
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        dish = readyOrders.get(0);
        ordersProcessed.addValue(dish.getTableId(), dish);
        
        //Notify the panel for updating te log file's content
        LogSingletonObs.getInstance().update("Waiter n° " + id + "has served the dish " + dish.getDishName() + " to the " +
                "table n° " + readyOrders.remove(0).getTableId());
        panel.updateReady(readyOrders);
        try {
            Thread.sleep(sleep_time);
        } catch (InterruptedException ignored) {

        }
        
        
        //Notify the panel for the updating the kitchen & tables boxed
        servedOrders.add(dish);
        panel.updateServed(servedOrders);
        try {
            Thread.sleep(sleep_time);
        } catch (InterruptedException ignored) {

        }
        this.notifyAll();

    }

    //For the table simulation:

//    private static int tenedorIzq;
//    private static int tenedorDer;
    private static void createThread(int i, int tableN) {
        int cont = 0;
        Thread p = new Thread(() -> {
            try {
                clientEat(i, cont);
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorProdCons.class.getName()).log(Level.SEVERE, null, ex);
            }
        }, "Customer nº " + i + " from table " + tableN);
        p.start();
    }

    public static void clientEat(int i, int cont) throws InterruptedException {
        semaforoEntrar.acquire();

        int tenedorIzq = (i == 0) ? NumCustomers - 1 : i - 1;

        Thread.sleep((long) Math.random());
        try{
        if ((i % NumCustomers) == 0) {
            semaforoTenedor[i].acquire();
            semaforoTenedor[tenedorIzq].acquire();

            System.out.println(Thread.currentThread().getName() + ": I'm hungry, let´s eat");
            Thread.sleep(sleep_time);

            semaforoTenedor[i].release();
            semaforoTenedor[tenedorIzq].release();
            System.out.println(Thread.currentThread().getName() + ": Tasty!! I´m done eating");
        } else {
            semaforoTenedor[tenedorIzq].acquire();
            semaforoTenedor[i].acquire();

            System.out.println(Thread.currentThread().getName() + ": I'm hungry, let´s eat");

            semaforoTenedor[tenedorIzq].release();
            semaforoTenedor[i].release();
            System.out.println(Thread.currentThread().getName() + ": Tasty!! I´m done eating");

        }
        }catch(Exception ignored){
            
        }
        cont++;
        semaforoEntrar.release();
    }

    public static void execute(int tableN) {
        semaforoEntrar = new Semaphore(NumChairs);
        semaforoTenedor = new Semaphore[NumCustomers];

        for (int i = 0; i < NumCustomers; i++) {
            semaforoTenedor[i] = new Semaphore(1);
        }

        for (int i = 0; i < NumCustomers; i++) {
            createThread(i, tableN);
        }
    }


    public synchronized void table(int id) {
        if(outOfOrders && servedOrders.isEmpty()){
            panel.printTablesClosed();
            Thread.currentThread().stop();
            return;
        }
        int tableN;
        try {
            while(servedOrders.size()==0){
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(MonitorProdCons.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableN = servedOrders.get(0).getTableId();
        execute(tableN);
        servedOrders.remove(0);
        panel.updateServed(servedOrders);
        try {
            Thread.sleep(sleep_time);
        } catch (InterruptedException ignored) {

        }

    }

    public FoodOrder getRandOrder(){
        FoodOrder order = null;
        int cont = 0;
        while(order == null && cont != 20){
            int number = ((Double) (Math.random() * 5)).intValue() + 1;
            TreeSet<FoodOrder> ordersToSelect = orders.getValue(number);
            order = ordersToSelect.pollFirst();
            cont ++;
        }
        return order;
    }

    public synchronized void noter(int id) {
        int capacityOfKitchen = 6;
        while (this.notedOrders.size() == capacityOfKitchen) {
            LogSingletonObs.getInstance().update("Waiter n°" + id + " ready to take notes ... but he/she will have to " +
                    "wait because the kitchen can't get more orders (capacity of the kitchen = "+ capacityOfKitchen + ")");

            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        FoodOrder newOrder = getRandOrder();

        if(newOrder == null){
            System.out.println("There are no more orders");
            outOfOrders = true;
            Thread.currentThread().stop();
            return;
        }
        LogSingletonObs.getInstance().update("Waiter n°"+ id + " taking notes. Order "+ newOrder.getDishName() +
                "for table: " + newOrder.getTableId() + " noted.");
        this.notedOrders.add(newOrder);
        panel.updateNoted(notedOrders);
        try {
            Thread.sleep(sleep_time);
        } catch (InterruptedException ignored) {
        }
        this.notifyAll();

    }
}
