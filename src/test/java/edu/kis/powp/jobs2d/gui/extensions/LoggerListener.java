package edu.kis.powp.jobs2d.gui.extensions;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggerListener extends ActionTemplate implements ActionListener
{
    private DriverManager driverManager;
    private Job2dDriver driver;

    public LoggerListener(DriverManager driverManager, Job2dDriver driver)
    {
        this.driverManager = driverManager;
        this.driver = driver;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        performAction(e);
        DriverFeature.updateDriverInfo();
    }

    @Override
    protected void onEnable(ActionEvent event) {
        driverManager.addDriver(driver);
    }

    @Override
    protected void onDisable(ActionEvent event) {
        driverManager.removeDriver(driver);
    }
}