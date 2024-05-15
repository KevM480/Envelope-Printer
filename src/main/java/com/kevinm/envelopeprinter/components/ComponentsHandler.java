package com.kevinm.envelopeprinter.components;

import java.awt.Component;

import javax.swing.JComponent;

public class ComponentsHandler {

	public ComponentsHandler(JComponent root) {
		ComponentHierarchy.TOP.setComponent(root);
		this.createMap(root);
	}

	private void createMap(JComponent root) {
		childrenComponents(ComponentHierarchy.TOP);
	}

	private void childrenComponents(ConnectedComponent connCompon) {
		if (connCompon.getComponent().getComponentCount() > 0) {
			for (Component c : connCompon.getComponent().getComponents()) {
				if (c instanceof JComponent cont && cont.getName() != null) {
					final ConnectedComponent child = new ConnectedComponent(cont);
					connCompon.addChild(child);
					childrenComponents(child);
					ComponentHierarchy.convertion.put(cont, child);
				}
			}
		}
	}

}
