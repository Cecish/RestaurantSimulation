/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw.Controllers;

import static ase_cw.ASE_CW.TIME_STEP;
import ase_cw.Models.*;
import ase_cw.Views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static ase_cw.ASE_CW.sleep_time;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author giannis
 */
public class Controller implements ChangeListener, ActionListener{
    
    //Variable which represent the number of threads doing each function
    private static final int nKitchens = 1;
    private static final int nWaitersNote = 2;
    private static final int nWaiters = 2;
    private static final int nTables = 5;
    
    private final MainPanel view;
    private final MonitorProdCons model;
    
    private final ArrayList<Thread> threadCollection = new ArrayList<>();

    public Controller(MainPanel view, MonitorProdCons model) throws WrongDimensionsBillException{
        this.view = view;
        this.model = model;

    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        String rate = "";
        int speed = view.getSlider().getValue();
        
        //Change the simulation speed
        if (speed == 0){
            sleep_time = TIME_STEP;
            rate = "1";
        } else if (speed < 0){
            sleep_time = TIME_STEP * (abs(speed) + 1);
            rate = "1/" + (abs(speed) + 1);
        } else {
            sleep_time = TIME_STEP / (speed + 1);
            rate = String.valueOf(speed + 1);
        }
        
        System.out.println("Simulation speed set to: x" + rate);
        view.getSpeedLabel().setText("x" + rate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == view.getButtonStart()){
            
        //Initialise the threads
            Thread t;
            for (int i = 0; i < nWaiters; i++) {
               t = new HiloWaiter(model, i);
               t.start();
               threadCollection.add(t);
            }
            for (int i = 0; i < nKitchens; i++) {
                t = new HiloKitchen(model, i);
                t.start();
               threadCollection.add(t);
            }
            for (int i = 0; i < nWaitersNote; i++) {
                t = new HiloNoter(model, i);
                t.start();
               threadCollection.add(t);
            }
            for (int i = 0; i < nTables; i++) {
                t = new HiloTable(model, i);
                t.start();
                threadCollection.add(t);
            }
            
        }
        
        if (e.getSource() == view.getButtonStop()){
            
            //Stopping the threads. We are using the depricated stop() here, as we don't need use any cooperative process for this simple example.
            try{
            for (Thread thread: threadCollection) {
                thread.stop();
                System.out.println("Stopping thread: " + thread.getName());
            }
            System.out.println("All threads have stopped");

                //Every time the simulation starts, clear the boxes 
                view.setTextAreas("");

                //Write the events inside a log file
                LogSingletonObs.getInstance().writeLogFile();

            }catch (Exception ignored) {}
        }
        
        if (e.getSource() == view.getButtonCloseKitchen()){
            view.getButtonReqBill().setEnabled(true);
            view.getTxtReqBill().setEnabled(true);
            
            model.closeKitchen();
        }
        
        if (e.getSource() == view.getButtonReqBill()){
            switch (view.getTxtReqBill().getText()) {
                case "":
                    JOptionPane.showMessageDialog(null, "Wrong table ID or empty", "Attention", JOptionPane.ERROR_MESSAGE);
                    break;
                case "0":
                    view.getBillArea().setText(model.getOrdersProcessed().toString());
                    break;
                default:
                    try{
                        view.getBillArea().setText(model.getOrdersProcessed().getBill(Integer.parseInt(view.getTxtReqBill().getText())));
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "TableID should be a number", "Attention", JOptionPane.ERROR_MESSAGE);
                    }catch(NullPointerException ignored){}
                    break;
            }
            
        }
    }

}
