package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

public class PreciseLoggerDriver implements Job2dDriver {
    private int counter;
    Logger logger = Logger.getLogger("global");

    public PreciseLoggerDriver() {
        this.counter = 1;
    }

    @Override
    public void setPosition(int i, int i1) {
        this.logger.info("Set position #" + this.counter + " {" + i + ", " + i1 + "}");
        incrementCounter();
    }

    @Override
    public void operateTo(int i, int i1) {
        this.logger.info("Operate to #" + this.counter + " {" + i + ", " + i1 + "}");
        incrementCounter();
    }

    private void incrementCounter() {
        this.counter++;
    }

    public String toString() {
        return "Precise Logger driver";
    }
}
