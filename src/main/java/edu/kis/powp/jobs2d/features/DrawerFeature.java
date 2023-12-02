package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.events.SelectClearPanelOptionListener;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.jobs2d.features.canvasDrawArea.*;

public class DrawerFeature {

    private static DrawPanelController drawerController;
    private static CanvasDrawAreaPanelController canvasDrawAreaPanelController;

    /**
     * Setup Drawer Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupDrawerPlugin(Application application) {
        SelectClearPanelOptionListener selectClearPanelOptionListener = new SelectClearPanelOptionListener();

        drawerController = new DrawPanelController();
        application.addComponentMenu(DrawPanelController.class, "Draw Panel", 0);
        application.addComponentMenuElement(DrawPanelController.class, "Clear Panel", selectClearPanelOptionListener);

        canvasDrawAreaPanelController = new CanvasDrawAreaPanelController(application);
    }

    /**
     * Get controller of application drawing panel.
     *
     * @return drawPanelController.
     */
    public static DrawPanelController getDrawerController() {
        return drawerController;
    }

    public static CanvasDrawAreaPanelController getCanvasDrawAreaPanelController() {
        return canvasDrawAreaPanelController;
    }
}
