package com.kevinm.envelopeprinter.components;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;

public class ComponentMap {

	private final JComponent parent;
	private static Container root;

	public ComponentMap(JComponent parent) {
		this.parent = parent;
	}

	public ComponentMap(String parent) {
		this.parent = get(JComponent.class, parent);
	}

	/**
	 * Do not use this method. It is only to be used to set the root component at
	 * start.
	 */
	public static void setRoot(Container root) {
		ComponentMap.root = root;
	}

	public static ComponentMap getRoot() {
		return new ComponentMap((JComponent) root);
	}

	public <T extends JComponent> T get(Class<T> type, String path) {
		String[] names = new String[] { path };
		if (path.contains("."))
			names = path.split(".");
		JComponent parent = this.parent;
		for (int i = 0; i < names.length; i++) {
			parent = get(parent, names[i]);
			if (parent == null)
				return null;
		}

		return type.cast(parent);
	}

	private JComponent get(JComponent parent, String name) {
		for (Component comp : parent.getComponents()) {
			if (comp.getName().equals(name))
				return (JComponent) comp;
		}
		return null;
	}

}
