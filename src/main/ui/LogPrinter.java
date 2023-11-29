package ui;

import model.Event;
import model.EventLog;

//Printer used to print logs from EventLog to console
public class LogPrinter {

    public LogPrinter() {
    }

    //EFFECTS: prints out the logs from LogEvent to the console
    public void printLogs(EventLog el) {
        System.out.println("Log:");
        for (Event e : el) {
            System.out.println(e);
        }
    }
}
