package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverMacro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMacroClearListener implements ActionListener {

    private DriverMacro macroDriver;

    public SelectMacroClearListener(DriverMacro macroDriver) {
        this.macroDriver = macroDriver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        macroDriver.clear();
    }
}
