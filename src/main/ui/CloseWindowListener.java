package ui;

import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//This is the window listener that handles the window closing event
public class CloseWindowListener extends WindowAdapter {

    //EFFECTS: prints logs to consoles when window is closed
    @Override
    public void windowClosing(WindowEvent e) {
        LogPrinter lp = new LogPrinter();
        lp.printLogs(EventLog.getInstance());
    }
}
