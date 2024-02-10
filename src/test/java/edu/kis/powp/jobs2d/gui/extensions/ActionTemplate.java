package edu.kis.powp.jobs2d.gui.extensions;

import java.awt.event.ActionEvent;

public abstract class ActionTemplate {
    protected boolean enabled;

    public ActionTemplate() {
        this.enabled = false;
    }

    // Template method
    public final void performAction(ActionEvent event) {
        toggleEnabled();
        if (enabled) {
            onEnable(event);
        } else {
            onDisable(event);
        }
    }

    private void toggleEnabled() {
        enabled = !enabled;
    }

    protected abstract void onEnable(ActionEvent event);
    protected abstract void onDisable(ActionEvent event);
}

