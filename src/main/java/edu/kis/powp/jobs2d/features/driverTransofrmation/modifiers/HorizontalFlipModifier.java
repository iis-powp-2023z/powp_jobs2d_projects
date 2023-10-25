package edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;

public class HorizontalFlipModifier implements TransformationModifier {
    public HorizontalFlipModifier() {
    }

    @Override
    public int[] modify(int x, int y) {
        return new int[]{(x * -1), y};
    }
}
