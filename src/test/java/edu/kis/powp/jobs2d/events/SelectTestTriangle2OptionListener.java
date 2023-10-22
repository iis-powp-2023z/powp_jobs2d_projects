package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ComplexCommandFactory;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestTriangle2OptionListener implements ActionListener {
    private DriverManager driverManager;

    public SelectTestTriangle2OptionListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ComplexCommandFactory.getTriangle().execute(driverManager.getCurrentDriver());
    }
}
