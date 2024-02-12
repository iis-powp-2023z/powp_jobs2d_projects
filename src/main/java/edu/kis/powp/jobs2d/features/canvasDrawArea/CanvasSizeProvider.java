package edu.kis.powp.jobs2d.features.canvasDrawArea;

import java.awt.*;

public interface CanvasSizeProvider {
    Dimension getCurrentDimension();
    void setCurrentDimension(Dimension dimension);
}
