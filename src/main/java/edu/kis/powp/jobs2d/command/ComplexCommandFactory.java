package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommandFactory {
    public static DriverCommand getRectangle(Job2dDriver job2dDriver) {
        return new ComplexCommandBuilder()
                .addCommand(new SetPositionCommand(-100, -50))
                .addCommand(new OperateToCommand(100, -50))
                .addCommand(new OperateToCommand(100, 50))
                .addCommand(new OperateToCommand(-100, 50))
                .addCommand(new OperateToCommand(-100, -50))
                .build();
    }
    public static DriverCommand getTriangle(Job2dDriver job2dDriver) {
        return new ComplexCommandBuilder()
                .addCommand(new SetPositionCommand(-100, -100))
                .addCommand(new OperateToCommand(-100, 100))
                .addCommand(new OperateToCommand(50, -100))
                .addCommand(new OperateToCommand(-100, -100))
                .build();
    }
}
