package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.canvasDrawArea.CanvasDimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCanvasB5SizeOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DrawerFeature.getCanvasDrawAreaPanelController().setPredefinedSize(CanvasDimension.CanvasSizes.B5);
    }
}
