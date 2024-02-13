package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;

/**
 * DriverCommand interface.
 */
public interface DriverCommand {

    /**
     * Execute command on driver.
     *
     * @param driver driver.
     */
    public void execute(Job2dDriver driver);

    public DriverCommand clone();

    public void accept(CommandVisitor commandVisitor);

    public default int getPosX() {
        return 0;
    }

    public default int getPosY() {
        return 0;
    }
}
