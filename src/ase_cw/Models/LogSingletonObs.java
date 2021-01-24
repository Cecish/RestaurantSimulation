package ase_cw.Models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Cécile Riquart
 * Date: 3/19/16
 * Time: 9:31 AM
 * Records any event in the simulation (start, order arrives in the kitchen, orders move to table) and write these
 * information into a text file. LogSingletonObs also plays the role of the subject in the observer pattern
 */
public class LogSingletonObs {

    private static volatile LogSingletonObs instance;
    private String all_events;

    /**
     * Constructor : Initially the event variable is empty (!= null !!!)
     */
    private LogSingletonObs() {
        this.all_events = "";
    }

    /**
     * Gets instance
     * @return the instance of LogSingletonObs
     */
    public static LogSingletonObs getInstance() {
        if (instance == null) {
            synchronized (LogSingletonObs.class) {
                if (instance == null) {
                    instance = new LogSingletonObs();
                }
            }
        }
        return instance;
    }

    /**
     *  Gets update from the subject = thread
     */
    public void update(String event) {
        all_events += "\n" + event;
    }


    /**
     * Write all log events into an output file
     */
    public void writeLogFile (){
        BufferedWriter writer;
        try {
            //To execute this from the id comment the following name and change the direction, up in the declaration and in the new filereader
            String path = System.getProperty("user.dir");
            writer = new BufferedWriter(new FileWriter(path + File.separator + Manager.PATH_LOG_FILE));
            writer.write(all_events);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(LogSingletonObs.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
}
