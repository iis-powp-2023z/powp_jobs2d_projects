package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ComplexCommandFactory;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestRectangle2OptionListener implements ActionListener {
    private DriverManager driverManager;

    public SelectTestRectangle2OptionListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ComplexCommandFactory.getRectangle().execute(driverManager.getCurrentDriver());
    }
}
