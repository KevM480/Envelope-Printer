package com.kevinm.envelopeprinter.properties.events;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.kevinm.envelopeprinter.EnvelopePrinter;

public interface PropertyItemListener extends ItemListener {

	public void setProperties(ItemEvent e);

	@Override
	public default void itemStateChanged(ItemEvent e) {
		if (!EnvelopePrinter.getIsBuilding() && EnvelopePrinter.getRoot() != null && e.getStateChange() == ItemEvent.SELECTED) {
			setProperties(e);
			EnvelopePrinter.getPropertiesHandler().setProperties();
			EnvelopePrinter.getPropertiesHandler().writeProperties();
		}
	}

	public default void repaintComponet(Component componet) {
		componet.revalidate();
		componet.repaint();
	}
}
