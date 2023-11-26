package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMacroStartListener implements ActionListener {

    private DriverManager driverManager;
    private DriverMacro macroDriver;

    public SelectMacroStartListener(DriverMacro macroDriver, DriverManager driverManager) {
        this.macroDriver = macroDriver;
        this.driverManager = driverManager;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        driverManager.addDriver(macroDriver);
    }
}
