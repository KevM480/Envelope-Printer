package com.kevinm.envelopeprinter.components;

import java.util.HashMap;

import javax.swing.JComponent;

public class ConnectedComponent {
	private HashMap<String, ConnectedComponent> children = new HashMap<>();
	private JComponent component;

	public ConnectedComponent(JComponent component) {
		this.component = component;
	}

	protected void addChild(ConnectedComponent component) {
		children.put(component.getName(), component);
	}

	protected void setComponent(JComponent component) {
		this.component = component;
	}

	public String getName() {
		return component.getName();
	}

	public JComponent getComponent() {
		return component;
	}

	public ConnectedComponent get(String name) throws Exception {
		if (!children.containsKey(name))
			throw new Exception("Child " + name + " was not found inside component " + component);
		return children.get(name);
	}
}
