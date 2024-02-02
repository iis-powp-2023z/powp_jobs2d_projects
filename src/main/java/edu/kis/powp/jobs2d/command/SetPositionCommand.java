package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;

/**
 * Implementation of Job2dDriverCommand for setPosition command functionality.
 */
public class SetPositionCommand implements DriverCommand {

    private int posX, posY;

    public SetPositionCommand(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void execute(Job2dDriver driver) {
        driver.setPosition(posX, posY);
    }

    @Override
    public SetPositionCommand clone() {
        SetPositionCommand setPositionCommand;
        try {
            setPositionCommand = (SetPositionCommand) super.clone();
        } catch (CloneNotSupportedException e) {
            setPositionCommand = new SetPositionCommand(this.posX, this.posY);
        }
        return setPositionCommand;
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visitSetPositionCommand(this);
    }

    @Override
    public String toString() {
        return "SetPositionCommand{posX=" + posX + ", posY=" + posY + "}";
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
