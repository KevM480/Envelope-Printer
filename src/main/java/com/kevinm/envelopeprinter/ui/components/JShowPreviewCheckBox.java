package com.kevinm.envelopeprinter.ui.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JSplitPane;

import com.kevinm.envelopeprinter.EnvelopePrinter;
import com.kevinm.envelopeprinter.components.ComponentMapUtils;

public class JShowPreviewCheckBox extends JCheckBox {
	public JShowPreviewCheckBox() {
		super("Show Preview", true);
		this.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				JSplitPane split = ComponentMapUtils.get(JSplitPane.class, "split_pane_vertical.split_pane_horizontal");
				if (JShowPreviewCheckBox.this.isSelected()) {
					split.getBottomComponent().setVisible(true);
					split.setDividerLocation(0.5);
					EnvelopePrinter.getRoot().revalidate();
				} else {
					split.getBottomComponent().setVisible(false);
					EnvelopePrinter.getRoot().revalidate();
				}
			}

		});
	}
}
