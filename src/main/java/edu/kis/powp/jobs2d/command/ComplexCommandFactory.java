package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommandFactory {
    public static DriverCommand getRectangle() {
        return new ComplexCommandBuilder()
                .addCommand(new SetPositionCommand(-100, -50))
                .addCommand(new OperateToCommand(100, -50))
                .addCommand(new OperateToCommand(100, 50))
                .addCommand(new OperateToCommand(-100, 50))
                .addCommand(new OperateToCommand(-100, -50))
                .build("getRectangle");
    }
    public static DriverCommand getTriangle() {
        return new ComplexCommandBuilder()
                .addCommand(new SetPositionCommand(-100, -100))
                .addCommand(new OperateToCommand(-100, 100))
                .addCommand(new OperateToCommand(50, -100))
                .addCommand(new OperateToCommand(-100, -100))
                .build("getTriangle");
    }
}
