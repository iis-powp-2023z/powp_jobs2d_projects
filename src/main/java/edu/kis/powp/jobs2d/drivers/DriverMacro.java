package edu.kis.powp.jobs2d.drivers;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import java.util.ArrayList;

public class DriverMacro implements Job2dDriver {
    private ArrayList<DriverCommand> macro = new ArrayList<>();

    public ArrayList<DriverCommand> getMacro() {
        return this.macro;
    }
    public void clear(){
        if (!this.macro.isEmpty()) {
            this.macro.clear();
        }
    }
    @Override
    public void setPosition(int x, int y) {
        this.macro.add(new OperateToCommand(x, y));
    }
    @Override
    public void operateTo(int x, int y) {
        this.macro.add(new SetPositionCommand(x, y));
    }
}
