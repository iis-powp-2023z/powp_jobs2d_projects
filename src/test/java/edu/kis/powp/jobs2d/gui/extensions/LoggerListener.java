package edu.kis.powp.jobs2d.gui.extensions;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggerListener implements ActionListener
{
    private DriverManager driverManager;
    private Job2dDriver driver;
    private boolean enabled;

    public LoggerListener(DriverManager driverManager, Job2dDriver driver)
    {
        enabled = false;
        this.driverManager = driverManager;
        this.driver = driver;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        enabled = !enabled;

        if (enabled)
        {
            driverManager.addDriver(driver);
        }
        else
        {
            driverManager.removeDriver(driver);
        }

        DriverFeature.updateDriverInfo();
    }
}