package com.kevinm.envelopeprinter.components;

import java.awt.Component;

import javax.swing.JComponent;

public class ComponentsHandler {
	private ConnectedComponent topComponent;

	public ComponentsHandler(JComponent root) {
		this.createMap(root);
	}

	public ConnectedComponent getTop() {
		return topComponent;
	}

	private void createMap(JComponent root) {
		this.topComponent = new ConnectedComponent(root);
		childrenComponents(this.topComponent);
	}

	private void childrenComponents(ConnectedComponent connCompon) {
		if (connCompon.getComponent().getComponentCount() > 0) {
			for (Component c : connCompon.getComponent().getComponents()) {
				if (c instanceof JComponent cont && c.getName() != null) {
					ConnectedComponent child = new ConnectedComponent(cont);
					connCompon.addChild(child);
					childrenComponents(child);
				}
			}
		}
	}

}
