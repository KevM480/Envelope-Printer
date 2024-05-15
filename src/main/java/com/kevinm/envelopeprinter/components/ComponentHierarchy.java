package com.kevinm.envelopeprinter.components;

import java.util.HashMap;

import javax.swing.JComponent;

public class ComponentHierarchy {
	protected static final ConnectedComponent TOP = new ConnectedComponent(null);
	protected static HashMap<JComponent, ConnectedComponent> convertion = new HashMap<>();

	public static ConnectedComponent get() {
		return TOP;
	}

	public static ConnectedComponent get(JComponent component) throws Exception {
		if (convertion.containsKey(component))
			throw new Exception("Convertion map does not contain a convertion for " + component);
		return convertion.get(component);
	}
}
