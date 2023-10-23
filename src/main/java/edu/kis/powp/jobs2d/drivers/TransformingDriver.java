package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * Decorator for Job2dDriver that applies transformations before forwarding the instructions.
 */
public class TransformingDriver implements Job2dDriver {
    private Job2dDriver driver;
    private double scaleX;
    private double scaleY;
    private double rotationAngle;
    private boolean isFlipped;

    public TransformingDriver(Job2dDriver driver) {
        this.driver = driver;
        scaleX = 1.0;
        scaleY = 1.0;
        rotationAngle = 0.0;
        isFlipped = false;
    }

    @Override
    public void setPosition(int x, int y) {
        int[] transformed = transformCoordinates(x, y);
        driver.setPosition(transformed[0], transformed[1]);
    }

    @Override
    public void operateTo(int x, int y) {
        int[] transformed = transformCoordinates(x, y);
        driver.operateTo(transformed[0], transformed[1]);
    }

    public void scale(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public void rotate(double angle) {
        this.rotationAngle = angle;
    }

    public void flip() {
        isFlipped = !isFlipped;
    }

    private int[] transformCoordinates(int x, int y) {
        int transformedX = (int) (x * scaleX);
        int transformedY = (int) (y * scaleY);
        double cos = Math.cos(Math.toRadians(rotationAngle));
        double sin = Math.sin(Math.toRadians(rotationAngle));
        int rotatedX = (int) (transformedX * cos - transformedY * sin);
        int rotatedY = (int) (transformedX * sin + transformedY * cos);
        if (isFlipped) {
            rotatedX = -rotatedX;
        }
        return new int[]{rotatedX, rotatedY};
    }
}
