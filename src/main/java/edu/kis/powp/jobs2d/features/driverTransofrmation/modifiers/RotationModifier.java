package edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;

public class RotationModifier implements TransformationModifier {
    private final double rotationAngle;

    public RotationModifier(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    @Override
    public int[] modify(int x, int y) {
        double cos = Math.cos(Math.toRadians(rotationAngle));
        double sin = Math.sin(Math.toRadians(rotationAngle));

        int rotatedX = (int) (x * cos - y * sin);
        int rotatedY = (int) (x * sin + y * cos);
        return new int[]{rotatedX, rotatedY};
    }
}
