package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import com.kevinm.envelopeprinter.window.EnvlopePrinterWindow;

public class JShowPreviewCheckBox extends JCheckBox {
	public JShowPreviewCheckBox() {
		super("Show Preview", true);
		this.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (SwingUtilities
						.getWindowAncestor(JShowPreviewCheckBox.this) instanceof EnvlopePrinterWindow window) {
					Component addressPane = window.getComponentNamed("address_pane");
					Component previewPanel = window.getComponentNamed("preview_panel");
					Container contentPane = window.getContentPane();
					SpringLayout layout = (SpringLayout) contentPane.getLayout();
					if (e.getStateChange() == 2) {
						layout.putConstraint(SpringLayout.SOUTH, addressPane, -3, SpringLayout.SOUTH, contentPane);
						previewPanel.setVisible(false);
					} else if (e.getStateChange() == 1) {
						layout.putConstraint(SpringLayout.SOUTH, addressPane, -3, SpringLayout.NORTH, previewPanel);
						previewPanel.setVisible(true);
					}
					previewPanel.revalidate();
					addressPane.revalidate();
					window.revalidate();
				}

			}
		});
	}
}
