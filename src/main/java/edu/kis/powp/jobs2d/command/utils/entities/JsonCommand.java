package edu.kis.powp.jobs2d.command.utils.entities;

public class JsonCommand {
    public final static String OPERATE_TO_COMMAND_TYPE = "operateTo";
    public final static String SET_POSITION_COMMAND_TYPE = "setPosition";

    private String type;
    private int x;
    private int y;

    public JsonCommand(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "JsonCommand{" +
                "type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
