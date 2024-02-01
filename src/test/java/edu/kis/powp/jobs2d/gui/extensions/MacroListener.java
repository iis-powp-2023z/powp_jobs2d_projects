package edu.kis.powp.jobs2d.gui.extensions;

import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.events.SelectMacroStartListener;
import edu.kis.powp.jobs2d.events.SelectMacroStopListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MacroListener implements ActionListener
{
    private boolean enabled;
    private DriverMacro driverMacro;
    private ActionListener startMacro, stopMacro;

    public MacroListener(DriverManager driverManager)
    {
        enabled = false;
        driverMacro = new DriverMacro();
        startMacro = new SelectMacroStartListener(driverMacro, driverManager);
        stopMacro = new SelectMacroStopListener(driverMacro, driverManager);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        enabled = !enabled;

        if (enabled)
        {
            startMacro.actionPerformed(e);
        }
        else
        {
            stopMacro.actionPerformed(e);
        }
    }
}
