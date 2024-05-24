package com.kevinm.envelopeprinter.components;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;

public class ComponentFinderUtils {

	private static Container root;

	/**
	 * Do not use this method. It is only to be used to set the root component at
	 * start.
	 */
	public static void setRoot(Container root) {
		ComponentFinderUtils.root = root;
	}

	public static <T extends JComponent> T get(Class<T> type, String path) {
		return get((JComponent) root, type, path);
	}

	public static <T extends JComponent> T get(JComponent parent, Class<T> type, String path) {
		String[] names = new String[] { path };
		if (path.contains("."))
			names = path.split("\\.");
		for (int i = 0; i < names.length; i++) {
			parent = get(parent, names[i]);
			if (parent == null)
				return null;
		}
		return type.cast(parent);
	}

	private static JComponent get(JComponent parent, String name) {
		for (Component comp : parent.getComponents()) {
			if (comp.getName() != null && comp.getName().equals(name))
				return (JComponent) comp;
		}
		return null;
	}

}
