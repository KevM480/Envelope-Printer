package com.kevinm.envelopeprinter.ui.controls;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;

import com.kevinm.envelopeprinter.window.EnvelopePrinterWindow;

public class JShowPreviewCheckBox extends JCheckBox {
	public JShowPreviewCheckBox() {
		super("Show Preview", true);
		this.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (SwingUtilities.getWindowAncestor(JShowPreviewCheckBox.this) instanceof EnvelopePrinterWindow window) {
					/*
					 * JSplitPane split = (JSplitPane) window.getComponentNamed("split_pane"); if
					 * (JShowPreviewCheckBox.this.isSelected()) {
					 * split.getBottomComponent().setVisible(true); split.setDividerLocation(0.5);
					 * window.revalidate(); } else { split.getBottomComponent().setVisible(false);
					 * window.revalidate(); }
					 */
					/*
					 * Component addressPane = window.getComponentNamed("address_pane"); Component
					 * previewPanel = window.getComponentNamed("preview_panel"); Container
					 * contentPane = window.getContentPane(); SpringLayout layout = (SpringLayout)
					 * contentPane.getLayout(); if (JShowPreviewCheckBox.this.isSelected()) {
					 * layout.putConstraint(SpringLayout.SOUTH, addressPane, -3, SpringLayout.NORTH,
					 * previewPanel); previewPanel.setVisible(true); } else {
					 * layout.putConstraint(SpringLayout.SOUTH, addressPane, -3, SpringLayout.SOUTH,
					 * contentPane); previewPanel.setVisible(false); } previewPanel.revalidate();
					 * addressPane.revalidate(); window.revalidate();
					 */
				}

			}
		});
	}
}
