/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw.Models;


public class HiloKitchen extends Thread {
    private final MonitorProdCons monitor;
    private final int id;

    public HiloKitchen(MonitorProdCons initMonitor, int id) {
        this.monitor = initMonitor;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            this.monitor.kitchen(id);
        }
    }
}
