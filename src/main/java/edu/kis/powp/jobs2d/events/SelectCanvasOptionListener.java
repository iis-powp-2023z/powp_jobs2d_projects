package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.canvasDrawArea.CanvasSize;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class SelectCanvasOptionListener implements ActionListener {
    private final Optional<CanvasSize> canvasSize;
    private final boolean openCustomSizeWindow;

    public SelectCanvasOptionListener() {
        this.canvasSize = Optional.empty();
        this.openCustomSizeWindow = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (openCustomSizeWindow) {
            DrawerFeature.getCanvasDrawAreaPanelController().openCustomSizeWindow();
        } else {
            canvasSize.ifPresent(size ->
                    DrawerFeature.getCanvasDrawAreaPanelController().setPredefinedSize(size));
        }
    }
}
