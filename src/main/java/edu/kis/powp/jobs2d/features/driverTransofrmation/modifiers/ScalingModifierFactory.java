package edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;

public class ScalingModifierFactory {
    public static TransformationModifier createHorizontalFlipModifier() {
        return new ScalingModifier(-1.0, 1.0);
    }

    public static TransformationModifier createVerticalFlipModifier() {
        return new ScalingModifier(1.0, -1.0);
    }
}