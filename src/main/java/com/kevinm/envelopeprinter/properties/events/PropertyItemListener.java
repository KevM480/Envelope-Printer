package com.kevinm.envelopeprinter.properties.events;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.kevinm.envelopeprinter.EnvelopePrinter;

public interface PropertyItemListener extends ItemListener {

	public void setProperties(ItemEvent e);

	@Override
	public default void itemStateChanged(ItemEvent e) {
		setProperties(e);
		EnvelopePrinter.getPropertiesHandler().setProperties();
		EnvelopePrinter.getPropertiesHandler().writeProperties();
	}
}
