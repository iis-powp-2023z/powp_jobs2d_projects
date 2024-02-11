package edu.kis.powp.jobs2d.features.canvasDrawArea;

import java.awt.*;

public class DefaultCanvasSizeProvider implements CanvasSizeProvider {
    private Dimension currentDimension = CanvasSizeFactory.getDefaultCanvasSize().getDimension();

    @Override
    public Dimension getCurrentDimension() {
        return currentDimension;
    }

    @Override
    public void setCurrentDimension(Dimension dimension) {
        this.currentDimension = dimension;
    }
}
