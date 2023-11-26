package edu.kis.powp.jobs2d.drivers;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import java.util.ArrayList;
import java.util.List;

public class DriverMacro implements Job2dDriver {
    private List<DriverCommand> macro = new ArrayList<>();

    public List<DriverCommand> getMacro() {
        List<DriverCommand> macro = new ArrayList<>();
        for (DriverCommand command: this.macro) {
            macro.add(command.clone());
        }
        return macro;
    }
    public void clear(){
        this.macro.clear();
    }
    @Override
    public void setPosition(int x, int y) {
        this.macro.add(new SetPositionCommand(x, y));
    }
    @Override
    public void operateTo(int x, int y) {
        this.macro.add(new OperateToCommand(x, y));
    }
}
