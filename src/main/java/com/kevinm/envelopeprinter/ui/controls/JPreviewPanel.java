package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JPreviewPanel extends JPanel {
	public JPreviewPanel() {
		this.setPreferredSize(new Dimension(0, 500));
		this.setBorder(BorderFactory.createLineBorder(getForeground()));
	}
}
