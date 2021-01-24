/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw;


import ase_cw.Models.Manager;
import ase_cw.Models.WrongDimensionsBillException;

import ase_cw.Models.HiloKitchen;
import ase_cw.Models.HiloNoter;
import ase_cw.Models.HiloTable;
import ase_cw.Models.HiloWaiter;
import ase_cw.Models.Manager;
import ase_cw.Models.MonitorProdCons;
import ase_cw.Models.WrongDimensionsBillException;
import ase_cw.Views.MainPanel;
import ase_cw.Controllers.*;



public class ASE_CW {

//    //Variable which represent the number of threads doing each function
//    private static final int nKitchens = 1;
//    private static final int nWaitersNote = 2;
//    private static final int nWaiters = 2;
//    private static final int nTables = 10;
    
    public static final int TIME_STEP = 1000;
    public static int sleep_time = 1000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            Manager manager = new Manager();
//            manager.printAll();
//            manager.guiTable();
//            manager.writeInFile();
            //Threads:
            //initialise the monitor
            MonitorProdCons model = new MonitorProdCons(manager.getOrders());
            
            
            MainPanel view = new MainPanel(model);
            model.setPanel(view);

            //Initialise the threads
//            for (int i = 0; i < nWaiters; i++) {
//                new HiloWaiter(monitor, i).start();
//            }
//            for (int i = 0; i < nKitchens; i++) {
//                new HiloConsumidor(monitor, i).start();
//            }
//            for (int i = 0; i < nWaitersNote; i++) {
//                new HiloProductor(monitor, i).start();
//            }
//            for (int i = 0; i < nTables; i++) {
//                new HiloTable(monitor, i).start();
//            }
        } catch (WrongDimensionsBillException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
