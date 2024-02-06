package edu.kis.powp.jobs2d.gui.extensions;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierListener extends ActionTemplate implements ActionListener
{
    private TransformationModifier modifier;
    private TransformingDriver lineTrans, specialLineTrans;

    public ModifierListener(TransformationModifier modifier, TransformingDriver lineTrans, TransformingDriver specialLineTrans)
    {
        enabled = false;
        this.modifier = modifier;
        this.lineTrans = lineTrans;
        this.specialLineTrans = specialLineTrans;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        performAction(e);
    }

    @Override
    protected void onEnable(ActionEvent event) {
        this.lineTrans.addModifier(modifier);
        this.specialLineTrans.addModifier(modifier);
    }

    @Override
    protected void onDisable(ActionEvent event) {
        this.lineTrans.removeModifier(modifier);
        this.specialLineTrans.removeModifier(modifier);
    }
}